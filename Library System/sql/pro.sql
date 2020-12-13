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







--模糊查询存储过程
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
--reader所有内容视图
if exists(select * from sysobjects where id=OBJECT_ID(N'view_reader_and_person') and objectproperty(id,N'IsView')=1)
drop view view_reader_and_person        --删除视图
go
create view view_reader_and_person    --创建视图
as
select rp.r_id r_id,rp.p_id p_id,pwd,can_lend,has_lent,wrong,addr,tele,name,sex,birth,position,dep
from reader r,reader_person rp,person p
where rp.p_id=p.p_id and rp.r_id=r.r_id
go





use library
--书籍所有内容视图
if exists(select * from sysobjects where id=OBJECT_ID(N'view_books') and objectproperty(id,N'IsView')=1)
drop view view_books        --删除视图
go
create view view_books    --创建视图
as
select b.b_id,b.ISBN,bd.name,bd.cata,bd.author,bd.introduction,bd.publisher,bd.publish_date,bd.price,sta
from book b,book_detail bd
where b.ISBN=bd.ISBN
go



use library
--书籍借阅内容视图
if exists(select * from sysobjects where id=OBJECT_ID(N'view_lend') and objectproperty(id,N'IsView')=1)
drop view view_lend        --删除视图
go
create view view_lend    --创建视图
as
select l_id,b.b_id,b.ISBN,bd.name,bd.cata,bd.author,bd.introduction,bd.publisher,bd.publish_date,bd.price,sta,r_id,is_due,lend_date,return_date
from book b,book_detail bd,lend
where lend.b_id=b.b_id and b.ISBN=bd.ISBN
go



--

use library
if exists(select * from sysobjects where id=OBJECT_ID(N'view_lastten') and objectproperty(id,N'IsView')=1)
drop view view_lastten        --删除视图
go
create view view_lastten    --创建视图
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
drop view view_topten        --删除视图
go
create view view_topten    --创建视图
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
drop view view_rank        --删除视图
go
create view view_rank    --创建视图
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



