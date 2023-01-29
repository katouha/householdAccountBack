package com.example.householdAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdAccount.requestDto.user.GetUseHistoryReqDto;
import com.example.householdAccount.requestDto.user.SetUseHistoryReqDto;
import com.example.householdAccount.responseDto.useHistory.GetUseHistoryResDto;
import com.example.householdAccount.responseDto.user.CommonResDto;
import com.example.householdAccount.service.useHistory.GetUseHistoryService;
import com.example.householdAccount.service.useHistory.SetUseHistoryService;

@RestController
@CrossOrigin
public class UseHistoryController {
	
	@Autowired
	SetUseHistoryService setUseHistoryService;
	
	@Autowired
	GetUseHistoryService getUseHistoryService;
	
	@RequestMapping(value = "householdAccount/user/setUseHistory",method = RequestMethod.POST)
    public CommonResDto registUseHistory(@RequestBody SetUseHistoryReqDto reqDto) {
		return setUseHistoryService.setUseHistory(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/getUseHistory",method = RequestMethod.POST)
    public GetUseHistoryResDto searchUseHistory(@RequestBody GetUseHistoryReqDto reqDto) {
		return getUseHistoryService.getUseHistory(reqDto);
	}

}
