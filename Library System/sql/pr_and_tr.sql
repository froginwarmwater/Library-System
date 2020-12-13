
/*添加用户的存储过程*/
use library
IF EXISTS (SELECT name from sysobjects where name='proc_add_reader' and type='p')
drop procedure proc_add_reader

go


create procedure proc_add_reader
@r_id varchar(50),@pwd varchar(50),@p_id varchar(50),@name varchar(50),@sex varchar(10),@birth date,@position varchar(100),@addr varchar(100),@tele varchar(50),@dep varchar(50)
as 
begin
	insert into person
	values(@p_id,@name ,@sex,@birth,@position,@addr,@tele,@dep)
	
	insert into reader
	values(@r_id,@pwd ,5,0,0)

	insert into reader_person
	values(@r_id,@p_id)


end
go

/*更改用户的存储过程*/
use library

IF EXISTS (SELECT name from sysobjects where name='proc_update_reader' and type='p')
drop procedure proc_update_reader

go

create procedure proc_update_reader
@r_id varchar(50),@pwd varchar(50),@p_id varchar(50),@name varchar(50),@sex varchar(10),@position varchar(100),@addr varchar(100),@tele varchar(50),@dep varchar(50)
as 
begin
	/*insert into person
	values(@p_id,@name ,@sex,@birth,@position,@addr,@tele,@dep)
	
	insert into reader
	values(@r_id,@pwd ,5,0,0)

	insert into reader_person
	values(@r_id,@p_id)*/

	update person set name=@name ,sex=@sex,position=@position,addr=@addr,tele=@tele,dep=@dep where p_id=@p_id;
	update reader set pwd=@pwd where r_id=@r_id;



end
go



/*删除用户的存储过程*/
use library
IF EXISTS (SELECT name from sysobjects where name='proc_delete_reader' and type='p')
drop procedure proc_delete_reader
go
create procedure proc_delete_reader
@r_id varchar(50)
as 
begin
declare @p_id varchar(50),@b_id int

	if(select count(*) from lend where r_id = @r_id and return_date is null)!=0
	begin
		select @b_id=b_id from lend where r_id = @r_id and return_date is null
		exec proc_return @r_id,@b_id

	end
	delete from lend where r_id=@r_id
	delete from reader_person where r_id=@r_id;
	delete from reader where r_id=@r_id
	delete from person where p_id=@p_id
	
end
go







/*插入书籍的存储过程*/
use library
go

IF EXISTS (SELECT name from sysobjects where name='proc_add_book' and type='p')
drop procedure proc_add_book

go
create procedure proc_add_book
@ISBN varchar(50),@name varchar(50),@cata varchar(50),@publish_date date,@introduction varchar(200),@price float,@publisher varchar(100),@author varchar(100)
as 
begin
	if 0=(select count(*) from book_detail where ISBN=@ISBN)
	begin
		insert into book_detail values(@ISBN,@name,@cata,@publish_date,@introduction,@price,@publisher,@author,1,1)
	end
	else 
	begin
		update book_detail set number=number+1 where ISBN=@ISBN
		update book_detail set remain=remain+1 where ISBN=@ISBN
	end

	insert into book values(@ISBN,1)

end
go
/*删除书籍的存储过程*/
use library
go

IF EXISTS (SELECT name from sysobjects where name='proc_delete_book' and type='p')
drop procedure proc_delete_book

go
create procedure proc_delete_book
@b_id varchar(50)
as 
begin
declare @r_id varchar(50)
		if (select count(*) from lend where b_id=@b_id and return_date is NULL)!=0
		begin
			select @r_id=r_id from lend where b_id=@b_id and return_date is NULL
			exec proc_return @r_id,@b_id
		end
		delete from lend where b_id=@b_id
		delete from book where b_id=@b_id

end
go


/*删除书籍的触发器*/    --选b_id删除
if EXISTS(SELECT * FROM sysobjects where name='tr_delete_book' and type='TR')
DROP TRIGGER tr_delete_book
go
create TRIGGER tr_delete_book
on book
after delete
as
begin tran
	declare @b_id varchar(50),@ISBN varchar(50)
	select @b_id=b_id
	from deleted
	select @ISBN=ISBN from book where b_id=@b_id;

	update book_detail set remain=remain-1,number=number-1 where ISBN=@ISBN;

	if 0=(select number from book_detail where ISBN=@ISBN)
		begin
			delete from	book_detail where ISBN=@ISBN;
		end
commit
go

/**借书的存储过程**/

IF EXISTS (SELECT name from sysobjects where name='proc_lend' and type='p')
drop procedure proc_lend

go
create procedure proc_lend
@r_id varchar(50),@b_id varchar(50)
as 
begin transaction
begin
	if (select sta from book where b_id=@b_id)=0
	begin
		ROLLBACK TRAN
		RAISERROR('该图书已经被借走！',16,10)
	end
	else 
		if(select can_lend from reader where r_id=@r_id)=(select has_lent from reader where r_id=@r_id)
		begin
		ROLLBACK TRAN
		RAISERROR('借书已达上限！',16,10)
		end
	else
		begin
			insert into lend values(@r_id,@b_id,getdate(),NULL,0);
			update reader set has_lent=has_lent+1 where r_id=@r_id;
			update reader set can_lend=can_lend-1 where r_id=@r_id;
			update book set sta=0 where b_id=@b_id;
			update book_detail set remain=remain-1 where ISBN=(
			select ISBN from book where b_id=@b_id
			);
		end


end
commit
go






/*还书的存储过程*/

use library
IF EXISTS (SELECT name from sysobjects where name='proc_return' and type='p')
drop procedure proc_return
go
create procedure proc_return
@r_id varchar(50),@b_id varchar(50)
as 
begin tran
begin
	if (select sta from book where b_id=@b_id)=1
	begin
		ROLLBACK TRAN
		RAISERROR('该书已经归还！',16,10)
	end
	else 
		if(select count(*) from lend where r_id=@r_id and b_id=@b_id and return_date is NULL)=0
		begin
		ROLLBACK TRAN
		RAISERROR('该图书不是你借的！',16,10)
		end
	else
		begin
			update lend set return_date=getdate() where r_id=@r_id and b_id=@b_id and return_date is NULL;
			update reader set has_lent=has_lent-1 where r_id=@r_id;
			update reader set can_lend=can_lend+1 where r_id=@r_id;
			update book set sta=1 where b_id=@b_id;
			update book_detail set remain=remain+1 where ISBN=(
			select ISBN from book where b_id=@b_id
			);
		end

end

commit
go


/*refresh*/

IF EXISTS (SELECT name from sysobjects where name='proc_refresh' and type='p')
drop procedure proc_refresh

go
create procedure proc_refresh
as 
begin
			
			

		update reader set wrong=wrong+1
			where r_id in
			(
				select r_id from lend where return_date is NULL and MONTH(getdate()-lend_date)>3 and is_due=0
			)
	
		update lend set is_due=1 
			where l_id in
			(
				select l_id from lend where return_date is NULL and MONTH(getdate()-lend_date)>3
			)
end

go



exec proc_refresh

select * from lend

select * from reader

select * from book
select * from book_detail
select * from reader_person
select * from person