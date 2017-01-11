package com.mall.repositories;

import java.util.List;

import com.mall.entities.AddressEntity;

public interface AddressRepository {

	public void addAddress(AddressEntity address);

	public void removeAddress(long idAddress);

	public void updateAddress(AddressEntity address);

	public List<AddressEntity> getAddresses();

	public AddressEntity getAddress(long idAddress);
}
