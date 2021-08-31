package com.test.housebook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.housebook.dao.BookDao;
import com.test.housebook.service.BookService;
import com.test.housebook.vo.Moneybook_tb;






@Controller
public class BookController {
	private static Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookDao dao;
	/**
	 * 검색
	 * @param searchItem
	 * @param searchWord
	 * @param model
	 * @return
	 */
	@RequestMapping("/listbook")
	public String listbook(
			@RequestParam(value="searchItem",defaultValue="entitle") String searchItem, 
			@RequestParam(value="searchWord",defaultValue="")  String searchWord,
			Model model) {
		
		logger.info("searchItem : {}", searchItem);
		logger.info("searchWord : {}", searchWord);
		Moneybook_tb moneybook_tb = new Moneybook_tb();
		moneybook_tb.setMoneybook_memo(searchWord);
		moneybook_tb.setMoneybook_type(searchItem);

	
		List<Moneybook_tb> list = dao.selectAll(moneybook_tb);
		
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("searchItem", searchItem);
		return "housebook/listBook"; // forwarding 
	}
	
	@RequestMapping("/writebook")
	public String writebook() {
		return "housebook/writeBook"; // forwarding 
	}
	
	@RequestMapping(value="/writebook", method=RequestMethod.POST)
	public String writebook(Moneybook_tb book) {
		
		System.out.println(book);
		
		int result = dao.insert(book);
		logger.info("가계부 등록 여부 {} : " + book);
		//int result = repository.insert(board);
		//logger.info("게시글 등록 여부 : {}", result);
		
		return "redirect:listbook";
	}
	/**
	 * 수정할 수 있는 화면 요청
	 * @param moneybook_no
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/updatebook")
	public String updatebook(int moneybook_no, Model model) {
		Moneybook_tb book = dao.selectOne(moneybook_no);
		model.addAttribute("book", book);
		
	
		return "housebook/updateBook";
	}	
	@RequestMapping(value = "/updatebook", method=RequestMethod.POST)
	public String updatebook(Moneybook_tb book) {
		int result = dao.updatebook(book);
		
		return "redirect:listbook";
	}	
	
	@RequestMapping("/deletebook")
	public String deletebook(int moneybook_no, HttpSession session) {
		logger.info("삭제할 글번호 : " + moneybook_no);
		
		String id = (String) session.getAttribute("loginId");
		Moneybook_tb book = new Moneybook_tb();
		book.setMoneybook_no(moneybook_no);
		book.setAcc_id(id);
		
		
		int result = dao.delete(moneybook_no);
		
		logger.info("게시글 삭제 여부 : {}", result);
		
		
		return "redirect:listbook?acc_id=" + id;
	}
	/**
	 * 최소 최대값 검색 
	 * @param mode
	 * @param searchItem
	 * @param searchWord
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/schMinOrmax" , method=RequestMethod.POST)
	public Map<String, String> schMinOrmax(String mode, String searchItem, String searchWord) {
		Map<String, String> map = new HashMap<>();
		Moneybook_tb moneybook_tb = new Moneybook_tb();
		moneybook_tb.setMoneybook_memo(searchWord);
		moneybook_tb.setMoneybook_type(searchItem);
		moneybook_tb.setMode(mode);
		
		int result = dao.minOrMax(moneybook_tb);
		map.put("result", Integer.toString(result));
		return map;
	}
	/**
	 * 총 수입, 총 지출
	 * @param mode
	 * @param searchItem
	 * @param searchWord
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/schTotalCaculate" , method=RequestMethod.POST)
	public Map<String, String> schTotalCaculate(String mode, String searchItem, String searchWord) {
		Map<String, String> map = new HashMap<>();
		Moneybook_tb moneybook_tb = new Moneybook_tb();
		moneybook_tb.setMoneybook_memo(searchWord);
		moneybook_tb.setMoneybook_type(searchItem);
		moneybook_tb.setMode(mode);
		
		int result = dao.totalCaculate(moneybook_tb);
		map.put("result", Integer.toString(result));
		return map;
	}

	
}
