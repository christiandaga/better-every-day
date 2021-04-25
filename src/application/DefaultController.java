package application;

import java.io.IOException;

import javafx.fxml.FXML;

public class DefaultController {

	// FXRouter changes scene to home.
	@FXML
	protected void goHome() throws IOException {
		FXRouter.goTo("home");
	}
	
	// FXRouter changes scene to profile.
	@FXML
	protected void goToProfile() throws IOException {
		FXRouter.goTo("profile");
	}
	
	// FXRouter changes scene to settings.
	@FXML
	protected void goToSettings() throws IOException {
		FXRouter.goTo("settings");
	}
}
