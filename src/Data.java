import java.sql.*;
public class Data{
	public static Connection getConnection()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://127.0.123.1:3306/Movies","root","root");
	}
}