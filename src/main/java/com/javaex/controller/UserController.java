package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
//@RequestMapping(value="/user")
public class UserController {
	
		@Autowired
		private UserService userService;
		
		//로그인폼
		@RequestMapping(value="/user/loginForm", method= {RequestMethod.GET,RequestMethod.POST}) // 생략하여 "/loginForm" 만 써도 됨 
		public String loginForm() {
			System.out.println("[UserController.loginForm()]");
			
			return "user/loginForm";
		}
		
		
		//로그인
		@RequestMapping(value="/user/login", method= {RequestMethod.GET,RequestMethod.POST})
		public String  login(@ModelAttribute UserVo userVo, HttpSession session) {
			System.out.println("[UserController.login()]");
			
			// 다오에서 리스트를 가져온다
			UserVo authUser = userService.login(userVo);
			System.out.println(authUser);
	
			if(authUser != null) { //로그인성공
				//세션에 저장
				System.out.println("로그인성공");
				session.setAttribute("authUser", authUser);
	
				//리다렉트 메인
				return "redirect:/";
			}else {  //로그인실패
				System.out.println("로그인실패");
				//리다렉트 로그인폼
				return "redirect:/user/loginForm?result=fail";
			}
		}
		
		//로그아웃
		@RequestMapping(value="/user/logout", method={RequestMethod.GET, RequestMethod.POST})
		public String logout(HttpSession session) {
			System.out.println("[UserController.logout()]");
			
			//세션의 정보 삭제
			session.removeAttribute("authUser");
			session.invalidate();
			
			return "redirect:/";
		}
		
		
		@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
		public String joinForm() {
			System.out.println("[UserComtroller.joinForm()]");

			return "/user/joinForm";
		}

		@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
		public String join(@ModelAttribute UserVo userVo) {
			System.out.println("[UserComtroller.join()]");
			userService.join(userVo);
			return "joinOk";
		}

		@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
		public String modifyForm(HttpSession session, Model model) {
			System.out.println("[UserComtroller.modifyForm()]");
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			UserVo userVo = userService.modifyForm(authUser.getNo());
			model.addAttribute("userVo", userVo);
			return "/user/modifyForm";
		}

		@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
		public String modify(@ModelAttribute UserVo userVo) {
			System.out.println("[UserComtroller.modify()]");
			userService.modify(userVo);
			return "redirect:/";
		}
			
			
		
	
			

}
