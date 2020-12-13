package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.Reader;

public class ReaderDao {
	
	 public String queryReaderPassword(String username) {//用户登录
		String sql = "select pwd from reader where r_id=?";
		ResultSet rs = DBHelper.executeQuery(sql,username);
		String pwd = null;
		try {
			while(rs.next()) {
				pwd = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in ReaderDao.ReaderLogin");
		}
		return pwd;
		
	}



	public static void readerRegister(Reader reader) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String birth = formatter.format(reader.getBirth()); 
		
		
		//调用插入读者的存储过程
		String sql = "use library execute proc_add_reader @r_id='"+reader.getR_id()+"',@pwd='"+reader.getPwd()+"',@p_id='"+reader.getP_id()+"',@name='"+reader.getName()+"',@sex='"+reader.getSex()+"',@birth='"+birth+"',@position='"+reader.getPosition()+"',@addr='"+reader.getAddr()+"',@tele='"+reader.getTele()+"',@dep='"+reader.getDep()+"';";
		//System.out.println(sql);
		DBHelper.executeSingle(sql);
		
	}

	public static Reader getReaderByR_id(String r_id) {
		Reader reader = new Reader();
		String sql = "select * from view_reader_and_person where r_id=?";
		ResultSet rs = DBHelper.executeQuery(sql,r_id);
		try {
			while(rs.next()) {
				
				reader.setAddr(rs.getString("addr"));
				reader.setBirth(rs.getDate("birth"));
				reader.setCan_lend(rs.getInt("can_lend"));
				reader.setDep(rs.getString("dep"));
				reader.setHas_lent(rs.getInt("has_lent"));
				reader.setName(rs.getString("name"));
				reader.setP_id(rs.getString("p_id"));
				reader.setR_id(rs.getString("r_id"));
				reader.setPosition(rs.getString("position"));
				reader.setSex(rs.getString("sex"));
				reader.setWrong(rs.getInt("wrong"));
				reader.setTele(rs.getString("tele"));
				reader.setPwd(rs.getString("pwd"));
				
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in ReaderDao.getReaderByR_id");
		}
		
		return reader;
	}
	
	public static Reader getDemoReader() {
		return getReaderByR_id("user_01");
	}




	public static int getReaderCanLend(String reader_id) {
		String sql = "select * from view_reader_and_person where r_id='"+reader_id+"'";
		ResultSet rs = DBHelper.executeQuery(sql);
		int can_lend = 0;
		try {
			while(rs.next()) {
				can_lend =rs.getInt("can_lend");
			}
		} catch (SQLException e) {
			System.out.println("error in ReaderDao.getReaderCanLend");
		}
		return can_lend;
	}
	public static void main(String[] args) {
		//System.out.println(getDemoReader());
	}



	public static boolean readerLendthisBookandHasntReturn(String reader_id, int book_id) {
		String sql = "select l_id from view_lend where r_id=? and b_id=? and return_date is NULL;";
		ResultSet rs = DBHelper.executeQuery(sql,"'"+reader_id+"'",book_id);
		int l_id = -1;
		try {
			while(rs.next()) {
				l_id =rs.getInt("l_id");
			}
		} catch (SQLException e) {
			System.out.println("error in ReaderDao.getReaderCanLend");
		}
		return !(l_id == -1);
	}



	public static void updateRegister(Reader reader) {
		
		//调用update读者的存储过程
		String sql = "use library execute proc_update_reader @r_id='"+reader.getR_id()+"',@pwd='"+reader.getPwd()+"',@p_id='"+reader.getP_id()+"',@name='"+reader.getName()+"',@sex='"+reader.getSex()+"',@position='"+reader.getPosition()+"',@addr='"+reader.getAddr()+"',@tele='"+reader.getTele()+"',@dep='"+reader.getDep()+"';";
		//System.out.println(sql);
		DBHelper.executeSingle(sql);
		
	}



	public static List<Reader> getAllReaders() {
		List<Reader> readers = new ArrayList<>();
		String sql = "select * from view_reader_and_person";
		ResultSet rs = DBHelper.executeQuery(sql);
		try {
			if(rs == null) return null;
			while(rs.next()) {
				Reader reader = new Reader();
				reader.setAddr(rs.getString("addr"));
				reader.setBirth(rs.getDate("birth"));
				reader.setCan_lend(rs.getInt("can_lend"));
				reader.setDep(rs.getString("dep"));
				reader.setHas_lent(rs.getInt("has_lent"));
				reader.setName(rs.getString("name"));
				reader.setP_id(rs.getString("p_id"));
				reader.setR_id(rs.getString("r_id"));
				reader.setPosition(rs.getString("position"));
				reader.setSex(rs.getString("sex"));
				reader.setWrong(rs.getInt("wrong"));
				reader.setTele(rs.getString("tele"));
				reader.setPwd(rs.getString("pwd"));
				
				
				readers.add(reader);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in ReaderDao.getReaderByR_id");
		}
		
		return readers;
	}



	public static List<Reader> getReadersBy(String r_id, String p_id, String dept, String name) {
		List<Reader> readers = new ArrayList<>();
		String sql = "select * from view_reader_and_person where r_id like '%"+r_id+"%' or p_id like '%"+p_id+"%' or dep like '%"+dept+"%' or name like '%"+name+"%'";
		ResultSet rs = DBHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Reader reader = new Reader();
				reader.setAddr(rs.getString("addr"));
				reader.setBirth(rs.getDate("birth"));
				reader.setCan_lend(rs.getInt("can_lend"));
				reader.setDep(rs.getString("dep"));
				reader.setHas_lent(rs.getInt("has_lent"));
				reader.setName(rs.getString("name"));
				reader.setP_id(rs.getString("p_id"));
				reader.setR_id(rs.getString("r_id"));
				reader.setPosition(rs.getString("position"));
				reader.setSex(rs.getString("sex"));
				reader.setWrong(rs.getInt("wrong"));
				reader.setTele(rs.getString("tele"));
				reader.setPwd(rs.getString("pwd"));
				
				
				readers.add(reader);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in ReaderDao.getReaderBy");
		}
		
		return readers;
	}



	public static void deleteReader(String r_id) {
		String sql = "use library execute proc_delete_reader @r_id='"+r_id+"';";
		
		//System.out.println(sql);
		DBHelper.executeSingle(sql);
	}
	
	
}
