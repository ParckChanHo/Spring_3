package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.common.ScriptAlertUtils;
import com.spring.dao.UserDAO;
import com.spring.dto.UserDTO;


@Controller
public class UserController {
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserDTO vo) {
		System.out.println("�α��� ȭ������ �̵�...");
		return "login.jsp";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserDTO dto,UserDAO dao ,HttpSession session,HttpServletResponse response) throws IOException {
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if(userID != null) // �̹� �α����� �Ǿ��ִ� ���
			ScriptAlertUtils.AlreadyLogin(response);
		
		int result = dao.login(dto);
		System.out.println("userID: "+dto.getUserID()+" "+"userPassword: "+dto.getUserPassword());
		System.out.println("result: "+result);
		
		if(result == 1){ // �α��� ����
			session.setAttribute("userID", dto.getUserID());//������ �̸��� userID �̰� ������ ���� ���ڷ� ���޹��� 
			// ������� ���̵��̴�.
			return "main.jsp";
		}
		
		else if(result == 0) { // ��й�ȣ ����ġ
			ScriptAlertUtils.WrongPassword(response);
		}
		else if(result == -1) { // ���̵� ����
			ScriptAlertUtils.WrongID(response);
		}
		else if(result == -2) { // �����ͺ��̽� ����
			ScriptAlertUtils.WrongDatabase(response);
		}
		
		return null;	
	}
		
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String joinView() {
		System.out.println("ȸ������ ȭ������ �̵�...");
		return "join.jsp";
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String join(UserDTO dto,UserDAO dao,HttpServletResponse response,HttpSession session) throws IOException {
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if(userID != null) // �̹� �α����� �Ǿ��ִ� ���
			ScriptAlertUtils.AlreadyLogin(response);
		
		if(dto.getUserID() == null || dto.getUserID().equals("") ||
				dto.getUserPassword() == null || dto.getUserPassword().equals("")
				|| dto.getUserName() == null || dto.getUserName().equals("") ||
				dto.getUserGender() == null || dto.getUserGender().equals("") ||
				dto.getUserEmail() == null || dto.getUserEmail().equals("")){
				
			ScriptAlertUtils.EmptyJoin(response);
		}
		else {
			int result = dao.join(dto);
			if(result == -1)
				ScriptAlertUtils.DuplicateId(response);
			else {
				//������ �̸��� userID �̰� ������ ���� ���ڷ� ���޹��� ������� ���̵��̴�. 
				session.setAttribute("userID", dto.getUserID()); // ȸ������ ����
				return "main.jsp";
			}
		}
		return null;
	}
	
	// �α׾ƿ� �ϱ�
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "main.jsp";
	}
	
	
	
	
	
}