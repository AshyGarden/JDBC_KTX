package com.java.Train.Trains.service;

import com.java.Train.Trains.repository.TrainsRepository;
import com.java.common.AppService;

import static com.java.view.AppUI.*;

public class TrainService implements AppService {
	
	private final TrainsRepository trainsRepository  = new TrainsRepository();
	
	@Override
	public void start() {
		while(true){
			TrainSituationScreen();
			int selection = inputInteger();
			
			switch(selection) {
			case 1000:
				ShowAlltrains();
				break;
			case 2001: case 4001: case 6001: case 8001:
				SelectTrain();
				break;
			case 9:
				return; //메인화면으로 돌아가기			
			default:
				System.out.println("# 메뉴를 다시 입력하세요.");			
			}
			System.out.println("\n======계속 진행하시려면 ENTER를 누르세요======");
			inputString();
		}
		
	}
	private void ShowAlltrains() {
		//1번. 모든 열차정보 보여주기
		
		//trainsRepository.findByTrainName();
	}
	
	private void SelectTrain() {
		//2번. 특정 열차정보 보여주기
		
	}

}
