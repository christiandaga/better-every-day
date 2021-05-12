package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class SettingsController implements Initializable{
	
	@FXML
	private Label level;
	
	@FXML
	private Label username;
	
	@FXML
	private TextField newUsername;

	@FXML
	private TextField newEmail;

	@FXML
	private TextField newPassword;

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

	@FXML
	protected void editProfile(ActionEvent event) throws IOException {
		EditUser.editProfile(newUsername.getText(), newEmail.getText(), newPassword.getText());
	}

	@FXML
	protected void deleteAccount() throws IOException {
		EditUser.deleteAccount();
		FXRouter.goTo("login");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		username.setText(Auth.currentUser.getUsername());
		level.setText(String.valueOf(Auth.currentUser.getUserLevel()));
	}
}