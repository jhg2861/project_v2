package net.scit.ajax.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.scit.ajax.vo.ReplyVO;

@Repository
public class ReplyRepository {

	@Autowired
	SqlSession session;

	public List<ReplyVO> selectAll() {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);

		List<ReplyVO> list = null;

		try {
			list = mapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int insert(ReplyVO reply) {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		int result = 0;
		try {
			result = mapper.insert(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(int replynum) {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		int result = 0;
		try {
			result = mapper.delete(replynum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}







