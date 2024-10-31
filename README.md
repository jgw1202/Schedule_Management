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

