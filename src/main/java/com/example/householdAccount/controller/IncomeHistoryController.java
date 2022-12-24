package com.example.householdAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdAccount.requestDto.user.SetIncomeHistoryReqDto;
import com.example.householdAccount.responseDto.user.CommonResDto;
import com.example.householdAccount.service.incomeHistory.SetIncomeHistoryService;

@RestController
@CrossOrigin
public class IncomeHistoryController {
	
	@Autowired
	SetIncomeHistoryService setIncomeHistoryService;
	
	@RequestMapping(value = "householdAccount/user/setIncomeHistory",method = RequestMethod.POST)
    public CommonResDto registIncomeHistory(@RequestBody SetIncomeHistoryReqDto reqDto) {
		return setIncomeHistoryService.setIncomeHistory(reqDto);
	}

}
