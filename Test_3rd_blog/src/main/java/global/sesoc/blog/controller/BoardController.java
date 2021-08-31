package global.sesoc.blog.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import global.sesoc.blog.dao.BoardDAO;
import global.sesoc.blog.vo.BoardVO;

/**
 * 블로그 콘트롤러
 * 블로그에 글쓰기, 읽기, 목록, 삭제, 수정, 추천 처리
 */
@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardDAO dao;		//블로그 게시글 관련 DB처리 객체

	/**
	 * 개인 블로그의 해당 회원이 쓴 글 목록
	 * @param id 블로그 ID
	 */
	@RequestMapping (value="list", method=RequestMethod.GET)
	public String list(String id, Model model) {
		//특정 회원이 쓴 글 목록
		ArrayList<BoardVO> boardlist = dao.list(id);	
		
		//블로그 주인 아이디와 글 목록을 모델에 저장
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("blogId", id);
		
		return "list";
	}

	/**
	 * 글 읽기
	 * @param boardnum 읽을 글번호
	 * @return 해당 글 정보
	 */
	@RequestMapping (value="read", method=RequestMethod.GET)
	public String read(int boardnum, Model model) {
		//전달된 글 번호로 해당 글정보 읽기
		BoardVO board = dao.read(boardnum);
		//해당 번호의 글이 없으면 글목록으로 이동
		if (board == null) {
			return "redirect:list";
		}

		//블로그 글정보를 모델에 저장
		model.addAttribute("board", board);
		model.addAttribute("blogId", board.getId());
		return "read";
	}
	
	
	/**
	 * 글쓰기 폼 보기
	 */
	@RequestMapping (value="write", method=RequestMethod.GET)
	public String writeForm() {
		return "writeForm";
	}

	/**
	 * 글 저장 처리
	 * @param board 사용자가 입력한 글 내용
	 */
	@RequestMapping (value="write", method=RequestMethod.POST)
	public String write(BoardVO board, HttpSession session,	Model model) {
		
		//세션에서 로그인한 사용자의 아이디를 읽어서 Board객체의 작성자 정보에 세팅
		String id = (String) session.getAttribute("loginId");
		board.setId(id);
		
		dao.write(board);
		return "redirect:list?id=" + id;
	}
	
	/**
	 * 본인 글 삭제
	 * @param boardnum 삭제할 글번호
	 */
	@RequestMapping (value="delete", method=RequestMethod.GET)
	public String delete(int boardnum, HttpSession session) {
		//세션에서 로그인한 아이디 읽기
		String id = (String) session.getAttribute("loginId");
		
		//삭제할 글 번호와 본인 글인지 확인할 로그인아이디
		BoardVO board = new BoardVO();
		board.setBoardnum(boardnum);
		board.setId(id);
		
		dao.delete(board);

		//블로그 글목록으로 이동
		return "redirect:list?id=" + id;
	}
	
	/**
	 * 글 수정 폼으로 이동
	 * @param boardnum 수정할 글번호
	 * @return 해당 번호의 글 정보
	 */
	@RequestMapping (value="update", method=RequestMethod.GET)
	public String updateForm(int boardnum, Model model, HttpSession session) {
		//세션에서 로그인한 아이디 읽기
		String id = (String) session.getAttribute("loginId");

		//전달된 글 번호로 수정할 글 읽기
		BoardVO board = dao.read(boardnum);
		
		//본인 글이 아니면 메인화면으로 이동
		if (!id.equals(board.getId())) {
			return "redirect:/";
		}
		
		model.addAttribute("board", board);
		
		return "updateForm";
	}
	
	/**
	 * 글 수정 처리
	 * @param board 수정할 글 정보
	 */
	@RequestMapping (value="update", method=RequestMethod.POST)
	public String update(BoardVO board, HttpSession session) {
		
		//수정할 글이 로그인한 본인 글인지 확인할 아이디 읽기
		String id = (String) session.getAttribute("loginId");
		
		//수정할 글 정보에 로그인 아이디 저장
		board.setId(id);
		
		//글 수정 처리
		dao.update(board);
		
		//원래의 글읽기 화면으로 이동 
		return "redirect:read?boardnum=" + board.getBoardnum();
	}

	/**
	 * 글 추천
	 * @param boardnum 추천할 글번호
	 */
	@ResponseBody
	@RequestMapping (value="like", method=RequestMethod.GET)
	public String like(int boardnum) {

		//글 번호를 전달하여 추천수 증가
		int likecnt = dao.like(boardnum);
		
		//블로그 글목록으로 이동
		return Integer.toString(likecnt);
	}

}
