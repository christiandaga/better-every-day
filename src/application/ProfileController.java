package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ProfileController implements Initializable {
	
	@FXML
	private Label level;
	
	@FXML
	private Label username;
	
	@FXML
	private Label daysOnApp;

	@FXML
	protected void goHome() throws IOException {
		FXRouter.goTo("home");
	}
	
	@FXML
	protected void goToProfile() throws IOException {
		FXRouter.goTo("profile");
	}
	
	@FXML
	protected void goToSettings() {
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		username.setText(Auth.currentUser.getUsername());
		level.setText(String.valueOf(Auth.currentUser.getUserLevel()));
	}
	
}
