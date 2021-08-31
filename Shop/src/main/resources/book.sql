-- 2021년 온라인 쇼핑몰 관련 DB

DROP TABLE bookmembers;
DROP TABLE book;
DROP SEQUENCE book_seq;
DROP TABLE sale;
DROP TABLE wishlist;

-- 회원테이블
CREATE TABLE bookmembers
(
   userid   VARCHAR2(20) primary key,
   userpwd  VARCHAR2(20) not null,
   username VARCHAR2(20) not null,
   address  VARCHAR2(100),
   phone    VARCHAR2(50)
);

-- 상품정보 테이블
CREATE TABLE book
(
   code     NUMBER primary key,
   title    VARCHAR2(50) not null,
   price    NUMBER default 0,
   quantity NUMBER default 0
);

-- 상품정보 시퀀스
CREATE SEQUENCE book_seq;

-- 판매정보 테이블
CREATE TABLE sale
(
    userid  VARCHAR2(20) not null,
    code    NUMBER not null,
    cnt     NUMBER default 0,
    purchasedate DATE default sysdate
);

-- 관심상품 테이블

CREATE TABLE wishlist
(
    userid  VARCHAR2(20) not null,
    code    NUMBER not null
);