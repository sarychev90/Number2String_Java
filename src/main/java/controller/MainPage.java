package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import services.NumbersDoubleToString;

@Controller //анотация сервлета
@RequestMapping ("/MainPage")
public class MainPage {
	
	  //console.log('Time elapsed!', this.value);

	@RequestMapping (method=RequestMethod.GET)
	public String MainPageGet(HttpSession session, HttpServletResponse res) {
		return "mainpage"; //имя jsp
	}
	
	@RequestMapping (method=RequestMethod.POST)
	public String MainPagePost(@RequestParam("number") String number, HttpSession session, HttpServletResponse res) {
		NumbersDoubleToString nts = new NumbersDoubleToString();
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
			String textNumber= nts.digits2Text(number);
			String textNumberFine = null;
			try {
				textNumberFine = new String(textNumber.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(textNumberFine);
			out.close();
		
		return "mainpage"; //имя jsp
	}
}
