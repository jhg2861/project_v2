package net.scit.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {

	@RequestMapping("cookieSave")
	public String cookieSave(HttpServletResponse response) {
		// 쿠키 생성
		Cookie cookie1 = new Cookie("id", "abc");
		Cookie cookie2 = new Cookie("num", "123");
		
		// 클라이언트로 쿠키 보내기
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		return "index";
	}
	
	@RequestMapping("cookieDelete")
	public String cookieDelete(HttpServletResponse response) {
		// 쿠키 생성
		Cookie cookie1 = new Cookie("id", null);
		Cookie cookie2 = new Cookie("num", null);
		
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		
		// 클라이언트로 쿠키 보내기
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		return "index";
	}
	
	@RequestMapping("/cookieConsolePrint1")
	public String cookieConsolePrint1(HttpServletRequest request) {
		Cookie[] cookie = request.getCookies();
		
		for(Cookie c : cookie) {
			System.out.println("Key " + c.getName() +", Value : " + c.getValue());
		}
		return "redirect:/";
	}
	
	@RequestMapping("/cookieConsolePrint2")
	public String cookieConsolePrint2(
		@CookieValue(value="id", defaultValue = "none") String id,
		@CookieValue(value="num", defaultValue = "0") int num
		) {
			
	System.out.println("Key : " + id +", Value : " + num);
		
		return "redirect:/";
	}
}
