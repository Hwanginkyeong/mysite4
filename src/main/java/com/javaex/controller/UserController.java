package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
//@RequestMapping(value="/user")
public class UserController {
	
		@Autowired
		private UserDao userDao;
		
		//로그인폼
		@RequestMapping(value="/user/loginForm", method= {RequestMethod.GET,RequestMethod.POST}) // 생략하여 "/loginForm" 만 써도 됨 
		public String loginForm() {
			System.out.println("[UserController.loginForm()]");
			
			return"loginForm";
		}
		
		
		//로그인
		@RequestMapping(value="/user/login", method= {RequestMethod.GET,RequestMethod.POST})
		public String login(Model model) {
			System.out.println("[UserController.login()]");
			
			// 다오에서 리스트를 가져온다
			List<UserVo> userList = userDao.getUserList();
			System.out.println(userList.toString());
	
			//컨트롤러-->DS데이터를 보낸다(model)
			model.addAttribute("userList", userList);
	
			//jsp정보를 리턴한다(view)
			return "loginForm";
		}
		
		// 회원_로그인성공
			@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
			public String login(@ModelAttribute UserVo userVo) {
				System.out.println("UserController > login()");
	
				UserVo authUser = userDao.getUser(userVo);
				System.out.println(authUser);
	
				return "";
			}
			
			
		// 회원_회원가입폼
		@RequestMapping(value = "/user/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
		public String joinForm() {
			System.out.println("UserController > joinForm()");
	
			return "joinForm";
		}
	
		// 회원_회원가입 후 성공
		@RequestMapping(value = "join", method = { RequestMethod.GET, RequestMethod.POST })
		public String join() {
			System.out.println("UserController > join()");
	
			// 다오에서 리스트를 가져온다
			List<UserVo> userList = userDao.getUserList();
			System.out.println(userList.toString());
	
			Model model = null;
			// 컨트롤러-->DS데이터를 보낸다(model)
			model.addAttribute("userList", userList);
	
			// jsp정보를 리턴한다(view)
			return "joinOkForm";
		}
	
			

}
