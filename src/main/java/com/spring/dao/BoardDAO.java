package com.spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.spring.dto.BoardDTO;

public class BoardDAO {
	private Connection conn;
	private ResultSet rs;

	public BoardDAO() {
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
	
	public int write(BoardDTO dto) { 
		String sql="insert into bbs (bbsID,bbsTitle,userID,bbsContent,bbsAvailable) VALUES((select nvl(max(bbsID),0)+1 from bbs),?,?,?,?)";  //�Խù� �ۼ��ϱ�

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBbsTitle());
			pstmt.setString(2, dto.getUserID());
			pstmt.setString(3, dto.getBbsContent());
			pstmt.setInt(4, 1); //������ ���� �ʾƼ�

			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
	
	public int getNext() {
		// ���� bbsID
		String sql="SELECT bbsID from bbs ORDER BY bbsID DESC";  //������������  �� ū �� >>> ���� ��

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1; 
				//bbsID int >> ��ñ��� ��ȣ�� �ο��� 
				// �� ���� ��ù��� ��ȣ�� ��ȯ�ϱ� ���ؼ� �̷��� ��!! ���� �� 1�� ������ 2��
			}
			return 1; //���� �ϳ��� �Խù��� ���� ��
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
	
	// bbsID�� �Խù��� ��ȣ�� �ǹ��Ѵ�.
		public ArrayList<BoardDTO> getList(int pageNumber){
			String sql="select * from bbs where bbsID < ? and bbsAvailable = 1 and ROWNUM >= 1 AND ROWNUM <= 10"
					+ "order by bbsID desc";  
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext() - (pageNumber - 1)*10);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardDTO bbs = new BoardDTO(); //1���� �Խù����� ��� ���ο� board��ü���ٰ� ������ 
					bbs.setBbsID(rs.getInt(1));
					bbs.setBbsTitle(rs.getString(2));
					bbs.setUserID(rs.getString(3));
					bbs.setBbsDate(rs.getDate(4));
					bbs.setBbsContent(rs.getString(5));
					bbs.setBbsAvailable(rs.getInt(6));
					list.add(bbs);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		public boolean nextPage(int pageNumber) {
			String sql="select * from bbs where bbsID < ? and bbsAvailable = 1";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext() - (pageNumber - 1)*10);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return true;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		public BoardDTO getBoard(BoardDTO dto) { // bbsID�� �ش��ϴ� �Խù��� ��ȯ�Ѵ�.
			String sql="select * from bbs where bbsID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBbsID());//���ڷ� ���޹��� bbsID�� ���� �ش��ϴ� �Խù��� �����Ѵ�!!!
				rs = pstmt.executeQuery();
				if(rs.next()) {// �ش�Ǵ� �Խù��� ������
					BoardDTO bbs = new BoardDTO(); //1���� �Խù����� ��� ���ο� board��ü���ٰ� ������ 
					bbs.setBbsID(rs.getInt(1));
					bbs.setBbsTitle(rs.getString(2));
					bbs.setUserID(rs.getString(3));
					bbs.setBbsDate(rs.getDate(4));
					bbs.setBbsContent(rs.getString(5));
					bbs.setBbsAvailable(rs.getInt(6));
					return bbs;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public int update(BoardDTO dto) {
			String SQL = "UPDATE bbs SET bbsTitle=?, bbsContent=? WHERE bbsID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, dto.getBbsTitle());
				pstmt.setString(2, dto.getBbsContent());
				pstmt.setInt(3, dto.getBbsID());
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1; 
		}

		public int delete(BoardDTO dto) {
			String SQL = "UPDATE bbs SET bbsAvailable = 0 WHERE bbsID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, dto.getBbsID());
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1; 
		}
		
}