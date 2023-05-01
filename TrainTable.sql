--유저정보
DROP TABLE train_users;
CREATE TABLE train_users(
    user_num NUMBER(4) PRIMARY KEY,             --유저번호
    user_id VARCHAR2(20) UNIQUE NOT NULL,       --유저 아이디
    user_pw VARCHAR2(20) NOT NULL,              --유저 비밀번호
    user_budget NUMBER(9) NOT NULL,             --유저의 자산 --표값계산
    user_discount NUMBER(3),                    --유저의 할인쿠폰 개수(10%)   
    user_rsv_train_tno NUMBER(4),               --예매한 열차번호
    user_rsv_train_rseat VARCHAR2(3),           --예매한 열차의 좌석번호
    user_rsv_train_rev VARCHAR2(5)              --예매한 좌석의 정방향여부
);
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
---------------------------------------------------------------------------

--열차 정보
DROP TABLE train;
CREATE TABLE train(
    train_num NUMBER(5) PRIMARY KEY,            --열차번호  
    train_start VARCHAR2(10) NOT NULL,          --열차가 출발하는 곳
    train_depart DATE NOT NULL,                 --예매한 열차출발시간  
    train_togo VARCHAR2(10) NOT NULL,           --열차가 도착하는 곳   
    train_arrive DATE NOT NULL,                 --예매한 열차도착예정시간     
    train_price NUMBER(5) NOT NULL              --열차표 가격
);

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

DROP TABLE train_seats;
CREATE TABLE train_seats(
    train_num NUMBER(5) NOT NULL,               --열차번호 
    train_seat VARCHAR2(3),                     --예매한 열차의 좌석번호
    train_rev VARCHAR2(5),                      --예매한 좌석의 정방향여부 
    train_isrsv VARCHAR2(5) NOT NULL            --좌석의 예매 여부
);

--무궁화호
INSERT INTO train_seats VALUES(2001, 'A', 'true', 'true');
INSERT INTO train_seats VALUES(2001, 'A', 'false', 'false');
INSERT INTO train_seats VALUES(2001, 'B', 'true', 'false');
INSERT INTO train_seats VALUES(2001, 'B', 'false', 'false');
INSERT INTO train_seats VALUES(2001, 'C', 'true', 'false');
INSERT INTO train_seats VALUES(2001, 'C', 'false', 'true');
INSERT INTO train_seats VALUES(2001, 'D', 'true', 'true');
INSERT INTO train_seats VALUES(2001, 'D', 'false', 'false');

--새마을호
INSERT INTO train_seats VALUES(4001, 'A', 'true', 'false');
INSERT INTO train_seats VALUES(4001, 'A', 'false', 'false');
INSERT INTO train_seats VALUES(4001, 'B', 'true', 'false');
INSERT INTO train_seats VALUES(4001, 'B', 'false', 'true');
INSERT INTO train_seats VALUES(4001, 'C', 'true', 'false');
INSERT INTO train_seats VALUES(4001, 'C', 'false', 'false');
INSERT INTO train_seats VALUES(4001, 'D', 'true', 'false');
INSERT INTO train_seats VALUES(4001, 'D', 'false', 'false');

--ITX
INSERT INTO train_seats VALUES(6001, 'A', 'true','true');
INSERT INTO train_seats VALUES(6001, 'A', 'false','false');
INSERT INTO train_seats VALUES(6001, 'B', 'true','false');
INSERT INTO train_seats VALUES(6001, 'B', 'false','false');
INSERT INTO train_seats VALUES(6001, 'C', 'true','false');
INSERT INTO train_seats VALUES(6001, 'C', 'false','false');
INSERT INTO train_seats VALUES(6001, 'D', 'true','false');
INSERT INTO train_seats VALUES(6001, 'D', 'false','false');

--KTX
INSERT INTO train_seats VALUES(8001, 'A', 'true','false');
INSERT INTO train_seats VALUES(8001, 'A', 'false','false');
INSERT INTO train_seats VALUES(8001, 'B', 'true','false');
INSERT INTO train_seats VALUES(8001, 'B', 'false','false');
INSERT INTO train_seats VALUES(8001, 'C', 'true','false');
INSERT INTO train_seats VALUES(8001, 'C', 'false','true');
INSERT INTO train_seats VALUES(8001, 'D', 'true','false');
INSERT INTO train_seats VALUES(8001, 'D', 'false','false');
