package kr.co.happy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnetor {
	private DBConnetor(){};
	public static Connection getConn() {
		Connection conn = null;
		
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "hr";
			String password = "hkitedu";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try { rs.close(); } catch (Exception e) { }
		}
		if(ps != null) {
			try { ps.close(); } catch (Exception e) { }
		}
		if(conn != null) {
			try { conn.close(); } catch (Exception e) { }
		}
	}
}
