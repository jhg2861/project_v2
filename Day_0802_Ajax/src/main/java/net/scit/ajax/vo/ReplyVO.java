package net.scit.ajax.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private int replynum;
	private int boardnum;
	private String userid;
	private String replytext;
	private String regdate;
}
