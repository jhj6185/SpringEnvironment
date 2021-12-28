package org.conan.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	// root로 들어온 요청을찾아서 화면에필요한 정보를 담아 home.jsp를 찾아 serverTime이라는 key값을 갖는
	//데이터를 보냄
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) // 그냥 루트는 무조건 home.jsp
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale); //console.log 자주하기
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate ); //setAttribute랑 유사한기능
		// model은 데이터를 담고있다
		return "home"; //view 이름
	}
	
}
