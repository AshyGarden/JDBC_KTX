package com.java.main;

import com.java.common.AppService;

public class AppController {
	private AppService service;
	public void chooseSystem(int selectNUmber) {
		switch(selectNUmber) {
		case 1: //좌석정보
			//service =  new MovieService();
			break;
		case 2: //열차표 예매하기
			//service =  new Reservation();
			break;
		case 3: //
			
			break;
		case 4:
			
		case 9:
			System.out.println("# 프로그램을 종료합니다.");
			System.exit(0);
		default:
			System.out.println("# 메뉴를 다시 입력해주세요.");
			
		}
		service.start();
	}

}
