CREATE TABLE `USER_GROUP` (
	`group_seq` int(11) NOT NULL,
	`group_name` varchar(100) NOT NULL,
	`group_desc` varchar(500) DEFAULT NULL,
	`use_flag` char(1) NOT NULL DEFAULT 'Y',
	PRIMARY KEY (`group_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*	`group_code` varchar(100) NOT NULL, */

CREATE TABLE `USER_GROUP_AUTHORITIES`(
	`group_seq` int(11) NOT NULL,
	`group_authority` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`group_seq`,`group_authority`),
	KEY `fk_user_group_authorities_seq` (`group_seq`),
	CONSTRAINT `fk_user_group_authorities_seq` FOREIGN KEY (`group_seq`) REFERENCES `USER_GROUP` (`group_seq`) ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `USER` (
	`user_id` varchar(30) NOT NULL,
	`user_nm` varchar(30) NOT NULL,
	`passwd` varchar(64) NOT NULL DEFAULT '',
	`lock_flag` char(1) DEFAULT 'N',
	`group_seq` int(11) DEFAULT NULL,
	PRIMARY KEY (`user_id`),
	KEY `fk_user_group_seq` (`group_seq`),
	CONSTRAINT `fk_user_group_seq` FOREIGN KEY (`group_seq`) REFERENCES `USER_GROUP` (`group_seq`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `USER_GR` (`group_seq`, `group_authority`)
VALUES
	(1, 'ROLE_ADMIN'),
	(1, 'ROLE_USER'),
	(2, 'ROLE_USER');
	
	
INSERT INTO `USER` (`user_id`, `user_nm`, `passwd`, `lock_flag`)
VALUES
	('admin', '관리자', 'admin', 'N'),
	('guest', '게스트', 'guest', 'N');
	