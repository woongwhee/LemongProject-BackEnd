﻿--Drop
DROP TABLE TEMPLATE CASCADE CONSTRAINTS PURGE ;;
DROP TABLE USER_PROFILE;
DROP TABLE TEMPLATE_TODO;
DROP TABLE MEMBER;
DROP TABLE HEART;
DROP TABLE REPORT;
DROP TABLE TODO;
DROP TABLE FOLLOW;
DROP TABLE ALARM;
DROP TABLE REVIEW;
DROP TABLE FEED CASCADE CONSTRAINTS PURGE;
DROP TABLE OFFICIAL_HOLIDAY;
DROP TABLE PHOTO;
DROP TABLE REFERENCE;
DROP TABLE CHALLENGE;
DROP TABLE REPLY;
DROP TABLE FEED_PHOTO;
DROP TABLE CHALLENGE_TODO;
DROP TABLE TEMPLATE_CATEGORY;
DROP TABLE CHALLENGE_USER;
DROP TABLE CHALLENGE_CHAT;


--Create Table,Comment Column
CREATE TABLE TEMPLATE (
	TEMPLATE_NO	NUMBER	NOT NULL,
	TP_CREATER	number	NOT NULL,
	CATEGORY_NO	number,
	RANGE	number,
	TITLE	varchar2(100),
	CONTENT	varchar2(1000),
	CREATED_AT	date	DEFAULT SYSDATE,
	SAVE_STATUS	number(1)	DEFAULT 0	CHECK(SAVE_STATUS IN(0,1)),
	TODO_COUNT	number
);

COMMENT ON COLUMN TEMPLATE.TEMPLATE_NO IS '템플릿번호';
COMMENT ON COLUMN TEMPLATE.TP_CREATER IS '작성자';
COMMENT ON COLUMN TEMPLATE.CATEGORY_NO IS '카테고리번호';
COMMENT ON COLUMN TEMPLATE.RANGE IS '기간';
COMMENT ON COLUMN TEMPLATE.TITLE IS '템플릿 제목';
COMMENT ON COLUMN TEMPLATE.CONTENT IS '템플릿 설명';
COMMENT ON COLUMN TEMPLATE.CREATED_AT IS '템플릿 생성 날짜';
COMMENT ON COLUMN TEMPLATE.SAVE_STATUS IS '작성상태 0 임시저장 1 작성완료';
COMMENT ON COLUMN TEMPLATE.TODO_COUNT IS '템플릿 투두 개수';

CREATE TABLE TODO (
	TODO_NO	number		NOT NULL,
	TODO_DATE	number	NOT NULL,
	USER_NO	varchar2(255) NOT NULL,
	TODO_CONTENT	varchar2(200)
	    NOT NULL,
	CLEAR	number(1)	DEFAULT 0 CHECK(CLEAR IN(0,1)),
	VALUE	number DEFAULT 1
);
COMMENT ON COLUMN TODO.TODO_NO IS '투두리스트 아이디';
COMMENT ON COLUMN TODO.TODO_DATE IS '투두리스트 날짜(index)';
COMMENT ON COLUMN TODO.USER_NO IS '투두리스트 사용자';
COMMENT ON COLUMN TODO.CLEAR IS '완료여부';
COMMENT ON COLUMN TODO.VALUE IS '순서가중치';

CREATE TABLE TEMPLATE_TODO (
	TP_TODO_NO	number		NOT NULL,
	TEMPLATE_NO number	NOT NULL,
    DAY	number	NOT NULL,
	CONTENT	varchar2(200) NULL,
	VALUE	number	DEFAULT 1
);

COMMENT ON COLUMN TEMPLATE_TODO.TP_TODO_NO IS '템플릿 투두 아이디';
COMMENT ON COLUMN TEMPLATE_TODO.TEMPLATE_NO IS '템플릿 아이디';
COMMENT ON COLUMN TEMPLATE_TODO.DAY IS '일';
COMMENT ON COLUMN TEMPLATE_TODO.VALUE IS '순서가중치';
COMMENT ON COLUMN TEMPLATE_TODO.CONTENT IS '투두 내용';

CREATE TABLE FOLLOW (
	FOLLOWER	number,
	FOLLOWING	number,
	FOLLOW_AT	date DEFAULT SYSDATE
);

COMMENT ON COLUMN FOLLOW.FOLLOWER IS '팔로우당하는사람(USER_NO)';
COMMENT ON COLUMN FOLLOW.FOLLOWING IS '팔로우 하는사람(USER_NO)';
COMMENT ON COLUMN FOLLOW.FOLLOW_AT IS '팔로우한날짜';


CREATE TABLE ALARM (
	ALR_NO	number		NOT NULL,
	USER_NO	number		NOT NULL,
	REF_TYPE	number	NOT NULL,
	REF_NO	number		NOT NULL,
	CALL_USER	number		NOT NULL,
	ALR_MESSAGE	varchar2(200)		NOT NULL,
	ALR_AT	date	DEFAULT sysdate	NOT NULL,
	READ	number(1)	 DEFAULT 0 check ( READ IN(0,1))
);

COMMENT ON COLUMN ALARM.ALR_NO IS 'PK';
COMMENT ON COLUMN ALARM.USER_NO IS '받은사람';
COMMENT ON COLUMN ALARM.REF_TYPE IS '참조속성';
COMMENT ON COLUMN ALARM.REF_NO IS '참조PK번호';
COMMENT ON COLUMN ALARM.SEND_USER IS '보낸사람';
COMMENT ON COLUMN ALARM.ALR_MESSAGE IS '알람 메세지';
COMMENT ON COLUMN ALARM.ALR_AT IS '알림시간';
COMMENT ON COLUMN ALARM.READ IS '읽음여부';

CREATE TABLE MEMBER (
	USER_NO	number,
	EMAIL	varchar2(100) ,
	USER_PWD	varchar2(70),
	SOCAIL_TYPE	varchar2(20),
	ACCESS_TOKEN	varchar2(100),
    NICK_NAME VARCHAR2(100),
	STATUS	number(1)	DEFAULT 0	check ( STATUS IN(0,1))
);

COMMENT ON COLUMN MEMBER.USER_NO IS '유저번호';
COMMENT ON COLUMN MEMBER.EMAIL IS '유저 이메일';
COMMENT ON COLUMN MEMBER.USER_PWD IS '유저 비밀번호';
COMMENT ON COLUMN MEMBER.SOCAIL_TYPE IS '아이디 타입';
COMMENT ON COLUMN MEMBER.ACCESS_TOKEN IS '소셜토큰';
COMMENT ON COLUMN MEMBER.STATUS IS '계정상태';

CREATE TABLE CHALLENGE (
	CHALLENGE_NO	number		NOT NULL,
	CREATE_USER	number		NOT NULL,
	TEMPLATE_NO number		NOT NULL,
	CHALLENGE_TITLE	varchar2(100)		NULL,
	CHALLENGE_INFO	varchar2(1000)		NULL,
	START_DATE	date	NOT NULL,
	END_DATE	date	NOT NULL,
	CREATE_AT	date	DEFAULT sysdate	NOT NULL,
	CHALLENGE_OPTION	varchar2(8)	DEFAULT '11111110',
	STATUS VARCHAR2(10) CHECK ( STATUS IN('PLAY','FINISH','READY','CANCEL','SINGLE')  )
);

COMMENT ON COLUMN CHALLENGE.CHALLENGE_NO IS '챌린지 번호';
COMMENT ON COLUMN CHALLENGE.CREATE_USER IS '방만든사람';
COMMENT ON COLUMN CHALLENGE.TEMPLATE_NO IS '템플릿번호';
COMMENT ON COLUMN CHALLENGE.CHALLENGE_TITLE IS '제목';
COMMENT ON COLUMN CHALLENGE.CHALLENGE_OPTION IS '챌린지옵션(월화수목금토일공휴일)(0000000)';
COMMENT ON COLUMN CHALLENGE.START_DATE IS '시작일';
COMMENT ON COLUMN CHALLENGE.END_DATE IS '종료일';
COMMENT ON COLUMN CHALLENGE.CREATE_AT IS '만든날짜';
COMMENT ON COLUMN CHALLENGE.STATUS IS '상태(''PLAY'',''FINISH'',''READY'',''CANCEL'',''SINGLE'')';

CREATE TABLE TEMPLATE_CATEGORY (
	CATEGORY_NO	number		NOT NULL,
	CATEGORY_NAME	varchar2(20) NOT NULL,
	IMAGE_PATH	varchar2(200) NOT NULL
);
COMMENT ON COLUMN TEMPLATE_CATEGORY.CATEGORY_NO IS '카테고리번호';
COMMENT ON COLUMN TEMPLATE_CATEGORY.CATEGORY_NAME IS '템플릿 카테고리';
COMMENT ON COLUMN TEMPLATE_CATEGORY.IMAGE_PATH IS '카테고리 이미지경로';

CREATE TABLE CHALLENGE_USER (
	CHALLENGE_NO	number	NOT NULL,
	USER_NO	number	NOT NULL,
	STATUS	varchar(10) check ( STATUS IN ('READY','PLAY','CANCEL','FAIL','CLEAR') )	NOT NULL,
	CLEAR_COUNT	number		NOT NULL
);
COMMENT ON COLUMN CHALLENGE_USER.CHALLENGE_NO IS '챌린지 번호';
COMMENT ON COLUMN CHALLENGE_USER.USER_NO IS '유저 번호';
COMMENT ON COLUMN CHALLENGE_USER.STATUS IS '상태(READY,PLAY,CANCEL,FAIL,CLEAR)';
COMMENT ON COLUMN CHALLENGE_USER.CLEAR_COUNT IS '클리어';

CREATE TABLE CHALLENGE_TODO (
	TODO_NO	number		NOT NULL,
	CHALLENGE_NO	number	NOT NULL,
	USER_NO	varchar(255)   NOT NULL,
	TODO_DATE	date NOT NULL,
	TODO_CONTENT	varchar2(200) NOT NULL,
	VALUE	number default 1,
	CLEAR	number(1)	DEFAULT 0	CHECK(CLEAR IN(0,1))
);
COMMENT ON COLUMN CHALLENGE_TODO.TODO_NO IS '투두리스트 아이디';
COMMENT ON COLUMN CHALLENGE_TODO.CHALLENGE_NO IS '챌린지 번호';
COMMENT ON COLUMN CHALLENGE_TODO.USER_NO IS '투두리스트 사용자';
COMMENT ON COLUMN CHALLENGE_TODO.TODO_DATE IS '투두리스트 날짜(index)';
COMMENT ON COLUMN CHALLENGE_TODO.TODO_CONTENT IS '투두내용';
COMMENT ON COLUMN CHALLENGE_TODO.VALUE IS '순서가중치';
COMMENT ON COLUMN CHALLENGE_TODO.CLEAR IS '완료여부';

CREATE TABLE REVIEW (
	TP_REVIEW_NO	number	NOT NULL,
	TEMPLATE_NO number	NOT NULL,
	USER_NO	number	NOT NULL,
	REVIEW_DETAIL	varchar2(1000)		NOT NULL,
	REVIEW_AT	date	DEFAULT SYSDATE	NOT NULL
);
COMMENT ON COLUMN REVIEW.REVIEW_NO IS '리뷰번호';
COMMENT ON COLUMN REVIEW.TEMPLATE_NO  IS '템플릿번호';
COMMENT ON COLUMN REVIEW.USER_NO IS '작성자';
COMMENT ON COLUMN REVIEW.REVIEW_DETAIL IS '후기내용';






CREATE TABLE FEED (
	FEED_NO	number	NOT NULL,
	USER_NO	number	NOT NULL,
	FEED_CONTENT varchar2(1000)		NOT NULL,
	FEED_AT	date	DEFAULT sysdate	NOT NULL
);
COMMENT ON COLUMN FEED.FEED_NO IS '피드번호';
COMMENT ON COLUMN FEED.USER_NO IS '작성자';
COMMENT ON COLUMN FEED.FEED_CONTENT IS '피드내용';
COMMENT ON COLUMN FEED.FEED_AT IS '순서 가중치';


CREATE TABLE HEART (
	USER_NO	number,
	REF_TYPE	number		NOT NULL,
	REF_NO	number		NOT NULL
);
COMMENT ON COLUMN HEART.USER_NO IS '좋아요한사람';
COMMENT ON COLUMN HEART.REF_TYPE IS '참조속성';
COMMENT ON COLUMN HEART.REF_NO IS '참조PK번호';

CREATE TABLE USER_PROFILE (
	USER_NO	number,
	NICK_NAME	varchar2(100),
	PHOTO_NO	number,
	PROFILE_COMMENT	varchar2(1000)
);
COMMENT ON COLUMN USER_PROFILE.USER_NO IS '유저번호';
COMMENT ON COLUMN USER_PROFILE.NICK_NAME IS '닉네임';
COMMENT ON COLUMN USER_PROFILE.PHOTO_NO IS 'PK';
COMMENT ON COLUMN USER_PROFILE.PROFILE_COMMENT IS '자기소개';

CREATE TABLE OFFICIAL_HOLIDAY (
	HOLIDAY_NO	number		NOT NULL,
	HOLIDAY	date	NOT NULL,
	HOLIDAY_NAME	varchar2(50)		NULL
);
COMMENT ON COLUMN OFFICIAL_HOLIDAY.HOLIDAY_NO IS 'PK';
COMMENT ON COLUMN OFFICIAL_HOLIDAY.HOLIDAY IS '휴일';
COMMENT ON COLUMN OFFICIAL_HOLIDAY.HOLIDAY_NAME IS '휴일이름';

CREATE TABLE FEED_PHOTO (
	FEED_NO	number	NOT NULL,
	PHOTO_NO	number	NOT NULL,
    VALUE number default 1
);
COMMENT ON COLUMN FEED_PHOTO.FEED_NO IS '피드번호';
COMMENT ON COLUMN FEED_PHOTO.PHOTO_NO IS 'PK';

CREATE TABLE PHOTO (
	PHOTO_NO	number	NOT NULL,
	USER_NO	number		NOT NULL,
	ORGIN_NAME	varchar2(255)		NULL,
	CHANGE_NAME	varchar2(255)
	    NOT NULL,
	FILE_PATH	varchar2(250)		NOT NULL
);
COMMENT ON COLUMN PHOTO.PHOTO_NO IS 'PK';
COMMENT ON COLUMN PHOTO.USER_NO IS '유저번호';
COMMENT ON COLUMN PHOTO.ORIGIN_NAME IS '원본이름';
COMMENT ON COLUMN PHOTO.CHANGE_NAME IS '파일이름';
COMMENT ON COLUMN PHOTO.FILE_PATH IS '파일경로';


CREATE TABLE REPLY (
	REPLY_NO	number		NOT NULL,
	USER_NO	number		NOT NULL,
	FEED_NO	number	NOT NULL,
	REPLY_CONTENT	varchar2(1000)		NOT NULL,
	REPLY_AT	date	DEFAULT SYSDATE	NULL
);
COMMENT ON COLUMN REPLY.REPLY_NO IS '댓글번호';
COMMENT ON COLUMN REPLY.USER_NO IS '댓글작성자';
COMMENT ON COLUMN REPLY.FEED_NO IS '피드번호';
COMMENT ON COLUMN REPLY.REPLY_CONTENT IS '댓글내용';
COMMENT ON COLUMN REPLY.REPLY_AT IS '작성일자';

CREATE TABLE CHALLENGE_CHAT (
	CHAT_NO	number		NOT NULL,
	CHALLENGE_NO	number		NOT NULL,
	USER_NO	number		NOT NULL,
	CHAT_MESSAGE	varchar2(1000)
	    NULL,
	SEND_AT	date	DEFAULT sysdate	NULL
);
COMMENT ON COLUMN CHALLENGE_CHAT.CHAT_NO IS '채팅번호';
COMMENT ON COLUMN CHALLENGE_CHAT.CHALLENGE_NO IS '챌린지 번호';
COMMENT ON COLUMN CHALLENGE_CHAT.USER_NO IS '보낸사람';
COMMENT ON COLUMN CHALLENGE_CHAT.CHAT_MESSAGE IS '채팅메세지';
COMMENT ON COLUMN CHALLENGE_CHAT.SEND_AT IS '보낸시간';

CREATE TABLE REPORT (
	REPORT_NO	number		NOT NULL,
	REF_TYPE	number		NOT NULL,
	REF_NO	number		NOT NULL,
	REPORT_AT	date	DEFAULT sysdate	NOT NULL,
	READ	number(1)		DEFAULT 0 NOT NULL
);
COMMENT ON COLUMN REPORT.REPORT_NO IS 'PK';
COMMENT ON COLUMN REPORT.REF_TYPE IS '참조PK속성';
COMMENT ON COLUMN REPORT.REF_NO IS '참조번호';
COMMENT ON COLUMN REPORT.REPORT_AT IS '신고시간';
COMMENT ON COLUMN REPORT.READ IS '처리상태';


CREATE TABLE REFERENCE (
	REF_TYPE	number	NOT NULL,
	REF_NAME	varchar2(20) NOT NULL
);

COMMENT ON COLUMN REFERENCE.REF_TYPE IS '참조속성';
COMMENT ON COLUMN REFERENCE.REF_NAME IS '속성이름';

CREATE TABLE EMAIL_CONFIRM(
    CONFIRM_NO number PRIMARY KEY ,
    CODE varchar2(20) not null ,
    EMAIL VARCHAR2(40) not null ,
    CONFIRM_AT DATE default SYSDATE,
    STATUS number(1) default 0
);
COMMENT ON COLUMN EMAIL_CONFIRM.CONFIRM_NO IS '인증식별번호';
COMMENT ON COLUMN EMAIL_CONFIRM.CODE IS '인증코드';
COMMENT ON COLUMN EMAIL_CONFIRM.EMAIL IS '이메일';
COMMENT ON COLUMN EMAIL_CONFIRM.CONFIRM_AT IS '인증요청시간';
COMMENT ON COLUMN EMAIL_CONFIRM.STATUS IS '처리여부';


--Unique
ALTER TABLE MEMBER ADD CONSTRAINT EMAIL_UNQ UNIQUE(EMAIL,SOCAIL_TYPE);
ALTER TABLE USER_PROFILE ADD CONSTRAINT NICKNAME_UNQ UNIQUE(NICK_NAME);

--Primary Key
ALTER TABLE TEMPLATE ADD CONSTRAINT "PK_TEMPLATE" PRIMARY KEY (TEMPLATE_NO);
ALTER TABLE TODO ADD CONSTRAINT "PK_TODOLIST" PRIMARY KEY (TODO_NO);
ALTER TABLE FOLLOW ADD CONSTRAINT "PK_FOLLOW" PRIMARY KEY (FOLLOWER,FOLLOWING);
ALTER TABLE TEMPLATE_TODO ADD CONSTRAINT "PK_TEMPLATE_TODO" PRIMARY KEY (TP_TODO_NO);
ALTER TABLE ALARM ADD CONSTRAINT "PK_ALARM" PRIMARY KEY (ALR_NO);
ALTER TABLE MEMBER ADD CONSTRAINT "PK_USER" PRIMARY KEY (USER_NO);
ALTER TABLE CHALLENGE ADD CONSTRAINT "PK_CHALLENGE" PRIMARY KEY (CHALLENGE_NO);
ALTER TABLE TEMPLATE_CATEGORY ADD CONSTRAINT "PK_TEMPLATE_CATEGORY" PRIMARY KEY (CATEGORY_NO);
ALTER TABLE CHALLENGE_USER ADD CONSTRAINT "PK_CHALLENGE_USER" PRIMARY KEY (CHALLENGE_NO);
ALTER TABLE CHALLENGE_TODO ADD CONSTRAINT "PK_CHALLENGE_TODOLIST" PRIMARY KEY (TODO_NO);
ALTER TABLE REVIEW ADD CONSTRAINT "PK_REVIEW" PRIMARY KEY (REVIEW_NO);
ALTER TABLE FEED ADD CONSTRAINT "PK_FEED" PRIMARY KEY (FEED_NO);
ALTER TABLE USER_PROFILE ADD CONSTRAINT "PK_USER_PROFILE" PRIMARY KEY (USER_NO);
ALTER TABLE OFFICIAL_HOLIDAY ADD CONSTRAINT "PK_OFFICIAL_HOLIDAY" PRIMARY KEY (HOLIDAY_NO);
ALTER TABLE FEED_PHOTO ADD CONSTRAINT "PK_FEED_PHOTO" PRIMARY KEY (	FEED_NO,PHOTO_NO);
ALTER TABLE PHOTO ADD CONSTRAINT "PK_PHOTO" PRIMARY KEY (PHOTO_NO);
ALTER TABLE REPLY ADD CONSTRAINT "PK_REPLY" PRIMARY KEY (REPLY_NO);
ALTER TABLE CHALLENGE_CHAT ADD CONSTRAINT "PK_CHALLENGE_CHAT" PRIMARY KEY (CHAT_NO);
ALTER TABLE REPORT ADD CONSTRAINT "PK_REPORT" PRIMARY KEY (REPORT_NO);
ALTER TABLE REFERENCE ADD CONSTRAINT "PK_REFERENCE" PRIMARY KEY (REF_TYPE);

--Foreign Key
ALTER TABLE TEMPLATE ADD CONSTRAINT "FK_user_TO_TEMPLATE_1" FOREIGN KEY (TP_CREATER)
REFERENCES MEMBER (USER_NO);

ALTER TABLE ALARM ADD CONSTRAINT "FK_user_TO_ALARM_1" FOREIGN KEY (USER_NO)
REFERENCES MEMBER (USER_NO);

ALTER TABLE ALARM ADD CONSTRAINT "FK_REFERENCE_TO_ALARM_1" FOREIGN KEY (REF_TYPE)
REFERENCES REFERENCE (REF_TYPE);

ALTER TABLE CHALLENGE ADD CONSTRAINT "FK_user_TO_CHALLENGE_1" FOREIGN KEY (CREATE_USER)
REFERENCES MEMBER (USER_NO);

ALTER TABLE CHALLENGE ADD CONSTRAINT "FK_TEMPLATE_TO_CHALLENGE_1" FOREIGN KEY (TEMPLATE_NO)
REFERENCES TEMPLATE (TEMPLATE_NO);

ALTER TABLE CHALLENGE_USER ADD CONSTRAINT "FK_CHALLENGE_TO_CHALLENGE_USER_1" FOREIGN KEY (CHALLENGE_NO)
REFERENCES CHALLENGE (CHALLENGE_NO);

ALTER TABLE CHALLENGE_USER ADD CONSTRAINT "FK_user_TO_CHALLENGE_USER_1" FOREIGN KEY (USER_NO)
REFERENCES MEMBER (USER_NO);

ALTER TABLE REVIEW ADD CONSTRAINT "FK_TEMPLATE_TO_REVIEW" FOREIGN KEY (TEMPLATE_NO)
REFERENCES TEMPLATE (TEMPLATE_NO);

ALTER TABLE REVIEW ADD CONSTRAINT "FK_user_TO_REVIEW" FOREIGN KEY (USER_NO)
REFERENCES MEMBER (USER_NO);

ALTER TABLE FEED ADD CONSTRAINT "FK_user_TO_FEED_1" FOREIGN KEY (USER_NO)
REFERENCES MEMBER (USER_NO);

ALTER TABLE HEART ADD CONSTRAINT "FK_user_TO_LIKE_1" FOREIGN KEY (USER_NO)
REFERENCES MEMBER (USER_NO);

ALTER TABLE HEART ADD CONSTRAINT "FK_REFERENCE_TO_LIKE_1" FOREIGN KEY (REF_TYPE)
REFERENCES REFERENCE (REF_TYPE);

ALTER TABLE USER_PROFILE ADD CONSTRAINT "FK_user_TO_USER_PROFILE_1" FOREIGN KEY (USER_NO)
REFERENCES MEMBER (USER_NO);

ALTER TABLE FEED_PHOTO ADD CONSTRAINT "FK_FEED_TO_FEED_PHOTO_1" FOREIGN KEY (FEED_NO)
REFERENCES FEED (FEED_NO);

ALTER TABLE FEED_PHOTO ADD CONSTRAINT "FK_PHOTO_TO_FEED_PHOTO_1" FOREIGN KEY (PHOTO_NO)
REFERENCES PHOTO (PHOTO_NO);

ALTER TABLE PHOTO ADD CONSTRAINT "FK_USER_PROFILE_TO_PHOTO_1" FOREIGN KEY (USER_NO)
REFERENCES USER_PROFILE (USER_NO);

ALTER TABLE REPLY ADD CONSTRAINT "FK_user_TO_REPLY_1" FOREIGN KEY (USER_NO)
REFERENCES MEMBER (USER_NO);

ALTER TABLE REPLY ADD CONSTRAINT "FK_FEED_TO_REPLY_1" FOREIGN KEY (FEED_NO)
REFERENCES FEED (FEED_NO);

ALTER TABLE CHALLENGE_CHAT ADD CONSTRAINT "FK_CHALLENGE_TO_CHALLENGE_CHAT_1" FOREIGN KEY (CHALLENGE_NO)
REFERENCES CHALLENGE (CHALLENGE_NO);

ALTER TABLE CHALLENGE_CHAT ADD CONSTRAINT "FK_user_TO_CHALLENGE_CHAT_1" FOREIGN KEY (USER_NO)
REFERENCES MEMBER (USER_NO);

ALTER TABLE REPORT ADD CONSTRAINT "FK_REFERENCE_TO_REPORT_1" FOREIGN KEY (REF_TYPE)
REFERENCES REFERENCE (REF_TYPE);

------------------------시퀀스
create sequence SEQ_TEMPLATE;
create sequence SEQ_CHAT;
create sequence SEQ_REVIEW;
create sequence SEQ_PHOTO;
create sequence SEQ_FEED;
create sequence SEQ_REPLY;
create sequence SEQ_REPORT;
create sequence SEQ_TODO;
create sequence SEQ_HOLIDAY;
create sequence SEQ_MEMBER;
create sequence SEQ_TEMPLATE_TODO;
create sequence SEQ_CHALLENGE_TODO;
create sequence SEQ_CHALLENGE;
CREATE SEQUENCE SEQ_CONFIRM;


------------------------인덱스


CREATE INDEX TODO_ix01 on TODO(TODO_DATE);

CREATE INDEX CHALLENGE_TODO_ix01 on CHALLENGE_TODO(TODO_DATE);


-- 비밀번호 사이즈 변경 쿼리문 추가하기

