use library
IF EXISTS (SELECT name from sysobjects where name='pro_add' and type='p')
drop procedure pro_add

go
create procedure pro_add
@r_id varchar(50),
@p_id varchar(50) output
as 
	select @p_id =p_id  from reader_person where r_id = @r_id;
go







--ģ����ѯ�洢����
use library
IF EXISTS (SELECT name from sysobjects where name='pro_findbook' and type='p')
drop procedure pro_findbook

go
create procedure pro_findbook
@name varchar(50),@ISBN varchar(50),@author varchar(50),@cata varchar(50),@publisher varchar(50)
as 
	select *  from view_books where ISBN like '%'+@ISBN+'%' or name like '%'+@name+'%' or author like '%'+@author+'%' or cata like '%'+@cata+'%' or publisher like '%'+@publisher+'%';
go





use library
--reader����������ͼ
if exists(select * from sysobjects where id=OBJECT_ID(N'view_reader_and_person') and objectproperty(id,N'IsView')=1)
drop view view_reader_and_person        --ɾ����ͼ
go
create view view_reader_and_person    --������ͼ
as
select rp.r_id r_id,rp.p_id p_id,pwd,can_lend,has_lent,wrong,addr,tele,name,sex,birth,position,dep
from reader r,reader_person rp,person p
where rp.p_id=p.p_id and rp.r_id=r.r_id
go





use library
--�鼮����������ͼ
if exists(select * from sysobjects where id=OBJECT_ID(N'view_books') and objectproperty(id,N'IsView')=1)
drop view view_books        --ɾ����ͼ
go
create view view_books    --������ͼ
as
select b.b_id,b.ISBN,bd.name,bd.cata,bd.author,bd.introduction,bd.publisher,bd.publish_date,bd.price,sta
from book b,book_detail bd
where b.ISBN=bd.ISBN
go



use library
--�鼮����������ͼ
if exists(select * from sysobjects where id=OBJECT_ID(N'view_lend') and objectproperty(id,N'IsView')=1)
drop view view_lend        --ɾ����ͼ
go
create view view_lend    --������ͼ
as
select l_id,b.b_id,b.ISBN,bd.name,bd.cata,bd.author,bd.introduction,bd.publisher,bd.publish_date,bd.price,sta,r_id,is_due,lend_date,return_date
from book b,book_detail bd,lend
where lend.b_id=b.b_id and b.ISBN=bd.ISBN
go



--

use library
if exists(select * from sysobjects where id=OBJECT_ID(N'view_lastten') and objectproperty(id,N'IsView')=1)
drop view view_lastten        --ɾ����ͼ
go
create view view_lastten    --������ͼ
as
select top 10 * from
	(select b.ISBN I,count(*) times
	from lend l,book b,book_detail bd 
	where l.b_id=b.b_id and b.ISBN=bd.ISBN
	group by b.ISBN) t,
	book_detail
where t.I = book_detail.ISBN
order by times
go



use library
if exists(select * from sysobjects where id=OBJECT_ID(N'view_topten') and objectproperty(id,N'IsView')=1)
drop view view_topten        --ɾ����ͼ
go
create view view_topten    --������ͼ
as
select top 10 * from
	(select b.ISBN I,count(*) times
	from lend l,book b,book_detail bd 
	where l.b_id=b.b_id and b.ISBN=bd.ISBN
	group by b.ISBN) t,
	book_detail
where t.I = book_detail.ISBN
order by times
desc
go



use library
if exists(select * from sysobjects where id=OBJECT_ID(N'view_rank') and objectproperty(id,N'IsView')=1)
drop view view_rank        --ɾ����ͼ
go
create view view_rank    --������ͼ
as
select top 100 * from
	(select b.ISBN I,count(*) times
	from lend l,book b,book_detail bd 
	where l.b_id=b.b_id and b.ISBN=bd.ISBN
	group by b.ISBN) t,
	book_detail
where t.I = book_detail.ISBN
order by times
desc
go



