package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class SettingsController implements Initializable{
	
	@FXML
	private Label level;
	@FXML
	private Label username;
	@FXML
	private ProgressBar pBar;
	@FXML
	private Label numAchieved;
	
	@FXML
	private TextField newUsername;
	@FXML
	private TextField newEmail;
	@FXML
	private TextField newPassword;

	// Navigation bar.
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
	// Logs the user out of their account when the Log Out button is clicked.
	@FXML
	protected void logOut() throws IOException {
		Auth.logout();
		FXRouter.goTo("login");
	}

	// Edits user information. Only edits user information if a value is given.
	// Special case for editing username: Only change username if username is not yet taken.
	@FXML
	protected void editProfile(ActionEvent event) throws IOException {
		String username = newUsername.getText();
		String email = newEmail.getText();
		String password = newPassword.getText();
		
		if(password != "") {
			Db.db.updateItem("users", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("password", password));
			Auth.currentUser.setPassword(password);
		}
		
		if(email != "") {
			Db.db.updateItem("users", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("email", email));
			Auth.currentUser.setEmail(email);
		}
		if(username != "") {
			Document user = Db.db.findOne("users", Filters.eq("username", username));
			if (user != null) {
				System.out.println("Username is taken");
			} else {
				Db.db.updateItem("users", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("username", username));
				Db.db.updateItems("habits", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("username", username));
				Db.db.updateItems("reminders", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("username", username));
				Db.db.updateItems("categories", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("username", username));
				Auth.currentUser.setUsername(username);
			}
			
		}
		FXRouter.goTo("settings");
	}

	// Deletes user account.
	@FXML
	protected void deleteAccount() throws IOException {
		Db.db.deleteItem("users", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("password", Auth.currentUser.getPassword()), Filters.eq("email", Auth.currentUser.getEmail())));
		Db.db.deleteItems("habits", Filters.eq("username", Auth.currentUser.getUsername()));
		Db.db.deleteItems("reminders", Filters.eq("username", Auth.currentUser.getUsername()));
		Db.db.deleteItems("categories", Filters.eq("username", Auth.currentUser.getUsername()));
		Auth.logout();
		FXRouter.goTo("login");
	}
	
	// initializes with username, level, and progress bar.
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Document> cats = Db.db.findMany("categories", Filters.eq("username", Auth.currentUser.getUsername()));
		int numAchvm = 0;
		for (Document cat : cats) {
			Category category = new Category(cat);
			numAchvm += category.getAchievements();
		}
		pBar.setProgress((double) numAchvm / 16);
		numAchieved.setText(numAchvm + "/16 Badges Achieved");
		username.setText(Auth.currentUser.getUsername());
		level.setText(String.valueOf(Auth.currentUser.getUserLevel()));
	}
}