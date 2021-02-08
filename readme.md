# 완료리스트

### 시스템 구조 관련
- [x] 프레임워크 기초
- [x] 인터셉터
- [x] 인터셉터에 의해 로그인 필요로 저지당한경우, 로그인 성공시 원래 목적지로 다시 이동
- [x] attr 서비스 도입
- [x] tomcat7 maven plugin 이용하여 간단 배포 적용
- [x] server 운영 모드일때 context path 제거 / server DB명 변경
- [x] WEB 에서 바로 JSP 파일에 접근 못하도록 수정

### 회원관련
- [x] 로그인/로그아웃
- [x] 회원가입
- [x] 로그인/로그아웃 상태 관련 권한체크
- [x] 비밀번호 암호화
- [x] 아이디찾기
- [x] 비번찾기(임시패스워드 발송)
- [x] 회원정보 수정
- [x] 임시비밀번호 사용시 경고 출력
- [x] 비밀번호 변경후 90일 경과시 경고 출력

### 게시물 관련
- [x] 게시글 CRUD
- [x] 게시물 수정/삭제 관련 권한체크
- [x] 게시물 검색
- [x] 게시물 페이징
- [x] 토스트 에디터 붙이기

# 당장 할일 리스트
- [ ] 게시물 블라인드
- [ ] 좋아요, 싫어요
- [ ] 댓글
- [ ] 대댓글
- [ ] 댓글, ajax화
- [ ] 댓글 좋아요, 싫어요

# 추후 할일 리스트
- [ ] 댓글멘션
- [ ] 내 글에 새 댓글 알림
- [ ] 내 댓글에 추가 댓글 알림
- [ ] 태그
- [ ] 파일업로드
- [ ] 회원인증
- [ ] 관리자페이지
- [ ] 1:1 쪽지
- [ ] 신고

# Maven settings.xml 템플릿
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>서버ID</id>
            <username>톰캣웹관리자계정(배포관리자)의 로그인아이디</username>
            <password>톰캣웹관리자계정(배포관리자)의 비빌번호</password>
        </server>
    </servers>
</settings>
```