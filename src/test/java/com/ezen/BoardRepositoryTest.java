package com.ezen;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ezen.domain.Board;
import com.ezen.domain.Member;
import com.ezen.domain.Role;
import com.ezen.persistence.BoardRepository;
import com.ezen.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testInsert() {
		Member member = new Member();
		
		member.setId("member");
		member.setPassword("member123");
		member.setName("성현");
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled(true);
		
		Member member2 = new Member();
		
		member2.setId("member2");
		member2.setPassword("member456");
		member2.setName("미지");
		member2.setRole(Role.ROLE_ADMIN);
		member.setEnabled(true);
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setMember(member);
			board.setTitle("성현의 게시물 " + i);
			board.setContent("성현 게시물 내용 테스트" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
		}
		memberRepo.save(member);
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setMember(member2);
			board.setTitle("미지 게시물 " + i);
			board.setContent("미지 게시물 내용 테스트" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
		}
		memberRepo.save(member2);
	}
	
	@Test
	@Ignore
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		//Optional<Board> optionalBoard = boardRepo.findById(1L);
		
		System.out.println("[" + board.getSeq() + "번 게시글 정보]");
		System.out.println("제목: " + board.getTitle());
		System.out.println("내용: " + board.getContent());
		System.out.println("작성자: " + board.getMember().getName());
		System.out.println("작성자 권한: " + board.getMember().getRole());
	}
	
	@Test
	@Ignore
	public void testGetBoardList() {
		Member member = memberRepo.findById("member2").get();
		
		System.out.printf("<<<%s의 게시글>>>", member.getName());
		System.out.println("---------------------------------");
		
		List<Board> boardList = member.getBoardList();
		for(Board board : boardList) {
			System.out.println(board.toString());
		}
		
		//for(Board board : memeber.getBoardList()) {
		//		System.out.println(board);
		//	}
		
	}
}

























