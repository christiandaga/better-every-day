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
<<<<<<< HEAD
		
		User newUser = new User(username.getText(), password.getText(), email.getText()); // Creates a new user containing the username, password, and email.
		Document doc = new Document("username", newUser.getUsername()).append("password", newUser.getPassword()).append("email", newUser.getEmail()).append("userLevel", newUser.getUserLevel()); // Initializes MongoDB document containing user information.
		Db.db.addItemToDB("users", doc); // Adds user document to database.
		
		try {
			FXRouter.goTo("HomeScreen"); // Switches to the Home screen.
		} catch (IOException e) {
			e.printStackTrace();
		}
=======
		
		boolean ok = Auth.createUser(username.getText(), password.getText(), email.getText());
		
		if (ok) {
			try {
				FXRouter.goTo("home");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
>>>>>>> b8a07b2 (login/register connection)
	}
}