package net.scit.board2.controllers;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.scit.board2.dao.BoardRepository;
import net.scit.board2.util.FileService;
import net.scit.board2.util.PageNavigator;
import net.scit.board2.vo.Board;

@Controller
public class BoardController {
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardRepository repository;

	final String uploadPath = "/boardfile";  // 파일이 저장될 경로


	@RequestMapping("/listboard")
	public String listboard(
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="searchItem", defaultValue="title") String searchItem, 
			@RequestParam(value="searchWord", defaultValue="") String searchWord, 
			Model model) {

		logger.info("searchItem : {}", searchItem);
		logger.info("searchWord : {}", searchWord);
		logger.info("요청한 페이지 : " + currentPage);

		// Paging 관련 
//		int countPerPage = 10;
		int totalRecordCount = repository.getBoardCount(searchItem, searchWord);
		logger.info("글 전체 개수 : " + totalRecordCount);

		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);

//		int totalPageCount = (totalRecordCount % countPerPage > 0) 
//				? totalRecordCount / countPerPage + 1 : totalRecordCount / countPerPage;
		// 5 : s 41 e: 50
//		int srow = countPerPage * currentPage - 9 ;
//		int erow = countPerPage * currentPage;
		
		int totalPageCount = navi.getTotalPageCount();
		int srow = navi.getSrow();
		int erow = navi.getErow();


		// Paging 관련 끝
		// 1) DB에 접속해서 글내용을 전부 가져옴
		List<Board> list = repository.selectAll(srow, erow, searchItem, searchWord);

		// 2) 글내용을 Model에 넣는 작업 수행
		model.addAttribute("list", list);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("totalRecordCount", totalRecordCount);  // JSP에서 사용하지 않음
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("currentPage", navi.getCurrentPage());
		model.addAttribute("navi", navi);

		return "board/listBoard";
	}

	/**
	 * 글 등록을 위한 화면 요청
	 * @return
	 */
	@RequestMapping("/writeboard")
	public String writeboard() {

		return "board/writeBoard";
	}

	/**
	 * 글 등록 처리를 위한 요청
	 * @return
	 */
	@RequestMapping(value="/writeboard", method=RequestMethod.POST)
	public String writeboard(Board board, MultipartFile upload) {
		if(!upload.isEmpty()) {
			String originalFilename = upload.getOriginalFilename();
			String savedFilename = FileService.saveFile(upload, uploadPath);
			
			board.setOriginalfile(originalFilename);
			board.setSavedfile(savedFilename);
		}
		
		System.out.println(board);
		
		int result = repository.insert(board);
		logger.info("게시글 등록 여부 : {}", result);

		return "redirect:listboard";
	}

	@RequestMapping("/detailboard")
	public String detailboard(int boardnum, Model model ) {
		Board board = repository.selectOne(boardnum);
		repository.updateHitCount(boardnum);

		model.addAttribute("board", board);

		return "board/detailBoard";
	}

	@RequestMapping("/deleteboard")
	public String deleteboard(int boardnum, HttpSession session) {
		logger.info("삭제할 글번호 : " + boardnum);

		String loginId = (String) session.getAttribute("loginId");
		
		Board b = repository.selectOne(boardnum);

		// 로그인한 사람과 글쓴 사람의 아이디가 같을 때만 삭제가 진행되도록 함
		if(loginId != null && loginId.equals(b.getUserid())) { 

			String oldfile = b.getSavedfile();
			
			repository.delete(boardnum);
	
			// 기존의 파일이 존재할 때 하드디스크에서 삭제
			if(oldfile != null) {
				String fullPath = uploadPath + "/" + oldfile;
				boolean result = FileService.deleteFile(fullPath);
				if(result) System.out.println("파일 삭제 완료");
			}
		}

		return "redirect:listboard";
	}

	/**
	 * 수정할 수 있는 화면 요청
	 * @param boardnum
	 * @param model
	 * @return 예) 로그인한 a가 b의 글을 수정하겠다고 요청했을 때 처리 => 첫화면으로 리다이렉트하게 함
	 */
	@RequestMapping("/updateboard")
	public String updateboard(int boardnum, Model model, HttpSession session) {
		logger.info("수정할 글번호 : " + boardnum);

		String loginId = (String) session.getAttribute("loginId");
		Board board = repository.selectOne(boardnum); // b다!

		if(!loginId.equals(board.getUserid())) {
			System.out.println("로그인한 사람과 글쓴 사람이 다름");
			return "redirect:/"; 
		} 
	
		model.addAttribute("board", board);
		return "board/updateBoard";
	}

	/**
	 * 사용자에 의해 수정된 게시글을 db에 적용
	 * @param boardnum
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateboard", method=RequestMethod.POST)
	public String updateboard(Board board, MultipartFile upload) {
		
		Board b = repository.selectOne(board.getBoardnum());
		String oldfile = b.getSavedfile();
		
		// 기존의 파일이 존재할 때 하드디스크에서 삭제
		if(oldfile != null) {
			String fullPath = uploadPath + "/" + oldfile;
			boolean result = FileService.deleteFile(fullPath);
			if(result) System.out.println("파일 삭제 완료");
		}
		// 새로 업로드된 파일이 존재할 때 하드디스크에 저장 및 DB에 저장할 수 있도록 준비
		if(!upload.isEmpty()) {
			String originalFilename = upload.getOriginalFilename();
			String savedFilename = FileService.saveFile(upload, uploadPath);
			
			board.setOriginalfile(originalFilename);
			board.setSavedfile(savedFilename);
		}
		
		System.out.println("수정되는 글 " + board);
		int result = repository.update(board);

		return "redirect:listboard";
	}
	
	@RequestMapping("/download")
	public String download(int boardnum, HttpServletResponse response) {
		Board board = repository.selectOne(boardnum);
		
		// 원래 파일명
		String originalFile = board.getOriginalfile();
		
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalFile,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fullPath = uploadPath +"/" + board.getSavedfile();
		
		FileInputStream filein = null;		  // 서버가 HDD에서 메모리로 파일을 업로드할 때 사용
		ServletOutputStream fileout = null;   // 클라이언트한테 통신으로 내보낼 때 사용하는 객체
		
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

/*

[예]
1) 전체 글개수(totalRecordCount) : 151개
2) 한 페이지(countPerPage) : 10개  ==> 페이지수 : 16페이지

int totalPageCount = (totalRecordCount % countPerPge > 0) 
                   ? totalRecoundCount / countPerPage + 1 : totalRecoundCount / countPerPage;

3) 페이지 그룹(pagePerGroup) : 5페이지를 하나의 그룹으로 ==> 그룹수 : 4개
4) 사용자가 요청한 페이지 : currentPage

int currentPage = 1;
int countPerPage = 10;

int srow = ??
int erow = ??


페이지수
0그룹 : 1, 2, 3, 4, 5
1 :  1 ~ 10 srow, erow
2 : 11 ~ 20
3 : 21 ~ 30
4 : 31 ~ 40
5 : 41 ~ 50

1그룹 : 6, 7, 8, 9, 10

16 : 151 ~ 마지막글(152)


 */