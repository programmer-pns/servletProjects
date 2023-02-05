package com.pns.servlet;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingMailAPP {
	public boolean send(String to,String from,String subject,String text){
		boolean flag = false;
		String username = "npriyabrata120";
		String password = "yfwwbrycojtdnnne";
		Properties prt = new Properties();
		prt.put("mail.smtp.auth", true);
		prt.put("mail.smtp.starttls.enable", true);
		prt.put("mail.smtp.port", "587");
		prt.put("mail.smtp.host", "smtp.gmail.com");
		
		
		Session s = Session.getInstance(prt,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		try {
			Message m = new MimeMessage(s);
			m.setFrom(new InternetAddress(from));
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(text);
			
			Transport.send(m);
			flag  = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
