# 일정 관리 프로그램


### 일정을 관리해주는 프로그램입니다.

## API 명세서

### 일정 API
| 기능       | Method | URL                  | request  | response | 상태코드                                   |
|----------|--------|----------------------|----------|----------|----------------------------------------|
| 일정 등록    | POST   | /api/schedulers      | 요청 body  | 등록 정보    | 201 : CREATED (정상 등록)  400 비정상 값       |
| 일정 목록 조회 | GET    | /api/schedulers      | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 404 : 조회 실패           |
| 일정 조회    | GET    | /api/schedulers/{id} | 요청 param     | 조회 정보    | 200 : OK   (정상 조회) 404 조회 대상 없음        |
| 일정 수정    | PUT    | /api/schedulers/{id} | 요청 body  | 수정 정보    | 200 : OK  (정상 수정)        404 : 수정 실패   |
| 일정 삭제    | DELETE | /api/schedulers/{id} | 요청 param     | -        | 200 : OK     (정상 삭제)  404 : 없는 값 삭제 시도 |


- 일정 등록

요청
```
{
 "userId" : "1",

 "title" : "제목",

 "content" : "할 일"
}
```
응답
```
{
    "id": "1",
    "userId": "1",
    "title" : "제목1",
    "content" : "할 일 1",
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
}
```

- 일정 목록 조회

요청
```
요청 없음
```
응답
```
"schedulers" :[ {
    "id": "1",
    "userId": "1",
    "title" : "제목1",
    "content" : "할 일 1",
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
}, {
    "id": "2",
    "userId": "2",
    "title" : "제목2",
    "content" : "할 일 2",
    "createdAt":"2024-10-31",
    "updatedAt":"2024-10-31"
    },
]
```

- 일정 조회

요청
```
/api/schedules/{scheduler_id}
```
응답
```
{
    "ud": "1",
    "userId": "1",
    "title" : "제목1",
    "content" : "할 일 1",
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
}
```
- 일정 수정

요청
```
 {
    "title" : "수정된 제목1",
    "content" : "수정된 할 일1",
  }
```
응답
```
{
    "id": "1",
    "userId": "1",
    "title" : "제목1",
    "content" : "할 일 1",
}
```
- 일정 삭제

요청
```
/api/schedules/{scheduler_id}
```
응답
```
{
    "id" : "1"
}
```

### 유저 API
| 기능       | Method | URL                 | request  | response | 상태코드                             |
|----------|--------|---------------------|----------|----------|----------------------------------|
| 유저 등록    | POST   | /api/users          | 요청 body  | 등록 정보    | 201 : CREATED (정상 등록)  400 비정상 값 |
| 유저 정보 조회 | GET    | /api/users      | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 404 : 조회 실패     |
| 유저 수정    | PUT    | /api/users/{id} | 요청 body  | 수정 정보    | 200 : OK  (정상 수정)  404 : 수정 실패   |

- 유저 등록

요청
```
 {
    "id": "user1",
    "password": "1234",
    "name": "Jung",
    "email" : "email@qwer.com"
}
```
응답
```
 {
    "id": "user1",
    "password": "1234",
    "name": "Jung",
    "email" : "email@qwer.com"
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
}
```

- 유저 정보 조회

요청
```
/api/schedules/{user_id}
```
응답
```
 {
    "id": "user1",
    "name": "Jung",
    "email" : "email@qwer.com"
}
```

- 유저 수정

요청
```
{
    "name": "Jung",
    "email" : "email@qwer.com"
}
```
응답
```
 {
    "id": "user1",
    "name": "Jung",
    "email" : "email@qwer.com"
}
```


## ERD 

유저 -< 일정 

유저는 여러 일정을 가질 수 있다. 일대다 관계

![](https://github.com/user-attachments/assets/416e2d95-3db9-4f34-a752-d24c26a5ef62)

### 일정 테이블
| Key | Logical | physical     | Domain   | Type    | Allow Null |
|-----|---------|--------------|----------|---------|------------|
| PK  | 아이디     | id            |   | Long    | N          |
| FK  | 아이디     | user_id      |   | Long    | N          |
|     | 제목      | title        |  | VARCHAR | N          |
|     | 할 일     | contents     |  | VARCHAR | N          |
|     | 생성일자    | created_date |  | DATE    | N          |
|     | 수정일자    | updated_date |  | DATE    | N          |

### 유저 테이블 
| Key      | Logical | physical | Domain   | Type    | Allow Null |
|----------|---------|---------|----------|---------|------------|
| PK       | 아이디     | id      |   | Long    | N          |
|    | 비밀번호    | password |  | VARCHAR | N          |
|  | 이름      | name    |  | VARCHAR | N          |
|     | 이메일     | email   |  | VARCHAR | N          |
|    | 생성일자    | created_date |  | DATE    | N          |
|    | 수정일자    | updated_date |  | DATE    | N          |

## SQL

CREATE TABLE scheduler (

id BIGINT NOT NULL AUTO_INCREMENT,

user_id BIGINT NOT NULL,

title VARCHAR(255) NOT NULL,

contents VARCHAR(255) NOT NULL,

created_date DATE NOT NULL,

updated_date DATE NOT NULL,

PRIMARY KEY (scheduler_id),

FOREIGN KEY (user_id) REFERENCES user(user_id)

);

------------------------------------------------------

CREATE TABLE user (

id BIGINT NOT NULL AUTO_INCREMENT,

password VARCHAR(255) NOT NULL,

name VARCHAR(255) NOT NULL,

email VARCHAR(255) NOT NULL,

created_date DATE NOT NULL,

updated_date DATE NOT NULL,

PRIMARY KEY (user_id)

);




