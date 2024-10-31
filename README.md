# 일정 관리 프로그램


### 일정을 관리해주는 프로그램입니다.

## API 명세서

| 기능       | Method | URL                | request  | response | 상태코드                  |
|----------|--------|--------------------|----------|----------|-----------------------|
| 일정 등록    | POST   | /api/scheduler     | 요청 body  | 등록 정보    | 201 : CREATED (정상 등록) |
| 일정 목록 조회 | GET    | /api/scheduler | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회)      |
| 일정 조회    | GET    | /api/scheduler/{id}     | 요청 param     | 조회 정보    | 200 : OK   (정상 조회)    |
| 일정 수정    | PUT    | /api/scheduler/{id}     | 요청 body  | 수정 정보    | 200 : OK  (정상 수정)     |
| 일정 삭제    | DELETE | /api/scheduler/{id}     | 요청 param     | -        | 200 : OK     (정상 삭제)  |

## ERD


| Key      | Logical | physical     | Domain   | Type    | Allow Null |
|----------|---------|--------------|----------|---------|-----------|
| PK       | 아이디     | scheduler_id |   | Long    | N         |
|  | 이름      | name         |  | VARCHAR | N)        |
|    | 비밀번호    | password     |  | VARCHAR | N         |
|     | 할 일     | contents     |  | DATE    | Y        |
|    | 생성일자    | created_date |  | DATE    | N |
|    | 수정일자    | updated_date |  | DATE    | N |

## SQL

CREATE TABLE scheduler (

scheduler_id BIGINT NOT NULL AUTO_INCREMENT,

name VARCHAR(255) NOT NULL,

password VARCHAR(255) NOT NULL,

contents VARCHAR(255),

created_date DATE NOT NULL,

updated_date DATE NOT NULL,

PRIMARY KEY (scheduler_id)

);

