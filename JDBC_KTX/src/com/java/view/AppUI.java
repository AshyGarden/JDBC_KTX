package com.java.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppUI {

	private static Scanner sc = new Scanner(System.in);

	public static String inputString() {
		return sc.nextLine();
	}

	public static int inputInteger() {
		int num = 0;
		try {
			num = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("정수로 입력해 주세요.");
		} finally {
			sc.nextLine();
		}

		return num;
	}

	// 시작화면 출력(Main)
	public static void startScreen() {
		System.out.println("\n========== KTX 예매 관리 시스템 ==========");
		System.out.println("### 1. 현재열차정보 ");
		System.out.println("### 2. 현재좌석정보 ");		
		System.out.println("### 3. 좌석 예매하기 & 예매한 좌석정보 보기 ");
		
		System.out.println("### 4. 예매좌석취소 ");		
		
		System.out.println("### 7. 예매 프로그램 건의사항 작성하기");	
		System.out.println("### 9. 예매 프로그램 종료하기 ");
		
		//System.out.println("### 99. 달리는 기차 감상하기");
		System.out.print(">>>");
	}

	// 현제좌석정보 화면 출력(1번)
	public static void TrainSituationScreen() {
		System.out.println("\n========== 현재열차운행정보 ==========");
		System.out.println("### 1000. 모든 열차의 정보");
		System.out.println("### 2001. 새마을호"); 
		System.out.println("### 4001. 무궁화호");	  
		System.out.println("### 6001. ITX"); 
		System.out.println("### 8001. KTX");
		System.out.println("### 9. 메인 페이지로 돌아가기");
		System.out.println("--------------------------------------");
		System.out.print(">>>"); 
	}
	 
	// 좌석 예매 화면 출력(2번)	
	public static void TrainSeatsSituationScreen() {
		System.out.println("\n========== 현재열차좌석정보 ==========");
		System.out.println("### 1000. 모든 열차의 좌석 정보");
		System.out.println("### 2001. 새마을호 좌석"); 
		System.out.println("### 4001. 무궁화호 좌석");
		System.out.println("### 6001. ITX 좌석"); 
		System.out.println("### 8001. KTX 좌석");
		System.out.println("### 9. 메인 페이지로 돌아가기");
		System.out.println("--------------------------------------");
		System.out.print(">>>"); 
	}
	 

	// 3. 좌석 예매하기 SELECT&INSERT
	public static void SeatsReservationScreen() {
		System.out.println("\n========= 좌석 예매하기 =========");
		System.out.println("### 1. 현재 회원의 예약정보확인");
		System.out.println("### 2. 예약하기");
		System.out.println("### 9. 메인 페이지로 돌아가기");
		System.out.println("----------------------------------------");
		System.out.print(">>> "); 
	}
		
		
	// 예매좌석 확인하기(4번) SELECT
	public static void SeatsCheckScreen() {
		System.out.println("\n========= 좌석 확인하기 =========");
		System.out.println("### 1. 좌석번호로로 확인하기");
		System.out.println("### 2. id로 확인하기");
		System.out.println("### 9. 메인 페이지로 돌아가기");
		System.out.println("----------------------------------------");
		System.out.print(">>> "); 
	}
		
	// 좌석 취소하기(5번) - update & delete
	public static void SeatsCancelationScreen() {
		System.out.println("### 1. 예매 취소하기");
		System.out.println("### 9. 메인 페이지로 돌아가기");
		System.out.println("----------------------------------------");
		System.out.print(">>> "); 
	}
		
	//건의사항 (7번) - insert
	public static void SuggestionScreen() {
		System.out.println("\n========= 예매 프로그램 건의사항 작성하기 =========");
		System.out.println("### 1. 건의사항 작성하기");
		System.out.println("### 2. 건의사항 조회하기");
		System.out.println("### 9. 메인 페이지로 돌아가기");
		System.out.println("----------------------------------------");
		System.out.print(">>> "); 
	}
}
