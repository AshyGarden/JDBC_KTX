package com.java.user.service;

import com.java.common.AppService;
import com.java.user.domain.TrainUser;
import com.java.user.repository.TrainUserRepository;

import static com.java.view.AppUI.*;

import java.util.List;

import com.java.Train.Trains.domain.Train;
import com.java.Train.Trains.repository.TrainsRepository;
import com.java.Train.seats.repository.TrainSeatsRepository;

public class TrainCancellationService implements AppService{
	
	TrainUserRepository tuRepository = new TrainUserRepository();
	TrainsRepository tRepository = new TrainsRepository();
	TrainSeatsRepository tsRepository = new TrainSeatsRepository();
	@Override
	public void start() {
		while(true){
			SeatsCancelationScreen();
			int selection = inputInteger();		
			switch(selection) {
			case 1:
				TicketCancellation();
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

	private void TicketCancellation() {
		System.out.println("# 예매 취소를 진행합니다.");		
		System.out.println("# 예매하신 ID를 입력해주세요.");		
		System.out.print(">>> "); 
		String userId = inputString();
		
		List<TrainUser> tUser = tuRepository.isUserBooked(userId);
		if(tUser.size() > 0) {
			for(TrainUser tu : tUser) {
				System.out.println(tu);
			}
			
			String tf ="";			
			if(tUser.get(0).getRsvTrainRev().equals("true")) {
				tf = "T";
			} else {
				tf = "F"; 
			}		
			String seat = tUser.get(0).getRsvTrainTno()
					+tUser.get(0).getRsvTrainSeat()
					+tf;
			String beforeRefundSeatName = seat;
			String tName = tRepository.printTrainName(tUser.get(0).getRsvTrainTno());
			
			System.out.printf(" %d건의 예매 정보가 있습니다. "
					+ "정말로 %s의 예매를 취소하시겠습니까?",tUser.size(),seat);	
			System.out.println("\n취소 하시려면 Y를 입력해주세요.(이외의 키는 취소처리됩니다.)");
			System.out.print(">>> "); 
			String yesOrNo = inputString();
			
			switch(yesOrNo) {
			case "ㅛ": case "y": case "Y":
				int refundCost = tRepository.ticketPrice(tUser.get(0).getRsvTrainTno());//환불금액
				tsRepository.rollbackcSeats(tUser,seat);//환불된 자리 돌려놓기	    
				tuRepository.refundMoney(tUser,refundCost);//열차표값 환불
			    System.out.printf(" %s님의 %s열차의 %s좌석의 예매가 취소되었습니다.\n"
			    		,tUser.get(0).getUserId(),tName,beforeRefundSeatName);
				break;	
				
			default:
				System.out.println("# 예매취소를 그만둡니다.");
				return;
			}			
		} else {
			System.out.println("\n### 예약정보가 없습니다.");
		}
		
	}

}
