package com.test.housebook.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.housebook.vo.Moneybook_tb;

@Repository
public class BookDao {
	
	@Autowired
	SqlSession session;

	public List<Moneybook_tb> selectAll(Moneybook_tb moneybook_tb) {
		
		BookMapper mapper = session.getMapper(BookMapper.class);
		List<Moneybook_tb> list = null;
		try {
			list=mapper.selectBook(moneybook_tb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int insert(Moneybook_tb book) {
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		int result = 0;
		
		try {
			result = mapper.insertBook(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public Moneybook_tb selectOne(int moneybook_no) {
		BookMapper mapper = session.getMapper(BookMapper.class);
		
		Moneybook_tb book = null;
		
		try {
			book = mapper.selectOne(moneybook_no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return book;
	}

	public int delete(int moneybook_no) {
		BookMapper mapper = session.getMapper(BookMapper.class);
		int result = 0;
		
		try {
			result = mapper.deleteBook(moneybook_no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	public int updatebook(Moneybook_tb book) {
		BookMapper mapper = session.getMapper(BookMapper.class);
		int result = 0;
		
		try {
			result = mapper.updatebook(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int minOrMax(Moneybook_tb moneybook_tb) {
		BookMapper mapper = session.getMapper(BookMapper.class);
		int result = 0;
		String mode = moneybook_tb.getMode();
		try {
			if(mode.equals("min")) {
				result = mapper.resultMin(moneybook_tb);
			}else if(mode.equals("max")) {
				result = mapper.resultMax(moneybook_tb);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int totalCaculate(Moneybook_tb moneybook_tb) {
		BookMapper mapper = session.getMapper(BookMapper.class);
		int result = 0;
		String mode = moneybook_tb.getMode();
		try {
			if(mode.equals("totalIncome") && moneybook_tb.getMoneybook_type().equals("income")) {
				result = mapper.resultTotalIncome(moneybook_tb);
			}else if(mode.equals("totalPay") && moneybook_tb.getMoneybook_type().equals("pay")) {
				result = mapper.resultTotalPay(moneybook_tb);
			}else {
				if(mode.equals("totalIncome")) {
					result = mapper.resultTotalIncome(moneybook_tb);
				}else {
					result = mapper.resultTotalPay(moneybook_tb);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
