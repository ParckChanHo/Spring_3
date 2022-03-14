package com.spring.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.common.ScriptAlertUtils;
import com.spring.dao.BoardDAO;
import com.spring.dto.BoardDTO;

@Controller
public class BoardController {
	// 게시판으로 이동하기
	@RequestMapping("/board.do")
	public String boardView(@RequestParam(value="pageNumber",required=false) String pageNumber,BoardDAO dao,Model model) {
		int int_pageNumber;
		if(pageNumber == null) {
			pageNumber="1";
			int_pageNumber = Integer.parseInt(pageNumber);
		}
		else { // pageNumber != null
			int_pageNumber = Integer.parseInt(pageNumber);
		}
		
		ArrayList<BoardDTO> list = dao.getList(int_pageNumber);
		model.addAttribute("list",list);
		model.addAttribute("pageNumber",int_pageNumber);
		model.addAttribute("nextPage",dao.nextPage(Integer.parseInt(pageNumber)+1));
		
		return "board.jsp";
	}
	
	// 글쓰기
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String writeView() {
		System.out.println("글쓰기 화면으로 이동...");
		return "write.jsp";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write(BoardDAO dao,BoardDTO dto,HttpSession session,HttpServletResponse response) throws IOException {
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(userID == null) { // 로그인이 되어있지 않음.
			ScriptAlertUtils.DoLogin(response);
		}
		else { // 로그인이 되어있다면
			if(dto.getBbsTitle() == null || dto.getBbsTitle().equals("") ||
					dto.getBbsContent() == null || dto.getBbsContent().equals("")) {
				ScriptAlertUtils.EmptyJoin(response);
			}
			else { // 모두 다 잘 기입이 되어있다면...
				int result = dao.write(dto);
				
				if(result == -1)
					ScriptAlertUtils.FailWrite(response);
				else
					return "board.do";
			}	
		}	
		return null;
	}
	
	// 상세보기
	@RequestMapping("/view.do")
	public String boardOneView(@RequestParam(value="bbsID") String bbsID,BoardDAO dao,BoardDTO dto,Model model) {
		int int_bbsID = Integer.parseInt(bbsID);
		dto.setBbsID(int_bbsID);
		model.addAttribute("board",dao.getBoard(dto));
		
		return "view.jsp";
	}
	
	// 수정하기
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateView(Model model,@RequestParam(value="bbsID") String bbsID,BoardDAO dao,BoardDTO dto,HttpSession session,HttpServletResponse response) throws IOException {
		System.out.println("수정하기 화면으로 이동...");
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(userID == null) { // 로그인이 되어있지 않음.
			ScriptAlertUtils.DoLogin(response);
		}
		
		dto.setBbsID(Integer.parseInt(bbsID));
		BoardDTO bbs = dao.getBoard(dto); //클릭한 게시물 제목에 해당하는 게시물 객체를 반환한다.!!
		if(!userID.equals(bbs.getUserID())){
			ScriptAlertUtils.NoAuthority(response);
		}
		
		model.addAttribute("board",bbs);
		
		return "update.jsp";
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String update(BoardDAO dao,BoardDTO dto,HttpSession session,HttpServletResponse response) throws IOException {
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(userID == null) { // 로그인이 되어있지 않음.
			ScriptAlertUtils.DoLogin(response);
		}
		
		BoardDTO bbs = dao.getBoard(dto); //클릭한 게시물 제목에 해당하는 게시물 객체를 반환한다.!!
		if(!userID.equals(bbs.getUserID())){ // 로그인한 사람이 쓴 게시물이라면 true를 반환한다!!!
			ScriptAlertUtils.NoAuthority(response);
		}
		else { // 수정
			int result = dao.update(dto);
			
			if(result == -1){
				ScriptAlertUtils.NoUpdate(response);
			}
			else{
				return "board.do";
			}
		}
		
		return null;
	}
	
	// 삭제하기
	@RequestMapping("/delete.do")
	public String boardDelete(@RequestParam(value="bbsID") String bbsID,BoardDAO dao,BoardDTO dto,HttpSession session,HttpServletResponse response) throws IOException {
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(userID == null) { // 로그인이 되어있지 않음.
			ScriptAlertUtils.DoLogin(response);
		}
		
		dto.setBbsID(Integer.parseInt(bbsID));
		BoardDTO bbs = dao.getBoard(dto); //클릭한 게시물 제목에 해당하는 게시물 객체를 반환한다.!!
		if(!userID.equals(bbs.getUserID())){
			ScriptAlertUtils.NoAuthority(response);
		}
		else { // 삭제
			int result = dao.delete(dto);
			
			if(result == -1){
				ScriptAlertUtils.NoDelete(response);
			}
			else{
				return "board.do";
			}
		}
		
		return null;
	}
	
}