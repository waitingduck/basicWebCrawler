package htmlUnit_crawler_test1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class controller {
	public static void main(String Args[]) throws Exception{
		System.out.println("Hello, It my fir try on htmlUnit. Hope it success! FIGHT ON!!!");
		crawler c = new crawler();
		//c.homePage();
		//c.submittingForm();
//		DBConnection DB = new DBConnection();
		Connection con = DBConnection.connect();
		Statement statement = con.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      statement.executeUpdate("drop table if exists art");
	      statement.executeUpdate("create table art (article_name string, country string,era string,year string, artist string, artist_year string)");
	      //statement.executeUpdate("insert into art values(null,null,null,null,null,'null')");
	      c.xpath();
	      ResultSet rs = statement.executeQuery("select * from art");
	      while(rs.next())
	      {
	        // read the result set
	        System.out.println("Article Name = " + rs.getString("article_name"));
	        System.out.println("Country = " + rs.getString("country"));
	        System.out.println("Era = " + rs.getString("era"));
	        System.out.println("Year = " + rs.getString("year"));
	        System.out.println("Artist = " + rs.getString("artist"));
	        System.out.println("Artist's Year = " + rs.getString("artist_year"));
	        System.out.println();
	      }
	      con.close();
	}
}