package com.test.housebook.dao;

import java.util.List;
import java.util.Map;

import com.test.housebook.vo.Moneybook_tb;

public interface BookMapper {

	public List<Moneybook_tb> selectBook(Moneybook_tb moneybook_tb) throws Exception;

	public int insertBook(Moneybook_tb book) throws Exception;

	public Moneybook_tb selectOne(int moneybook_no) throws Exception;

	public int deleteBook(int moneybook_no) throws Exception;

	public int updatebook(Moneybook_tb book) throws Exception;

	/**
	 * 최소값 구하기
	 * @param moneybook_tb
	 * @return
	 * @throws Exception
	 */
	public int resultMin(Moneybook_tb moneybook_tb)  throws Exception;
	
	/**
	 * 최대값 구하기
	 * @param moneybook_tb
	 * @return
	 * @throws Exception
	 */
	public int resultMax(Moneybook_tb moneybook_tb)  throws Exception;
	/**
	 * 총수입
	 * @param moneybook_tb
	 * @return
	 * @throws Exception
	 */
	public int resultTotalIncome(Moneybook_tb moneybook_tb) throws Exception;
	/**
	 * 총지출
	 * @param moneybook_tb
	 * @return
	 * @throws Exception
	 */
	public int resultTotalPay(Moneybook_tb moneybook_tb) throws Exception;


}
