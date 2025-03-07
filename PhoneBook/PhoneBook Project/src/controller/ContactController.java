package controller;

import view.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.*;
import javax.swing.*;

public class ContactController {

	// Create field
	private ContactView contactView;
	private static Contact contact = null;
	
	public ContactController(ContactView cv) {
		this.contactView = cv;
		
		// show contacts on the list
		updateContactList();
		
		// ad button listener
		contactView.addAddButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// check for empty fields in the
				// txtFirstName, txtLastName, txtPhoneNumber'
				if(contactView.getFirstName().isEmpty() || contactView.getLastName().isEmpty() || contactView.getPhoneNumber().isEmpty()) {
					return;
				} else { // perform action if they are filled
					String firstName = contactView.getFirstName();
					String lastName = contactView.getLastName();
					String phoneNumber = contactView.getPhoneNumber();
					
					Contact contact = new Contact(firstName, lastName, phoneNumber);
					new ContactData().addContact(contact);
					
					JOptionPane.showMessageDialog(null, "Contact Added Successfully");
					
					// Clear the text fields after adding
					contactView.setFirstNameField("");
					contactView.setLastNameField("");
					contactView.setPhoneNumberField("");
					
					updateContactList();
				}
				
				
			}
		});
		
		// ListSelectionListener
		contactView.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				getSelectedContact();
				
				if (contact != null) {
					updateFields(contact);
				}
			}
		});
		
		// Update Button
		contactView.addUpdateButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fn = contactView.getFirstName();
				String ln = contactView.getLastName();
				String pn = contactView.getPhoneNumber();
				
				// Validate the fields - firstname, lastname, and phonenumber
				if (fn.isEmpty() || ln.isEmpty() || pn.isEmpty()) {
					return;
				}
				
				contact.setFirstName(fn);
				contact.setLastName(ln);
				contact.setPhoneNumber(pn);
				
				if (new ContactData().updateContact(contact)) {
					JOptionPane.showMessageDialog(null, "Contact updated successfully");
					updateContactList();
				}
			}
		});
		
		// Logout Button
		contactView.addLogoutButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserData.currentUserId = 0;
				
				contactView.setVisible(false);
				LoginView lv = new LoginView();
				lv.setVisible(true);
				new LoginController(lv);
				
			}
		});
		
		// delete button
		contactView.addDeleteButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (contact != null) {
					int option = JOptionPane.showConfirmDialog(contactView, 
							"Confirm Delete?", "Delete",JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION); {
						
						if (new ContactData().deleteContact(contact)) {
							JOptionPane.showMessageDialog(null,  "Contact deleted successfully");
							updateContactList();
						} else {
							JOptionPane.showMessageDialog(null,  "Contact delete error");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No contact selected");
				}
				
			}
		});
		
	}
	/**
	 * Method to update the contact list
	 */
	private void updateContactList() {
		List<Contact> contacts = new ContactData().getAllContacts();
		contactView.setContactsToModel(contacts);
		contactView.setFirstNameField("");
		contactView.setLastNameField("");
		contactView.setPhoneNumberField("");
	}
	
	/**
	 * Method to get the selected contact from the JList
	 * @return contact Object
	 */
	private Contact getSelectedContact() {
		// Contact contact = null;
		
		int row = contactView.getContactList().getSelectedIndex();
		
		if (row != -1) {
			contact = new ContactData().getAllContacts().get(row);
			// contact.setId(row+1);
		}
		return contact;
	}
	
	private void updateFields(Contact contact) {
		contactView.getFirstNameField().setText(contact.getFirstName());
		contactView.getLastNameField().setText(contact.getLastName());
		contactView.getPhoneNumberField().setText(contact.getPhoneNumber());
	}
	
}
