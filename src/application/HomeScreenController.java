package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
	
	// marks habit done and increments level and daysCompleted
	@FXML
	protected void markDone() throws IOException {
		System.out.println(" In markDone");
		String selectedHabit = " ";
		
		// Check if a habit from the list has been selected
		selectedHabit = getSelectedHabit();
		
		int newUserLevel = Auth.currentUser.getUserLevel() + 1;
		Auth.currentUser.setUserLevel(newUserLevel);
		
		// Update the users collection with new level
		Db.db.updateItem("users", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("userLevel", newUserLevel));

		
		Document habitDoc = Db.db.findOne("habits", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("name", selectedHabit)));
		if (habitDoc != null) {
			Habit tempHabit = new Habit(habitDoc);
			tempHabit.completeDay();
		}
	}
	
	// deletes habit and corresponding reminders
	@FXML
	protected void deleteHabit() throws IOException {
		System.out.println(" In deleteHabit");
		String selectedHabit = " ";
		
		// Check if a habit from the list has been selected
		selectedHabit = getSelectedHabit();
		
		
		// Drop this habit from habits collection
		Db.db.deleteItem("habits", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("name", selectedHabit)));
		
		// Deletes reminder and removes it from timer
		RemindersManager.removeReminder(selectedHabit);
		
		// habitList.getSelectionModel().clearSelection();
		habitList.getItems().clear();
		
		renderItemList();
	}
	
	
	// Logs the user out of their account when the Log Out button is clicked.
	@FXML
	protected void logOut() throws IOException {
		Auth.logout();
		FXRouter.goTo("login");
	}

	private String getSelectedHabit() {
		String selectedHabit = (String) habitList.getSelectionModel().getSelectedItem();
		if (selectedHabit == null) selectedHabit = "";
		String[] splitSelected = selectedHabit.split("  ");
		return splitSelected[0];
	}
	
	private String beautifyTime(Date dat) {
		int hour = dat.getHours();
		String minutes = String.valueOf(dat.getMinutes());
		if (minutes.length() == 1) minutes = "0" + minutes;
		if (hour >= 12) {
			hour -= 12;
			if (hour == 0) hour += 12;
			return String.valueOf(hour) + ":" + minutes + "pm";
		}
		if (hour == 0) hour += 12;
		return String.valueOf(hour) + ":" + minutes + "am";
	}
	
	// renders a list of habits, todays habits at the top
	private void renderItemList() {
		List<Document> habitDocs = Db.db.findMany("habits", Filters.eq("username", Auth.currentUser.getUsername()));
		
		ArrayList<String> itemList = new ArrayList<String>();
		Date date = new Date();
		List<Document> reminderDocs = Db.db.findMany("reminders", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("day", date.getDay())));
		HashMap<String, Date> reminderMap = new HashMap<String, Date>();
		for (Document reminder : reminderDocs) {
			if (!reminderMap.containsKey(reminder.getString("name"))) {
				Date remindDate = new Date();
				remindDate.setHours(reminder.getInteger("hour"));
				remindDate.setMinutes(reminder.getInteger("minute"));
				reminderMap.put(reminder.getString("name"), remindDate);
			}
		}
		reminderMap.forEach((String name, Date dat) -> {
			itemList.add(name + "  today at " + beautifyTime(dat));
		});
		habitDocs.forEach((Document habit) -> {
			if (!reminderMap.containsKey(habit.getString("name")))
				itemList.add(habit.getString("name"));
		});
		habitList.getItems().addAll(itemList);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		renderItemList();
	}
}