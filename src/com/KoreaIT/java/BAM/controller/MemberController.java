package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private ArrayList<Member> members;
	private String cmd;
	private String actionMethodName;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		
		members = new ArrayList<Member>();
	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		default:
			System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			break;
		}
	}

	private void doJoin() {
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
	
	private void doLogin() {
		System.out.println("�α����� �����ϰڽ��ϴ�.");
		
		while(true) {			
			System.out.printf("���̵� : ");
			String loginedId = sc.nextLine();
			System.out.printf("��й�ȣ : ");
			String loginedPw = sc.nextLine();
			
			boolean isLoginSuccess = false;
			
			for(Member member : members) {
				if(member.loginId.equals(loginedId)) {
					if(member.loginPw.equals(loginedPw)) {
						System.out.printf("%s�� �ݰ����ϴ�.\n", member.name);
						isLoginSuccess = true;
						break;
					}
				}
			}
			
			if(isLoginSuccess == false) {
				System.out.println("���̵� Ȥ�� ��й�ȣ�� ���� �ʽ��ϴ�. �ٽ� �õ��Ͻʽÿ�.");
			} else {
				break;
			}
		}
		
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
