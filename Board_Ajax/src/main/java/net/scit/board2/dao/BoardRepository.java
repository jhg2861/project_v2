package net.scit.board2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.scit.board2.vo.Board;

@Repository
public class BoardRepository {

	@Autowired
	SqlSession session;
	
	public int insert(Board board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		int result = 0;
		
		try {
			result = mapper.insertBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 모든 게시글을 조회
	 * @param searchWord 
	 * @param searchItem 
	 * @return 게시글 전체 목록
	 */
	public List<Board> selectAll(int srow, int erow, String searchItem, String searchWord) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		Map<String, Object> search = new HashMap<>();
		
		search.put("srow", srow);
		search.put("erow", erow);
		search.put("searchItem", searchItem);
		search.put("searchWord", searchWord);
		
		List<Board> list = null;
		try {
			list = mapper.selectBoard(search);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 글 하나 조회(boardnum)
	 * @param boardnum
	 * @return DB에서 가져온 글 한개의 정보(Board)
	 */
	public Board selectOne(int boardnum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		Board board = null;
				
		try {
			board = mapper.selectOne(boardnum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	/**
	 * boardnum에 해당하는 글을 삭제
	 * @param boardnum
	 */
	public int delete(int boardnum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		int result = 0;
		
		try {
			result = mapper.deleteBoard(boardnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int update(Board board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		int result = 0;
		
		try {
			result = mapper.updateBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public int getBoardCount(String searchItem, String searchWord) {
		Map<String, String> search = new HashMap<>();
		
		search.put("searchItem", searchItem);
		search.put("searchWord", searchWord);
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		int totalRecordCount = 0;
		
		try {
			totalRecordCount = mapper.getBoardCount(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalRecordCount;
	}
	public void updateHitCount(int boardnum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
	
		
		try {
			mapper.updateHitCount(boardnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}









