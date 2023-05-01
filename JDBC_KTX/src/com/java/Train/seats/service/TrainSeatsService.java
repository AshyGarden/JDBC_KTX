package com.java.Train.seats.service;

import com.java.Train.seats.repository.TrainSeatsRepository;
import com.java.common.AppService;

import static com.java.view.AppUI.*;

public class TrainSeatsService implements AppService{

	private final TrainSeatsRepository trainSeatsRepository = new TrainSeatsRepository();
	
	@Override
	public void start() {
		while(true) {
			TrainSeatsSituationScreen();
			int selection = inputInteger();
			
			switch (selection) {
			case 1:
				
				break;
			case 2:
				
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
