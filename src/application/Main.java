package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		
		FXRouter.bind(this, primaryStage, new DefaultController(), "Better Every Day");
		FXRouter.when("login", "LoginScreen.fxml", new LoginScreenController());
		FXRouter.when("register", "CreateAccountScreen.fxml", new CreateAccountController());
		FXRouter.when("home", "HomeScreen.fxml", new HomeScreenController());
		FXRouter.when("profile", "ProfileScreen.fxml", new ProfileController());
		FXRouter.when("createHabit", "CreateHabit.fxml", new CreateHabitController());
		FXRouter.when("editHabit", "EditHabit.fxml", new EditHabitController());
		FXRouter.when("settings", "Settings.fxml", new SettingsController());
		
		Platform.setImplicitExit(false);
		
		try {
			FXRouter.startFrom("login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}