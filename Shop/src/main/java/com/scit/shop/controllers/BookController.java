package com.scit.shop.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scit.shop.service.BookService;
import com.scit.shop.vo.Book;
import com.scit.shop.vo.Wishlist;

@Controller
public class BookController {

	@Autowired
	BookService service;
	
	// 상품목록
	@RequestMapping("/booklist")
	public String bookList(Model model) {
	
		List<Book> list = service.bookList(); 
		// System.out.println(list);
		
		model.addAttribute("list", list);
		
		return "book/booklist";
	}
	
	// 주문정보 : 로그인한 사람만 가능
	@RequestMapping("/orderlist")
	public String orderList(Model model, String userid) {
		// DB에서 책정보를 끌어온다.
		List<Book> list = service.purchaseList(userid);
		
		model.addAttribute("list", list);

		
		return "book/orderlist";
	}
	
	// 위시리스트
	@RequestMapping("/wishlist")
	public String wishList(Wishlist wishlist, HttpSession session) {
		String userid = (String)session.getAttribute("loginId");

		wishlist.setUserid(userid);
		
		service.wishlist(wishlist);
		
		return "redirect:booklist";
	}
	
	// 관심상품 목록을 출력
	@RequestMapping("wishitemlist")
	public String wishItemList(Model model, HttpSession session) {
		String userid = (String)session.getAttribute("loginId");
		
		List<Book> list = service.wishItemList(userid);
		model.addAttribute("list", list);
		
		return "book/wishitemlist";
	}
	
	@RequestMapping("/bestseller")
	public String bestseller(Model model) {

		
		return "";
	}
	
	@RequestMapping("/detailbook")
	public String detailbook(int code, Model model) {
		Book book = service.bookDetail(code);
		
		model.addAttribute("book", book);
		
		return "book/bookdetail";
	}	
	
	
}
