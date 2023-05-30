package employeedetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");

		// get connection object
		Connection con;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedetails", "root", "root");
		System.out.println("DatabaseConnection" + !con.isClosed());
		con.close();
	}
}
