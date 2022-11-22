package com.example.householdAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdAccount.requestDto.user.RegistUserReqDto;
import com.example.householdAccount.requestDto.user.UpdateUserReqDto;
import com.example.householdAccount.responseDto.user.RegistUserResDto;
import com.example.householdAccount.responseDto.user.UpdateUserResDto;
import com.example.householdAccount.service.user.RegistUserService;
import com.example.householdAccount.service.user.UpdateUserService;

@RestController
@CrossOrigin
public class RegistUserController {
	@Autowired
	RegistUserService registUserService;
	
	@Autowired
	UpdateUserService updateUserService;
	
	@RequestMapping(value = "householdAccount/user/registUser",method = RequestMethod.POST)
    public RegistUserResDto getRegistUser(@RequestBody RegistUserReqDto reqDto) {
		return registUserService.registUser(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/updateUser",method = RequestMethod.POST)
    public UpdateUserResDto getUpdateUser(@RequestBody UpdateUserReqDto reqDto) {
		return updateUserService.updateUser(reqDto);
	}
}
