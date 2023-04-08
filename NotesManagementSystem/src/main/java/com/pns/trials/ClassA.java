package com.pns.trials;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.pns.javabean.NewUserData;

public class ClassA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClassA().meth1();
	}

	private void meth1() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("P:/NBMS/Objects/beanobject1.ser"));
			NewUserData nud2 = (NewUserData)ois.readObject();
			System.out.println(nud2.getEmail());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
