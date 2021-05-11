package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class EditHabitController implements Initializable {
	
	@FXML
	private TextField name;
	
	// Goes to the Home Screen.
	@FXML
	protected void goHome() throws IOException {
		FXRouter.goTo("home");
	}
	
	// Goes to the Profile Screen.
	@FXML
	protected void goToProfile() throws IOException {
		FXRouter.goTo("profile");
	}
	
	// Goes to the Settings Screen.
	@FXML
	protected void goToSettings() throws IOException {
		FXRouter.goTo("settings");
	}
	
	// Logs the user out of their account and goes back to the Login Screen.
	@FXML
	protected void logout() throws IOException {
		Auth.logout();
		FXRouter.goTo("login");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		String myHabit = (String) FXRouter.getData();
		name.setText(myHabit);
		
	}
	
	// Adds habit to user's list of habits.
	@FXML
	protected void changeHabit() throws IOException {
		
		// Get habit name from text field
		String habitName = name.getText(); 
		
		// Update that habit in DB
		// Call updateItem(...) 
		
		System.out.println("Updating " + habitName);
		
		
		FXRouter.goTo("home");
		
	}
	
	// Habit is not created and user is returned to home.
	@FXML
	protected void cancel() throws IOException {
		FXRouter.goTo("home");
	}
	
}
