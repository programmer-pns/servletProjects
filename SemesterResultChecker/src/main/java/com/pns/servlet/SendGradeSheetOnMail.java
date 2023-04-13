package com.pns.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendGradeSheetOnMail {
	public boolean send(String to, String name){
		String subject = "Grade Sheet of 1st Semester";
		String text = "<h1>Here is your grade sheet---->"+name+"</h1>";
		File gradeSheet = new File("P:\\Eclipse-Workspaces\\servlet-workspace-eclipse\\SemesterResultChecker\\src\\main\\webapp\\studentData"+"\\javaWritten.pdf");
		boolean flag = false;
		String username = "npriyabrata120";
		String password = "yfwwbrycojtdnnne";
		Properties prt = new Properties();
		prt.put("mail.smtp.auth", true);
		prt.put("mail.smtp.starttls.enable", true);
		prt.put("mail.smtp.port", "587");
		prt.put("mail.smtp.host", "smtp.gmail.com");
		
		MimeMultipart mmp = new MimeMultipart();
		MimeBodyPart textMbp = new MimeBodyPart();
		MimeBodyPart pdfMbp = new MimeBodyPart();
		try {
			textMbp.setContent(text,"text/html");
			mmp.addBodyPart(textMbp);
			pdfMbp.attachFile(gradeSheet);
			mmp.addBodyPart(pdfMbp);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session s = Session.getInstance(prt,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		try {
			Message m = new MimeMessage(s);
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setContent(mmp);
			
			Transport.send(m);
			flag  = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
