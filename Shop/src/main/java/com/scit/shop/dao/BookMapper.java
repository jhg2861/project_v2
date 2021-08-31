package com.scit.shop.dao;

import java.util.List;

import com.scit.shop.vo.Book;
import com.scit.shop.vo.Wishlist;

public interface BookMapper {
	// 상품목록
	public List<Book> selectAll() throws Exception;
	
	// 베스트 셀러
	public Book bestSeller() throws Exception;
	
	// 위시리스트 저장
	public int wishList(Wishlist wishlist) throws Exception;
	
	// 위시리스트 목록 출력
	public List<Book> wishItemList(String userid) throws Exception;


	// 구매목록
	public List<Book> purchaseList(String userid) throws Exception;
	
	// 책 상세정보
	public Book detailBook(int code) throws Exception;
	

	
}
