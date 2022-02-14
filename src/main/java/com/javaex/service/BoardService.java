package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {
		System.out.println("[BoardService.getList()]");
		return boardDao.getList();
	}

//글쓰기 
	public int addBoard(BoardVo boardVo) {
		System.out.println("boardService/addBoard");
		
		//페이징 데이터 추가 123개 
		for(int i=1; i<=123; i++) {
			
			boardVo.setTitle(i+"번째글 제목입니다.");
			boardVo.setContent(i + "번째 내용입니다.");
			boardVo.setHit(0);
			boardVo.setUserNo(3);
			
			boardDao.boardInsert(boardVo);
			
		}

		//return boardDao.boardInsert(boardVo);
		return 1;
		
	}
	
	
	
	public BoardVo getBoard(int num) {
		System.out.println("[BoardService.getBoard()]");
		boardDao.hitPlus(num);
		return boardDao.getBoard(num);
	}

	public void boardInsert(BoardVo boardVo) {
		System.out.println("[BoardService.boardInsert()]");
		boardDao.boardInsert(boardVo);
	}

	public void boardDelete(int num) {
		System.out.println("[BoardService.boardDelete()]");
		boardDao.boardDelete(num);
	}
	
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("[BoardService.boardUpdate()]");
		boardDao.boardUpdate(boardVo);
	}

}
