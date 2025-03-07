package controller;

import java.awt.event.ActionEvent;

import model.*;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.*;

public class RegisterController {

	private RegisterView registerView;
	
	public RegisterController(RegisterView rv) {
		this.registerView = rv;
		
		// Go Back to login Button
		registerView.addLoginButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// hide window
				registerView.setVisible(false);
				
				// show login view
				LoginView loginView = new LoginView();
				LoginController loginController = new LoginController(loginView);
				
				loginView.setVisible(true);

			}
		});
		
		// Register Button Listener
		registerView.addRegisterButtonListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				// Validate the email, username and password fields
				// Check for empty fields
				// Check for correct email formats
				
				// Get the text in the fields
				String emailAttempt = registerView.email();
				String usernameAttempt = registerView.username();
				String passwordAttempt = registerView.password();
				// VALIDATE
				
				// check for blanks
				if (emailAttempt.isEmpty() || usernameAttempt.isEmpty() || passwordAttempt.isEmpty()) {
					JOptionPane.showMessageDialog(null, "One of the fields are empty, invalid register");
					return;
				// check for correct email format
				} else if (!emailAttempt.contains("@") && !emailAttempt.contains(".com")) {
					JOptionPane.showMessageDialog(null, "email does not contain @ or .com");
					return;
				}
				// create new user if passed validation
				User user = new User();
				user.setEmail(emailAttempt);
				user.setUsername(usernameAttempt);
				user.setPassword(passwordAttempt);
				
				
				UserData data = new UserData();
				if (data.registerUser(user)) {
					JOptionPane.showMessageDialog(null, "Register Successfully");
				} else {
					JOptionPane.showMessageDialog(null, "An Error occured");
				}
				
				
				
			}
		});
	}
}
