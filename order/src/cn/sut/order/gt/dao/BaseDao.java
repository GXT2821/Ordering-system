package cn.sut.order.gt.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/order?useUnicode=true&characterEncoding=utf-8";
	public static final String DBNAME = "root";
	public static final String DBPASS = "281450";
	
	public Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,DBNAME, DBPASS);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		return conn;
	}

}
