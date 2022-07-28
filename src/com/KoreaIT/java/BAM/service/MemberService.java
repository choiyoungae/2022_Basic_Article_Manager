package com.KoreaIT.java.BAM.service;

import java.util.ArrayList;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dao.MemberDao;
import com.KoreaIT.java.BAM.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public int setNewId() {
		
		int id = memberDao.setNewId();
		
		return id;
	}

	public void add(Member member) {
		
		memberDao.add(member);
	}

	public Member getMemberByLoginId(String loginId) {
		
		Member member = memberDao.getMemberByLoginId(loginId);
		
		return member;
	}

	public boolean isJoinableLoginId(String loginId) {
		
		boolean isJoinableLoginId = memberDao.isJoinableLoginId(loginId);
		
		return isJoinableLoginId;
	}

	public ArrayList<Member> getMembers() {
		
		return memberDao.getMembers();
	}

}
