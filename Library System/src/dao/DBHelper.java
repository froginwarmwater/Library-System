package dao;

import java.sql.*;

public class DBHelper {
	/* 定义driver,url,user,pwd等 */
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=library";
	private static String user = "sa", pwd = "1234";
	private static Connection con;
    /*加载驱动程序*/
	static {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("数据库连接成功");
		} catch (Exception ex) {
			System.out.println("数据库连接失败");
		}
	}
    /*执行增删改操作*/
	public static void executeUpdate(String sql,Object...objects) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);//预处理
			for(int i=0;i<objects.length;i++){ 
				if(objects[i] instanceof Integer)
					ps.setInt(i+1, (int) objects[i]); //获取完整的SQL语句，无通配符
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
	/*执行查询操作*/
	
	
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
					ps.setInt(i+1, (int) objects[i]); //获取完整的SQL语句，无通配符
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
			PreparedStatement ps = con.prepareStatement(sql);//预处理
			for(int i=0;i<objects.length;i++){ 
				if(objects[i] instanceof Integer)
					ps.setInt(i+1, (int) objects[i]); //获取完整的SQL语句，无通配符
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
	
	/**************调用存储过程********************/
	public static CallableStatement executePro(String sql,Object...objects) {
		CallableStatement cstm = null;
		try {
			
			cstm = con.prepareCall(sql); //实例化对象cstm 
			
			for(int i=0;i<objects.length;i++){ 
				if(objects[i] instanceof Integer)
					cstm.setInt(i+1, (int) objects[i]); //获取完整的SQL语句，无通配符
				else if(objects[i] instanceof Double)
					cstm.setDouble(i+1, (double)objects[i]);
				else if(objects[i] instanceof Float)
					cstm.setDouble(i+1, (float)objects[i]);
				else if(objects[i] instanceof String)
					cstm.setString(i+1, objects[i].toString());
				
				
			}
			

				
			//cstm.registerOutParameter(3, Types.INTEGER); // 设置返回值类型 
			//cstm.execute(); // 执行存储过程 
			//System.out.println(cstm.getInt(3)); 

		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		return cstm;
	}
	
	
	
   /*执行关闭数据库连接操作*/
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
