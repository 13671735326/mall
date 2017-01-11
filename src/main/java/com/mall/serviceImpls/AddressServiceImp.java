package com.mall.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dtos.AddressDTO;
import com.mall.entities.AddressEntity;
import com.mall.repositories.AddressRepository;
import com.mall.services.AddressService;

@Service
public class AddressServiceImp implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void addAddress(AddressDTO address) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddress(address.getAddress());
		addressEntity.setTelephone(address.getTelephone());
		addressRepository.addAddress(addressEntity);

	}

	@Override
	public void removeAddress(int idAddress) {
		addressRepository.removeAddress(idAddress);
	}

	@Override
	public void updateAddress(AddressDTO address) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddress(address.getAddress());
		addressEntity.setTelephone(address.getTelephone());		
		addressRepository.updateAddress(addressEntity);
	}

	@Override
	public List<AddressDTO> getAddresses() {
		List<AddressEntity> addresses = addressRepository.getAddresses();
		List<AddressDTO> dtos = new ArrayList<>();
		for (AddressEntity address : addresses) {
			AddressDTO dto = new AddressDTO();
			dto.setId(address.getId());
			dto.setAddress(address.getAddress());
			dto.setTelephone(address.getTelephone());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public AddressDTO getAddress(int idAddress) {
		AddressEntity address = addressRepository.getAddress(idAddress);
		AddressDTO dto = new AddressDTO();
		dto.setId(address.getId());
		dto.setAddress(address.getAddress());
		dto.setTelephone(address.getTelephone());
		return dto;
	}

}
