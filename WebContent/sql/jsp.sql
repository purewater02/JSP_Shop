create table jsp_admin(
	id varchar2(10),
	pwd varchar2(10)
);

create table jsp_member(
	id varchar2(15) primary key,
	pwd varchar(20) not null,
	name varchar2(10) not null,
	phone varchar2(20),
	email varchar2(30),
	addr1 varchar2(100),
	addr2 varchar2(100),
	mileage number(10),
	regdate date
);

create table jsp_product(
	pNo number(10) primary key,
	ctg1 varchar2(20),
	ctg2 varchar2(20),
	pCode varchar2(50),
	pName varchar2(50),
	pPrice number(10),
	sold number(10),
	size varchar2(20),
	color varchar2(20),
	pImage varchar2(100),
	pContent varchar2(500),
	mileage number(10)
);

create table jsp_recently(
	sId varchar2(15) primary key,
	ctg1 varchar2(20),
	ctg2 varchar2(20),
	pCode varchar2(50),
	pName varchar2(50)
);

create table jsp_orderProduct(
	oNo number(10) primary key,		-- 주문번호
	pNo number(10),			-- 제품번호
	pName varchar2(50),			-- 제품이름
	pAmount number(10),			-- 주문수량
	prdPrice number(10),			-- 제품금액
	totPrice number(10),			-- 총금액
	size varchar2(20),
	color varchar2(20)
);

create table jsp_orderInfo(
	oNo number(10) primary key,
	oId varchar2(15),
	oDate date,
	oReceiver varchar2(10),
	oAddr varchar2(200), 			-- addr1 + addr2
	oPhone varchar2(20),
	oPay varchar2(20),			-- 결제 수단(현금 or 카드)
	oCard varchar2(20),
	oCardNo varchar2(30),
	oCardPwd varchar2(10),
	oState varchar2(20)			-- 주문상태
);

create table jsp_cart(
	cartNo number(10) primary key,
	sessionId varchar2(15),
	pNo number(10),
	pName varchar2(50),
	pAmount number(10),
	pPrice number(10),
	totPrice number(10),
size varchar2(20),
	color varchar2(20),
	pImage varchar2(100)
);

create table jsp_notice(
	bNo number(10) primary key,
	bTitle varchar2(30),
	bWriter varchar2(15),
	bCont varchar2(500),
	bDate date
);

create table jsp_qna(
	bNo number(10) primary key,
	bTitle varchar2(30),
	bWriter varchar2(15),
	bCont varchar2(500),
	bDate date,
	bPwd varchar2(10),
	bGroup number(10),
	bStep number(10),
	bIndent number(10)
);

create table jsp_review(
bNo number(10) primary key,
	bTitle varchar2(30),
	bWriter varchar2(15),
	bCont varchar2(500),
	bDate date,
	bPwd varchar2(10)
);
