package global.sesoc.blog.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.blog.vo.BoardVO;

/**
 * 게시판 관련 DAO
 */
@Repository
public class BoardDAO {
	@Autowired
	SqlSession sqlSession;
	

	/**
	 * 한 회원의 게시글 목록 읽기
	 * @param id 회원아이디
	 * @return 글목록
	 */
	public ArrayList<BoardVO> list(String id) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		ArrayList<BoardVO> boardlist = mapper.list(id);
		return boardlist;
	}
	
	/**
	 * 글 번호로 해당 게시글 읽기
	 * @param boardnum 검색할 글번호
	 * @return 검색된 게시글 정보. 없으면 null.
	 */
	public BoardVO read(int boardnum) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		//해당 번호의 글정보 읽기
		BoardVO board = mapper.read(boardnum);

		return board;
	}
	
	
	/**
	 * 게시글 저장
	 * @param board 저장할 게시글 정보
	 */
	public int write(BoardVO board) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		int result = 0;
		try {
			result = mapper.write(board);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 글 번호로 해당 게시글 삭제
	 * @param 삭제할 글 번호와 로그인아이디가 포함된 정보
	 * @return 삭제된 글 개수
	 */
	public int delete(BoardVO board) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int result = mapper.delete(board);
		return result;
	}

	/**
	 * 게시글 수정
	 * @param board 수정할 글 정보
	 * @return 수정된 글 개수
	 */
	public int update(BoardVO board) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int result = mapper.update(board);
		return result;
	}
	
	/**
	 * 게시글 추천
	 * @param 추천할 글번호
	 * @return 추천 성공 글의 증가된 추천수
	 */
	public int like(int boardnum) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.like(boardnum);
		BoardVO board = mapper.read(boardnum);
		int likecnt = board.getLikecnt();
		return likecnt;
	}

}
