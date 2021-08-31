package com.test.housebook.vo;

import lombok.Data;

@Data
public class Moneybook_tb {

	public int moneybook_no;
	public String acc_id;
	public String moneybook_memo;
	public String moneybook_type;
	public int moneybook_amount;
	public String moneybook_indate;
	
	//최솟값 최대값 구분값
	public String mode;
	//검색어 입력
	public String searchWord;
	//검색어 타입
	public String searchItem;
	
}


/* CREATE TABLE moneybook_tb
	(
		moneybook_no number primary key,
		acc_id varchar2(30) not null references account_tb,
		moneybook_memo varchar2(1000) not null,
		moneybook_type varchar2(20) not null,
		moneybook_amount number default 0,
		moneybook_indate date default sysdate
	);*/