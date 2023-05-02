package com.java.user.service;

import com.java.common.AppService;
import com.java.user.domain.TrainUserSuggestion;
import com.java.user.repository.TrainUserSugesstionRepository;

import static com.java.view.AppUI.*;
public class TrainUserSuggetionService implements AppService {
	
	TrainUserSugesstionRepository tusRepository = new TrainUserSugesstionRepository();
	@Override
	public void start() {
		while(true){		
			SuggestionScreen();
			int selection = inputInteger();
			
			switch(selection) {
			case 1: //1. 건의사항 작성하기
				WritingSuggestion();
				break;
			case 2: //2. 건의사항 조회하기
				SearchingSuggestion();
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

	private void WritingSuggestion() {
		System.out.println("\n====== 건의사항을 추가합니다. ======");
		System.out.print("# 건의자 ID: ");
		String userID = inputString();
		
		System.out.print("# 건의사항: ");
		String suggestion = inputString();
		
		
		TrainUserSuggestion tus = new TrainUserSuggestion();
		tus.setUserId(userID);
		tus.setSuggestions(suggestion);
		
		tusRepository.addSuggestion(tus);
	}
	
	private void SearchingSuggestion() {
		while(true){	
			System.out.println("\n========= 건의사항 조회하기 =========");
			System.out.println("### 1. 전체 조회하기");
			System.out.println("### 2. ID로 조회하기");
			System.out.println("### 3. 건의게시판 번호로 조회하기");
			System.out.println("### 9. 건의사항 페이지로 돌아가기");
			System.out.println("----------------------------------------");
			System.out.print(">>> "); 
			int selection = inputInteger();			
			switch(selection) {
			case 1: //1. 건의사항 전체 조회하기
				tusRepository.searchingSuggestion();
				break;
			case 2: //2. 건의사항 ID로 조회하기
				searchByID();
				break;
			case 3: //3. 건의사항게시판 번호로 조회하기
				
				break;
			case 9:
				return; //건의사항 페이지으로 돌아가기		
				
			default:
				System.out.println("# 메뉴를 다시 입력하세요.");			
			}
			System.out.println("\n======계속 진행하시려면 ENTER를 누르세요======");
			inputString();
		}	
	}

	private void searchByID() {
		//searchingSuggestion();
	}
	

}
