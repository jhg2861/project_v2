package com.test.housebook.dao;

import java.util.Map;

import com.test.housebook.vo.Account_tb;

public interface MemberMapper {

	public int insert(Account_tb account) throws Exception;

	public Account_tb selectOne(Map<String, String> map) throws Exception; 
	
}	
