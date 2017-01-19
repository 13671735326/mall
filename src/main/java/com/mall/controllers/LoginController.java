package com.mall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mall.dtos.SessionInfoDTO;
import com.mall.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public SessionInfoDTO login(@RequestHeader("Authorization") String authValue){
		
		SessionInfoDTO sessionInfo =userService.validateCreditial(authValue);
		
		
		return sessionInfo;
		
		
	}
}
