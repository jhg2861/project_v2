package com.scit.shop.dao;

import java.util.Map;
import com.scit.shop.vo.Bookmembers;

public interface MemberMapper {
	// 회원가입 : insert into 
	public int insert(Bookmembers member) throws Exception; 
	
	// 아이디 체크, 로그인
	public Bookmembers selectOne(Map<String, String> map) throws Exception;

}
