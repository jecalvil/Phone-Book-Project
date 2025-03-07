package view;

import java.awt.*;
import  java.util.List;
import java.awt.event.ActionListener;
import model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

public class ContactView extends JFrame{

	// Create references to components
	private JLabel firstName, lastName, phoneNumber;
	private JTextField txtFirstName, txtLastName, txtPhoneNumber;
	private JButton btnAdd, btnUpdate, btnDelete, btnLogout;
	private JList<String> contactList;
	private DefaultListModel<String> listModel;
	
	public ContactView() {
		setTitle("PhoneBook");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// initialize components
		listModel = new DefaultListModel<String>();
		setContactList(new JList<>(listModel));
		
		firstName = new JLabel("First name:");
		lastName = new JLabel("Last name:");
		phoneNumber = new JLabel("Phone Number");
		txtFirstName = new JTextField(15);
		txtLastName = new JTextField(15);
		txtPhoneNumber = new JTextField(15);
		btnAdd = new JButton("Add");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnLogout  = new JButton("Logout");
		contactList = new JList<String>();
		contactList.setModel(listModel);
		
		// Input panels
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4,2));
		inputPanel.add(firstName);
		inputPanel.add(txtFirstName);
		inputPanel.add(lastName);
		inputPanel.add(txtLastName);
		inputPanel.add(phoneNumber);
		inputPanel.add(txtPhoneNumber);
		inputPanel.add(btnAdd);
		inputPanel.add(btnUpdate);
		
		// Contact List Panel
		JPanel listPanel = new JPanel(new GridLayout(2,1));
		listPanel.add(new JLabel("Contact list"));
		listPanel.add(new JScrollPane(getContactList()));

		
		// Buttons Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnLogout);
		
		// add panels to window
		setLayout(new BorderLayout());
		add(inputPanel, BorderLayout.NORTH);
		add(listPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		
		
		
		pack(); // automatically resize the window to fit the components
		setLocationRelativeTo(null);
		
	}
	// Action listeners
	public void addAddButtonListener(ActionListener listener) {
		btnAdd.addActionListener(listener);
	}
	
	public void addUpdateButtonListener(ActionListener listener) {
		btnUpdate.addActionListener(listener);
	}
	
	public void addDeleteButtonListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
	}
	
	public void addLogoutButtonListener(ActionListener listener) {
		btnLogout.addActionListener(listener);
	}
	public void addListSelectionListener(ListSelectionListener listener) {
		contactList.addListSelectionListener(listener);
	}
	
	// getters
	public JTextField getFirstNameField() {
		return txtFirstName;
	}
	public String getFirstName() {
		return getFirstNameField().getText();
	}
	
	public JTextField getLastNameField() {
		return txtLastName;
	}
	public String getLastName() {
		return getLastNameField().getText();
	}
	
	public JTextField getPhoneNumberField() {
		return txtPhoneNumber;
	}
	public String getPhoneNumber() {
		return getPhoneNumberField().getText();
	}
	public JList<String> getContactList() {
		// contactList.setModel(listModel);
		return contactList;
	}
	// setters
	public void setFirstNameField(String fn) {
		this.txtFirstName.setText(fn);
	}
	public void setLastNameField(String ln) {
		this.txtLastName.setText(ln);
	}
	public void setPhoneNumberField(String pn) {
		this.txtPhoneNumber.setText(pn);
	}
	public void setContactList(JList<String> cl) {
		// this.contactList.setModel(listModel);
		this.contactList = cl;
		
	}
	
	public void setContactsToModel(List<Contact> contacts) {
		listModel.clear();
		
		for (Contact c: contacts) {
			listModel.addElement(c.getFirstName()+" "+c.getLastName()+" "+c.getPhoneNumber());
		}
	}
}
