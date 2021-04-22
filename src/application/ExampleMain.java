package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class ExampleMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		FXRouter.bind(this, primaryStage);
		FXRouter.when("exampleScreen", "examplescreen.fxml");
		FXRouter.when("anotherExampleScreen", "anotherexamplescreen.fxml");
		
		try {
			FXRouter.goTo("exampleScreen");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
