package controller;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import services.NumberProcessor;

@Controller
@RequestMapping ("/start")
public class MainPageController {
	
	private static final Logger LOGGER = Logger.getLogger(MainPageController.class.getName());
	
	@GetMapping
	public String goToMainPage(HttpSession session, HttpServletResponse res) {
		return "mainpage";
	}
	
	@PostMapping
	public String convertNumberToString(@RequestParam("number") String number, HttpSession session, HttpServletResponse res) {
		try (PrintWriter out = res.getWriter()){
			String textNumber= NumberProcessor.digits2Text(number);
			String textNumberFine = new String(textNumber.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
			LOGGER.log(Level.INFO, String.format("convertNumberToString: number=%s, textNumber=%s", number, textNumberFine));
			out.println(textNumberFine);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "convertNumberToString exception: " + e);
		}
		return "mainpage";
	}
}
