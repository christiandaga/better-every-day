package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class ExampleMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		FXRouter.bind(this, primaryStage, new FXMLController());
		FXRouter.when("exampleScreen", "examplescreen.fxml", new ExampleScreenController());
		FXRouter.when("anotherExampleScreen", "anotherexamplescreen.fxml");
		
		try {
			FXRouter.goTo("exampleScreen", "userC");
		} catch (Exception e) {
			e.printStackTrace();  
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
