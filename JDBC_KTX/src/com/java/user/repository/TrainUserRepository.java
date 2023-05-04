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
	private DataBaseConnection connection = DataBaseConnection.getInstance();
	
	public List<TrainUser> findByUserID(String userId){
		List<TrainUser> tUserList = new ArrayList<>();
		String sql = "SELECT * FROM train_users WHERE user_id=?";
		
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
	
	public List<TrainUser> isUserBooked(String userId){
		List<TrainUser> tUserList = new ArrayList<>();
		String sql = "SELECT tu.user_id, tu.user_budget, tu.user_rsv_train_tno, "
				+ "tu.user_rsv_train_rseat, tu.user_rsv_train_rev "
				+ "FROM train_users tu "
				+ "JOIN train_seats ts "
				+ "ON tu.user_rsv_train_tno = ts.train_num "
				+ "AND tu.user_rsv_train_rseat = ts.train_col "
				+ "AND tu.user_rsv_train_rev = ts.train_rev "
				+ "WHERE ts.train_isrsv='true' AND tu.user_id=? ";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TrainUser tUser = new TrainUser(						
						rs.getString("user_id"),
						rs.getInt("user_budget"),
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
	public void payingMoney(List<TrainUser> tUser,int cost, int tNum, String row, String rev){
		
		String sql = "UPDATE train_users "
				+ "SET user_budget "+ "= user_budget - ? , "
				+ "user_rsv_train_tno = ? ,  "
				+ "user_rsv_train_rseat = ? ,  "
				+ "user_rsv_train_rev = ? "
				+ "WHERE user_id=?";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cost);
			pstmt.setInt(2, tNum);
			pstmt.setString(3, row);
			pstmt.setString(4, rev);
			pstmt.setString(5, tUser.get(0).getUserId());
			
			if((tUser.get(0).getBudget()-cost)<0) {
				System.out.printf("\n### %s님의 소지금이 부족해서 예매가 취소되었습니다.\n",tUser.get(0).getUserId());
				return;
			} else {							
				if(pstmt.executeUpdate() == 1) {
					System.out.printf("\n### %s님의 예매가 완료되었습니다.\n",tUser.get(0).getUserId());
					System.out.printf("\n### %s님의 소지금에서 %d만큼 지불되어 현재 잔여금액은 %d입니다.\n"
							,tUser.get(0).getUserId(), cost, tUser.get(0).getBudget()-cost);
					
				} else {
					System.out.println("예매가 실패했습니다. 이전화면으로 돌아갑니다.");
					return;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void refundMoney(List<TrainUser> tUser,int cost){
		String sql = "UPDATE train_users "
				+ "SET user_budget "+ "= user_budget + ? , "
				+ "user_rsv_train_tno = ? ,  "
				+ "user_rsv_train_rseat = ? ,  "
				+ "user_rsv_train_rev = ? "
				+ "WHERE user_id=?";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cost);
			pstmt.setInt(2, 0);
			pstmt.setString(3, "N");
			pstmt.setString(4, "N");
			pstmt.setString(5, tUser.get(0).getUserId());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.printf("\n### %s님의 예매취소가 완료되었습니다.\n",tUser.get(0).getUserId());
				System.out.printf("\n### %s님의 에게 %d만큼 환불되어 현재 잔여금액은 %d입니다.\n"
						,tUser.get(0).getUserId(), cost, cost + tUser.get(0).getBudget());
			} else {
				System.out.println("예매 취소가 실패했습니다. 이전화면으로 돌아갑니다.");
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
