package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class ExampleMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		FXRouter.bind(this, primaryStage);
		FXRouter.when("exampleScreen", "examplescreen.fxml", new ExampleScreenController());
		FXRouter.when("anotherExampleScreen", "anotherexamplescreen.fxml", new AnotherExampleScreenController());
		
		try {
			FXRouter.startFrom("exampleScreen");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}