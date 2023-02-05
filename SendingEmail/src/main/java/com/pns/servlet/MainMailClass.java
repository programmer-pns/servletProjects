package com.pns.servlet;

public class MainMailClass {
	static String TO,FROM,SUBJECT,BODY;
	public static void main(String[] args) {
//		TO = "bikashkumarbehera119@gmail.com";
		TO = "npriyabrata120@gmail.com";
		FROM = "npriyabrata23@gmail.com";
		SUBJECT = "Mail App Testing";
		BODY = "Hello";
		boolean b = new SendingMailAPP().send(TO,FROM,SUBJECT,BODY);
		if(b) {
			System.out.println("Mail sent successfully");
		}else {
			System.out.println("Mail sending unsuccessful");
		}
	}

}
