package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Book;
import entity.Reader;

public class BookDao {

	public static List<Book> findBooks(String name, String iSBN, String author, String catalog, String publisher) {
		List<Book> books = new ArrayList<>();

		String sql = "use library execute pro_findbook @name='"+name+"',@ISBN='"+iSBN+"',@author='"+author+"',@cata='"+catalog+"',@publisher='"+publisher+"';";
		System.out.println(sql);
		ResultSet rs = DBHelper.executeSingleQuery(sql);
		String pwd = null;
		try {
			while(rs.next()) {
				Book book = new Book();
				book.setB_id(rs.getInt("b_id"));
				book.setAuthor(rs.getString("author"));
				book.setCatalog(rs.getString("cata"));
				book.setContent(rs.getString("introduction"));
				book.setISBN(rs.getString("ISBN"));
				book.setName(rs.getString("name"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublish_date(rs.getDate("publish_date"));
				book.setPrice(rs.getDouble("price"));
				book.setState(rs.getInt("sta"));
				System.out.println(book);
				books.add(book);
			}
		} 
			catch (SQLException e) {
			System.out.println("error in BookDao.findBooks");
			}
		
		return books;
	}

	public static List<Book> findAllBooks() {

		List<Book> books = new ArrayList<>();
		//创建视图 view_books
		String sql = "select * from view_books";
		ResultSet rs = DBHelper.executeSingleQuery(sql);
		try {
			while(rs.next()) {
				Book book = new Book();
				book.setB_id(rs.getInt("b_id"));
				book.setAuthor(rs.getString("author"));
				book.setCatalog(rs.getString("cata"));
				book.setContent(rs.getString("introduction"));
				book.setISBN(rs.getString("ISBN"));
				book.setName(rs.getString("name"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublish_date(rs.getDate("publish_date"));
				book.setPrice(rs.getDouble("price"));
				book.setState(rs.getInt("sta"));
				//System.out.println(book);
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println("error in BookDao.findAllBooks");
		}

		return books;
	}

	public static int getBookstate(int book_id) {
		String sql = "select * from book where b_id=?";
		ResultSet rs = DBHelper.executeQuery(sql,book_id);
		int sta = 0;
		try {
			while(rs.next()) {
				sta =rs.getInt("sta");
			}
		} catch (SQLException e) {
			System.out.println("error in BookDao.getBookstate");
		}
		return sta;
	}

	public static void lendBook(String r_id, int b_id) {
		String sql = "use library execute proc_lend @r_id='"+r_id+"',@b_id="+b_id+";";
		//System.out.println(sql);
		DBHelper.executeSingle(sql);
		
	}

	public static List<Book> lendHistory(Reader reader) {
		List<Book> books = new ArrayList<>();
		//创建视图 view_books
		String sql = "select * from view_lend where r_id='"+reader.getR_id()+"';";
		ResultSet rs = DBHelper.executeSingleQuery(sql);
		try {
			while(rs.next()) {
				Book book = new Book();
				book.setB_id(rs.getInt("b_id"));
				book.setCatalog(rs.getString("cata"));
				book.setName(rs.getString("name"));
				book.setISBN(rs.getString("ISBN"));
				book.setReturn_date(rs.getDate("return_date"));
				book.setLend_date(rs.getDate("lend_date"));
				book.setIs_due(rs.getInt("is_due"));
				
				
				
				//System.out.println(book);
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println("error in BookDao.lendHistory");
		}

		return books;
	}

	public static List<Book> hasntReturn(Reader reader) {
		List<Book> books = new ArrayList<>();
		//创建视图 view_books
		String sql = "select * from view_lend where r_id='"+reader.getR_id()+"' and return_date is NULL;";
		ResultSet rs = DBHelper.executeSingleQuery(sql);
		try {
			while(rs.next()) {
				Book book = new Book();
				book.setB_id(rs.getInt("b_id"));
				book.setCatalog(rs.getString("cata"));
				book.setName(rs.getString("name"));
				book.setISBN(rs.getString("ISBN"));
				book.setReturn_date(rs.getDate("return_date"));
				book.setLend_date(rs.getDate("lend_date"));
				book.setIs_due(rs.getInt("is_due"));
				
				
				
				//System.out.println(book);
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println("error in BookDao.lendHistory");
		}

		return books;
	}

	public static void returnBook(String r_id, int b_id) {
		String sql = "use library execute proc_return @r_id='"+r_id+"',@b_id="+b_id+";";
		//System.out.println(sql);
		DBHelper.executeSingle(sql);
		
	}

	public static void init() {
		String sql = "use library execute proc_refresh;";
		//System.out.println(sql);
		DBHelper.executeSingle(sql);
		
	}

	public static List<Book> rank(String rank) {
		List<Book> books = new ArrayList<>();
		//创建视图 view_books
		String sql = "select * from view_"+rank;
		ResultSet rs = DBHelper.executeSingleQuery(sql);
		try {
			while(rs.next()) {
				Book book = new Book();
				book.setLendTimes(rs.getInt("times"));
				book.setCatalog(rs.getString("cata"));
				book.setName(rs.getString("name"));
				book.setISBN(rs.getString("ISBN"));
				book.setContent(rs.getString("introduction"));
				
				
				//System.out.println(book);
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println("error in BookDao.rank");
		}

		return books;
	}

	public static void addBook(String name, String iSBN, String price, String publisher, String author, Date p_date,
			String sdept, String introduction) {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String d = formatter.format(p_date); 
		
		String sql = "use library execute proc_add_book @ISBN='"+iSBN+"',@name='"+name+"',@cata='"+sdept+"',@publish_date='"+d+"',@introduction='"+introduction+"',@price='"+price+"',@publisher='"+publisher+"',@author='"+author+"';";
		//System.out.println(sql);
		DBHelper.executeSingle(sql);
		
	}

	public static void removeByB_id(int b_id) {
		// TODO Auto-generated method stub
		//proc_delete_book
		
		String sql = "use library execute proc_delete_book @b_id='"+b_id+"';";
		//System.out.println(sql);
		DBHelper.executeSingle(sql);
		
	}



	
}
