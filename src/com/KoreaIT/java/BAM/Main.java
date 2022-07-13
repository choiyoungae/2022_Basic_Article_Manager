package com.KoreaIT.java.BAM;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf(">> ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("exit")) {
				System.out.println("프로그램이 종료됩니다.");
				break;
			}
		}
		
		System.out.println("==프로그램 끝==");
		sc.close();

	}

}
