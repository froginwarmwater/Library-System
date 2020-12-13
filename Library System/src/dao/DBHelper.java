package dao;

import java.sql.*;

public class DBHelper {
	/* ����driver,url,user,pwd�� */
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=library";
	private static String user = "sa", pwd = "1234";
	private static Connection con;
    /*������������*/
	static {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception ex) {
			System.out.println("���ݿ�����ʧ��");
		}
	}
    /*ִ����ɾ�Ĳ���*/
	public static void executeUpdate(String sql,Object...objects) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);//Ԥ����
			for(int i=0;i<objects.length;i++){ 
				if(objects[i] instanceof Integer)
					ps.setInt(i+1, (int) objects[i]); //��ȡ������SQL��䣬��ͨ���
				else if(objects[i] instanceof Double)
					ps.setDouble(i+1, (double)objects[i]);
				else if(objects[i] instanceof Float)
					ps.setDouble(i+1, (float)objects[i]);
				else if(objects[i] instanceof String)
					ps.setString(i+1, objects[i].toString());

			}
			ps.executeUpdate();
			}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/*ִ�в�ѯ����*/
	
	
	public static ResultSet executeSingleQuery(String sql) {
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	public static void executeSingle(String sql) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void executeVoid(String sql,Object...objects) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for(int i=0;i<objects.length;i++){ 
				if(objects[i] instanceof Integer)
					ps.setInt(i+1, (int) objects[i]); //��ȡ������SQL��䣬��ͨ���
				else if(objects[i] instanceof Double)
					ps.setDouble(i+1, (double)objects[i]);
				else if(objects[i] instanceof Float)
					ps.setDouble(i+1, (float)objects[i]);
				else if(objects[i] instanceof String)
					ps.setString(i+1, objects[i].toString());
			}
			
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static ResultSet executeQuery(String sql,Object...objects) {
	
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);//Ԥ����
			for(int i=0;i<objects.length;i++){ 
				if(objects[i] instanceof Integer)
					ps.setInt(i+1, (int) objects[i]); //��ȡ������SQL��䣬��ͨ���
				else if(objects[i] instanceof Double)
					ps.setDouble(i+1, (double)objects[i]);
				else if(objects[i] instanceof Float)
					ps.setDouble(i+1, (float)objects[i]);
				else if(objects[i] instanceof String)
					ps.setString(i+1, objects[i].toString());
				
				
			}
			rs = ps.executeQuery();
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		return rs;
	}
	
	/**************���ô洢����********************/
	public static CallableStatement executePro(String sql,Object...objects) {
		CallableStatement cstm = null;
		try {
			
			cstm = con.prepareCall(sql); //ʵ��������cstm 
			
			for(int i=0;i<objects.length;i++){ 
				if(objects[i] instanceof Integer)
					cstm.setInt(i+1, (int) objects[i]); //��ȡ������SQL��䣬��ͨ���
				else if(objects[i] instanceof Double)
					cstm.setDouble(i+1, (double)objects[i]);
				else if(objects[i] instanceof Float)
					cstm.setDouble(i+1, (float)objects[i]);
				else if(objects[i] instanceof String)
					cstm.setString(i+1, objects[i].toString());
				
				
			}
			

				
			//cstm.registerOutParameter(3, Types.INTEGER); // ���÷���ֵ���� 
			//cstm.execute(); // ִ�д洢���� 
			//System.out.println(cstm.getInt(3)); 

		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		return cstm;
	}
	
	
	
   /*ִ�йر����ݿ����Ӳ���*/
	public static void closeConnection() {
		try {
           if(!con.isClosed())
			con.close();
		  } catch (Exception ex) {
		}
	}
	
	
	

	public static void main(String[] args) {
		DBHelper helper = new DBHelper();
	}
}
