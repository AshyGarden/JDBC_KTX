package com.java.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.common.DataBaseConnection;
import com.java.user.domain.TrainUser;

public class TrainUserRepository {
	private DataBaseConnection connection =
			DataBaseConnection.getInstance();
	
	public List<TrainUser> findByUserID(String userId){
		List<TrainUser> tUserList = new ArrayList<>();
		String sql = "Select * FROM train_users WHERE user_id";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TrainUser tUser = new TrainUser(
						rs.getInt("user_num"),
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getInt("user_budget"),
						rs.getInt("user_discount"),
						rs.getInt("user_rsv_train_tno"),
						rs.getString("user_rsv_train_rseat"),
						rs.getString("user_rsv_train_rev")					
					);
				tUserList.add(tUser);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return tUserList;
	}
	
}
