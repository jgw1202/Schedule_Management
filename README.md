# 일정 관리 프로그램


### 일정을 관리해주는 프로그램입니다.

## API 명세서

### 일정 API
| 기능       | Method | URL                  | request  | response | 상태코드                                             |
|----------|--------|----------------------|----------|----------|--------------------------------------------------|
| 일정 등록    | POST   | /api/schedulers      | 요청 body  | 등록 정보    | 201 : CREATED (정상 등록)  400 비정상 값                 |
| 일정 목록 조회 | GET    | /api/schedulers      | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 400 비정상 값 404 : 조회 실패           |
| 일정 조회    | GET    | /api/schedulers/{id} | 요청 param     | 조회 정보    | 200 : OK   (정상 조회) 400 비정상 값 404 조회 대상 없음        |
| 일정 수정    | PUT    | /api/schedulers/{id} | 요청 body  | 수정 정보    | 200 : OK  (정상 수정)    400 비정상 값     404 : 수정 실패   |
| 일정 삭제    | DELETE | /api/schedulers/{id} | 요청 param     | -        | 200 : OK     (정상 삭제)  400 비정상 값 404 : 없는 값 삭제 시도 |


- 일정 등록

요청
```
{
 "password" : "1234",

 "userName" : "작성자명1",

 "contents" : "할 일"
}
```
응답
```
{
    "id": 1,
    "userName" : "작성자명1",
    "contents" : "할 일 1",
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
    "id": 1,
    "userName" : "작성자명1",
    "contents" : "할 일 1",
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
}, {
    "id": 2,
    "userName" : "작성자명2",
    "contents" : "할 일 2",
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
    "id": 1,
    "userName" : "작성자명1",
    "contents" : "할 일 1",
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
}
```
- 일정 수정

요청
```
 {
    "password" : "1234"
    "userName" : "작성자명1",
    "contents" : "수정된 할 일1",
  }
```
응답
```
{
    "id": 1,
    "userName" : "작성자명1",
    "contents" : "할 일 1",
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
}
```
- 일정 삭제

요청
```
 {
    "password" : "1234"
  }
```
응답
```
{
 응답 없음
}
```

### 유저 API
| 기능       | Method | URL                 | request  | response | 상태코드                                     |
|----------|--------|---------------------|----------|----------|------------------------------------------|
| 유저 등록    | POST   | /api/users          | 요청 body  | 등록 정보    | 201 : CREATED (정상 등록)  400 비정상 값         |
| 유저 정보 조회 | GET    | /api/users      | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 400 비정상 값 404 : 조회 실패   |
| 유저 수정    | PUT    | /api/users/{id} | 요청 body  | 수정 정보    | 200 : OK  (정상 수정)  400 비정상 값 404 : 수정 실패 |

- 유저 등록

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
    "id": 1,
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
    "id": 1,
    "name": "Jung",
    "email" : "email@qwer.com"
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
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
    "id": 1,
    "name": "Jung",
    "email" : "email@qwer.com"
    "createdAt":"2024-10-30",
    "updatedAt":"2024-10-30"
}
```


## ERD 

유저 -< 일정 

유저는 여러 일정을 가질 수 있다. 일대다 관계

![image](https://github.com/user-attachments/assets/583480a3-ce86-4708-81ca-9747cbd939da)


### 일정 테이블
| Key | Logical | physical     | Domain   | Type      | Allow Null |
|-----|---------|--------------|----------|-----------|------------|
| PK  | 아이디     | id           |   | BIGINT    | N          |
| FK  | 아이디     | user_id      |   | BIGINT    | N          |
|     | 비밀번호      | password     |  | VARCHAR(255)   | N          |
|  | 이름      | user_name    |  | VARCHAR(255)      | N          |
|     | 할 일     | contents     |  | VARCHAR(255)   | N          |
|     | 생성일자    | created_date |  | TIMESTAMP | N          |
|     | 수정일자    | updated_date |  | TIMESTAMP      | N          |

### 유저 테이블 
| Key      | Logical | physical   | Domain   | Type         | Allow Null |
|----------|---------|------------|----------|--------------|------------|
| PK       | 아이디     | id         |   | BIGINT       | N          |
|  | 이름      | name       |  | VARCHAR(255)      | N          |
|     | 이메일     | email      |  | VARCHAR(255)      | N          |
|    | 생성일자    | created_at |  | TIMESTAMP    | N          |
|    | 수정일자    | updated_at |  | TIMESTAMP    | N          |

## SQL

CREATE TABLE scheduler (

id BIGINT NOT NULL AUTO_INCREMENT,

user_id BIGINT NOT NULL,

password VARCHAR(255) NOT NULL,

user_name VARCHAR(255) NOT NULL,

contents VARCHAR(255) NOT NULL,

created_at TIMESTAMP NOT NULL,

updated_at TIMESTAMP NOT NULL,

PRIMARY KEY (id),

FOREIGN KEY (id) REFERENCES user(id)

);

------------------------------------------------------

CREATE TABLE user (

id BIGINT NOT NULL AUTO_INCREMENT,

name VARCHAR(255) NOT NULL,

email VARCHAR(255) NOT NULL,

created_at TIMESTAMP NOT NULL,

updated_at TIMESTAMP NOT NULL,

PRIMARY KEY (id)

);




