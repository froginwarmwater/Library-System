

USE library
Go

CREATE TABLE book_detail
(  ISBN varchar(50) NOT NULL PRIMARY KEY check(ISBN like 'ISBN[0-9][0-9][0-9]-[0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9]'),
    name varchar(50) NOT NULL,
	cata varchar(50) NOT NULL,
	publish_date datetime NOT NULL check(publish_date<=getdate()),
	introduction varchar(200) NOT NULL,
	price float NOT NULL check(price>=0 and price<=2000),
	publisher varchar(100) NOT NULL,
	author varchar(100) NOT NULL,
	number int NOT NULL check(number>=0 and number<=200),
	remain int NOT NULL check(remain>=0 and remain<=200),
	constraint ck_remain
	check(remain<=number)
);

CREATE TABLE book
(  b_id int identity(1,1) PRIMARY KEY ,
    ISBN varchar(50) NOT NULL FOREIGN KEY REFERENCES book_detail(ISBN),
	sta tinyint default '1' check(sta='0' or sta='1') --1=available
);









CREATE TABLE lend
(  l_id int identity(1,1) PRIMARY KEY ,
    r_id varchar(50) NOT NULL FOREIGN KEY REFERENCES reader(r_id),
	b_id int NOT NULL FOREIGN KEY REFERENCES book(b_id),
	lend_date datetime NOT NULL check(lend_date<=getdate()),
	return_date datetime,
	is_due tinyint default '0' check(is_due='0' or is_due='1'),    --   1=is due  0=hasnt
	constraint ck_return_date
	check(lend_date<=return_date)
)


INSERT INTO book_detail
VALUES
('ISBN978-7-5601-3496-3','c++教程','计算机类','2008-11-11','这是一部关于c++的教程书籍',50.0,'高等教育出版社','李昕阳',1,1),
('ISBN978-7-5601-3496-4','Java教程','计算机类','2008-11-11','这是一部关于Java的教程书籍',70.0,'高等教育出版社','张三',1,1),
('ISBN978-7-5601-3496-5','c#教程','计算机类','2008-11-11','这是一部关于c#的教程书籍',53.0,'机械工业出版社','李四',1,1),
('ISBN978-7-5601-3496-6','Python教程','计算机类','2008-11-11','这是一部关于Python的教程书籍',43.0,'清华大学出版社','刘小明',1,1),
('ISBN978-7-5601-3496-7','golang教程','计算机类','2008-11-11','这是一部关于golang的教程书籍',35.0,'机械工业出版社','周峰',1,1),
('ISBN978-7-5601-3496-8','R语言教程','计算机类','2008-11-11','这是一部关于R语言的教程书籍',70.0,'高等教育出版社','王五',1,1),
('ISBN978-7-5601-3496-9','matlab教程','计算机类','2008-11-11','这是一部关于matlab的教程书籍',58.0,'高等教育出版社','叶叶',1,1),
('ISBN978-7-5601-3496-0','公司理财','金融类','2008-11-11','这是一部公司理财的书籍',85.0,'机械工业出版社','赵二麻子',1,1)



INSERT INTO book
VALUES
('ISBN978-7-5601-3496-3',1),
('ISBN978-7-5601-3496-4',1),
('ISBN978-7-5601-3496-5',1),
('ISBN978-7-5601-3496-6',1),
('ISBN978-7-5601-3496-7',1),
('ISBN978-7-5601-3496-8',1),
('ISBN978-7-5601-3496-9',1),
('ISBN978-7-5601-3496-0',1)


