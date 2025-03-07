package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import my_utils.Utility;

public class ContactData {

	// Connection field
	private Connection connection;

	// Constructor
	public ContactData() {
		try {
			this.connection = Utility.dbConnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to add new contacts
	 * 
	 * @param contact
	 * @return boolean
	 */
	public boolean addContact(Contact contact) {
		try {

			String query = "INSERT INTO tb_contact (first_name, last_name, phone_number, user) VALUES (?, ?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(query);

			stm.setString(1, contact.getFirstName());
			stm.setString(2, contact.getLastName());
			stm.setString(3, contact.getPhoneNumber());
			stm.setInt(4, UserData.currentUserId);

			int row = stm.executeUpdate();
			if (row > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Method to retrieve contacts from the database
	 * 
	 * @return list of contacts
	 */
	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<>();
		try {
			String query = "SELECT * FROM tb_contact WHERE user = ?";
			PreparedStatement stm = connection.prepareStatement(query);

			stm.setInt(1, UserData.currentUserId);

			ResultSet result = stm.executeQuery();

			while (result.next()) {
				String fn = result.getString("first_name");
				String ln = result.getString("last_name");
				String pn = result.getString("phone_number");
				int id = result.getInt("id");
				contacts.add(new Contact(id, fn, ln, pn));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	/**
	 * Method to update contacts
	 * 
	 * @param contact
	 * @return boolean
	 */
	public boolean updateContact(Contact contact) {
		try {
			String query = "UPDATE tb_contact SET first_name=?, last_name=?, phone_number=? WHERE id=? AND user=?";
			PreparedStatement stm = connection.prepareStatement(query);

			stm.setString(1, contact.getFirstName());
			stm.setString(2, contact.getLastName());
			stm.setString(3, contact.getPhoneNumber());
			stm.setInt(4, contact.getId());
			stm.setInt(5, UserData.currentUserId);

			System.out.println("CONTACT ID" + contact.getId());
			System.out.println("USER ID" + UserData.currentUserId);

			int row = stm.executeUpdate();
			if (row > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Method to delete contact from database
	 * 
	 * @param contact
	 * @return boolean
	 */
	public boolean deleteContact(Contact contact) {
		try {
			String query = "DELETE FROM tb_contact WHERE id=? AND user=?";
			PreparedStatement stm = connection.prepareStatement(query);

			stm.setInt(1, contact.getId());
			stm.setInt(2, UserData.currentUserId);

			//
			System.out.println(contact.getFirstName());
			System.out.println(contact.getLastName());
			System.out.println(contact.getPhoneNumber());
			System.out.println(contact.getId());
			//

			int row = stm.executeUpdate();
			System.out.println(row);
			if (row > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
