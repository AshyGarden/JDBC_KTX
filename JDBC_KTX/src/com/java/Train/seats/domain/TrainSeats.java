package com.java.Train.seats.domain;

public class TrainSeats {
	private String  trainSeat;      //예매한 열차의 좌석번호
    private int trainNo;            //열차번호 
    private String trainCol;        //열차의 열
    private String trainRev;        //예매한 좌석의 정방향여부 
    private String trainIsrsv;      //좌석의 예매 여부
    
    public TrainSeats() {}

    public TrainSeats(String trainSeat, int trainNo, String trainCol, String trainRev, String trainIsrsv) {
		super();
		this.trainSeat = trainSeat;
		this.trainNo = trainNo;
		this.trainCol = trainCol;
		this.trainRev = trainRev;
		this.trainIsrsv = trainIsrsv;
	}

	public int getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainSeat() {
        return trainSeat;
    }

    public void setTrainSeat(String trainSeat) {
        this.trainSeat = trainSeat;
    }

    public String getTrainRev() {
        return trainRev;
    }

    public void setTrainRev(String trainRev) {
        this.trainRev = trainRev;
    }

    public String getTrainIsrsv() {
        return trainIsrsv;
    }

    public void setTrainIsrsv(String trainIsrsv) {
        this.trainIsrsv = trainIsrsv;
    }

	@Override
	public String toString() {
//		return "열차좌석정보 <열차좌석번호: " + trainSeat 
//				+ ", 열차번호: " + trainNo 
//				+ ", 탑승열차의 열: " + trainCol 
//				+ ", 정방향 좌석여부: "+ trainRev 
//				+ ", 예약여부: " + trainIsrsv + ">";
		
		//열차번호
		String tName ="";		
		switch(trainNo) {
			case 2001: tName = "무궁화호"; break; case 4001: tName = "새마을호"; break;
			case 6001: tName = "ITX"; break;    case 8001: tName = "KTX"; break;
			default : tName = "운행정보 없음"; break;
		}
		//정방향 좌석여부
		String frontRear= "";
		if(trainRev.equals("true")) frontRear = "정방향";
		else frontRear = "역방향";
		//예약여부
		String isRsv= "";
		if(trainIsrsv.equals("true")) isRsv= "예약불가";
		else isRsv= "예약가능";
		
		return "<열차좌석번호: " + trainSeat 
				+ ", 열차종류: " + tName 
				+ ", 탑승열차의 열: " + trainCol 
				+ ", 정방향 좌석여부: "+ frontRear
				+ ", 예약여부: " + isRsv + ">";
	} 

}
