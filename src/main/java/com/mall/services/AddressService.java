package com.mall.services;

import java.util.List;

import com.mall.dtos.AddressDTO;

public interface AddressService {

	public void addAddress(AddressDTO address);

	public void removeAddress(int idAddress);

	public void updateAddress(AddressDTO address);

	public List<AddressDTO> getAddresses();

	public AddressDTO getAddress(int idAddress);
}
