package application;

import java.io.IOException;

import javafx.fxml.FXML;

public class DefaultController {

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
}
