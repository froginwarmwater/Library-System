package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Reader;

public class AdministratorDao {


	public String queryAdministratorPassword(String username) {
		String sql = "select pwd from administrator where username=?";
		ResultSet rs = DBHelper.executeQuery(sql,username);
		String pwd = null;
		try {
			while(rs.next()) {
				pwd = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("error in AdministratorDao.AdministratorLogin");
		}
		
		return pwd;
	}
	
	public List<Reader> queryReaderBySomething(String str , int funcNum) {
		List<Reader> list = new ArrayList<>();
		String by[] = {
				"r_id=?",//0
				"p_id=?",//1
				"name=?",//2
				"position=?",//3
				"tele=?",//4
				"dep=?"//5
		};
		String sql = "select "
				+ "r_id,pwd,can_lend,has_lent,wrong,p_id,name,sex,birth,position,addr,tele,dep "
				+ "from "
				+ "reader,reader_person,person "
				+ "where "
				+ "reader.r_id=reader_person.r_id "
				+ "and "
				+ "reader_person.p_id=person.pid "
				+ "and "
				+ ""+by[funcNum];
		//System.out.println(sql);
		ResultSet rs = DBHelper.executeQuery(sql,str);
		try {
			while(rs.next()) {
				Reader reader = new Reader();
				reader.setAddr(rs.getString(11));
				reader.setBirth(rs.getDate(9));
				reader.setCan_lend(rs.getInt(3));
				reader.setDep(rs.getString(13));
				reader.setHas_lent(rs.getInt(4));
				reader.setName(rs.getString(7));
				reader.setP_id(rs.getString(6));
				reader.setPosition(rs.getString(10));
				reader.setPwd(rs.getString(2));
				reader.setR_id(rs.getString(1));
				reader.setSex(rs.getString(8));
				reader.setTele(rs.getString(12));
				reader.setWrong(rs.getInt(5));
			}
		}catch (SQLException e) {
			System.out.println("error in AdministratorDao.queryReaderBySomething");
		}
		return list;
		

	}
	
}
