package controller;

import view.*;
import model.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class LoginController {

	// create a reference variable
	private LoginView loginView;
	
	// create a constructor
	public LoginController(LoginView lv) {
		this.loginView = lv;
		
		// login button listener
		loginView.addLoginButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Validate the username and password fields
				// Check for empty fields
				
				if (new UserData().login(loginView.username(), loginView.password())) {
//					JOptionPane.showMessageDialog(null, "Login Successful");
					loginView.setVisible(false);
					ContactView cv = new ContactView();
					new ContactController(cv);
					
					cv.setVisible(true);
				} else {
					// CHECKING FOR EMPTY FIELDS
					if (loginView.username().isBlank() || loginView.password().isBlank()) {
						JOptionPane.showMessageDialog(null, "Empty Username or Password Fields");
					} else {
						JOptionPane.showMessageDialog(null, "Wrong Username Or Password");
					}
				}
				
//				
			}
		});
		
		// For the register button listener
		loginView.addRegisterButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Register page view");
				
				// hide the login view
				loginView.setVisible(false);
				
				// open the register view
				RegisterView registerView = new RegisterView();
				RegisterController rc = new RegisterController(registerView);
				
				registerView.setVisible(true);
			}
		});
	}
	
	
}
