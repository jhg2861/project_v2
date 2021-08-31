package com.scit.shop.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scit.shop.service.MemberService;
import com.scit.shop.vo.Bookmembers;

@Controller
public class MemberController {

	// MemberService service = new MemberService();
	@Autowired
	MemberService service;
	
	// 회원가입 : 화면제공(GET), DB작업(POST)
	@RequestMapping("/join")
	public String join() {
		return "member/join"; // forwarding 
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Bookmembers member) {
		// DB에 저장하는 작업 필요
		int result = service.insert(member);
		
		return "redirect:/"; // GET방식으로 요청하도록 함!
	}
	
	// 로그인: 화면제공(GET)
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String userid, String userpwd, HttpSession session) {
		System.out.println(userid+", " +userpwd);
		
		Bookmembers member = service.selectOne(userid, userpwd);
		
		// member 존재하냐? 로그인할 수 있음 ==> HttpSession -> 넣기(set)/꺼내기(get)/지우기(remove, invalidate)
		if(member != null) {
			session.setAttribute("loginId",   member.getUserid());
			session.setAttribute("loginName", member.getUsername());
			return "redirect:/";
		}
		// String loginId = (String)session.getAttribute("loginId");
		return "member/login";
	}
	
	// 로그아웃 : HttpSession 에 들어있는 정보 삭제
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("/idCheck")
	public String idCheck(String userid) {
		
		Bookmembers member = service.selectOne(userid, null);
		System.out.println(member);
		// null이면 사용가능
		if(member == null) {
			return "success"; // ViewResolver x
		} else {
			return "fail";
		}
	}	
}
