package global.sesoc.blog.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.blog.vo.MemberVO;

/**
 * 회원 관련 DAO
 */
@Repository
public class MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 회원 가입 처리
	 * @param member 사용자가 입력한 가입 정보
	 */
	public int insertMember(MemberVO member) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int result = 0;
		try {
			result = mapper.insertMember(member);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ID로 회원정보 검색
	 * @param id 검색할 아이디
	 * @return 검색된 회원정보. 없으면 null.
	 */
	public MemberVO getMember(String id) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		MemberVO member = mapper.getMember(id);
		return member;
	}

	public ArrayList<MemberVO> listMember() {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		ArrayList<MemberVO> memberlist = mapper.listMember();
		return memberlist;
	}

}
