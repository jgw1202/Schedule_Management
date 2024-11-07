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

## TIL
- 배경
일정(Scheduler)과 사용자(User) 정보를 효율적으로 관리하고자 Spring Boot와 JDBC를 이용하여 API를 개발하였습니다. 이 시스템은 일정 생성, 조회, 수정, 삭제 기능을 제공하며, 데이터의 무결성과 사용자 편의를 위해 다양한 유효성 검사 및 예외 처리가 필요합니다. 

- 발단
여러 예외 상황이 발생하기 시작했습니다. 일정 생성 시 입력된 데이터가 잘못되거나 필수 값이 누락되는 경우가 발생했고, 특정 일정이나 사용자를 조회할 때 존재하지 않는 데이터를 조회하려는 요청이 있었습니다. 일정 수정 및 삭제 시 비밀번호가 일치하지 않아 발생하는 오류도 있었습니다. 이러한 예외 상황에 대해 클라이언트에게 적절한 에러 메시지와 HTTP 상태 코드를 반환하는 것이 중요해졌습니다.

- 전개
이 문제를 해결하기 위해 다음과 같은 예외 처리를 추가하였습니다:

유효성 검사: 잘못된 입력이나 누락된 필수 값을 미리 방지하기 위해 DTO 클래스에 유효성 검사 어노테이션(@NotBlank, @Size, @Email)을 추가하고, 컨트롤러 메서드에 @Valid 어노테이션을 사용하여 요청 본문의 유효성을 검사했습니다.
사용자 정의 예외 클래스: ResourceNotFoundException, UnauthorizedException 등의 사용자 정의 예외 클래스를 생성하여 특정 예외 상황을 보다 명확하게 처리할 수 있도록 했습니다.
글로벌 예외 처리: @ControllerAdvice와 @ExceptionHandler를 활용하여 공통 예외 처리 로직을 구현했습니다. 이를 통해 발생한 예외에 대해 일관된 HTTP 상태 코드와 에러 메시지를 반환했습니다.
- 위기
예외 처리를 추가했음에도 불구하고, 예외 상황 발생 시 클라이언트가 이해하기 어려운 메시지가 반환되거나, 불필요하게 많은 데이터가 반환되는 문제가 여전히 남아있었습니다. 특히 페이지네이션을 구현할 때 잘못된 페이지 번호나 크기를 요청하는 경우 빈 배열을 반환해야 했지만, 이를 적절히 처리하지 못하는 경우가 있었습니다.

- 절정
이 문제를 해결하기 위해 예외 처리 로직을 보다 세분화하고, 페이지네이션 로직을 개선하였습니다:

세분화된 예외 처리: 잘못된 입력, 존재하지 않는 데이터 조회, 비밀번호 불일치 등의 구체적인 상황에 대해 각각의 예외 클래스를 사용하여 처리하였습니다.
페이지네이션 로직 개선: 잘못된 페이지 번호나 크기를 요청하는 경우 빈 배열을 반환하도록 로직을 수정하고, 이를 클라이언트에게 명확하게 전달하도록 했습니다.

- 결말
- 이러한 수정과 개선을 통해 예외 상황이 발생할 때 클라이언트에게 명확하고 일관된 에러 메시지와 HTTP 상태 코드를 제공할 수 있게 되었습니다. 페이지네이션 로직도 개선하여 잘못된 요청에 대해 빈 배열을 반환하도록 처리했습니다.