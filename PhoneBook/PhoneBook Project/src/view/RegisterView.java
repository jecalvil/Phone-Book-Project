package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import my_utils.Utility;

public class RegisterView extends JFrame{

	// create component reference variables
	private JLabel email, username, password;
	private JTextField txtEmail, txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnRegister;
	
	// constructor
	public RegisterView() {
		setTitle("Register");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// initialize components
		email = new JLabel("Email:");
		username = new JLabel("Username:");
		password = new JLabel("Password:");
		txtEmail = new JTextField(10);
		txtUsername = new JTextField(10);
		txtPassword = new JPasswordField(10);
		btnLogin = new JButton("Go Back To Login");
		btnRegister = new JButton("Register");
		
		// create a panel with grid layout
		JPanel panel = new JPanel(new GridLayout(4,2));
		
		// add components to the panel
		panel.add(email);
		panel.add(txtEmail);
		panel.add(username);
		panel.add(txtUsername);
		panel.add(password);
		panel.add(txtPassword);
		panel.add(btnLogin);
		panel.add(btnRegister);
		
		// add the panel to the window
		add(panel);
		setLocationRelativeTo(null);
		setVisible(false);
		
	}
	
	// create method to add an action listener on the login button
		public void addLoginButtonListener(ActionListener listener) {
			btnLogin.addActionListener(listener);
		}
		
		public void addRegisterButtonListener(ActionListener listener) {
			btnRegister.addActionListener(listener);
		}
		
		// getters
		public String email() {
			return txtEmail.getText();
		}
		
		public String username() {
			return txtUsername.getText();
		}
		
		public String password() {
			String password = Utility.hashPassword(txtPassword.getText());
			return password;
		}

}
