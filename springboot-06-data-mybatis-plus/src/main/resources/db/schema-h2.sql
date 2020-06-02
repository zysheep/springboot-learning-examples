DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);


INSERT INTO user (id, name, age, email) VALUES
(1, '伽罗', 18, 'zysheep@126.com'),
(2, '韩信', 36, 'zysheep@126.com'),
(3, '李白', 28, 'zysheep@126.com'),
(4, '武则天', 21, 'zysheep@126.com'),
(5, '嬴政', 24, 'zysheep@126.com');