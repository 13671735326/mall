package com.mall.services;

import java.util.List;

import com.mall.dtos.AddressDTO;
import com.mall.dtos.SessionInfoDTO;
import com.mall.dtos.UserDTO;

public interface UserService {

	public UserDTO createUser(UserDTO user);

	public void deleteUser(Long idUser);

	public UserDTO updateUser(UserDTO user);

	public UserDTO getUser(Long id);

	public List<AddressDTO> getUserAddresses(Long id);

	public List<UserDTO> getUsers(Integer currentPage,Integer pageSize);

	public SessionInfoDTO validateCreditial(String authValue);

	boolean validateToken(String token);

	public static void updateTokenTime() {
		// TODO Auto-generated method stub
		
	}


}
