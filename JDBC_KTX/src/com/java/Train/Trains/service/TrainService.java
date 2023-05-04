package com.java.Train.Trains.service;

import com.java.Train.Trains.domain.Train;
import com.java.Train.Trains.repository.TrainsRepository;
import com.java.common.AppService;

import static com.java.view.AppUI.*;

import java.util.List;

public class TrainService implements AppService {
	
	private final TrainsRepository trainsRepository = new TrainsRepository();

	@Override
	public void start() {
		while(true){
			TrainSituationScreen();
			int selection = inputInteger();
			
			switch(selection) {
			case 9:
				return; //메인화면으로 돌아가기			
			case 1000:
				ShowAlltrains();
				break;
			case 2001: case 4001: case 6001: case 8001:
				SelectTrain(selection);
				break;
			default:
				System.out.println("# 메뉴를 다시 입력하세요.");			
			}
			System.out.println("\n======계속 진행하시려면 ENTER를 누르세요======");
			inputString();
		}
		
	}
	private void ShowAlltrains() {
		//1번. 모든 열차정보 보여주기
		List<Train> trains = trainsRepository.allTrainName();
		if(trains.size() > 0) {
			for(Train t : trains) {
				System.out.println(t);
			}
			System.out.printf("===================== 현재 운행 정보 (%d건) ====================",trains.size());
		} else {
			System.out.println("\n### 현재 운행중인 열차는 없습니다.");
		}
	}
	
	private void SelectTrain(int tNo) {
		//2번. 특정 열차정보 보여주기
		List<Train> sTrains = trainsRepository.findByTrainName(tNo);	
		if(sTrains.size() > 0) {
			for(Train t : sTrains) {
				System.out.println(t);
			}
			System.out.printf("===================== 현재 운행 정보 (%d건) ====================",sTrains.size());
		} else {
			System.out.println("\n### 현재 운행중인 열차는 없습니다.");
		}
	}

}
