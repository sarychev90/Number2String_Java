package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import services.NumbersDoubleToString;


public class Tezt {

	public static void main(String[] args) {

		String s = "12.20";
		NumbersDoubleToString nts = new NumbersDoubleToString();
		String textNumber = nts.digits2Text(s);
		System.out.println(textNumber);

		int n = s.length() - s.lastIndexOf('.');
		System.out.println(n);
		String[] sa = s.split("\\.");
		for(int i = 0; i<sa.length;i++) {
			System.out.println(sa[i]);	
		}	
	}
}
