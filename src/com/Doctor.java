package com;

import java.sql.*;

public class Doctor {

	// DB Connection
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hcdb", "root", "");

			// For testing
			System.out.print("DB Successfully connected");
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.print("DB not connected");
		}

		return con;
	}

	// Read
	public String readDr() {
		
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// prepare the html table
			output = "<table border='1'><tr><th>Doctor Name</th><th>Password</th><th>Gender</th><th>Email</th><th>Address</th><th>Phone No</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from doctordetail";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// duplicate the rows
			while (rs.next()) {
				String drID = Integer.toString(rs.getInt("drID"));
				String drName = rs.getString("drName");
				String drPwd = rs.getString("drPwd");
				String drGender = rs.getString("drGender");
				String drEmail = rs.getString("drEmail");
				String drAddress = rs.getString("drAddress");
				String drPhone = Integer.toString(rs.getInt("drPhone"));

				// add into the html table
				output += "<tr><td><input id='hidDoctorIDUpdate' name='hidDoctorIDUpdate' type='hidden' value='" + drID + "'>" + drName + "</td>";
				output += "<td>" + drPwd + "</td>";
				output += "<td>" + drGender + "</td>";
				output += "<td>" + drEmail + "</td>";
				output += "<td>" + drAddress + "</td>";
				output += "<td>" + drPhone + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button'value='Update' class='btnUpdate btn btn-secondary'></td>"
				          +"<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-drid='"
						  + drID + "'>" + "</td></tr>";
			}

			con.close();
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Doctor Details";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert Doctor Details
	public String insertDr(String name, String pwd, String gender, String email, String address, String phone) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// creating a prepared statement
			String query = "insert into doctordetail(drID,drName,drPwd,drGender,drEmail,drAddress,drPhone)" + "values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, pwd);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, address);
			preparedStmt.setInt(7, Integer.parseInt(phone));

			preparedStmt.execute();
			con.close();

			String newDoctor = readDr();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the doctor details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update
	public String updateDr(String ID, String name, String pwd, String gender, String email, String address,String phone) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating";
			}

			// create a prepared statement
			String query = "update doctordetail set drName=?,drPwd=?,drGender=?,drEmail=?,drAddress=?,drPhone=? where drID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, pwd);
			preparedStmt.setString(3, gender);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, address);
			preparedStmt.setInt(6, Integer.parseInt(phone));
			preparedStmt.setInt(7, Integer.parseInt(ID));

			preparedStmt.execute();
			con.close();

			String newDoctor = readDr();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the doctor details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Delete
	public String deleteDr(String ID) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from doctordetail where drID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(ID)); 
			preparedStmt.execute();
			con.close();
			
			
			String newDoctor = readDr();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the doctor details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
}
