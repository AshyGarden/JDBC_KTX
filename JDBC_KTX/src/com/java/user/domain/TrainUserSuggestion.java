package com.java.user.domain;

public class TrainUserSuggestion {

	private int suggestNum;
	private String userId;
	private String suggestions;
	
	public TrainUserSuggestion() {}

	public TrainUserSuggestion(int suggestNum, String userId, String suggestions) {
		super();
		this.suggestNum = suggestNum;
		this.userId = userId;
		this.suggestions = suggestions;
	}

	public int getSuggestNum() {
		return suggestNum;
	}

	public void setSuggestNum(int suggestNum) {
		this.suggestNum = suggestNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	@Override
	public String toString() {
		return "건의사항 번호: " + suggestNum 
				+ "\n작성자명: " + userId 
				+ "\n건의사항 : " + suggestions
				+ "\n";
	}
	
	
}
