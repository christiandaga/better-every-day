package application;

import java.io.IOException;

import org.bson.Document;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController {
	
	// Username field.
	@FXML
	private TextField username;
	
	// Email field.
	@FXML
	private TextField email;
	
	// Password field.
	@FXML
	private PasswordField password;
	
	// Create Account button.
	@FXML
	private Button createAccount;
	
	// When the Create Account button is clicked, the Home Screen comes up.
	@FXML 
	protected void createAccount(ActionEvent event) throws IOException {
		
		// ExampleMain exampleMain = new ExampleMain();
		// exampleMain.changeScene("HomeScreen.fxml");
		
		boolean ok = Auth.createUser(username.getText(), password.getText(), email.getText());
		
		if (ok) {
			try {
				FXRouter.goTo("home");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}