package com.spring.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptAlertUtils {
	public static void init(HttpServletResponse response) {		   	   
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }
	
	public static void WrongPassword(HttpServletResponse response) throws IOException {
    	// 사용법 : ScriptAlertUtils.alert("아이디가 중복");
        init(response);
        
        PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 틀립니다.')");
		script.println("history.back()");
		script.println("</script>");
    }
	
	public static void WrongID(HttpServletResponse response) throws IOException {
    	// 사용법 : ScriptAlertUtils.alert("아이디가 중복");
        init(response);
        
        PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('존재하지 않는 아이디입니다.')");
		script.println("history.back()");
		script.println("</script>");
    }
	
	public static void WrongDatabase(HttpServletResponse response) throws IOException {
    	// 사용법 : ScriptAlertUtils.alert("아이디가 중복");
        init(response);
        
        PrintWriter script = response.getWriter();
        script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생했습니다.')");
		script.println("history.back()");
		script.println("</script>");
    }
	
	public static void EmptyJoin(HttpServletResponse response) throws IOException {
		init(response);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.')");
		script.println("history.back()");
		script.println("</script>");
	}
	
	public static void DuplicateId(HttpServletResponse response) throws IOException {
		init(response);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('아이디가 이미 존재합니다.')"); //id가 primary key이기 떄문이다.
		script.println("history.back()");
		script.println("</script>");
	}
	
	// 이미 로그인이 되어있다. 따라서 로그인을 하면 안된다.
	public static void AlreadyLogin(HttpServletResponse response) throws IOException {
		init(response);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 로그인이 되어있습니다.')"); //id가 primary key이기 떄문이다.
		script.println("history.back()");
		script.println("</script>");
	}
	
}
