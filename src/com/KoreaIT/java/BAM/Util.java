package com.KoreaIT.java.BAM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	// ���� ��¥�� �ð� ����
	public static String getNowDateStr() {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date now = new Date();
		
		return sdf1.format(now);
			 
	}
}
