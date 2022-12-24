package com.example.householdAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdAccount.requestDto.user.RegistUserReqDto;
import com.example.householdAccount.requestDto.user.ReissuePasswordReqDto;
import com.example.householdAccount.requestDto.user.UpdateUserPasswordReqDto;
import com.example.householdAccount.requestDto.user.UpdateUserReqDto;
import com.example.householdAccount.responseDto.user.CommonResDto;
import com.example.householdAccount.service.user.RegistUserService;
import com.example.householdAccount.service.user.ReissuePasswordService;
import com.example.householdAccount.service.user.UpdateUserPasswordService;
import com.example.householdAccount.service.user.UpdateUserService;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	RegistUserService registUserService;
	
	@Autowired
	UpdateUserService updateUserService;
	
	@Autowired
	ReissuePasswordService reissuePasswordService;
	
	@Autowired
	UpdateUserPasswordService updateUserPasswordService;
	
	
	@RequestMapping(value = "householdAccount/user/registUser",method = RequestMethod.POST)
    public CommonResDto getRegistUser(@RequestBody RegistUserReqDto reqDto) {
		return registUserService.registUser(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/updateUser",method = RequestMethod.POST)
    public CommonResDto getUpdateUser(@RequestBody UpdateUserReqDto reqDto) {
		return updateUserService.updateUser(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/reissuePassword",method = RequestMethod.POST)
    public CommonResDto reissuePassword(@RequestBody ReissuePasswordReqDto reqDto) {
		return reissuePasswordService.reIssuePassword(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/updateUserPassword",method = RequestMethod.POST)
    public CommonResDto reissuePassword(@RequestBody UpdateUserPasswordReqDto reqDto) {
		return updateUserPasswordService.updateUserPassword(reqDto);
	}
	
}
