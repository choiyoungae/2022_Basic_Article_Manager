package com.KoreaIT.java.BAM.dto;

public class Member {
	public int id;
	public String regDate;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(String loginId, String loginPw, String name) {
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
}
