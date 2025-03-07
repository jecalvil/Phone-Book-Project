package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import my_utils.Utility;

public class LoginView extends JFrame{
	
	// create component reference variables
	private JLabel username, password;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnRegister;
	
	// constructor
	public LoginView() {
		setTitle("Login");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// initialize components
		username = new JLabel("Username:");
		password = new JLabel("Password:");
		txtUsername = new JTextField(10);
		txtPassword = new JPasswordField(10);
		btnLogin = new JButton("Login");
		btnRegister = new JButton("Register");
		
		// create a panel with grid layout
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		// add components to the panel
		panel.add(username);
		panel.add(txtUsername);
		panel.add(password);
		panel.add(txtPassword);
		panel.add(btnLogin);
		panel.add(btnRegister);
		
		// add the panel to the window
		add(panel);
		
		setLocationRelativeTo(null);
			
	}
	
	// create method to add an action listener on the login button
	public void addLoginButtonListener(ActionListener listener) {
		btnLogin.addActionListener(listener);
	}
	
	public void addRegisterButtonListener(ActionListener listener) {
		btnRegister.addActionListener(listener);
	}
	// Method to return text from the username text field
	public String username() {
		return txtUsername.getText();
	}
	// Method to return text from the password text field
	public String password() {
		String password = Utility.hashPassword(txtPassword.getText());
		return password;
	}
}
