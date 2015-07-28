package htmlUnit_crawler_test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	private static Connection con = null;
	private static DBConnection DB = new DBConnection();
	
	private DBConnection(){
		try{
			con = DriverManager.getConnection("jdbc:sqlite::memory:");
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}
	}
	
	public static Connection connect() throws SQLException{
//		Connection connection = null;
//		try{
//			connection = DriverManager.getConnection("jdbc:sqlite::memory:");
//		}
//		catch(SQLException e){
//			System.err.println(e.getMessage());
//		}
//		return connection;
		return con;
	}
}
