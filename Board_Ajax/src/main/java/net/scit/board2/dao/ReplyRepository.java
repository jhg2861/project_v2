package net.scit.board2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.scit.board2.vo.ReplyVO;

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

	public ReplyVO selectOne(int replynum) {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		ReplyVO replyVO = null;
		try {
			replyVO = mapper.selectOne(replynum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyVO;
	}

	public int update(ReplyVO reply) {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		int result = 0;
		try {
			result = mapper.update(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}







