use library
if exists(select * from sysindexes where name='lend_index')
drop index lend.lend_index
create nonclustered index lend_index on lend(r_id asc,b_id asc)
go



use library
if exists(select * from sysindexes where name='reader_index')
drop index reader.reader_index
create nonclustered index reader_index on reader(r_id asc)
go




use library
if exists(select * from sysindexes where name='book_index')
drop index book.book_index
create nonclustered index book_index on book(ISBN asc)
go




use library
if exists(select * from sysindexes where name='person_index')
drop index person.person_index
create nonclustered index person_index on person(name asc)
go
