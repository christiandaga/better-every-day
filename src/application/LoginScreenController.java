package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController {

	// Username field.
	@FXML
	private TextField username;
	
	// Password field.
	@FXML
	private PasswordField password;
	
	// Incorrect login label.
	@FXML
	private Label incorrectLogin;
	
	// Login button.
	@FXML
	private Button login;
	
	// Register button.
	@FXML
	private Button register;
	
	// When the register button is clicked, the Create Account screen comes up.
	@FXML 
	protected void register(ActionEvent event) throws IOException {
		
		FXRouter.goTo("register");
	}
	
	// When the Login button is clicked, the Home Screen comes up. If the user enters a wrong username and/or password, an error message pops up.
	@FXML
	protected void login(ActionEvent event) throws IOException
	{
		if (Auth.login(username.getText(), password.getText())) {
			FXRouter.goTo("home");
		} 
		
		else {
			incorrectLogin.setText("Invalid username and/or password.");
		}
	}
}