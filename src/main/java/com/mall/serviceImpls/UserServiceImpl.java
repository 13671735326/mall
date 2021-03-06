package com.mall.serviceImpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.dtos.AddressDTO;
import com.mall.dtos.SessionInfoDTO;
import com.mall.dtos.UserDTO;
import com.mall.entities.AddressEntity;
import com.mall.entities.UserEntity;
import com.mall.exceptions.LoginFailesException;
import com.mall.exceptions.NotFoundExcption;
import com.mall.repositories.UserRepository;
import com.mall.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	//
	//
	// 默认状态下保留只有一份
	// @Scope("singleton")
	// 如果相互冲突"prototype"
	public static Map<String, Date> tokenMap = new ConcurrentHashMap<String, Date>();

	public static Map<String, Date> getTokenMap() {
		return tokenMap;
	}

	public static void setTokenMap(Map<String, Date> tokenMap) {
		UserServiceImpl.tokenMap = tokenMap;
	}

	@Autowired
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private AddressDTO convertToDTO(AddressEntity entity) {
		AddressDTO addressDTO = new AddressDTO();

		addressDTO.setId(entity.getId());
		addressDTO.setTelephone(entity.getTelephone());
		addressDTO.setAddress(entity.getAddress());
		addressDTO.setDefault(entity.isDefault());
		return addressDTO;
	}

	private UserDTO convertToDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();

		dto.setId(entity.getId());
		dto.setNickName(entity.getNickName());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setGender(entity.getGender());

		for (AddressEntity address : entity.getAddressEntityList()) {
			if (address.isDefault()) {
				dto.setDefaultAddress(convertToDTO(address));
			}
		}
		return dto;

	}

	private UserEntity convertToEntity(UserDTO dto) {
		UserEntity userEntity = new UserEntity();

		userEntity.setNickName(dto.getNickName());
		userEntity.setEmail(dto.getEmail());
		userEntity.setPassword(DigestUtils.sha1Hex(dto.getPassword()));
		userEntity.setGender(dto.getGender());
		convertToEntity(dto.getDefaultAddress(), userEntity, true);

		return userEntity;

	}

	private AddressEntity convertToEntity(AddressDTO dto, UserEntity user, boolean isDefault) {
		AddressEntity entity = new AddressEntity();

		entity.setUserEntity(user);
		entity.setAddress(dto.getAddress());
		entity.setTelephone(dto.getTelephone());
		entity.setDefault(isDefault);

		return entity;
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		UserEntity userEntity = convertToEntity(user);
		userEntity = userRepository.createUser(userEntity);

		if (user.getDefaultAddress() != null) {
			AddressEntity addressEntity = convertToEntity(user.getDefaultAddress(), userEntity, true);
			userRepository.createAddress(addressEntity);
			userEntity.getAddressEntityList().add(addressEntity);
		}
		return getUser(userEntity.getId());
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteUser(id);
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		UserEntity userEntity = convertToEntity(user);
		userEntity.setId(user.getId());

		userRepository.updateUser(userEntity);
		return getUser(userEntity.getId());
	}

	@Override
	public List<UserDTO> getUsers(Integer currentPage, Integer pageSize) {
		List<UserEntity> users;
		List<UserEntity> userss=userRepository.getUsers();
		if (currentPage == null && pageSize == null) {
			users = userRepository.getUsers();
		} else {
			if (currentPage != null || pageSize != null) {
				if (currentPage == null) {
					currentPage = 1;
				} else if (pageSize == null) {
					pageSize = 10;
				}
			}
			if (currentPage < 1 || pageSize < 1) {
				throw new NotFoundExcption("超出边界");
			} else {
				users = userRepository.getPagedUsers(currentPage, pageSize);
			}
		}
		
		
		List<UserDTO> dtos = new ArrayList<>();
		for (UserEntity user : users) {
			UserDTO dto = convertToDTO(user);
			dto.setCount(userss.size());
			dtos.add(dto);
		}
		return dtos;

	}

	@Override
	public UserDTO getUser(Long id) {
		UserEntity user = userRepository.getUser(id);

		if (user == null) {
			throw new NotFoundExcption("");
		}
		return convertToDTO(user);
	}

	@Override
	public List<AddressDTO> getUserAddresses(Long id) {
		List<AddressEntity> addresses = userRepository.getUserAddresses(id);
		List<AddressDTO> dtos = new ArrayList<>();
		for (AddressEntity address : addresses) {
			AddressDTO dto = convertToDTO(address);
			dtos.add(dto);
		}
		return dtos;
	}

	public SessionInfoDTO validateCreditial(String authValue) {

		String creditial = authValue.split(" ")[1];
		String userNamePassword = new String(Base64.decodeBase64(creditial));

		String[] userNamePasswordArray = userNamePassword.split(":");
		String userName = userNamePasswordArray[0];
		String password = userNamePasswordArray[1];

		UserEntity user = userRepository.getUserByName(userName);
		if (user != null) {
			String currentEncrptPassword = DigestUtils.sha1Hex(password);
			if (user.getPassword().equals(currentEncrptPassword)) {
				String token = UUID.randomUUID().toString();
				SessionInfoDTO sessionInfoDTO = new SessionInfoDTO();
				sessionInfoDTO.setToken(token);
				System.out.println(authValue);
				System.out.println(userNamePassword); 
				System.out.println(token);
				tokenMap.put(token, new Date());
				return sessionInfoDTO;
			} else {
				throw new LoginFailesException();
			}
		} else {
			throw new LoginFailesException();
		}
	}

	@Override
	public boolean validateToken(String token) {

		if (tokenMap.containsKey(token)) {
			return true;
		} else
			return false;
	}
}
