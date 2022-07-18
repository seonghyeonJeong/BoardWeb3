package com.ezen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.domain.Board;
import com.ezen.domain.Member;
import com.ezen.domain.Role;
import com.ezen.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		
	    List<Board> boardList = boardService.getBoardList().getContent();
			
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	@PostMapping("/getBoardList")
	public String getBoardList1(Model model) {
		
	    List<Board> boardList = boardService.getBoardList().getContent();
			
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoardView(Board board, Model model) {
		
		Board boardDetail = boardService.getBoard(board);
		
		model.addAttribute("board", boardDetail);
		
		return "board/getBoard";
	}
	
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "board/insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@RequestParam("writer") String writer, Board board) {
		
		Member member1 = new Member();
		
		member1.setId("member");
		member1.setPassword("member789");
		member1.setName(writer);
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		
		board.setMember(member1);
		boardService.insertBoard(board);
		
		return "redirect:/board/getBoardList";
	}
	
	@PostMapping("/updateBoard")
	   public String updateBoard(Board board) {
	      
	      boardService.updateBoard(board);
	      
	      return "redirect:/board/getBoardList";
	   }
	
	
	 @GetMapping("/deleteBoard")
	   public String deleteBoard(Board board) {
	      
	      boardService.deleteBoard(board);
	      
	      return "redirect:/board/getBoardList";
	   }
	 
 
}





















