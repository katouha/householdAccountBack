package com.example.householdAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdAccount.requestDto.user.UserIdReqDto;
import com.example.householdAccount.responseDto.selectItem.GetIncomeItemResDto;
import com.example.householdAccount.responseDto.selectItem.GetLocationItemResDto;
import com.example.householdAccount.responseDto.selectItem.GetUseItemResDto;
import com.example.householdAccount.service.selectItem.GetIncomeItemService;
import com.example.householdAccount.service.selectItem.GetLocationItemService;
import com.example.householdAccount.service.selectItem.GetUseItemService;

@RestController
@CrossOrigin
public class SelectItemController {
	
	@Autowired
	GetIncomeItemService getIncomeItemService;
	
	@Autowired
	GetUseItemService getUseItemService;
	
	@Autowired
	GetLocationItemService getLocationItemService;
	
	@RequestMapping(value = "householdAccount/user/getIncomeItem",method = RequestMethod.POST)
    public GetIncomeItemResDto getIncomeItem(@RequestBody UserIdReqDto reqDto) {
		return getIncomeItemService.getIncomeItem(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/getUseItem",method = RequestMethod.POST)
    public GetUseItemResDto getUseItem(@RequestBody UserIdReqDto reqDto) {
		return getUseItemService.getUseItem(reqDto);
	}
	
	@RequestMapping(value = "householdAccount/user/getLocationItem",method = RequestMethod.POST)
    public GetLocationItemResDto getLocationItem(@RequestBody UserIdReqDto reqDto) {
		return getLocationItemService.getLocationItem(reqDto);
	}
	
	

}
