package net.scit.ajax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.scit.ajax.dao.ReplyRepository;
import net.scit.ajax.vo.ReplyVO;

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
		System.out.println(list);
		
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
}








