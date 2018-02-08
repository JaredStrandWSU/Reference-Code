package posgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.PreparedStatement; use create statement instead


//Establishes a SQL connection with a DB, use pgAdmin to prepare your database for querying on the correct host and port. Python parsing of json and csv files available also.
public class PostgresqlConnection {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException e){
			System.out.println("Driver not found");
			e.printStackTrace();
			return;
		}
		System.out.println("Driver Registered Successfully...");
		
		Connection conn = null;
		try {
			//Create the Connection to the DB through the JDBC driver.
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/:5432/DBname","username","password");
		}
		catch(SQLException e) {
			System.out.println("Unable to create the connection");
			e.printStackTrace();
			return;
		}
		if(conn != null) {
			ResultSet rs = null;
			try {
				//Get the queried data set from the database - select a table
				rs = conn.createStatement().executeQuery("select * from ADC_Telemetry");
				
				//Insert into DB with SQL
				//conn.createStatement().excecuteUpdate("insert into ADC_Telemetry values(date, x-position, y-position, z-position, longitude, latitude)");
				
				//SQL Update Query - Info based on primary key in tables - date is a good option
				//conn.createStatement().excecuteUpdate("update public.ADC_Telemetry set Altitude = 12345.75 where date = '04/05/2018'");
				
			}
			catch(SQLException e){
				System.out.println("Error: Unable to perform Query");
				e.printStackTrace();
			}
			try {
				//Print data to the GUI or Process for calculations - this can be modified to look pretty
				while(rs.next())
				{
					System.out.println("------------------------");
					System.out.println("Date: " + rs.getString(0));
					System.out.println("x-position: " + rs.getInt(1));
					System.out.println("y-position: " + rs.getInt(2));
					System.out.println("z-position: " + rs.getInt(3));
					System.out.println("longitude: " + rs.getInt(4));
					System.out.println("latitude: " + rs.getInt(5));
					System.out.println("------------------------");
				}
			}
			catch(SQLException e) {
				System.out.println("Error: Unable to perform output");
				e.printStackTrace();
			}
		}
	}

}
