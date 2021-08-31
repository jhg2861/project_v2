-- 2021년 7월 
DROP TABLE reply_test;
DROP SEQUENCE replytest_seq;

-- 댓글(Reply)
CREATE TABLE reply_test
(
	replynum  number primary key,		-- 댓글 번호
	boardnum  number default 0, 		-- 참조하는 본문글번호(지금은 사용하지 않음)
	userid    varchar2(50)   not null,	-- 댓글 작성자 아이디(입력받을 예정)
	replytext varchar2(1000) not null,	-- 댓글 내용
	regdate   date default sysdate		-- 댓글 작성일
);

CREATE SEQUENCE replytest_seq;