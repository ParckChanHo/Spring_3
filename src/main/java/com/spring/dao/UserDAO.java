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
		String sql = "select userPassword from users where userID=?"; //�ϴ� where������ �н����带 ã�Ҵٴ� ���� �ش� ���̵� �����Ѵٴ� ��!!
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, dto.getUserID());
			rs = pstmt.executeQuery();//rs���� userPassword��� 1���� �ʵ�ۿ� ���� ���̴�.!!!
			// rs >>>> '123456'
			if(rs.next()) { //���̵� �����ϴ� ���
				if(rs.getString(1).equals(dto.getUserPassword()))//��й�ȣ�� ��ġ��!!!
					return 1; //�α��� ����
				else
					return 0; //��й�ȣ ����ġ
			}
			else
				return -1; //���̵� ����!!!!
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return -2; // �����ͺ��̽� ����!!
	}

	// �ϳ��� ����ڸ� �Է¹��� �� �ֵ���
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
