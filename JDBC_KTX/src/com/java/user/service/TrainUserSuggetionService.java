package com.java.user.service;

import com.java.common.AppService;
import com.java.user.domain.TrainUserSuggestion;
import com.java.user.repository.TrainUserSugesstionRepository;

import static com.java.view.AppUI.*;

import java.util.ArrayList;
import java.util.List;
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
				SearchAll();
				break;
			case 2: //2. 건의사항 ID로 조회하기
				SearchByID();
				break;
			case 3: //3. 건의사항게시판 번호로 조회하기
				SearchBySBoardNum();
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
	
	private void SearchAll() {
		List<TrainUserSuggestion>  tusList= tusRepository.SearchSuggestion();
		System.out.println(tusList.size());
		//System.out.println(tusList.size());
		if(tusList.size()>0) {
			for(TrainUserSuggestion tusg: tusList) {
				System.out.println(tusg);
			}		
		} else {
			System.out.println("건의게시판이 비어있습니다.");
		}		
	}

	private void SearchByID() {	
		System.out.println("### 검색할 아이디를 입력해주세요.");
		System.out.print(">>> "); 
		String inputID = inputString();								
		List<TrainUserSuggestion> tusIDList= tusRepository.SearchSuggestion(inputID);
		
		//System.out.println(tusIDList.size());
		if(tusIDList.size()>0) {
			for(TrainUserSuggestion tusg: tusIDList) {
				System.out.println(tusg);
			}		
		} else {
			System.out.println("해당 id로 저장된 건의사항은 없습니다.");
		}
	}
	
	private void SearchBySBoardNum() {
		System.out.println("### 검색할 건의글 번호를 입력해주세요.");
		System.out.print(">>> "); 
		int inputNum = inputInteger();								
		List<TrainUserSuggestion> tusNumList= tusRepository.SearchSuggestion(inputNum);
		
		//System.out.println(tusIDList.size());
		if(tusNumList.size() == 1) {
			for(TrainUserSuggestion tusg: tusNumList) {
				System.out.println(tusg);
			}		
		} else {
			System.out.println("해당 건의글 번호로 저장된 건의사항은 없습니다.");
		}	
	}

}
