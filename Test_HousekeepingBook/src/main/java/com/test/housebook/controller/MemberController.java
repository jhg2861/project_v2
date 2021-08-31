package com.test.housebook.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.housebook.service.MemberService;
import com.test.housebook.vo.Account_tb;



@Controller
public class MemberController {

	@Autowired
	MemberService service;

	
	@RequestMapping("/join")
	public String join() {
		return "member/join"; // forwarding 
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Account_tb account) {
		
		int result = service.insert(account);
		
		if(result != 0) {
		return "redirect:/";
		
		};
		return "member/join";
	}
	// 로그인: 화면제공(GET)
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String acc_id, String acc_pw, HttpSession session) {
		System.out.println(acc_id+", " +acc_pw);
		
		Account_tb account = service.selectOne(acc_id, acc_pw);
		
		// member 존재하냐? 로그인할 수 있음 ==> HttpSession -> 넣기(set)/꺼내기(get)/지우기(remove, invalidate)
		if(account != null) {
			session.setAttribute("loginId",   account.getAcc_id());
			session.setAttribute("loginName", account.getAcc_pw());
			return "redirect:/";
		}
		// String loginId = (String)session.getAttribute("loginId");
		return "member/login";
	}	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	
}
