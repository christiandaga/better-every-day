package application;

import java.io.IOException;

import com.mongodb.client.model.Filters;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SettingsController {
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
	
	@FXML
	protected void editProfile(ActionEvent event) throws IOException {
		EditUser.editProfile(newUsername.getText(), newEmail.getText(), newPassword.getText());
	}
	
	@FXML
	protected void deleteAccount() throws IOException {
		EditUser.deleteAccount();
		FXRouter.goTo("login");
	}
}
