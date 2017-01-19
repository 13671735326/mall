package com.mall.repositories;

import java.util.List;

import com.mall.entities.AddressEntity;
import com.mall.entities.UserEntity;

public interface UserRepository {

	public UserEntity createUser(UserEntity user);

	public AddressEntity createAddress(AddressEntity addressEntity);

	public void deleteUser(Long id);

	public UserEntity updateUser(UserEntity user);

	public List<UserEntity> getUsers();

	public List<UserEntity> getPagedUsers(Integer currentPage, Integer pageSize);

	public UserEntity getUser(Long idUser);

	public List<AddressEntity> getUserAddresses(Long id);

	public UserEntity getUserByName(String userName);

}
