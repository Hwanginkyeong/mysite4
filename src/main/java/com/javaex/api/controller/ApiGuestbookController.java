package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;

	
	@RequestMapping("/addList") //줄여쓴 것 -> 원래 어떤지 비교 확인 
	public String addList() {
		System.out.println("ApiGuestbookController/addList()");
		
		return "aGuestbook/addList";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController/list()");
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	@RequestMapping("/write")
	public String write() {
		System.out.println("ApiGuestbookController/write()");
		
		return"";
		
	}
	
}
