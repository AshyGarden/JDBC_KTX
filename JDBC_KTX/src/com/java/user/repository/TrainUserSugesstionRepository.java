package com.java.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.common.DataBaseConnection;
import com.java.user.domain.TrainUser;
import com.java.user.domain.TrainUserSuggestion;

public class TrainUserSugesstionRepository {
	private DataBaseConnection connection =
			DataBaseConnection.getInstance();
	//삽입
	public void addSuggestion(TrainUserSuggestion tus) {
		String sql = "INSERT INTO train_sugesstion "
		        +"(suggestnum, user_id, user_sugesstion) "
				+ "VALUES(ts_seq.NEXTVAL,?,?)";

		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, tus.getSuggestNum());
			pstmt.setString(2, tus.getUserId());
			pstmt.setString(3, tus.getSuggestions());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.printf("\n### %s님의 건의사항이 신규 등록되었습니다.\n", tus.getUserId());
			} else {
				System.out.println("건의사항 등록에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//전체 다 가져오기
	public List<TrainUserSuggestion> searchingSuggestion(){
		List<TrainUserSuggestion> tusList = new ArrayList<>();
		String sql = "Select * FROM train_sugesstion";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TrainUserSuggestion tus = new TrainUserSuggestion(
						rs.getInt("suggestnum"),
						rs.getString("user_id"),
						rs.getString("user_sugesstion")			
					);
				tusList.add(tus);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return tusList;
	}
	
	//아이디로 가져오기
	public List<TrainUserSuggestion> searchingSuggestion(String userId){
		List<TrainUserSuggestion> tusList = new ArrayList<>();
		String sql = "Select * FROM train_suggestion WHERE user_id=?";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TrainUserSuggestion tus = new TrainUserSuggestion(
						rs.getInt("suggestnum"),
						rs.getString("user_id"),
						rs.getString("user_sugesstion")			
					);
				tusList.add(tus);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return tusList;
	}
	
	//조회번호로 가져오기
	public List<TrainUserSuggestion> searchingSuggestion(int num){
		List<TrainUserSuggestion> tusList = new ArrayList<>();
		String sql = "Select * FROM train_users WHERE suggestnum=?";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TrainUserSuggestion tus = new TrainUserSuggestion(
						rs.getInt("suggestnum"),
						rs.getString("user_id"),
						rs.getString("user_sugesstion")			
					);
				tusList.add(tus);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return tusList;
	}
}
