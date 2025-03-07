package model;

import java.sql.Connection;

import my_utils.Utility;

import java.sql.*;

public class UserData {
	
	// fields
	private Connection connection;
	public static int currentUserId;
	
	// constructor
	public UserData() {
		try {
			this.connection = Utility.dbConnect();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	/**
	 * Method to register a new user
	 * @param user
	 * @return boolean
	 */
	public boolean registerUser(User user) {
		
		try {
			
			String query = "INSERT INTO tb_user (email, username, password) VALUES (?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setString(1, user.getEmail());
			stm.setString(2, user.getUsername());
			stm.setString(3, user.getPassword());
			
			int row = stm.executeUpdate();
			if (row > 0)
				return true;
			else
				return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Method to login to the system
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public boolean login(String username, String password) {
		try {
			
			String query = "SELECT * FROM tb_user WHERE username=? AND password=?";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setString(1, username);
			stm.setString(2, password);
			
			ResultSet result = stm.executeQuery();
			
			while(result.next()) {
				currentUserId = result.getInt("id");
				return true;
			}
			
			
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
