DROP DATABASE IF EXISTS `YUMYUM`;
CREATE DATABASE `YUMYUM`;
USE `YUMYUM`;

-- 회원 정보 테이블
CREATE TABLE `users` (
	`id` BIGINT	PRIMARY KEY AUTO_INCREMENT,
	`user_id` VARCHAR(255) NOT NULL UNIQUE,
	`user_pw` VARCHAR(255) NOT NULL,
	`user_name`	VARCHAR(255) NOT NULL,
	`user_email` VARCHAR(255) NOT NULL,
    `user_phone` VARCHAR(30) NOT NULL,
	`user_business_number` VARCHAR(20) NOT NULL UNIQUE,
	`privacy_policy_agreed`	BOOLEAN	NOT NULL DEFAULT FALSE, -- 개인 정보 동의
	`marketing_agreed` BOOLEAN NOT NULL DEFAULT FALSE, -- 마케팅 수신 동의
    `join_path` VARCHAR(5) NOT NULL COMMENT '가입경로 (HOME, KAKAO, NAVER)',
    `sns_id` VARCHAR(255) DEFAULT NULL COMMENT 'OAuth 사용자 아이디'
);


-- 가게 정보 테이블
CREATE TABLE `stores` (
	`id` BIGINT	PRIMARY KEY AUTO_INCREMENT,
	`owner_id` BIGINT NOT NULL,
	`store_name` VARCHAR(255) NOT NULL,
	`logo_url`	LONGTEXT NOT NULL,
	`category` ENUM('치킨', '중식', '돈까스_회', '피자', '패스트푸드', '찜_탕', '족발_보쌈', '분식', '카페_디저트', '한식', '고기', '양식', '아시안', '야식', '도시락') NOT NULL,
	`opening_time` TIME	NOT NULL,
	`closing_time` TIME NOT NULL,
	`break_start_time` TIME,
	`break_end_time` TIME,
	`zone_code` VARCHAR(255),
    `address` VARCHAR(255),
    `detail_address` VARCHAR(255),
	`description` TEXT,
    FOREIGN KEY (owner_id) REFERENCES `users` (id) ON DELETE CASCADE
);


-- 메뉴별 카테고리 테이블 (인기 메뉴, 세트 메뉴, 사이드메뉴, 음료 ...)
CREATE TABLE `menu_categories` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `store_id` BIGINT NOT NULL,
	`menu_category` VARCHAR(255)	NOT NULL,
    `menu_category_sequence` INT NOT NULL,
    FOREIGN KEY (store_id) REFERENCES `stores` (id) ON DELETE CASCADE
);


-- 메뉴 정보 테이블
CREATE TABLE `menus` (
	`id` BIGINT	PRIMARY KEY AUTO_INCREMENT,
    `store_id` BIGINT NOT NULL,
    `category_id` BIGINT NOT NULL,
	`menu_name`	VARCHAR(255) NOT NULL,
	`image_url`	VARCHAR(255) DEFAULT '/images/profile/default1.png',
	`menu_description` TEXT,
    `menu_price` INT NOT NULL,
    `is_available` BOOLEAN NOT NULL	DEFAULT TRUE,
    FOREIGN KEY (store_id) REFERENCES `stores` (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES `menu_categories` (id) ON DELETE CASCADE
);


-- 메뉴 옵션(사이즈, 추가 토핑 등)
CREATE TABLE `menu_options`(
	`id` BIGINT	PRIMARY KEY AUTO_INCREMENT,
    `option_name` VARCHAR(255) NOT NULL
);


-- 메뉴, 메뉴 옵션 연결 테이블
CREATE TABLE `menu_option_group` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `menu_id` BIGINT NOT NULL,
    `menu_option_id` BIGINT NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES `menus`(id) ON DELETE CASCADE
);


-- 주문 정보 테이블
CREATE TABLE `orders` (
	`id` BIGINT	PRIMARY KEY AUTO_INCREMENT,
    `store_id` BIGINT NOT NULL,
	`delivery_address` VARCHAR(255) NOT NULL,
	`order_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `order_state` VARCHAR(100) NOT NULL DEFAULT '0', -- 주문 상태 (0: 접수대기 / 1: 처리중 / 2: 주문완료) 
    FOREIGN KEY (store_id) REFERENCES `stores` (id) ON DELETE CASCADE
);


-- 주문 목록 내 주문 1개 상세 정보 테이블
CREATE TABLE `order_details` (
	`id` BIGINT	PRIMARY KEY AUTO_INCREMENT,
    `order_id` BIGINT NOT NULL,
    `menu_id` BIGINT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `orders` (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES `menus` (id) ON DELETE CASCADE
);


-- 메뉴 옵션의 세부사항(S, M, L ... 등)
CREATE TABLE `menu_option_details`(
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `menu_option_id` BIGINT NOT NULL, -- 메뉴 옵션 테이블 id
    `option_detail_name` VARCHAR(255) NOT NULL,
    `additional_fee` INT NOT NULL DEFAULT 0,
    FOREIGN KEY (menu_option_id) REFERENCES `menu_options` (id) ON DELETE CASCADE
);


-- 주문한 메뉴 옵션 연결 테이블
CREATE TABLE `order_menu_option` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `order_detail_id` BIGINT NOT NULL,
    `menu_option_detail_id` BIGINT NOT NULL
);


-- 손님 정보 테이블
CREATE TABLE `guests` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `order_id` BIGINT NOT NULL,
    `guest_nickname` VARCHAR(255) NOT NULL UNIQUE,
	`profile_image` VARCHAR(255) DEFAULT '/images/profile/default.png',
    FOREIGN KEY (order_id) REFERENCES `orders` (id) ON DELETE CASCADE
);


-- 리뷰 정보 테이블
CREATE TABLE `reviews` (
	`id` BIGINT	PRIMARY KEY AUTO_INCREMENT,
	`order_id` BIGINT NOT NULL,
    `guest_id` BIGINT NOT NULL,
	`store_id` BIGINT NOT NULL,
	`rating` INT, -- 별점
	`review_date` DATETIME NOT NULL,
	`review_text` TEXT,
	`is_reported` BOOLEAN DEFAULT FALSE, -- 신고 기능
    FOREIGN KEY (order_id) REFERENCES `orders` (id) ON DELETE CASCADE,
	FOREIGN KEY (guest_id) REFERENCES `guests` (id) ON DELETE CASCADE,
    FOREIGN KEY (store_id) REFERENCES `stores` (id) ON DELETE CASCADE
);

-- 리뷰 사진 테이블
CREATE TABLE `review_photos` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`review_id` BIGINT	NOT NULL,
	`photo_url`	VARCHAR(255),
    FOREIGN KEY (review_id) REFERENCES `reviews` (id) ON DELETE CASCADE
);

-- 리뷰 이벤트 공지사항 정보 테이블
CREATE TABLE `review_event_notices` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`store_id` BIGINT,
    `notice_date` DATETIME NOT NULL,
	`notice_photo_url` LONGTEXT,
    `notice_text`	TEXT,
	FOREIGN KEY (store_id) REFERENCES `stores` (id) ON DELETE CASCADE
);

-- 작성된 리뷰에 대한 답글 테이블
CREATE TABLE `review_comments` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`review_id` BIGINT NOT NULL,
	`comment_text` TEXT NOT NULL,
    `comment_date` DATETIME NOT NULL,
    FOREIGN KEY (review_id) REFERENCES `reviews` (id) ON DELETE CASCADE
);