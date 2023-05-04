package com.java.user.service;

import com.java.Train.Trains.repository.TrainsRepository;
import com.java.Train.seats.domain.TrainSeats;
import com.java.Train.seats.repository.TrainSeatsRepository;
import com.java.user.domain.TrainUser;
import com.java.user.repository.TrainUserRepository;
import com.java.common.AppService;

import static com.java.view.AppUI.*;

import java.util.List;

public class TrainSeatReservationService implements AppService{
	private final TrainSeatsRepository tsRepository = new TrainSeatsRepository ();
	private final TrainUserRepository tuRepository = new TrainUserRepository ();
	private final TrainsRepository tRepository = new TrainsRepository ();
	
	public void start() {
		while(true) {
			SeatsReservationScreen();
			int selection = inputInteger();

			switch (selection) {
			case 1: // 회원의 예약 정보 조회
				showSearchResult();
				break;
			case 2: // 예약 하기
				TicketReservation();
				//ticketinglogin();
				break;
			case 9:
				return;

			default:
				System.out.println("메뉴를 다시 입력해주세용");
			}
			System.out.println("\n====== 계속 진행하시려면 ENTER를 누르세요");
			inputString();
		}
	}
	
	private List<TrainUser> searchUserId() {
		System.out.println("\n### 회원의 ID를 입력해 주세요.");
		System.out.print(">>> ");
		String id = inputString();
		List<TrainUser> user = tuRepository.findByUserID(id);
		TrainUser userId = user.get(0);
		if(userId.getUserId().equals(id)) {
			System.out.println("\n### 회원의 PW를 입력해 주세요.");
			System.out.print(">>> ");
			String pw = inputString();
			if (userId.getUserPw().equals(pw)) {
				return tuRepository.findByUserID(id);			
			} else {
				System.out.println("PW를 잘못 입력하셨습니다.");
			}
		} else {
			System.out.println("ID를 잘못 입력하셨습니다.");
		}
		return null;
	}

	private int showSearchResult() {
		List<TrainUser> userId = searchUserId();
		if(!userId.isEmpty()) {
			System.out.println("\n======================= 회원 조회 결과 =======================");
			for(TrainUser uId : userId) {
				System.out.println(uId);
			}
		} else {
			System.out.println("\n### 조회 결과가 없습니다.");
		}
		return userId.size();
	}
	
	private void TicketReservation() {
		System.out.println("# 예매를 진행합니다.");		
		System.out.println("# 예매하실 ID를 입력해주세요.");		
		System.out.print(">>> "); 
		String userId = inputString();
		
		List<TrainUser> tUser = tuRepository.findByUserID(userId);
		if(tUser.size() == 0) {			
			System.out.println("\n### id정보가 없습니다.");
		} else {
			for(TrainUser tu : tUser) {
				//System.out.println(tu);
				System.out.printf("환영합니다 %s님\n",tu.getUserId());
			}
			boolean flag = true;
			while(flag) {
				System.out.println("\n 예약하실 기차의 번호를 입력하세요(숫자를 넣지않으면 예매가 취소됩니다.)");
				System.out.println("\n 2001. 무궁화호 | 4001. 새마을호 | 6001. ITX | 8001. KTX ");
				System.out.print(">>> ");
				int selection = inputInteger();
				if(!(selection == 2001 ||selection ==4001||selection == 6001 ||selection == 8001)) {
					flag =false;
					break;
				}
				
				System.out.println("\n 예약하실 기차의 열을 입력해주세요(이외의 알파벳은 취소처리됩니다.)");
				System.out.println("\n A,B,C,D ");
				System.out.print(">>> ");
				String row = inputString();
				if(!(row.equals("A")||row.equals("B")||row.equals("C")||row.equals("D"))) {
					flag =false;
					break;
				}
				
				System.out.println("\n 예약하실 기차의 좌석방향를 입력하세요(이외의 키는 취소처리됩니다.)");
				System.out.println("\n 정방향 F | 역방향 R ");
				System.out.print(">>> ");
				String rev = inputString();		
				if(!(row.equals("F")||row.equals("R"))) {
					flag =false;
					break;
				}
				
				String seat = selection + row + rev;
				String beforePaySeatName = seat;
				
				System.out.printf(" 정말로 %s의 예매를 진행하시겠습니까?",seat);	
				System.out.println("\n예매 하시려면 Y를 입력해주세요.(이외의 키는 취소처리됩니다.)");
				System.out.print(">>> "); 
				String yesOrNo = inputString();
				
				switch(yesOrNo) {
				case "ㅛ": case "y": case "Y":
					int payingCost = tRepository.ticketPrice(selection);//지불금액
					tsRepository.bookingSeats(tUser,seat);//지불된 자리 예약하기	    2001CF
					tuRepository.payingMoney(tUser,payingCost,selection,row,rev);//열차표값 지불
					System.out.printf(" %s님의 %s열차의 %s좌석의 예매가 완료되었습니다.\n"
							,userId,tRepository.printTrainName(selection),beforePaySeatName);
					break;						
				default:
					System.out.println("# 예매를 그만둡니다.");
					return;			
				}				
			}
			
			if(!flag) {
				System.out.println("입력값이 잘못되어 예매가 취소되었습니다.");
			}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//예매
//	private void ticketinglogin() {
//		System.out.println("\n=============== 열차 예매 예약하기  ===============");
//		System.out.println("\n### 회원의 ID를 입력해 주세요.");
//		System.out.print(">>> ");
//		String id = inputString();
//		List<TrainUser> user = tuRepository.findByUserID(id);
//		TrainUser userId = user.get(0);
//		if(userId.getUserId().equals(id)) {
//			System.out.println("\n### 회원의 PW를 입력해 주세요.");
//			System.out.print(">>> ");
//			String pw = inputString();
//			if (userId.getUserPw().equals(pw)) {
//				System.out.println("로그인 성공");
//
//				ticketingTrain();
//
//			} else {
//				System.out.println("PW를 잘못 입력하셨습니다.");
//			}
//		} else {
//			System.out.println("ID를 잘못 입력하셨습니다.");
//		}
//	}
//	
	// 열차의 종류 선택
//	private void ticketingTrain() {
//		System.out.println("\n 예약하실 기차의 번호를 입력하세요");
//		System.out.println("\n 2001. 무궁화호 | 4001. 새마을호 | 6001. ITX | 8001. KTX ");
//		System.out.print(">>> ");
//		int selection = inputInteger();
//
//		switch (selection) {
//		case 2001:	case 4001: case 6001: case 8001:
//			System.out.println("%s호 기차의 예매를 시작합니다.");
//			tsRepository.bookingSeats(selection);
//			break;
//		}
//	}

	
}
