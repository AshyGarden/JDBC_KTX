--유저정보
CREATE TABLE train_users(
    user_num NUMBER(4) PRIMARY KEY,                --유저번호
    user_id VARCHAR2(20) UNIQUE NOT NULL,          --유저 아이디
    user_pw VARCHAR2(20) NOT NULL,                 --유저 비밀번호
    user_budget NUMBER(9) NOT NULL,                --유저의 자산 --표값계산
    user_discount NUMBER(3) DEFAULT 0,             --유저의 할인쿠폰 개수(10%)   
    user_rsv_train_tno NUMBER(4) DEFAULT 0,        --예매한 열차번호
    user_rsv_train_rseat VARCHAR2(3) DEFAULT 'N',  --예매한 열차의 열
    user_rsv_train_rev VARCHAR2(5)  DEFAULT 'N'    --예매한 좌석의 정방향여부
);

DROP TABLE train_users;
----------------------------------------------------------------------
-- ktx 8000번대
-- itx 6000번대
-- Saemaeul 4000번대
-- Mugunghwa 2000번대

--열차예매 안한 사람들(1001~1003)
INSERT INTO train_users
    (user_num,user_id,user_pw,user_budget)
VALUES(1001, 'kim1234','kim1234', 50000); 
INSERT INTO train_users
    (user_num,user_id,user_pw,user_budget)
VALUES(1002, 'lee4567','lee4567', 30000); 
INSERT INTO train_users
    (user_num,user_id,user_pw,user_budget)
VALUES(1003, 'park3568','park3568', 120000); 

--열차예매 한 사람들(1004~1009)
INSERT INTO train_users
VALUES(1004, 'jung9987','jung9987', 20000, 3, 6001, 'A', 'true'); 
INSERT INTO train_users
VALUES(1005, 'choi2580','choi2580', 70000, 0, 8001, 'C', 'false'); 
INSERT INTO train_users
VALUES(1006, 'sim3468','sim3468', 40000, 2, 4001, 'B', 'false'); 
INSERT INTO train_users
VALUES(1007, 'kwon9320','kwon9320', 50000, 1, 2001, 'D', 'true'); 
INSERT INTO train_users
VALUES(1008, 'kyoung7710','kyoung7710', 10000, 4, 2001, 'C', 'false'); 
INSERT INTO train_users
VALUES(1009, 'gong4228','gong4228', 30000, 0, 2001, 'A', 'true'); 

UPDATE train_users SET  user_rsv_train_tno = 8001,  user_rsv_train_rseat ='C',  user_rsv_train_rev = 'false'
WHERE user_id = 'choi2580';
UPDATE train_users SET  user_budget =70000
WHERE user_id = 'choi2580';
UPDATE train_users SET  user_rsv_train_tno = null,  user_rsv_train_rseat = null,  user_rsv_train_rev = null
WHERE user_id = 'choi2580';

UPDATE train_users SET  user_rsv_train_tno = 0,  user_rsv_train_rseat ='N',  user_rsv_train_rev = 'N'
WHERE user_id = 'kim1234';
UPDATE train_users SET  user_budget =50000
WHERE user_id = 'kim1234';

COMMIT;


COMMIT;
---------------------------------------------------------------------------

--열차 정보
CREATE TABLE train(
    train_num NUMBER(5) PRIMARY KEY,            --열차번호  
    train_start VARCHAR2(10) NOT NULL,          --열차가 출발하는 곳
    train_depart DATE NOT NULL,                 --예매한 열차출발시간  
    train_togo VARCHAR2(10) NOT NULL,           --열차가 도착하는 곳   
    train_arrive DATE NOT NULL,                 --예매한 열차도착예정시간     
    train_price NUMBER(5) NOT NULL              --열차표 가격
);

DROP TABLE train;
SELECT * FROM train;

-- Mugunghwa 2000번대 30000원
INSERT INTO train
VALUES(2001, 'SEOUL', TO_DATE('2023/05/10 14:00', 'YYYY/MM/DD HH24:MI') , 'BUSAN', TO_DATE('2023/05/10 20:30', 'YYYY/MM/DD HH24:MI'), 30000); 

-- Saemaeul 4000번대 40000원
INSERT INTO train
VALUES(4001, 'DAEJEON',  TO_DATE('2023/05/11 14:30', 'YYYY/MM/DD HH24:MI'), 'GWANGJU',  TO_DATE('2023/05/11 16:30', 'YYYY/MM/DD HH24:MI'), 40000); 

-- itx 6000번대 10000원
INSERT INTO train
VALUES(6001, 'CHUNCHEON',  TO_DATE('2023/05/13 12:00', 'YYYY/MM/DD HH24:MI'), 'SEOUL',  TO_DATE('2023/05/13 16:00', 'YYYY/MM/DD HH24:MI'), 10000); 

-- ktx 8000번대 60000원
INSERT INTO train
VALUES(8001, 'SEOUL',  TO_DATE('2023/05/12 12:00', 'YYYY/MM/DD HH24:MI'), 'BUSAN',  TO_DATE('2023/05/12 16:00', 'YYYY/MM/DD HH24:MI'),60000); 
---------------------------------------------------------------------------------------------------------------


CREATE TABLE train_seats(
    train_seat VARCHAR2(10) PRIMARY KEY,         --예매한 열차의 좌석번호-pk
    train_num NUMBER(10) NOT NULL,               --열차번호 
    train_col VARCHAR2(5) NOT NULL,              --애매한 열차 열
    train_rev VARCHAR2(10)NOT NULL,              --예매한 좌석의 정방향여부 
    train_isrsv VARCHAR2(10) NOT NULL            --좌석의 예매 여부
);

DROP TABLE train_seats;
--무궁화호
INSERT INTO train_seats VALUES('2001AR', 2001, 'A', 'true', 'true');
INSERT INTO train_seats VALUES('2001AF', 2001, 'A', 'false', 'false');
INSERT INTO train_seats VALUES('2001BR', 2001, 'B', 'true', 'false');
INSERT INTO train_seats VALUES('2001BF', 2001, 'B', 'false', 'false');
INSERT INTO train_seats VALUES('2001CR', 2001, 'C', 'true', 'false');
INSERT INTO train_seats VALUES('2001CF', 2001, 'C', 'false', 'true');
INSERT INTO train_seats VALUES('2001DR', 2001, 'D', 'true', 'true');
INSERT INTO train_seats VALUES('2001DF', 2001, 'D', 'false', 'false');

--새마을호
INSERT INTO train_seats VALUES('4001AR', 4001, 'A', 'true', 'false');
INSERT INTO train_seats VALUES('4001AF', 4001, 'A', 'false', 'false');
INSERT INTO train_seats VALUES('4001BR', 4001, 'B', 'true', 'false');
INSERT INTO train_seats VALUES('4001BF', 4001, 'B', 'false', 'true');
INSERT INTO train_seats VALUES('4001CR', 4001, 'C', 'true', 'false');
INSERT INTO train_seats VALUES('4001CF', 4001, 'C', 'false', 'false');
INSERT INTO train_seats VALUES('4001DR', 4001, 'D', 'true', 'false');
INSERT INTO train_seats VALUES('4001DF', 4001, 'D', 'false', 'false');

--ITX
INSERT INTO train_seats VALUES('6001AR', 6001, 'A', 'true', 'true');
INSERT INTO train_seats VALUES('6001AF', 6001, 'A', 'false', 'false');
INSERT INTO train_seats VALUES('6001BR', 6001, 'B', 'true', 'false');
INSERT INTO train_seats VALUES('6001BF', 6001, 'B', 'false', 'true');
INSERT INTO train_seats VALUES('6001CR', 6001, 'C', 'true', 'false');
INSERT INTO train_seats VALUES('6001CF', 6001, 'C', 'false', 'false');
INSERT INTO train_seats VALUES('6001DR', 6001, 'D', 'true', 'false');
INSERT INTO train_seats VALUES('6001DF', 6001, 'D', 'false', 'false');

--KTX
INSERT INTO train_seats VALUES('8001AR', 8001, 'A', 'true', 'true');
INSERT INTO train_seats VALUES('8001AF', 8001, 'A', 'false', 'false');
INSERT INTO train_seats VALUES('8001BR', 8001, 'B', 'true', 'false');
INSERT INTO train_seats VALUES('8001BF', 8001, 'B', 'false', 'false');
INSERT INTO train_seats VALUES('8001CR', 8001, 'C', 'true', 'false');
INSERT INTO train_seats VALUES('8001CF', 8001, 'C', 'false', 'true');
INSERT INTO train_seats VALUES('8001DR', 8001, 'D', 'true', 'false');
INSERT INTO train_seats VALUES('8001DF', 8001, 'D', 'false', 'false');
COMMIT;

--
DROP TABLE train_suggestion;

CREATE TABLE train_suggestion(
    suggestNum NUMBER(5) PRIMARY KEY,
    user_id VARCHAR2(20) UNIQUE NOT NULL,
    user_suggestion VARCHAR2(200)
);

DROP SEQUENCE ts_seq;
CREATE SEQUENCE ts_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE
    NOCACHE;
COMMIT;
INSERT INTO train_suggestion VALUES(ts_seq.NEXTVAL, 'park3568', '이거 왜 예약이 안되요?');
INSERT INTO train_suggestion VALUES(ts_seq.NEXTVAL, 'kyoung7710', '혹시 6월달 열차 시간은 언제쯤 업로드 되나요?');
INSERT INTO train_suggestion VALUES(ts_seq.NEXTVAL, 'choi2580', '아 빨리 여행가고 싶다.');
INSERT INTO train_suggestion VALUES(ts_seq.NEXTVAL, 'sim3468', '윗 댓님 여기 건의게시판이에요.');

SELECT tu.user_id, tu.user_rsv_train_tno, tu.user_rsv_train_rseat, tu.user_rsv_train_rev
FROM train_users tu
JOIN train_seats ts
ON tu.user_rsv_train_tno = ts.train_num 
AND tu.user_rsv_train_rseat = ts.train_col 
AND tu.user_rsv_train_rev = ts.train_rev
WHERE ts.train_isrsv='true' AND tu.user_id='gong4228'; --id=?

/*
SELECT tu.user_id, ts.train_seat
FROM train_users tu
LEFT OUTER JOIN train_seats ts
ON UPPER(TO_CHAR(tu.user_rsv_train_tno)||TO_CHAR(tu.user_rsv_train_rseat)||TO_CHAR(tu.user_rsv_train_rev)) = UPPER(ts.train_seat)
WHERE ts.train_isrsv='true';
*/

--TO_CHAR(train_users.user_rsv_train_tno);
--TO_CHAR(train_seats.train_num || train_seats.train_col);

UPDATE train_seats SET train_isrsv='true'
WHERE train_seat = '8001CF';

COMMIT;
ROLLBACK;

