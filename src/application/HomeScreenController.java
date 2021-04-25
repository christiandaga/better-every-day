package application;

import java.io.IOException;

import javafx.fxml.FXML;

public class HomeScreenController {

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
	
	@FXML
	protected void createHabit() throws IOException {
		FXRouter.goTo("createHabit");
	}
	
}
