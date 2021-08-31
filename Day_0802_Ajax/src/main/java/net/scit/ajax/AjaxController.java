package net.scit.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.scit.ajax.vo.UserVO;

@Controller
public class AjaxController {

	@RequestMapping("/ajaxReq1")
	public void ajaxReq1() {
		System.out.println("요청 도착함!");
	}
	
	@RequestMapping("/ajaxReq2")
	public @ResponseBody Map<String,String> ajaxReq2(String name, String phone) {
		Map<String, String> map = new HashMap<>();
		
		map.put("name", name);
		map.put("phone", phone);
		
		System.out.println(name +" , " + phone);
		System.out.println("두번째 요청 도착함!");
		
		return map;
	}
	
	@RequestMapping(value="/ajaxReq3", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> ajaxReq3(String name, String phone) {
		Map<String, String> map = new HashMap<>();
		
		map.put("name", name);
		map.put("phone", phone);
		
		System.out.println(name +" , " + phone);
		System.out.println("세번째 요청 도착함!");
		
		return map;
	}
	@RequestMapping("/rawdata")
	public String rawdata() {
		return "rawdata";
	}
	
	@RequestMapping(value="/ajaxReq4", produces="text/html;charset=UTF-8")
	public @ResponseBody String ajaxReq4() {
		String html = "<p><b>오늘의 경기 : </b> <i>이스라엘과 야구경기가 있었음</i></p>";
		
		return html;
	}
	
	@RequestMapping(value="/ajaxReq5", produces="application/json; charset=UTF-8")
	public @ResponseBody String ajaxReq5() {
		String json = "{\"date\" : \"오늘의 경기\" , \"play\" : \"이스라엘과 야구경기가 있었음\" }";
		
		return json;
	}
	
	@RequestMapping("/variousType")
	public String variousType() {
		
		return "variousType";
	}
	
	@RequestMapping(value="/send1", method=RequestMethod.POST)
	public @ResponseBody String send1(String username, String userid) {
		System.out.println(username+ ", "  + userid);
		
		return "SUCCESS";
	}
	
	@RequestMapping(value="/send2", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> send2(@RequestBody Map<String, String> map) {
		System.out.println(map);
		
		return map;
	}
	
	@RequestMapping(value="/send3", method=RequestMethod.POST)
	@ResponseBody
	public UserVO send3(UserVO vo) {
		System.out.println(vo);
		
		return vo;
	}
	
	@RequestMapping("/send4")
	@ResponseBody
	public List<UserVO> send4() {
		List<UserVO> list = new ArrayList<>();
		
		list.add(new UserVO("son", "손오공", "010-1111-2222", 34));
		list.add(new UserVO("lim", "임꺽정", "010-2222-3333", 22));
		list.add(new UserVO("sa", "사오정", "010-1111-7777", 22));
		list.add(new UserVO("hong", "홍길동", "010-1111-4545", 20));
		
		return list;
	}
}









