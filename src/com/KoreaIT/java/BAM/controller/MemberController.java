package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private ArrayList<Member> members;
	private String cmd;
	private String actionMethodName;
	private Member loginedMember;

	public Member getLoginedMember() {
		return loginedMember;
	}
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		this.loginedMember = null;
		
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
		case "profile":
			showProfile();
			break;
		default:
			System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			break;
		}
	}

	private void showProfile() {
		if(loginedMember == null) {
			System.out.println("�α׾ƿ� �����Դϴ�.");
			
		} else {
			System.out.println("== ���� �α��� �� ȸ�� ���� ==");
			System.out.printf("�α��� ���̵� : %s\n", loginedMember.loginId);
			System.out.printf("�̸� : %s\n", loginedMember.name);
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
		
		if(loginedMember != null) {
			System.out.println("�̹� �α��εǾ��ֽ��ϴ�.");
			return;
		}
		
		System.out.println("�α����� �����ϰڽ��ϴ�.");
		
		while(true) {			
			System.out.printf("���̵� : ");
			String loginId = sc.nextLine();
			System.out.printf("��й�ȣ : ");
			String loginPw = sc.nextLine();
			
			Member member = getMemberByLoginId(loginId);
			
			if(member == null) {
				System.out.println("�Է��Ͻ� ���̵� �������� �ʽ��ϴ�.");
				return;
			}
			
			if(loginPw.equals(member.loginPw)) {
				loginedMember = member;
				System.out.printf("%s��, �ݰ����ϴ�.\n", loginedMember.name);
				break;
				
			} else {
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				return;
			}
			
//			for(Member member : members) {
//				if(member.loginId.equals(loginedId)) {
//					if(member.loginPw.equals(loginedPw)) {
//						System.out.printf("%s�� �ݰ����ϴ�.\n", member.name);
//						loginedMember = member;
//						break;
//					}
//				}
//			}
//			
//			if(loginedMember == null) {
//				System.out.println("���̵� Ȥ�� ��й�ȣ�� ���� �ʽ��ϴ�. �ٽ� �õ��Ͻʽÿ�.");
//			} else {
//				break;
//			}
		}
		
	}
	
	
	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return null;
		}
		
		return members.get(index-1);
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
	
	public void makeTestData() {
		String regDate = Util.getNowDateStr();
		
		members.add(new Member("test1", "test1", "test1", 1, regDate));
		members.add(new Member("test2", "test2", "test2", 2, regDate));
		members.add(new Member("test3", "test3", "test3", 3, regDate));			
	
		System.out.println("�׽�Ʈ�� �����͸� �����߽��ϴ�.");
	}
}
