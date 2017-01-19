package com.mall.dtos;

import javax.validation.constraints.NotNull;

public class UserDTO {

	private long id;
	
	@NotNull
	private String nickName;
	
	private String email;
	
	private String password;
	
	private String gender;
	
	private AddressDTO defaultAddress;

	private long count;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public AddressDTO getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(AddressDTO defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}


}
