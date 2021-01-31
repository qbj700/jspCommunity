DROP DATABASE IF EXISTS jspCommunity;
CREATE DATABASE jspCommunity;
USE jspCommunity;

# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(50) NOT NULL UNIQUE,
    loginPw VARCHAR(200) NOT NULL,
    `name` CHAR(50) NOT NULL,
    `nickname` CHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    cellphoneNo CHAR(20) NOT NULL,
    authLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증/4=관리자'
);

# 회원1 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "user1",
loginPw = SHA2("user1", 256),
`name` = "김민수",
`nickname` = "강바람",
`email` = "qbj700@gmail.com",
cellphoneNo = "01012341234";


# 회원2 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "user2",
loginPw = SHA2("user2", 256),
`name` = "김미소",
`nickname` = "미소천사",
`email` = "qbj700@gmail.com",
cellphoneNo = "01011112222";


# 게시판 테이블 생성
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(10) NOT NULL UNIQUE,
    `name` CHAR(10) NOT NULL UNIQUE
);

# 공지사항 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = "notice",
`name` = "공지사항";

# 자유 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = "free",
`name` = "자유";

# 방명록 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = "guestBook",
`name` = "방명록";

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    title CHAR(100) NOT NULL,
    `body` LONGTEXT NOT NULL,
    hitsCount INT(10) UNSIGNED NOT NULL DEFAULT 0
);

# 테스트 게시물 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목3',
`body` = '내용3';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = '제목4',
`body` = '내용4';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = '제목5',
`body` = '내용5';

# 부가정보 테이블 생성
CREATE TABLE attr(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    relTypeCode CHAR(20) NOT NULL,
    relId INT(10) UNSIGNED NOT NULL,
    typeCode CHAR(30) NOT NULL,
    type2Code CHAR(30) NOT NULL,
    `value` TEXT NOT NULL,
    `expireDate` DATETIME NULL,
    UNIQUE INDEX (relTypeCode, relId, typeCode, type2Code),
    INDEX (relTypeCode, typeCode, type2Code)
);

INSERT INTO attr 
(regDate, updateDate, expireDate, `relTypeCode`, `relId`, `typeCode`, `type2Code`, `value`) 
VALUES 
(NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), 'member', '1', 'extra', 'isValidPassword', '1') 
ON DUPLICATE KEY UPDATE 
updateDate = NOW() , expireDate = DATE_ADD(NOW(), INTERVAL 90 DAY) , `value` = '1';

INSERT INTO attr 
(regDate, updateDate, expireDate, `relTypeCode`, `relId`, `typeCode`, `type2Code`, `value`) 
VALUES 
(NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 30 SECOND), 'member', '2', 'extra', 'isValidPassword', '1') 
ON DUPLICATE KEY UPDATE 
updateDate = NOW() , expireDate = DATE_ADD(NOW(), INTERVAL 30 SECOND) , `value` = '1';