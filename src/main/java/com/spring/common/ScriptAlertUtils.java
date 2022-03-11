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
    	// ���� : ScriptAlertUtils.alert("���̵� �ߺ�");
        init(response);
        
        PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('��й�ȣ�� Ʋ���ϴ�.')");
		script.println("history.back()");
		script.println("</script>");
    }
	
	public static void WrongID(HttpServletResponse response) throws IOException {
    	// ���� : ScriptAlertUtils.alert("���̵� �ߺ�");
        init(response);
        
        PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�������� �ʴ� ���̵��Դϴ�.')");
		script.println("history.back()");
		script.println("</script>");
    }
	
	public static void WrongDatabase(HttpServletResponse response) throws IOException {
    	// ���� : ScriptAlertUtils.alert("���̵� �ߺ�");
        init(response);
        
        PrintWriter script = response.getWriter();
        script.println("<script>");
		script.println("alert('�����ͺ��̽� ������ �߻��߽��ϴ�.')");
		script.println("history.back()");
		script.println("</script>");
    }
	
	public static void EmptyJoin(HttpServletResponse response) throws IOException {
		init(response);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�Է��� �� �� ������ �ֽ��ϴ�.')");
		script.println("history.back()");
		script.println("</script>");
	}
	
	public static void DuplicateId(HttpServletResponse response) throws IOException {
		init(response);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('���̵� �̹� �����մϴ�.')"); //id�� primary key�̱� �����̴�.
		script.println("history.back()");
		script.println("</script>");
	}
	
	// �̹� �α����� �Ǿ��ִ�. ���� �α����� �ϸ� �ȵȴ�.
	public static void AlreadyLogin(HttpServletResponse response) throws IOException {
		init(response);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�̹� �α����� �Ǿ��ֽ��ϴ�.')"); //id�� primary key�̱� �����̴�.
		script.println("history.back()");
		script.println("</script>");
	}
	
}
