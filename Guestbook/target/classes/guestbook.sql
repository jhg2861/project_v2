-- 테이블과 시퀀스 객체를 삭제
DROP TABLE guestbook;
DROP SEQUENCE guestbook_seq;

-- 글 저장을 위한 테이블
CREATE TABLE guestbook
(
	guestbooknum number primary key,    -- 글번호
	writer  varchar2(20) not null,      -- 작성자 이름
	pwd     varchar2(20) not null,	    -- 삭제 시 사용될 비번
	text    varchar2(2000) not null,    -- 글 내용
	regdate date default sysdate	    -- 글 작성날짜
);

-- 글 일련번호에서 사용할 시퀀스
CREATE SEQUENCE guestbook_seq;

-- 글 저장 예
INSERT INTO guestbook
(guestbooknum, writer, password, text)
VALUES
(guestbook_seq.nextval, '홍길동', '1111', '글내용입니다.');