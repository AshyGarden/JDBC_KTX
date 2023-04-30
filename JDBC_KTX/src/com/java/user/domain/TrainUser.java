package com.java.user.domain;

public class TrainUser {

	private int userNum;         //유저번호-pk
	private String userId;       //유저 아이디
	private String userPw;       //유저 비번
	private int budget;          //유저 자산
	private int coupons;         //유저의 할인쿠폰 갯수(10% 할인)   
	private int rsvTrainTno;     //예매한 열차번호
	private String rsvTrainSeat; //예매한 열차의 좌석번호
	private String rsvTrainRev;  //예매한 좌석의 정방향여부
		
	public TrainUser() {}

	public TrainUser(int userNum, String userId, String userPw, int budget, int coupons, int rsvTrainTno,
			String rsvTrainSeat, String rsvTrainRev) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.userPw = userPw;
		this.budget = budget;
		this.coupons = coupons;
		this.rsvTrainTno = rsvTrainTno;
		this.rsvTrainSeat = rsvTrainSeat;
		this.rsvTrainRev = rsvTrainRev;
	}


	public int getUserNum() {
		return userNum;
	}


	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPw() {
		return userPw;
	}


	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}


	public int getBudget() {
		return budget;
	}


	public void setBudget(int budget) {
		this.budget = budget;
	}


	public int getCoupons() {
		return coupons;
	}


	public void setCoupons(int coupons) {
		this.coupons = coupons;
	}


	public int getRsvTrainTno() {
		return rsvTrainTno;
	}


	public void setRsvTrainTno(int rsvTrainTno) {
		this.rsvTrainTno = rsvTrainTno;
	}


	public String getRsvTrainSeat() {
		return rsvTrainSeat;
	}


	public void setRsvTrainSeat(String rsvTrainSeat) {
		this.rsvTrainSeat = rsvTrainSeat;
	}


	public String getRsvTrainRev() {
		return rsvTrainRev;
	}


	public void setRsvTrainRev(String rsvTrainRev) {
		this.rsvTrainRev = rsvTrainRev;
	}


	@Override
	public String toString() {
		return "## 회원번호=" + userNum + 
				", 아이디=" + userId + 
				", 에산=" + budget
				+ ", 10% 할인 쿠폰 개수=" + coupons + 
				", 예매한 열차번호=" + rsvTrainTno + 
				", 예매한 열차좌석번호=" + rsvTrainSeat
				+ ", 예매한 열차 좌석의 정방향 여부=" + rsvTrainRev;
	}
	
	
	


}
