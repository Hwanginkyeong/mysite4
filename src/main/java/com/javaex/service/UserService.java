package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

//로그인 
	public UserVo login(UserVo userVo) {
		UserVo authUser = userDao.selectUser(userVo);
		return authUser;
	} 
//회원가입 	
	public void join(UserVo userVo) {
		userDao.insert(userVo);
	}
//회원정보 수정폼 
	public UserVo modifyForm(int no) {
		return userDao.getUser(no);
	}
//회원정보 수정 	
	public void modify(UserVo userVo) {
		userDao.Update(userVo);
	}

}
