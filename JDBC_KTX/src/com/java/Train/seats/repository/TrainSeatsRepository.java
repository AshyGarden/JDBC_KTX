package com.java.Train.seats.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.Train.Trains.repository.TrainsRepository;
import com.java.Train.seats.domain.TrainSeats;
import com.java.common.DataBaseConnection;
import com.java.user.domain.TrainUser;
import com.java.user.repository.TrainUserRepository;

import static com.java.view.AppUI.*;

public class TrainSeatsRepository {
	
	private DataBaseConnection connection 
	= DataBaseConnection.getInstance();
	
	private final TrainsRepository tRepository = new TrainsRepository ();
	private final TrainUserRepository tuRepository = new TrainUserRepository ();
	TrainSeats tseats;
	
	//좌석번호
	public List<TrainSeats> findTrainSeats(String tSeatNo) {
		List<TrainSeats> selectTrainSeat = new ArrayList<>();
		String sql = "SELECT * FROM train_seats WHERE train_seat=? AND train_isrsv=?";			
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
		String sql = "SELECT * FROM train_seats WHERE train_num=?";			
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
	
	// 각 열차의 좌석 예약 유무 확인(findBySeats)
	public List<TrainSeats> isSeatBooked(String trainCheck) {
		List<TrainSeats> trainList = new ArrayList<>();
		String sql = "SELECT * "
				+ " FROM train_seats "
				+ "WHERE train_seat LIKE ? ";

		try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, trainCheck + "%");
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				TrainSeats trains = new TrainSeats(
						rs.getString("TRAIN_SEAT"),
						rs.getInt("TRAIN_NUM"),
						rs.getString("TRAIN_COL"),
						rs.getString("TRAIN_REV"),
						rs.getString("TRAIN_ISRSV")
						);

				trainList.add(trains);
			}
			System.out.println(trainCheck + "의 좌석 정보를 출력합니다.");
			for (int i=0; i<trainList.size(); i++) {
				System.out.println("열차 좌석 정보 " + trainList.get(i).getTrainSeat() + "\t예약 여부" + trainList.get(i).getTrainIsrsv());
				//System.out.println("예약 여부" + trainList.get(i).getTrainIsrsv());
			}
			if(pstmt.executeUpdate() != 1) {}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return trainList;
	};
	//예약하기
	public void bookingSeats(List<TrainUser>tUser, String seat) {
		String sql = "UPDATE train_seats SET train_isrsv='true' "
				+ "WHERE train_seat = ?";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, seat);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("좌석 예매 성공.");				
			} else {
				System.out.println("예매가 실패했습니다. 이전화면으로 돌아갑니다.");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return tUserList;		
	}
	
	public void ticetingreservations(String trainSeat) {
		String sql = " UPDATE TRAIN_SEATS "
				+ " SET train_isrsv = 'true' "
				+ " WHERE train_seat = ? ";
		try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, trainSeat);
	
			if(pstmt.executeUpdate() == 1) {
				System.out.println(trainSeat + " 자리가 예매됐습니다" );
			} else {
				System.out.println("확인");
					
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	//좌석 되돌려놓는 로직
	public void rollbackcSeats(List<TrainUser> tUser, String seat) {
		String sql = "UPDATE train_seats SET train_isrsv='false' "
				+ "WHERE train_seat = ?";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, seat);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("좌석 롤백 성공.");				
			} else {
				System.out.println("예매 취소가 실패했습니다. 이전화면으로 돌아갑니다.");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return tUserList;		
	}

	
	
	
	

}
