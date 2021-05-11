package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HomeScreenController implements Initializable {

	@FXML
	private ListView habitList;
	
	@FXML
	private Label welcomeLabel;
	
	
	@FXML
	protected void goHome() throws IOException {
		FXRouter.goTo("home");
	}
	
	@FXML
	protected void goToProfile() throws IOException {
		FXRouter.goTo("profile");
	}
	
	@FXML
	protected void goToSettings() throws IOException {
		FXRouter.goTo("settings");
	}
	
	@FXML
	protected void createHabit() throws IOException {
		System.out.println("In createHabit");
		FXRouter.goTo("createHabit");
	}
	
	@FXML
	protected void markDone() throws IOException {
		System.out.println(" In markDone");
		String selectedHabit = " ";
		
		// Check if a habit from the list has been selected
		selectedHabit = (String) habitList.getSelectionModel().getSelectedItem();
		
		int newUserLevel = Auth.currentUser.getUserLevel() + 1;
		Auth.currentUser.setUserLevel(newUserLevel);
		
		// Update the users collection with new level - TBD
		
		Db.db.updateItem("users", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("userLevel", newUserLevel));

		// temp
		Habit tempHabit = new Habit(Db.db.findOne("habits", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("name", selectedHabit))));
		tempHabit.completeDay();
	}
	
	@FXML
	protected void editHabit() throws IOException {
		System.out.println("In editHabit");
		String selectedHabit = " ";
		
		// Check if a habit from the list has been selected
		selectedHabit = (String) habitList.getSelectionModel().getSelectedItem();

		FXRouter.goTo("editHabit", selectedHabit);
	}
	
	// Logs the user out of their account when the Log Out button is clicked.
	@FXML
	protected void logOut() throws IOException {
		Auth.logout();
		FXRouter.goTo("login");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		// welcomeLabel.setText("Welcome " + Auth.currentUser.getUsername() + " !");
		List<Document> habitDocs = Db.db.findMany("habits", Filters.eq("username", Auth.currentUser.getUsername()));
		habitList.getItems().addAll(habitDocs.stream().map((Document habit) -> habit.getString("name")).toArray());
	}
}