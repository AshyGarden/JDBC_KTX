package com.java.Train.seats.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.Train.Trains.domain.Train;
import com.java.common.DataBaseConnection;

public class TrainSeatsRepository {
	private DataBaseConnection connection 
	= DataBaseConnection.getInstance();

	//열차의 번호로 정보 검색
		public List<Train> findByTrainName(String trainNo) {
			List<Train> trainList = new ArrayList<>();
			String sql = "SELECT * FROM train WHERE trainNo=?";
			
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, trainNo); //
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					//Grade grade = Grade.valueOf(rs.getString("grade"));
					Train train = new Train(
								rs.getInt("train_num"),
								rs.getString("train_start"),
								rs.getDate("train_depart"),
								rs.getString("train_togo"),
								rs.getDate("train_arrive"),
								rs.getInt("train_price")
							);
					trainList.add(train);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return trainList;
		}
		
		
		// 전체 열차로 정보 검색
		public List<Train> allTrainName() {
			List<Train> trainList = new ArrayList<>();
			String sql = "SELECT * FROM train";
			
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) { //
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					//Grade grade = Grade.valueOf(rs.getString("grade"));
					Train train = new Train(
								rs.getInt("train_num"),
								rs.getString("train_start"),
								rs.getDate("train_depart"),
								rs.getString("train_togo"),
								rs.getDate("train_arrive"),
								rs.getInt("train_price")
							);
					trainList.add(train);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return trainList;
		}
		
		

}
