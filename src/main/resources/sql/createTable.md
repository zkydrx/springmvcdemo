### 执行以下命令创建blog表
CREATE TABLE `blog` (
	`id` INT(30) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(50) NULL DEFAULT NULL,
	`content` VARCHAR(50) NULL DEFAULT NULL,
	`user_id` INT(11) NULL DEFAULT NULL,
	`pub_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FKpxk2jtysqn41oop7lvxcp6dqq` (`user_id`),
	CONSTRAINT `FK9dyskmk1e1ikvs3dup9rn9tj4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
	CONSTRAINT `FK_blog_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FKpxk2jtysqn41oop7lvxcp6dqq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=8
;
### 执行以下命令创建user表
CREATE TABLE `user` (
	`id` INT(40) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(50) NULL DEFAULT '0',
	`last_name` VARCHAR(60) NULL DEFAULT '0',
	`password` VARCHAR(70) NULL DEFAULT '0',
	`nick_name` VARCHAR(70) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='user'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=26
;
