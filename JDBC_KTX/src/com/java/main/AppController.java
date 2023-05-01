package com.java.main;

import com.java.Train.Trains.service.TrainService;
import com.java.Train.seats.service.TrainSeatsService;
import com.java.common.AppService;
import com.java.user.service.TrainUserService;
import com.java.user.service.TrainUserSuggetions;

public class AppController {
	private AppService service =  new TrainService();;
	public void chooseSystem(int selectNUmber) {
		switch(selectNUmber) {
		case 1: //현재열차운행정보
			service =  new TrainService();
			break;
		case 2: //현재열차좌석정보
			service =  new TrainSeatsService();
			break;
		case 3: //열차표 예매하기 
			break;
		case 4: //예매한 좌석정보	
			break;
		case 5: //예매좌석취소			
			service =  new TrainUserService();
			break;
		case 7: //예매 프로그램 건의사항 작성하기
			service = new TrainUserSuggetions();
			break;
		case 9: //프로그램 종료
			System.out.println("# 프로그램을 종료합니다.");
			System.exit(0);
		case 99: //Console로 달리는 기차 감상하기
			//DrawingTrain();
			break;
		default:
			System.out.println("# 메뉴를 다시 입력해주세요.");		
		}
		service.start();
	}

}
