package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController {

	private Scanner sc;
	private ArrayList<Member> members;
	
	public MemberController(Scanner sc, ArrayList<Member> members) {
		this.sc = sc;
		this.members = members;
	}


	public void doJoin() {
		System.out.println("ȸ�������� �����ϰڽ��ϴ�.");
		
		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();
		
		String loginId = null;

		while(true) {
			System.out.printf("���̵� : ");
			loginId = sc.nextLine();
			
			if(isJoinableLoginId(loginId) == false) {
				System.out.println("�̹� ������� ���̵��Դϴ�. �ٽ� �������ּ���.");
				continue;
			}
			
			break;
		}
		
		String loginPw = null;
		String loginPwConfrim = null;
		
		while(true) {					
			System.out.printf("��й�ȣ : ");
			loginPw = sc.nextLine();
			System.out.printf("��й�ȣ Ȯ�� : ");
			loginPwConfrim = sc.nextLine();
			
			if(!loginPwConfrim.equals(loginPw)) {
				System.out.println("��й�ȣ Ȯ�� �����Դϴ�. �ٽ� �������ּ���.");
				continue;
			}
			
			break;
		}
		
		System.out.printf("�̸� : ");
		String name = sc.nextLine();
		
		Member member = new Member(loginId, loginPw, name, id, regDate);
		members.add(member);
		System.out.printf("ȸ�������� �Ϸ�Ǿ����ϴ�. %s�� �ݰ����ϴ�.\n", member.name);
	}

	
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return true;
		}
		
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		
		for(Member member : members) {
			if(loginId.equals(member.loginId)) {
				return member.id;
			}
		}
		
		return -1;
	}
}
