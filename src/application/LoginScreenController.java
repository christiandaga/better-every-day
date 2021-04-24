package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController {
	
	/**
	@FXML
	ListView<String> list;
	*/
	
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
<<<<<<< HEAD
		
		try {
			FXRouter.goTo("CreateAccountScreen"); // Switches to the Create Account screen.
		} catch (IOException e) {
			e.printStackTrace();
		}
		
=======
		
		FXRouter.goTo("register");
		
>>>>>>> b8a07b2 (login/register connection)
		// ExampleMain exampleMain = new ExampleMain();
		// exampleMain.changeScene("CreateAccountScreen.fxml");
	}
	
	/**
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<String> items = FXCollections.observableArrayList();
		list.setItems(items);
		
		Document u = Db.db.findOne("users", Filters.eq("username", FXRouter.getData()));
		if (u != null) {
			User user = new User(u);
			items.add(user.getUsername());
			items.add(user.getEmail());
			items.add(String.valueOf(user.getUserLevel()));
		} else {
			items.add("No Data");
		}
		
	}
	*/
	
	// When the Login button is clicked, the Home Screen comes up.
	@FXML
	protected void login(ActionEvent event) throws IOException
	{
<<<<<<< HEAD
		try {
			FXRouter.goTo("HomeScreen"); // Switches to the Home screen.
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ExampleMain exampleMain = new ExampleMain();
		// exampleMain.changeScene("HomeScreen.fxml");
=======
		// ExampleMain exampleMain = new ExampleMain();
		// exampleMain.changeScene("HomeScreen.fxml");
		
		if (Auth.login(username.getText(), password.getText())) {
			FXRouter.goTo("home");
		}
		
>>>>>>> b8a07b2 (login/register connection)
	}
}
