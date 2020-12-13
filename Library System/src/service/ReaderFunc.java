package service;

import dao.AdministratorDao;
import dao.ReaderDao;
import entity.Reader;

public class ReaderFunc {
	public static boolean login(String username,String pwd) {
		ReaderDao dao = new ReaderDao();
		String result = dao.queryReaderPassword(username);
		if(pwd.equals(result)) {
			return true;
		}
			
		return false;
	}
	public static boolean hasUser(String username) {
		ReaderDao dao = new ReaderDao();
		String result = dao.queryReaderPassword(username);
		if(result == null) return false;
		else return true;
	}
	public static void readerRegister(Reader reader) {
		ReaderDao.readerRegister(reader);
		
	}
	public static Reader loadReader(String uname) {
		return ReaderDao.getReaderByR_id(uname);
	}
	public static boolean canLend(String reader_id) {
		return !(ReaderDao.getReaderCanLend(reader_id)==0);
	}
	public static boolean canReturn(String reader_id, int book_id) {
		return ReaderDao.readerLendthisBookandHasntReturn(reader_id,book_id);
	}


}
