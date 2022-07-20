package com.KoreaIT.java.BAM.dto;

public class Member extends Dto {
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(String loginId, String loginPw, String name, int id, String regDate) {
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.id = id;
		this.regDate = regDate;
	}
}
