package global.sesoc.blog.dao;

import java.util.ArrayList;

import global.sesoc.blog.vo.BoardVO;

/**
 * 블로그 글 관련 Mybatis 사용 메서드
 */
public interface BoardMapper {
	//1명 회원의 글 목록
	public ArrayList<BoardVO> list(String id);
	//글번호로 해당 게시글 읽기
	public BoardVO read(int boardnum);
	//게시글 저장
	public int write(BoardVO board);
	//글번호와 아이디로 해당 게시글 삭제
	public int delete(BoardVO board);
	//글 수정
	public int update(BoardVO board);
	//추천수 1 증가
	public int like(int boardnum);


}
