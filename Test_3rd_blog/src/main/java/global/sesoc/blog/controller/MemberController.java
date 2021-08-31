package global.sesoc.blog.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import global.sesoc.blog.dao.MemberDAO;
import global.sesoc.blog.vo.MemberVO;

/**
 * 회원 관련 콘트롤러
 * 가입, 로그인, 로그아웃
 */
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberDAO dao;		//회원 관련 데이터 처리 객체
	
	/**
	 * 회원 가입 폼 보기
	 */
	@RequestMapping (value="join", method=RequestMethod.GET)
	public String joinForm() {
		return "joinForm";
	}

	/**
	 * 회원 가입 처리
	 * @param customer 가입폼에서 사용자가 입력한 정보가 VO객체로 전달
	 */
	@RequestMapping (value="join", method=RequestMethod.POST)
	public String join(MemberVO member) {
		int result = dao.insertMember(member);
		if (result != 1) {
			//DB저장에 실패한 경우 가입폼으로 이동
			return "joinForm";
		}
		//저장 성공한 경우 메인화면으로 이동
		return "redirect:/";
	}

	/**
	 * 로그인 폼 보기
	 */
	@RequestMapping (value="login", method=RequestMethod.GET)
	public String loginForm() {
		return "loginForm";
	}

	/**
	 * 로그인 처리
	 * @param id 사용자가 입력한 아이디
	 * @param password 사용자가 입력한 비밀번호
	 * @param model Model객체
	 * @param session HttpSession객체
	 */
	@RequestMapping (value="login", method=RequestMethod.POST)
	public String login(String id, String password, Model model, HttpSession session) {
		
		MemberVO member = dao.getMember(id);
		
		//ID와 비밀번호가 맞으면 세션에 ID와 회원이름을 저장하고 메인화면으로 이동
		if (member != null && member.getPassword().equals(password)) {
			session.setAttribute("loginId", member.getId());
			session.setAttribute("loginName", member.getName());
			return "redirect:/";
		}
		//맞지 않으면 로그인폼으로 이동
		else {
			return "loginForm";
		}
	}
	
	/**
	 * 로그아웃 처리
	 * @param session HttpSession객체
	 */
	@RequestMapping (value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	/**
	 * ID순으로 정렬된 전체 회원 목록 읽기
	 * @param model 모델 객체
	 * @return	모든회원의 정보가 담긴 리스트
	 */
	@RequestMapping (value="listMember", method=RequestMethod.GET)
	public String list(Model model) {
		//전체 회원 정보 목록 읽기
		ArrayList<MemberVO> memberlist = dao.listMember();
		//모델에 목록 저장하고 뷰로 이동
		model.addAttribute("memberlist", memberlist);
		
		return "listMember";
	}
	
}
