package net.scit.board2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.scit.board2.dao.ReplyRepository;
import net.scit.board2.vo.ReplyVO;


@Controller
public class ReplyController {

	@Autowired
	ReplyRepository repository;
	
	@RequestMapping("/reply")
	public String replyForm() {
		return "replyForm";
	}
	
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<ReplyVO> selectAll() {
		List<ReplyVO> list = repository.selectAll();
		
		return list;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	public void insert(ReplyVO reply) {

		int result = repository.insert(reply);
		
		return;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int replynum) {
		int result = repository.delete(replynum);
		
		if(result == 1) {
			return "Success";
		}
		return "Fail";
	}
	
	@RequestMapping("/selectOne")
	@ResponseBody
	public ReplyVO selectOne(int replynum) {
		ReplyVO replyVO = repository.selectOne(replynum);
		
		return replyVO;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public void update(ReplyVO reply) {

		int result = repository.update(reply);
		
		return;
	}
}








