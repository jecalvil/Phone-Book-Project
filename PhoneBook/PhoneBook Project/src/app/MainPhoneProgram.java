package app;
import view.*;
import controller.*;
public class MainPhoneProgram {
	
	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		LoginController loginController = new LoginController(loginView);
		loginView.setVisible(true);
		// code 1: set the location of the loginView to the center of the screen
	}

}
