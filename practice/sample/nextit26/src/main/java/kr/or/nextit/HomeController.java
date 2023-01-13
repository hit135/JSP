package kr.or.nextit;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	// sl4j
	// private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	// class를 다 지정해주기 귀찮으니
	// 밑에처럼 쓰면 됨,, static은 왜 빠질까
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// sl4j 안쓰고 바로 log4j2
	// apache.commons.logging.Log;
	// private static final Log logger = LogFactory.getLog(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/logger", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// sl4j
		// logger.info("Welcome home! The client locale is {}.", locale);
		logger.trace("(logger.trace) HomeController logger");
		logger.debug("(logger.debug) HomeController logger");
		logger.info("(logger.info)HomeController logger");
		logger.warn("(logger.warn)HomeController logger");
		logger.error("(logger.error)HomeController logger");
		
		// sl4j 안쓰고
		// logger.info("HomeController logger");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/tiles/test")
	public String tilesTest() {
		return "tiles.basic";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
