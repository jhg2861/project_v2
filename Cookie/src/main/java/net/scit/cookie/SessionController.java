package net.scit.cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("value")
public class SessionController {
	
	@RequestMapping("/sessionAnnotation")
	public String sessionAnnotation(Model model) {
		model.addAttribute("value", "sessionAnnotation에서 저장한 값");
		
		return "index";
	}
	
	@RequestMapping("/sessionAnnotationDelete")
	public String sessionAnnotationDelete(
			@ModelAttribute("value") String value, 
			SessionStatus status) {
		System.out.println(value);
		status.setComplete();
		return "index";
	}
}
