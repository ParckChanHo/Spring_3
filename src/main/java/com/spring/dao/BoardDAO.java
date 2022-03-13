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
		String sql="insert into bbs (bbsID,bbsTitle,userID,bbsContent,bbsAvailable) VALUES((select nvl(max(bbsID),0)+1 from bbs),?,?,?,?)";  //게시물 작성하기

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBbsTitle());
			pstmt.setString(2, dto.getUserID());
			pstmt.setString(3, dto.getBbsContent());
			pstmt.setInt(4, 1); //삭제가 되지 않아서

			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
	public int getNext() {
		// 다음 bbsID
		String sql="SELECT bbsID from bbs ORDER BY bbsID DESC";  //내림차순으로  즉 큰 것 >>> 작은 것

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1; 
				//bbsID int >> 계시글의 번호를 부여함 
				// 즉 다음 계시물의 번호를 반환하기 위해서 이렇게 함!! 예를 들어서 1번 다음에 2번
			}
			return 1; //현재 하나도 게시물이 없을 때
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
	// bbsID가 게시물의 번호를 의미한다.
		public ArrayList<BoardDTO> getList(int pageNumber){
			String sql="select * from bbs where bbsID < ? and bbsAvailable = 1 and ROWNUM >= 1 AND ROWNUM <= 10"
					+ "order by bbsID desc";  
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext() - (pageNumber - 1)*10);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardDTO bbs = new BoardDTO(); //1개의 게시물들을 모두 새로운 board객체에다가 저장을 
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

		public BoardDTO getBoard(BoardDTO dto) { // bbsID에 해당하는 게시물을 반환한다.
			String sql="select * from bbs where bbsID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBbsID());//인자로 전달받은 bbsID의 값에 해당하는 게시물을 선택한다!!!
				rs = pstmt.executeQuery();
				if(rs.next()) {// 해당되는 게시물이 있으면
					BoardDTO bbs = new BoardDTO(); //1개의 게시물들을 모두 새로운 board객체에다가 저장을 
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