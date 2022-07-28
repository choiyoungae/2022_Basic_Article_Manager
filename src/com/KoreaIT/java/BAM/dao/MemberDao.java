package com.KoreaIT.java.BAM.dao;

import java.util.ArrayList;

import com.KoreaIT.java.BAM.dto.Member;

public class MemberDao extends Dao {
	public ArrayList<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
		lastId++;
	}
	
	public Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return null;
		}
		
		return members.get(index);
	}
	
	public boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return true;
		}
		
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		
		int i=0;
		
		for(Member member : members) {
			if(loginId.equals(member.loginId)) {
				return i;
			}
			i++;
		}
		
		return -1;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}
}
