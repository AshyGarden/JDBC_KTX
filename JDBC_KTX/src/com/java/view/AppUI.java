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
		System.out.println("### 1. 현재좌석정보 ");
		System.out.println("### 2. 좌석 예매하기 ");
		System.out.println("### 3. 예약한 좌석정보 ");
		System.out.println("### 4. 예매좌석취소 ");
		System.out.println("### 9. 예매 프로그램 종료하기 ");
		System.out.print(">>>");
	}

	// 현제좌석정보 화면 출력(1번)
	
	  public static void SeatsSituationScreen() {
	  System.out.println("\n========== 현재좌석정보 ==========");
	  System.out.println("### 1. 새마을"); 
	  System.out.println("### 2. 무궁화");
	  System.out.println("### 3. ITX"); 
	  System.out.println("### 4. KTX");
	  System.out.println("### 5. 모든 좌석의 정보");
	  System.out.println("### 9. 메인 페이지로 돌아가기");
	  System.out.println("--------------------------------------");
	  System.out.print(">>>"); }
	 
	// 좌석 예매 화면 출력
	
	  public static void orderManagementScreen() {
	  System.out.println("\n========= 대여 주문 관리 시스템 =========");
	  System.out.println("### 1. 영화 DVD 대여하기");
	  System.out.println("### 2. 영화 DVD 반납하기");
	  System.out.println("### 3. 첫 화면으로 가기");
	  System.out.println("----------------------------------------");
	  System.out.print(">>> "); }
	 

	// 영화 DVD 관리 시스템 화면 출력
	/*
	 * public static void movieManagementScreen() {
	 * System.out.println("\n========= 영화 DVD 관리 시스템 =========");
	 * System.out.println("### 1. 신규 영화 DVD 추가");
	 * System.out.println("### 2. 영화 DVD 정보 검색");
	 * System.out.println("### 3. 첫 화면으로 가기");
	 * System.out.println("----------------------------------------");
	 * System.out.print(">>> "); }
	 */

}
