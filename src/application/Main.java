package application;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Db db = new Db();
		
		try {
			
			VBox vbox = new VBox(); // Creates a Vertical Box.
			vbox.setStyle("-fx-background-color: orange;"); // Sets background color of the Vertical Box to orange.
			vbox.setSpacing(20); // Sets spacing for the Vertical Box.
			vbox.setPadding(new Insets(20, 20, 20, 20)); // Sets padding for the Vertical Box.
			
			Label label1 = new Label("Username:"); // Creates a label for the username.
			label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20)); // Sets font properties of the username label.
			
			TextField textField = new TextField(); // Creates a text field to enter the username.
			
			Label label2 = new Label("Password:"); // Creates a label for the password.
			label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20)); // Sets font properties of the password label.
			
			PasswordField passwordField = new PasswordField(); // Creates a password field - when the user is typing the password, the password
															   // will be hidden.
			
			Button button = new Button("LOGIN"); // Creates a login button.
			button.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20)); // Sets font properties of the login button.
			
			// When the login button is clicked, the Home page will come up.
			button.setOnAction(new EventHandler<ActionEvent> () {
				
				@Override
				public void handle(ActionEvent event) {
					Document doc = new Document("username", textField.getText()).append("password", passwordField.getText()); // Initializes MongoDB document containing user info.
					db.addItemToDB("users", doc); // Adds user document to database.
					
					VBox vbox2 = new VBox(); // Creates a Vertical Box.
					vbox2.setStyle("-fx-background-color: green;"); // Sets background color of the Vertical Box to green.
					vbox2.setSpacing(20); // Sets spacing for the Vertical Box.
					vbox2.setPadding(new Insets(20, 20, 20, 20)); // Sets padding for the Vertical Box.
					
					Document user = db.findOne("users", Filters.eq("username", textField.getText())); // Gets user from db
					
					Label label3 = new Label("Welcome, " + (user == null ? "<username>" : user.getString("username")) + "!"); // Creates a label to welcome the user to the Home page.
					label3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20)); // Sets font properties of the 'welcome' label.
					
					vbox2.getChildren().add(label3); // Adds the 'welcome' label to the scene.
					
					Scene newScene = new Scene(vbox2, 600, 400); // Creates a scene.
					primaryStage.setTitle("Home Page"); // Sets the title of the stage.
					primaryStage.setScene(newScene); // Allows us to go to a new scene when the login button is clicked by the user.
					primaryStage.show(); // Displays the stage.
				}
			});
			
			vbox.getChildren().addAll(label1, textField, label2, passwordField, button); // Adds all the nodes created to the scene.
			
			
			/**
			ListView listView = new ListView();
			
			db.database.getCollection("users").find().forEach((Document item) -> listView.getItems().add(item.get("username")));
			
			HBox hbox = new  HBox(listView);
			
			Scene scene = new Scene(hbox,300,120);
			*/ 
			
			Scene scene = new Scene(vbox, 600, 400); // Creates a scene.
			primaryStage.setTitle("Login Screen"); // Sets the title of the stage.
			primaryStage.setScene(scene); // Sets the scene of the stage.
			primaryStage.show(); // Displays the stage.

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
