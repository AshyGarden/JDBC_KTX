package com.java.Train.seats.service;

import com.java.Train.seats.repository.TrainSeatsRepository;
import com.java.common.AppService;

import static com.java.view.AppUI.*;
import static com.java.common.location.*;

public class TrainSeatsService implements AppService{
private final TrainSeatsRepository trainSeatsRepository = new TrainSeatsRepository();
	
	@Override
	public void start() {
		while(true) {
			SeatsSituationScreen();
			int selection = inputInteger();
			
			switch (selection) {
			case 1:
				//insertMovieData();
				break;
			case 2:
				//showSearchResult();
				break;
			case 3:
				return;

			default:
				System.out.println("메뉴를 다시 입력하세요.");
			}
			System.out.println("\n====== 계속 진행하시려면 ENTER를 누르세요 ======");
			inputString();
		}
	}
	
	

}
