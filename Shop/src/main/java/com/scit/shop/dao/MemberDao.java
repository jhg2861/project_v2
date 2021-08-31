package com.scit.shop.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scit.shop.vo.Bookmembers;

@Repository
public class MemberDao {

	@Autowired
	SqlSession session;

	public Bookmembers selectOne(Map<String, String> map) {
		MemberMapper mapper = session.getMapper(MemberMapper.class); 
		Bookmembers member = null;

		try {
			member = mapper.selectOne(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}

	public int insert(Bookmembers member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result=0;
		try {
			result = mapper.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
