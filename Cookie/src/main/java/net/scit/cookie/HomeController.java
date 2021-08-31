package net.scit.cookie;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "index";
	}
	
	@RequestMapping(value = "/sessionSave", method = RequestMethod.GET)
	public String sessionSave(HttpSession session) {
		session.setAttribute("value", "session에 저장한 정보");
		
		return "index";
	}
	
	@RequestMapping(value = "/sessionDelete", method = RequestMethod.GET)
	public String sessionDelete(HttpSession session) {
		String value = (String) session.getAttribute("value");
		System.out.println(value);
		
		// session.invalidate(); <<한번에 다 지울때 removeAttribute();는 선택적 삭제할때
		session.removeAttribute("value"); // Spring Security
		return "index";
	}
	
}
