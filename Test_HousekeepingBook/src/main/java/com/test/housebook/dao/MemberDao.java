package com.test.housebook.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.housebook.vo.Account_tb;

@Repository
public class MemberDao {
	
	@Autowired
	SqlSession session;
	
	public int insert(Account_tb account) {
		
		//MemberMapper mapper = session.getMapper(MemberMapper.class);
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int result=0;
		
		try {
			result = mapper.insert(account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public Account_tb selectOne(Map<String, String> map) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		Account_tb account = null;
		
		try {
			account = mapper.selectOne(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

}
