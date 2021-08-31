package com.test.housebook.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.housebook.dao.MemberDao;
import com.test.housebook.vo.Account_tb;

@Service
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	public int insert(Account_tb account) {
		
		return dao.insert(account);
	}

	public Account_tb selectOne(String acc_id, String acc_pw) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("acc_id", acc_id);
		if(acc_pw != null)
			map.put("acc_pw", acc_pw);
		Account_tb account = dao.selectOne(map);
		
		return account;
	}

}
