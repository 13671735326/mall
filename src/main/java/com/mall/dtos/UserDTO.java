package com.mall.dtos;

public class UserDTO {

	private long id;
	private String nickName;
	private String email;
	private String password;
	private String gender;
	private AddressDTO defaultAddress;

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

}
