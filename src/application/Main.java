package application;

import java.io.IOException;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
	private static final Color cGreen = Color.web("#55C461"); // customized green color
	private static final Color lGray = Color.web("#EEEEEE"); // customized light gray color
	private static final Color dGray = Color.web("#707070"); // customized dark gray color
	
	@Override
	public void start(Stage primaryStage) {
		
		FXRouter.bind(this, primaryStage, new CreateAccountController());
		FXRouter.when("exampleScreen", "examplescreen.fxml", new LoginScreenController());
		FXRouter.when("anotherExampleScreen", "anotherexamplescreen.fxml");
		
		try {
			VBox vbox = new VBox(); // Creates a Vertical Box.
			vbox.setStyle("-fx-background-color: orange;"); // Sets background color of the Vertical Box to orange.
			vbox.setSpacing(20); // Sets spacing for the Vertical Box.
			vbox.setPadding(new Insets(20, 20, 20, 20)); // Sets padding for the Vertical Box.
			
			Label label1 = new Label("Username:"); // Creates a label for the username.
			label1.setFont(Font.font("Monserrat", FontWeight.BOLD, 20)); // Sets font properties of the username label.
			
			TextField textField = new TextField(); // Creates a text field to enter the username.
			
			Label label2 = new Label("Password:"); // Creates a label for the password.
			label2.setFont(Font.font("Monserrat", FontWeight.BOLD, 20)); // Sets font properties of the password label.
			
			PasswordField passwordField = new PasswordField(); // Creates a password field - when the user is typing the password, the password
															   // will be hidden.
			
			Button loginBtn = new Button("LOGIN"); // Creates a login button.
			loginBtn.setFont(Font.font("Monserrat", FontWeight.BOLD, 20)); // Sets font properties of the login button.
			loginBtn.setBackground(new Background(new BackgroundFill(cGreen, CornerRadii.EMPTY, Insets.EMPTY)));
			
			// When the login button is clicked, the Home page will come up.
			loginBtn.setOnAction(new EventHandler<ActionEvent> () {
				
				@Override
				public void handle(ActionEvent event) {
					User newUser = new User(textField.getText(), passwordField.getText(), "userC@c.com");
					
					Habit myHabit = new Habit("Swimming", 2);
					myHabit.addSchedule("Saturday", "2 pm", 30);
					myHabit.addSchedule("Sunday", "3 pm", 60);
					
					newUser.addHabit(myHabit);
					
					newUser.viewHabits();
					
					
					
					//Document doc = new Document("username", textField.getText()).append("password", passwordField.getText()); // Initializes MongoDB document containing user info.
					// Db.db.addItemToDB("users", newUser.getDocument()); // Adds user document to database.
					
					String uname = textField.getText();
					
					VBox vbox2 = new VBox(); // Creates a Vertical Box.
					vbox2.setStyle("-fx-background-color: green;"); // Sets background color of the Vertical Box to green.
					vbox2.setSpacing(20); // Sets spacing for the Vertical Box.
					vbox2.setPadding(new Insets(20, 20, 20, 20)); // Sets padding for the Vertical Box.
					
					Document user = Db.db.findOne("users", Filters.eq("username", textField.getText())); // Gets user from db
					
					
					
					// Label label3 = new Label("Welcome, " + (user == null ? "username" : user.getString("username")) + "!"); // Creates a label to welcome the user to the Home page.
					Label label3 = new Label("WELCOME, " + uname + "!"); // Creates a label to welcome the user to the Home page.
					label3.setFont(Font.font("Monserrat", FontWeight.BOLD, 20)); // Sets font properties of the 'welcome' label.
					
					// Label label4 = new Label("User Level" + ((  user.getUserLevel() )));
					
					Button exampleBtn = new Button("Go to example screen");
					exampleBtn.setFont(Font.font("Monserrat", FontWeight.BOLD, 20));
					exampleBtn.setBackground(new Background(new BackgroundFill(cGreen, CornerRadii.EMPTY, Insets.EMPTY)));
					
					// When the login button is clicked, the Home page will come up.
					exampleBtn.setOnAction(new EventHandler<ActionEvent> () {
						
						@Override
						public void handle(ActionEvent event) {
							try {
								FXRouter.goTo("exampleScreen", textField.getText());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
					
					vbox2.getChildren().addAll(label3, exampleBtn); // Adds the 'welcome' label to the scene.
					
					Scene newScene = new Scene(vbox2, 600, 400); // Creates a scene.
					primaryStage.setTitle("Home Page"); // Sets the title of the stage.
					primaryStage.setScene(newScene); // Allows us to go to a new scene when the login button is clicked by the user.
					primaryStage.show(); // Displays the stage.
				}
			});
			
			
			
			vbox.getChildren().addAll(label1, textField, label2, passwordField, loginBtn); // Adds all the nodes created to the scene.
			
			
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
