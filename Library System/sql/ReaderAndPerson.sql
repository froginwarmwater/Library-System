USE library
Go
CREATE TABLE reader
(  r_id varchar(50) NOT NULL PRIMARY KEY,
    pwd varchar(50) NOT NULL ,
	can_lend int default 5 check(can_lend>=0 and can_lend<=5),
	has_lent int default 0 check(has_lent>=0),
	wrong int default 0 check(wrong>=0),

	constraint ck_has_lent
	check(has_lent<=can_lend)
);

CREATE TABLE person
(  p_id varchar(50) NOT NULL PRIMARY KEY check(p_id LIKE'[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][X,0-9]'),
    name varchar(50) NOT NULL,
	sex varchar(10) NOT NULL check(sex='��' or sex='Ů'),
	birth date NOT NULL check(birth<getdate()),
	position varchar(100) NOT NULL,
	addr varchar(100) NOT NULL,
	tele varchar(50) NOT NULL 
	check (len(tele)=11 and tele like'[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	dep varchar(50) NOT NULL,
);



CREATE TABLE reader_person
(  r_id varchar(50) NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES reader(r_id),
    p_id varchar(50) NOT NULL FOREIGN KEY REFERENCES person(p_id)
);




INSERT INTO person
VALUES
('321088200001010001','��һ','��','2000-01-01','������','�Ϻ�������ѧ','18135265214','����'),
('321088200002010002','�Ŷ�','��','2000-02-01','��У��','�Ϻ�������ѧ','18135265214','У���칫��'),
('321088200003010003','����','��','2000-03-01','����Ա','�Ϻ�������ѧ','18135265214','����Ա�칫��'),
('321088200004010004','����','��','2000-04-01','Ժ��','�Ϻ�������ѧ','18135265214','�����ѧԺ'),
('321088200005010005','����','��','2000-05-01','��Ժ��','�Ϻ�������ѧ','18135265214','����ѧԺ'),
('321088200006010006','����','��','2000-06-01','����','�Ϻ�������ѧ','18135265214','ѧ����'),
('321088200007010007','����','��','2000-07-01','����','�Ϻ�������ѧ','18135265214','����'),
('321088200008010008','�Ű�','��','2000-08-01','����','�Ϻ�������ѧ','18135265214','����')


INSERT INTO reader
VALUES
('user_01','userpwd_01',5,0,0),
('user_02','userpwd_02',5,0,0),
('user_03','userpwd_03',5,0,0),
('user_04','userpwd_04',5,0,0),
('user_05','userpwd_05',5,0,0),
('user_06','userpwd_06',5,0,0),
('user_07','userpwd_07',5,0,0),
('user_08','userpwd_08',5,0,0)



INSERT INTO reader_person
VALUES
('user_01','321088200001010001'),
('user_02','321088200002010002'),
('user_03','321088200003010003'),
('user_04','321088200004010004'),
('user_05','321088200005010005'),
('user_06','321088200006010006'),
('user_07','321088200007010007'),
('user_08','321088200008010008')


