package com.javaex.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
//회원정보 1명 가져오기(session저장용, 로그인용)
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.getUser()]");
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
//회원정보 저장 
	public int insert(UserVo userVo) {
		System.out.println("[UserDao.insert()");
		int count = sqlSession.insert("user.insert", userVo);
		System.out.println("["+count+"건이 등록되었습니다(UserDao)");
		return count;
	}

//회원정보 가져오기(회원정보 수정폼)
	public UserVo getUser(int no) {
		System.out.println("[UserDao.insert()");
		
		return sqlSession.selectOne("user.getUser", no);
	}
	
//회원정보 수정 
	public int Update(UserVo userVo) {
		System.out.println("[UserDao.update()]");
		int count = sqlSession.update("user.update", userVo);
		System.out.println("["+count+"건이 수정되었습니다(UserDao)");
		return count;
	}





}
