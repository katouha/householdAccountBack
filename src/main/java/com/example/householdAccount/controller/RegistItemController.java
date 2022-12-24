package com.example.householdAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdAccount.requestDto.user.SetIncomeItemReqDto;
import com.example.householdAccount.requestDto.user.SetLocationItemReqDto;
import com.example.householdAccount.requestDto.user.SetUseItemReqDto;
import com.example.householdAccount.responseDto.user.CommonResDto;
import com.example.householdAccount.service.registItem.SetIncomeItemService;
import com.example.householdAccount.service.registItem.SetLocationItemService;
import com.example.householdAccount.service.registItem.SetUseItemService;

@RestController
@CrossOrigin
public class RegistItemController {
	
	@Autowired
	SetIncomeItemService setIncomeItemService;
	
	@Autowired
	SetUseItemService setUseItemService;
	
	@Autowired
	SetLocationItemService setLocationItemService;
	
	@RequestMapping(value = "householdAccount/user/setIncomeItem",method = RequestMethod.POST)
    public CommonResDto registIncomeItem(@RequestBody SetIncomeItemReqDto reqDto) {
		return setIncomeItemService.setIncomeItem(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/setUseItem",method = RequestMethod.POST)
    public CommonResDto registUseItem(@RequestBody SetUseItemReqDto reqDto) {
		return setUseItemService.setUseItem(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/setLocationItem",method = RequestMethod.POST)
    public CommonResDto registLocationItem(@RequestBody SetLocationItemReqDto reqDto) {
		return setLocationItemService.setLocationItem(reqDto);
	}

}
