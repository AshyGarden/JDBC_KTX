package com.java.Train.seats.service;

import com.java.Train.seats.domain.TrainSeats;
import com.java.Train.seats.repository.TrainSeatsRepository;
import com.java.common.AppService;
import com.java.common.DataBaseConnection;

import static com.java.view.AppUI.*;

import java.util.ArrayList;
import java.util.List;

public class TrainSeatsService implements AppService{

	private final TrainSeatsRepository trainSeats = new TrainSeatsRepository();
	DataBaseConnection connection = DataBaseConnection.getInstance();

	public void start() {
		while(true) {
			TrainSeatsSituationScreen();
			int select = inputInteger();
					 
			switch (select) {
			case 1000:
				showMoreData();
				break;
				
			case 2001: 
				Mugunghwa();
				break;
				
			case 4001:
				Saemaeul();
				break;
			
			case 6001:
				showITX();
				break;
				
			case 8001:
				showKTX();
				break;
				
			case 9:
				return;
				
			default:
				System.out.println("없는 번호입니다. 다시 입력해주세요.");
			}
			System.out.println("\n========== 계속 진행하시려면 ENTER를 누르세요 ===========");
			inputString();
		}
	}
	
	//특정 열차 좌석 정보 보여주기
	private int Mugunghwa() {
		List<TrainSeats> selectSeat = trainSeats.findTrainSeats(2001);
		if(!selectSeat.isEmpty()) {
			for(TrainSeats s : selectSeat) {
				System.out.println(s);
			}
			System.out.println("========== 현재 무궁화호 좌석 정보 ==========");
		} else {
			System.out.println("현재 좌석 정보가 존재하지 않습니다.");
		}
		return selectSeat.size();	
	}
	
	private int Saemaeul() {
		List<TrainSeats> selectSeat = trainSeats.findTrainSeats(4001);
		if(!selectSeat.isEmpty()) {
			for(TrainSeats s : selectSeat) {
				System.out.println(s);
			}
			System.out.println("========== 현재 새마을호 좌석 정보 ==========");
		} else {
			System.out.println("현재 좌석 정보가 존재하지 않습니다.");
		}
		return selectSeat.size();		
	}
	
	private int showITX() {
		List<TrainSeats> selectSeat = trainSeats.findTrainSeats(6001);
		if(!selectSeat.isEmpty()) {
			for(TrainSeats s : selectSeat) {
				System.out.println(s);
			}
			System.out.println("========== 현재 ITX 좌석 정보 ==========");
		} else {
			System.out.println("현재 좌석 정보가 존재하지 않습니다.");
		}
		return selectSeat.size();	
	}
	
	private int showKTX() {
		List<TrainSeats> selectSeat = trainSeats.findTrainSeats(8001);
		if(!selectSeat.isEmpty()) {
			for(TrainSeats s : selectSeat) {
				System.out.println(s);
			}
			System.out.println("========== 현재 KTX 좌석 정보 ==========");
		} else {
			System.out.println("현재 좌석 정보가 존재하지 않습니다.");
		}
		return selectSeat.size();	
	}


	//전체 열차 좌석 정보 보여주기
	private int showMoreData() {		
		List<TrainSeats> seats = new ArrayList<>();		
		List<TrainSeats> seats2001 = trainSeats.findTrainSeats(2001);
		List<TrainSeats> seats4001 = trainSeats.findTrainSeats(4001);
		List<TrainSeats> seats6001 = trainSeats.findTrainSeats(6001);
		List<TrainSeats> seats8001 = trainSeats.findTrainSeats(8001);
				
		seats.addAll(seats2001);
		seats.addAll(seats4001);
		seats.addAll(seats6001);
		seats.addAll(seats8001);
		if(!seats.isEmpty()) {
			System.out.println("========== 현재 전체 좌석 정보 ==========");
			for(TrainSeats s : seats) {
				System.out.println(s);
			}
			
//			for(int i=0; i<seats.size(); i++) {
//				System.out.printf("%s번 열차정보\n",seats.get(i).getTrainNo());
//				
//				
//				if(seats.get(i).getTrainNo()==seats.size() || seats.get(i).getTrainNo() == seats.get(i+1).getTrainNo()) {
//					System.out.println();
//				} else {
//					System.out.println(seats.toString());			
//				}
//				System.out.println("--------------------------------------------------------------------------");				
//			}
						
		} else {
			System.out.println("현재 좌석 정보가 존재하지 않습니다.");
		}
		return seats.size();
	}

}
