package com.java.Train.Trains.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.Train.Trains.domain.Train;
import com.java.common.DataBaseConnection;

public class TrainsRepository {
	private DataBaseConnection connection 
	= DataBaseConnection.getInstance();

	//열차의 번호로 정보 검색
	public List<Train> findByTrainName(int trainNo) {
		List<Train> selectTrainList = new ArrayList<>();
		String sql = "SELECT * FROM train WHERE train_num=?";			
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, trainNo); //
			ResultSet rs = pstmt.executeQuery();
				
			while(rs.next()) {				
				Train train = new Train(
							rs.getInt("train_num"),
							rs.getString("train_start"),
							rs.getDate("train_depart"),
							rs.getString("train_togo"),
							rs.getDate("train_arrive"),
							rs.getInt("train_price")
						);
				selectTrainList.add(train);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return selectTrainList;
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
	
	public int ticketPrice(int tNo) {
		int price = 0;
		switch(tNo) {
			case 2001: price = 30000;  break;
			case 4001: price = 40000;  break;
			case 6001: price = 10000;  break;
			case 8001: price = 100000; break;
		}		
		return price;
	}
	
	public String printTrainName(int tNo) {
		String tName = "";
		switch(tNo) {
			case 2001: tName = "무궁화호";  break;
			case 4001: tName = "새마을호";  break;
			case 6001: tName = "ITX";  break;
			case 8001: tName = "KTX";  break;
		}		
		return tName;
	}


	
}
