<h1 align="center">Welcome to  JspCommunity 👋</h1>

<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000" />
  <a href="https://github.com/qbj700/JspCommunity#readme" target="_blank">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" />
  </a>
  <a href="https://github.com/qbj700/JspCommunity/graphs/commit-activity" target="_blank">
    <img alt="Maintenance" src="https://img.shields.io/badge/Maintained%3F-yes-green.svg" />
  </a>
</p>


### 사이트 바로가기 [modumoa.site](https://modumoa.site)
<br>

### 포트폴리오 - 바로가기
[커뮤니티 사이트 기획서](https://drive.google.com/file/d/1lhhku9HOb1FBjSfP4r_rog3JGWcc55TA/view?usp=sharing)
<br>
[커뮤니티 사이트 기술 설명서](https://drive.google.com/file/d/1_jP1vVjUTr-2nMv0xy2_ESeMkCf5MRaY/view?usp=sharing)
<br>
<br>

## 프로젝트 소개
이 프로젝트는 JAVA기반으로 Servlet 및 JSP 를 이용한 MVC 구조의 
커뮤니티 사이트 이며, 개인 프로젝트 입니다.

해당 프로젝트는 회원가입을 통해 자유롭게 이용할 수 있도록 구현하였고,
서버 배포를 진행하여 지속적으로 관리하고 있습니다.

<br>
<br>
<br>
<br>



## 개발환경
### 개발 툴
* STS
* SQLYogCommunity Edition
* Maven
* Git, Github
* Visual Studio Code
* Chorme
* Winodws 10
* Putty
* Filezilla

### 기술스택
* CentOS
* Tomcat
* JDK 1.8
* Servlet / JSP
* JDBC
* MariaDB
* jQuery
* Ajax

<br>
<br>

## 현재 구현된 기능

#### 시스템 구조 관련
- [x] 프레임워크 기초
- [x] 인터셉터
- [x] 인터셉터에 의해 로그인 필요로 저지당한경우, 로그인 성공시 원래 목적지로 다시 이동
- [x] attr 서비스 도입
- [x] tomcat7 maven plugin 이용하여 간단 배포 적용
- [x] server 운영 모드일때 context path 제거 / server DB명 변경
- [x] WEB 에서 바로 JSP 파일에 접근 못하도록 수정

#### 회원관련
- [x] 로그인/로그아웃
- [x] 회원가입
- [x] 로그인/로그아웃 상태 관련 권한체크
- [x] 비밀번호 암호화
- [x] 아이디찾기
- [x] 비번찾기(임시패스워드 발송)
- [x] 회원정보 수정
- [x] 임시비밀번호 사용시 경고 출력
- [x] 비밀번호 변경후 90일 경과시 경고 출력

#### 게시물 관련
- [x] 게시글 CRUD
- [x] 게시물 수정/삭제 관련 권한체크
- [x] 게시물 검색
- [x] 게시물 페이징
- [x] 토스트 에디터 붙이기
- [x] 댓글 작성
- [x] 좋아요, 싫어요
- [x] 대댓글
- [x] 댓글, ajax화


## 추후 구현할 기능
- [ ] 댓글멘션
- [ ] 댓글 수정
- [ ] 내 글에 새 댓글 알림
- [ ] 내 댓글에 추가 댓글 알림
- [ ] 댓글 좋아요, 싫어요
- [ ] 태그
- [ ] 파일업로드
- [ ] 회원인증
- [ ] 관리자페이지
- [ ] 1:1 쪽지
- [ ] 신고
- [ ] 게시물 블라인드


<br>
<br>
<br>

## Author

👤 **최형석**

* Website: https://modumoa.site/
* Github: [@qbj700](https://github.com/qbj700)


## 📝 Copyright

Copyright © 2021 [qbj700](https://github.com/qbj700).<br />


---

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
