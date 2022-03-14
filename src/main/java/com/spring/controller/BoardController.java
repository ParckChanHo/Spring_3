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
	// �Խ������� �̵��ϱ�
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
	
	// �۾���
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String writeView() {
		System.out.println("�۾��� ȭ������ �̵�...");
		return "write.jsp";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write(BoardDAO dao,BoardDTO dto,HttpSession session,HttpServletResponse response) throws IOException {
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(userID == null) { // �α����� �Ǿ����� ����.
			ScriptAlertUtils.DoLogin(response);
		}
		else { // �α����� �Ǿ��ִٸ�
			if(dto.getBbsTitle() == null || dto.getBbsTitle().equals("") ||
					dto.getBbsContent() == null || dto.getBbsContent().equals("")) {
				ScriptAlertUtils.EmptyJoin(response);
			}
			else { // ��� �� �� ������ �Ǿ��ִٸ�...
				int result = dao.write(dto);
				
				if(result == -1)
					ScriptAlertUtils.FailWrite(response);
				else
					return "board.do";
			}	
		}	
		return null;
	}
	
	// �󼼺���
	@RequestMapping("/view.do")
	public String boardOneView(@RequestParam(value="bbsID") String bbsID,BoardDAO dao,BoardDTO dto,Model model) {
		int int_bbsID = Integer.parseInt(bbsID);
		dto.setBbsID(int_bbsID);
		model.addAttribute("board",dao.getBoard(dto));
		
		return "view.jsp";
	}
	
	// �����ϱ�
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateView(Model model,@RequestParam(value="bbsID") String bbsID,BoardDAO dao,BoardDTO dto,HttpSession session,HttpServletResponse response) throws IOException {
		System.out.println("�����ϱ� ȭ������ �̵�...");
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(userID == null) { // �α����� �Ǿ����� ����.
			ScriptAlertUtils.DoLogin(response);
		}
		
		dto.setBbsID(Integer.parseInt(bbsID));
		BoardDTO bbs = dao.getBoard(dto); //Ŭ���� �Խù� ���� �ش��ϴ� �Խù� ��ü�� ��ȯ�Ѵ�.!!
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
		
		if(userID == null) { // �α����� �Ǿ����� ����.
			ScriptAlertUtils.DoLogin(response);
		}
		
		BoardDTO bbs = dao.getBoard(dto); //Ŭ���� �Խù� ���� �ش��ϴ� �Խù� ��ü�� ��ȯ�Ѵ�.!!
		if(!userID.equals(bbs.getUserID())){ // �α����� ����� �� �Խù��̶�� true�� ��ȯ�Ѵ�!!!
			ScriptAlertUtils.NoAuthority(response);
		}
		else { // ����
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
	
	// �����ϱ�
	@RequestMapping("/delete.do")
	public String boardDelete(@RequestParam(value="bbsID") String bbsID,BoardDAO dao,BoardDTO dto,HttpSession session,HttpServletResponse response) throws IOException {
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(userID == null) { // �α����� �Ǿ����� ����.
			ScriptAlertUtils.DoLogin(response);
		}
		
		dto.setBbsID(Integer.parseInt(bbsID));
		BoardDTO bbs = dao.getBoard(dto); //Ŭ���� �Խù� ���� �ش��ϴ� �Խù� ��ü�� ��ȯ�Ѵ�.!!
		if(!userID.equals(bbs.getUserID())){
			ScriptAlertUtils.NoAuthority(response);
		}
		else { // ����
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