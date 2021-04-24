package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExampleMain extends Application {
	
	private static Stage tempStage; // Temporary stage.
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/**
		FXRouter.bind(this, primaryStage, new FXMLController());
		FXRouter.when("exampleScreen", "examplescreen.fxml", new ExampleScreenController());
		FXRouter.when("anotherExampleScreen", "anotherexamplescreen.fxml");
		
		try {
			FXRouter.goTo("exampleScreen", "userC");
		} catch (Exception e) {
			e.printStackTrace();  
		}
		*/
		
		tempStage = primaryStage;
		primaryStage.setResizable(false); // The user is not allowed to resize the screen.
		
		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml")); // Loads LoginScreen.fxml.
		primaryStage.setTitle("Better Every Day");
		
		Scene scene = new Scene(root, 840, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Allows us to change from one scene to another scene.
	public void changeScene(String fxmlFile) throws IOException
	{
		Parent pane = FXMLLoader.load(getClass().getResource(fxmlFile)); // We input the .fxml file we want to load.
		tempStage.getScene().setRoot(pane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
