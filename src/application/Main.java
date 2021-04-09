package application;
	
import org.bson.Document;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Db db = new Db();
		
		try {
			primaryStage.setTitle("Users");
			
			ListView listView = new ListView();
			
			db.database.getCollection("users").find().forEach((Document item) -> listView.getItems().add(item.get("username")));
			
			HBox hbox = new  HBox(listView);
			
			Scene scene = new Scene(hbox,300,120);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
