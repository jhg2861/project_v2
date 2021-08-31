package global.sesoc.blog.dao;

import java.util.ArrayList;

import global.sesoc.blog.vo.MemberVO;

/**
 * 회원 관련 Mybatis 사용 메서드
 */
public interface MemberMapper {
	//회원 정보 저장
	public int insertMember(MemberVO member);
	
	//ID로 해당 회원 정보 검색
	public MemberVO getMember(String id);

	//모든 회원 정보 읽기
	public ArrayList<MemberVO> listMember();

}
