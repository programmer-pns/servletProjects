package com.pns.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class CreateGradeSheetPDF {
	public boolean sendMail(BeanClass beanobj) {
		boolean sentMail = true;
		String filepath = "P:\\Eclipse-Workspaces\\servlet-workspace-eclipse\\SemesterResultChecker\\src\\main\\webapp\\javaWritten.pdf";
		String imagePath = "P:\\Eclipse-Workspaces\\servlet-workspace-eclipse\\SemesterResultChecker\\src\\main\\webapp\\Logo_vssut.png";
		PDDocument pdd = new PDDocument();
		PDPage page = new PDPage();
		
		pdd.addPage(page);
		
		PDFont headingFont = PDType1Font.TIMES_ROMAN;
		PDFont bodyFont = PDType1Font.COURIER;
		
		int headSize = 16;
		int bodyFontSize = 14;
		int colonSize = 20;
		int gap = 25;
		int tableRow = 490;
		
		String subjectCode[] = {"MCA2103","MCA2106","MCA2107","MCA2108","MCA2109","MCA2195","MCA2197","MCA2198"};
		String grades[] = beanobj.getGrades();
		String subjectName[] = {"DISCRETE MATHEMATICS","DATA STRUCTURES USING C","DATABASE ENGINEERING","COMPUTER SYSTEM ARCHITECTURE","OPERATING SYSTEM","DATA STRUCTURES USING C LAB","DATABASE ENGINEERING LAB","OPERATING SYSTEM LAB"};
		float CGPA = beanobj.getCgpa(),SGPA = beanobj.getSgpa();
		String status = "PASS";
		if(grades.toString().contains("F"))
			status = "FAIL";
		String DATE_OF_ISSUE = "06-04-2023";
				
		String title = "VEER SURENDRA SAI UNIVERSITY OF TECHNOLOGY";
		
		
		//creating a content stream to write the data to the pdf
		PDPageContentStream pdpcs = null;
		try {
			pdpcs = new PDPageContentStream(pdd,page);
			
			//vssut name
			pdpcs.beginText();
			pdpcs.setFont(headingFont, headSize);
			pdpcs.newLineAtOffset(120,750);
			pdpcs.showText(title);
			pdpcs.endText();
			
			//location
			pdpcs.beginText();
			pdpcs.setFont(headingFont, headSize);
			pdpcs.newLineAtOffset(240,730);
			pdpcs.showText("ODISHA, BURLA");
			pdpcs.endText();
			
			//(Formerly University College of Engineering,Burla)
			pdpcs.beginText();
			pdpcs.setFont(PDType1Font.TIMES_ITALIC, 14);
			pdpcs.newLineAtOffset(150,710);
			pdpcs.showText("(Formerly University College of Engineering, Burla)");
			pdpcs.endText();
			
			//GRADE SHEET
			pdpcs.beginText();
			pdpcs.setFont(headingFont, headSize);
			pdpcs.newLineAtOffset(240,670);
			pdpcs.showText("GRADE SHEET");
			pdpcs.endText();
			
			//VSSUT LOGO
			pdpcs.drawImage(PDImageXObject.createFromFile(imagePath, pdd), 50, 650 , 100, 100);
			
			//student informations
			
			//registration number
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(63,620);
			pdpcs.showText("Registration No.".toUpperCase());
			pdpcs.endText(); 
			
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, colonSize);
			pdpcs.newLineAtOffset(210,620);
			pdpcs.showText(":");
			pdpcs.endText();
			
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(230,620);
			pdpcs.showText(Long.toString(beanobj.getRollno()));
			pdpcs.endText(); 
			
			//name
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(63,600);
			pdpcs.showText("Name".toUpperCase());
			pdpcs.endText(); 
			
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, colonSize);
			pdpcs.newLineAtOffset(210,600);
			pdpcs.showText(":");
			pdpcs.endText();
			
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(230,600);
			pdpcs.showText(beanobj.getName().toUpperCase());
			pdpcs.endText();
			
			//Branch
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(63,580);
			pdpcs.showText("Branch".toUpperCase());
			pdpcs.endText(); 
			
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, colonSize);
			pdpcs.newLineAtOffset(210,580);
			pdpcs.showText(":");
			pdpcs.endText();
			
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(230,580);
			pdpcs.showText("Master in computer Application".toUpperCase());
			pdpcs.endText();
			
			//drawing a line
			pdpcs.moveTo(63, 560);
			pdpcs.lineTo(page.getMediaBox().getWidth()-63, 560);
			pdpcs.stroke();
			
			/**------------setting the table heading------------**/
			//semester 1st
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(63,545);
			pdpcs.showText("Semester : 1st");
			pdpcs.endText(); 
			
			//subject code
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(63,525);
			pdpcs.showText("Sub. Code");
			pdpcs.endText(); 
			
			//Subjects Registered
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(160,525);
			pdpcs.showText("Subjects Registered");
			pdpcs.endText();
			
			//Credit
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(430,525);
			pdpcs.showText("Credit");
			pdpcs.endText();
			
			//Grade
			pdpcs.beginText();
			pdpcs.setFont(bodyFont, bodyFontSize);
			pdpcs.newLineAtOffset(500,525);
			pdpcs.showText("Grade");
			pdpcs.endText();
			
			
			//drawing end line
			pdpcs.moveTo(63, 520);
			pdpcs.lineTo(page.getMediaBox().getWidth()-63, 520);
			pdpcs.stroke();
			
			
			/**--------Setting the table body------------**/
			
			for(int i = 0;i<8;i++) {
				//MCA2013,....
				pdpcs.beginText();
				pdpcs.setFont(bodyFont, bodyFontSize);
				pdpcs.newLineAtOffset(63,tableRow);
				pdpcs.showText(subjectCode[i].toUpperCase());
				pdpcs.endText(); 
				
				//DISCRETE MATHEMATICS,....
				pdpcs.beginText();
				pdpcs.setFont(bodyFont, bodyFontSize);
				pdpcs.newLineAtOffset(160,tableRow);
				pdpcs.showText(subjectName[i].toUpperCase());
				pdpcs.endText();
				
				//3.00,....
				pdpcs.beginText();
				pdpcs.setFont(bodyFont, bodyFontSize);
				pdpcs.newLineAtOffset(440,tableRow);
				if(i<=4)
					pdpcs.showText("3.00");
				else
					pdpcs.showText("2.00");
				pdpcs.endText();
				
				//A+,....
				pdpcs.beginText();
				pdpcs.setFont(bodyFont, bodyFontSize);
				pdpcs.newLineAtOffset(510,tableRow);
				pdpcs.showText(grades[i].toUpperCase());
				pdpcs.endText();
				
				//providing space to table row
				tableRow -= gap;
			
			}
			
			//end of table line
			pdpcs.moveTo(63, tableRow-10);
			pdpcs.lineTo(page.getMediaBox().getWidth()-63, tableRow-10);
			pdpcs.stroke();
			
			tableRow-=10;
			
			
			/**
			 Down to table part
			 **/
			//SGPA
			pdpcs.beginText();
			pdpcs.newLineAtOffset(63, tableRow-gap);
			pdpcs.setFont(PDType1Font.COURIER_BOLD, bodyFontSize);
			pdpcs.showText("SGPA(1st) :");
			pdpcs.endText();
			
			//9.86
			pdpcs.beginText();
			pdpcs.newLineAtOffset(200, tableRow-gap);
			pdpcs.setFont(PDType1Font.COURIER_BOLD, bodyFontSize);
//			pdpcs.showText(Float.toString(SGPA));
			pdpcs.showText(""+SGPA);
			pdpcs.endText();
			
			//CGPA
			pdpcs.beginText();
			pdpcs.newLineAtOffset(370, tableRow-gap);
			pdpcs.setFont(PDType1Font.COURIER_BOLD, bodyFontSize);
			pdpcs.showText("CGPA :");
			pdpcs.endText();
			
			//9.86
			pdpcs.beginText();
			pdpcs.newLineAtOffset(440, tableRow-gap);
			pdpcs.setFont(PDType1Font.COURIER_BOLD, bodyFontSize);
//			pdpcs.showText(Float.toString(CGPA));
			pdpcs.showText(""+CGPA);
			pdpcs.endText();
			
			tableRow-=gap;
			
			//Promotional Status
			pdpcs.beginText();
			pdpcs.newLineAtOffset(150, tableRow-gap);
			pdpcs.setFont(PDType1Font.COURIER_BOLD, bodyFontSize);
			pdpcs.showText("PROMOTIONAL STATUS :");
			pdpcs.endText();
			
			//PASS
			pdpcs.beginText();
			pdpcs.newLineAtOffset(320, tableRow-gap);
			pdpcs.setFont(PDType1Font.COURIER_BOLD, bodyFontSize);
			pdpcs.showText("( "+status.toUpperCase()+" )");
			pdpcs.endText();
			
			
			//Date of issue
			pdpcs.beginText();
			pdpcs.newLineAtOffset(63, 30);
			pdpcs.setFont(PDType1Font.COURIER_BOLD, bodyFontSize-3);
			pdpcs.showText("Date-of-Issue : "+DATE_OF_ISSUE);
			pdpcs.endText();
			
			
			
			//closing the stream and saving the document
			pdpcs.close();
			pdd.save(filepath);			
			
			//adding watermark
			HashMap<Integer, String> overlayGuide = new HashMap<Integer, String>();
	        for(int i=0; i<pdd.getNumberOfPages(); i++){
	            overlayGuide.put(i+1, "P:\\Eclipse-Workspaces\\servlet-workspace-eclipse\\SemesterResultChecker\\src\\main\\webapp\\vssut_logo_watermark.pdf");
	        }
	        Overlay overlay = new Overlay();
	        overlay.setInputPDF(pdd);
	        overlay.setOverlayPosition(Overlay.Position.BACKGROUND);
	        overlay.overlay(overlayGuide);
	        pdd.save(new File("P:\\Eclipse-Workspaces\\servlet-workspace-eclipse\\SemesterResultChecker\\src\\main\\webapp\\javaWritten.pdf"));
	        pdd.close();
		} catch (IOException e) {
			sentMail = false;
			try {
				pdd.close();
				pdpcs.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		if(sentMail)
			sentMail = new SendGradeSheetOnMail().send(beanobj.getMail(), beanobj.getName());
		return sentMail;
	}

}
