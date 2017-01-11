package com.mall.services;

import java.util.List;

import com.mall.dtos.AddressDTO;
import com.mall.dtos.UserDTO;

public interface UserService {

	public UserDTO createUser(UserDTO user);

	public void deleteUser(Long idUser);

	public UserDTO updateUser(UserDTO user);

	public List<UserDTO> getUsers();

	public UserDTO getUser(Long id);

	public List<AddressDTO> getUserAddresses(Long id);

}
