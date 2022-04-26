package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/billing?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertBill(String bname, String price, String bdes) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into Billservice(`id`, `bname`, `price`, `bdes`)" + " values ( ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, bname);
			preparedStmt.setString(3, price);
			preparedStmt.setString(4, bdes);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readBill() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Bill Name</th><th>Price</th><th>Description</th></tr>";
			String query = "select * from Billservice";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String bId = Integer.toString(rs.getInt("id"));
				String bName = rs.getString("bname");
				String bPrice = rs.getString("price");
				String bDes = rs.getString("bDes");

				output += "<tr><td>" + bId + "</td>";
				output += "<td>" + bName + "</td>";
				output += "<td>" + bPrice + "</td>";
				output += "<td>" + bDes + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateBill(String Id, String bname, String price, String bdes) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE Billservice SET bname=?,price=?,bdes=? WHERE id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, bname);
			preparedStmt.setString(2, price);
			preparedStmt.setString(3, bdes);
			preparedStmt.setInt(4, Integer.parseInt(Id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Bill.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public String deleteBill(String bId) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from Billservice where id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(bId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Bill.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
