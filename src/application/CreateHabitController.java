package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateHabitController {
	
	@FXML
	private TextField name;

	@FXML
	protected void goHome() throws IOException {
		FXRouter.goTo("home");
	}
	
	@FXML
	protected void goToProfile() throws IOException {
		FXRouter.goTo("profile");
	}
	
	@FXML
	protected void goToSettings() throws IOException {
		FXRouter.goTo("settings");
	}
	
	// Adds habit to user's list of habits.
	@FXML
	protected void createHabit() throws IOException {
		Habit habit = new Habit(name.getText(), 100);
		Db.db.addItemToDB("habits", habit.getDocument().append("username", Auth.currentUser.getUsername()));
		FXRouter.goTo("home");
	}
	
	// Habit is not created and user is returned to home.
	@FXML
	protected void cancel() throws IOException {
		FXRouter.goTo("home");
	}
	
}
