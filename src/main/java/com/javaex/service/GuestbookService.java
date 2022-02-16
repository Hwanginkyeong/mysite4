package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> getList() {
		System.out.println("GuestbookService.getList()");
		return guestbookDao.getList();
	}

	public void guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("guestbookService.guestbookInsert()");
		guestbookDao.guestbookInsert(guestbookVo);
		
	}

	public void guestbookDelete(int no, String password) {
		System.out.println("guestbookService.guestbookDelete()");
		guestbookDao.guestbookDelete(no, password);
	}
	
	
	//방명록 글 저장 저장글 리턴 
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		
		System.out.println("guestbookService.addGuestResultVo");
		guestbookDao.insertSelectKey(guestbookVo);
		
		
		return guestbookDao.guestbookInsert(guestbookVo.getNo());
	}
}