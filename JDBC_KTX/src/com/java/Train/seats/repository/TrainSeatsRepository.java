package com.java.Train.seats.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.Train.seats.domain.TrainSeats;
import com.java.common.DataBaseConnection;

public class TrainSeatsRepository {
	
	private DataBaseConnection connection 
	= DataBaseConnection.getInstance();
	
	TrainSeats tseats;
	
	//좌석번호
	public List<TrainSeats> findTrainSeats(String tSeatNo) {
		List<TrainSeats> selectTrainSeat = new ArrayList<>();
		String sql = "SELECT * FROM train WHERE train_seat=? AND train_isrsv=?";			
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, tSeatNo); 
			pstmt.setString(2, tseats.getTrainIsrsv()); 
			ResultSet rs = pstmt.executeQuery();
				
			while(rs.next()) {				
				TrainSeats trainSeats 
				= new TrainSeats(													
							rs.getString("train_seat"),
							rs.getInt("train_num"),
							rs.getString("train_col"),
							rs.getString("train_rev"),
							rs.getString("train_isrsv")							
						);
				selectTrainSeat.add(trainSeats);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return selectTrainSeat;
	}
	
	//Overloading - 열차번호
	public List<TrainSeats> findTrainSeats(int tNo) {
		List<TrainSeats> selectTrainSeat = new ArrayList<>();
		String sql = "SELECT * FROM train WHERE train_num=?";			
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, tNo); 
			//pstmt.setString(2, tseats.getTrainIsrsv()); 
			ResultSet rs = pstmt.executeQuery();
				
			while(rs.next()) {				
				TrainSeats trainSeats 
				= new TrainSeats(													
							rs.getString("train_seat"),
							rs.getInt("train_num"),
							rs.getString("train_col"),
							rs.getString("train_rev"),
							rs.getString("train_isrsv")							
						);
				selectTrainSeat.add(trainSeats);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return selectTrainSeat;
	}

}
