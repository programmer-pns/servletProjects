package com.pns.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreDataInDB {
	
	Connection conn = null;
	
	public static void main(String[] args) {
		new StoreDataInDB().createBeanObjects();
	}

	private void createBeanObjects() {
		FileInputStream fis = null,fis2=null;
		BeanClass[] beanobj = new BeanClass[30];
		
		for(int i = 0;i<30;i++)
			beanobj[i] = new BeanClass();
		try {
			fis = new FileInputStream("C:\\Users\\npriy\\OneDrive\\Desktop\\StudentData.TXT");
			fis2 = new FileInputStream("C:\\Users\\npriy\\OneDrive\\Desktop\\StudentName.TXT");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fis!=null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
			String line = "";
			String name = "";
			int i = 0;
			try {
				while((line=br.readLine())!=null  && (name=br2.readLine())!=null) {
					String data[] = line.split(" ");
					int p = 1;
					//store the data from array to the object array
					
					beanobj[i].setRollno(Integer.parseInt(data[p++]));
					beanobj[i].setName(name);
					String grades[] = new String[8];
					for(int j=0; j<8;j++)
						grades[j] = data[p++];
					beanobj[i].setGrades(grades);
					beanobj[i].setSgpa(Float.parseFloat(data[p++]));
					beanobj[i].setCgpa(Float.parseFloat(data[p]));
										
					//incrementing student array
					i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			establishConnection();
			if(beanobj!=null && beanobj.length==30)
				storeData(beanobj);
		}
	}
	
	public void establishConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbuser = "SYSTEM";
		String dbpass = "TIGER";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			conn = DriverManager.getConnection(url,dbuser,dbpass);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void storeData(BeanClass beanobj[]) {
		String query = "INSERT INTO STUDENTs_RESULT(ROLL_NO,NAME,DM,DS,DBE,CSA,OS,DSLAB,DBELAB,OSLAB,SGPA,CGPA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";//name will be set manually
//		int i = 1;
		
		for(BeanClass bcobj:beanobj) {
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				int j = 1;
				ps.setLong(j++,bcobj.getRollno());
				ps.setString(j++, bcobj.getName());
				for(int p = 0;p<8;p++) {
					ps.setString(j++,bcobj.getGrades()[p]);//getGrades returns the array of grades
				}
				ps.setFloat(j++, bcobj.getSgpa());
				ps.setFloat(j, bcobj.getCgpa());
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Completed the insertion");
	}
}
