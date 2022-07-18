package com.ezen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezen.domain.Board;
import com.ezen.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

   @Autowired
   private BoardRepository boardRepo;
   
   @Override
   public void insertBoard(Board board) {
      boardRepo.save(board);
   }

   @Override
   public void updateBoard(Board board) {
	 //수정할 게시글을 우선 조회
	Board findBoard = boardRepo.findById(board.getSeq()).get();
	
	//board - 화면에서 입력된 데이터로 수정
	findBoard.setTitle(board.getTitle());
	findBoard.setContent(board.getContent());
	
	boardRepo.save(findBoard);
   }

   @Override
   public void deleteBoard(Board board) {
      boardRepo.deleteById(board.getSeq());
   }

   @Override
   public Board getBoard(Board board) {
      return boardRepo.findById(board.getSeq()).get();
   }

   @Override
   public Page<Board> getBoardList() {
      Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
      
      return boardRepo.getBoardList(page);
   }

}


































