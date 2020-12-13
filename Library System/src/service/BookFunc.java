package service;

import java.util.ArrayList;
import java.util.List;

import dao.BookDao;
import entity.Book;
import entity.Reader;


public class BookFunc {

	public static List<Book> findBooks(String name, String iSBN, String author, String catalog, String publisher) {
		List<Book> books ;
		books = BookDao.findBooks(name,iSBN,author,catalog,publisher);
		return books;
	}

	public static List<Book> findAllBooks() {
		List<Book> books ;
		books = BookDao.findAllBooks();
		return books;
	}

	public static boolean canLend(int book_id) {
		return (0 == BookDao.getBookstate(book_id));
	}

	public static List<Book> lendHistory(Reader reader) {
		List<Book> books ;
		books = BookDao.lendHistory(reader);
		return books;
	}

	public static List<Book> hasntReturn(Reader reader) {
		List<Book> books ;
		books = BookDao.hasntReturn(reader);
		return books;
	}



}
