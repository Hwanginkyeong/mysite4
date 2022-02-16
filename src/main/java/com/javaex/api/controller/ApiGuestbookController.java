package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	
	
	//요청 먼저 보내서 콘솔에서 데이터 받는 지 확인 (2022-01-28-(2) 강의 2:42:00 ~)
	@RequestMapping("/write")
	public String write(@ModelAttribute GuestbookVo guestbookVo) { //파라미터로 오니까 바뀐 것이 없음. DisaptcherServlet한테 파라미터에 있으니 내놔. 이왕이면 묶어주는 게 좋음. 
		System.out.println("ApiGuestbookController/write()");
		System.out.println(guestbookVo); //얘를 찍을 수 있으면 목표 성공 
		
		guestbookService.addGuestResultVo(guestbookVo);
		
		return"";
		
	}
	
}
