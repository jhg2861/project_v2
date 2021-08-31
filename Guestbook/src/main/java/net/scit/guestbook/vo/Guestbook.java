package net.scit.guestbook.vo;

import lombok.Data;

@Data
public class Guestbook {
	private int guestbooknum;
	private String writer;
	private String pwd;
	private String text;
	private String regdate;
}
