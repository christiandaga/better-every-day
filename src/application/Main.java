package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	// private static Stage tempStage; // Temporary stage.
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		FXRouter.bind(this, primaryStage, new LoginScreenController()); // Connects FXRouter to our application stage.
//		
//		// Defines our application routes with a label identifier and its corresponding .fxml file.
//		FXRouter.when("LoginScreen", "LoginScreen.fxml"); 
//		FXRouter.when("CreateAccountScreen", "CreateAccountScreen.fxml", new CreateAccountController());
//		FXRouter.when("HomeScreen", "HomeScreen.fxml");
//		
//		try {
//			FXRouter.goTo("LoginScreen"); Switches to the Login screen.
//		} catch (Exception e) {
//			e.printStackTrace();  
//		}
//		
//		tempStage = primaryStage;
//		primaryStage.setResizable(false); // The user is not allowed to resize the screen.
//		
//		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml")); // Loads LoginScreen.fxml.
//		primaryStage.setTitle("Better Every Day");
//		
//		Scene scene = new Scene(root, 840, 600);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//		primaryStage.show();*/
//		
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
		FXRouter.when("settings", "Settings.fxml", new SettingsController());
		
		Platform.setImplicitExit(false);
		
		try {
			FXRouter.startFrom("login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	// Allows us to change from one scene to another scene.
	/*public void changeScene(String fxmlFile) throws IOException
	{
		Parent pane = FXMLLoader.load(getClass().getResource(fxmlFile)); // We input the .fxml file we want to load.
		tempStage.getScene().setRoot(pane);
	}
	*/
	public static void main(String[] args) {
		launch(args);
	}
}
