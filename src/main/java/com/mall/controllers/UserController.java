package com.mall.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mall.dtos.AddressDTO;
import com.mall.dtos.UserDTO;
import com.mall.exceptions.NotFoundExcption;
import com.mall.services.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public UserDTO getUse(@PathVariable long id) {
		return userService.getUser(id);
	}
	@RequestMapping(path = "/{id}/addresses", method = RequestMethod.GET)
	public List<AddressDTO> getUseAddresses(@PathVariable Long id) {
		return userService.getUserAddresses(id);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<UserDTO> getUsers() {
		return userService.getUsers();
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	@ResponseStatus(code=HttpStatus.CREATED)
	public UserDTO createUser(@RequestBody UserDTO user) {

		for (UserDTO userDTO : userService.getUsers()) {
			if(userDTO.getId() ==user.getId()) {
				throw new NotFoundExcption();
			}
		}
		return userService.createUser(user);

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT)
	public UserDTO updateUser(@RequestBody UserDTO user) {
		return userService.updateUser(user);

	}

}
