package com.scit.shop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scit.shop.dao.MemberDao;
import com.scit.shop.vo.Bookmembers;

@Service
public class MemberService {

	@Autowired
	MemberDao dao;
	
	// 아이디체크, 로그인
	public Bookmembers selectOne(String userid, String userpwd) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("userid", userid);
		if(userpwd != null) 
			map.put("userpwd", userpwd);
		
		Bookmembers member = dao.selectOne(map);
		
		return member;
	}

	public int insert(Bookmembers member) {
		
		return dao.insert(member);
	}

}
