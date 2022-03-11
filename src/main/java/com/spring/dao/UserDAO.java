package com.spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.spring.dto.UserDTO;

@Repository("userDAO")
public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbID = "scott";
			String dbPassword = "1234";

			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(UserDTO dto) {
		String sql = "select userPassword from users where userID=?"; //일단 where절에서 패스워드를 찾았다는 것은 해당 아이디가 존재한다는 뜻!!
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, dto.getUserID());
			rs = pstmt.executeQuery();//rs에는 userPassword라는 1나의 필드밖에 없을 것이다.!!!
			// rs >>>> '123456'
			if(rs.next()) { //아이디가 존재하는 경우
				if(rs.getString(1).equals(dto.getUserPassword()))//비밀번호가 일치함!!!
					return 1; //로그인 성공
				else
					return 0; //비밀번호 불일치
			}
			else
				return -1; //아이디가 없음!!!!
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return -2; // 데이터베이스 오류!!
	}

	// 하나의 사용자를 입력받을 수 있도록
	public int join(UserDTO user) {
		String sql = "insert into users(userID,userPassword,userName,userGender,userEmail) values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user.getUserID());
			pstmt.setString(2,user.getUserPassword());
			pstmt.setString(3,user.getUserName());
			pstmt.setString(4,user.getUserGender());
			pstmt.setString(5,user.getUserEmail());
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		} // end finally
		return -1;
	}
	
}
