package com.example.householdAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdAccount.requestDto.user.LoginReqDto;
import com.example.householdAccount.responseDto.user.LoginResDto;
import com.example.householdAccount.service.user.LoginService;

@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "householdAccount/user/login",method = RequestMethod.POST)
    public LoginResDto getLogin(@RequestBody LoginReqDto reqDto) {
		return loginService.getLogin(reqDto);
	}
	
}
