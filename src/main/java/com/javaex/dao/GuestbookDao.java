package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		System.out.println("[GuestbookDao.getList()]");
		
		return sqlSession.selectList("guestbook.getList");
	}
	
	public int guestbookInsert(int no) {
		System.out.println("[GuestbookDao.guestbookInsert()");
		
		return sqlSession.selectOne("guestbook.selectByNo", no);
		
	}
	
//방명록 글1개 가져오기
		public GuestbookVo selectGuest(int no) {
			System.out.println("guestbookDao/selectGuest");
			return sqlSession.selectOne("guestbook.selectByNo", no);
		}
	
//방명록 글 저장(selectKey)   //리턴 성공한 갯수
		public int insertSelectKey(GuestbookVo guestbookVo){
			System.out.println("guestbookDao/insertSelectKey");
			
			return sqlSession.insert("guestbook.insertSelectKey", guestbookVo);	
		}
		
		

	public int guestbookDelete(int no, String password) {
		System.out.println("[GuestbookDao.guestbookDelete()");
		
		Map<String, Object> guestMap = new HashMap<String, Object>();
		
		guestMap.put("no", no);
		guestMap.put("password", password);

		int count = sqlSession.delete("guestbook.guestbookDelete", guestMap);
		return count;
	}
}
