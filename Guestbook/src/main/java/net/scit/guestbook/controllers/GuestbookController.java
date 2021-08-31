package net.scit.guestbook.controllers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.scit.guestbook.dao.GuestbookMapper;
import net.scit.guestbook.vo.Guestbook;

@Controller
public class GuestbookController {
	
	//DI
	@Autowired
	SqlSession session;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/* 방명록 글 전체 보기 */
	@RequestMapping("/list")
	public String list(Model model) {
		GuestbookMapper mapper = session.getMapper(GuestbookMapper.class);
		List<Guestbook> list = mapper.select();
		
		model.addAttribute("list",list);
		
		return "list";
	}
	
	/* 글 입력 화면 요청 */
	@RequestMapping("/write")
	public String writeForm( ) {
		return "writeForm";
	}
	
	/* 글 등록 요청 */
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(Guestbook guestbook) {
		
		GuestbookMapper mapper = session.getMapper(GuestbookMapper.class);
		int result = mapper.insert(guestbook);
		
		if(result == 1) {
			System.out.println("등록 완료");
		}
		
		return "redirect:list";
	}
}
