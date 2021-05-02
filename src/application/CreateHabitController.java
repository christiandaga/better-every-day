package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateHabitController {
	private int hour = 7;
	private int minute = 0;
	
	
	@FXML
	private TextField name;
	
	@FXML
	private Label hourLbl;
	
	@FXML
	private Label minuteLbl;

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
	
	// Adds habit to user's list of habits.
	@FXML
	protected void createHabit() throws IOException {
		Habit habit = new Habit(name.getText(), 100);
		Db.db.addItemToDB("habits", habit.getDocument().append("username", Auth.currentUser.getUsername()));
		FXRouter.goTo("home");
	}
	
	// Habit is not created and user is returned to home.
	@FXML
	protected void cancel() throws IOException {
		FXRouter.goTo("home");
	}
	
	// When "^" button is pressed, hour is incremented.
	// Edge cases: a) If hour is greater than 23, carry over to 0.
	//             b) If hour is a single digit number, add a 0 to the front in the label.
	@FXML
	protected void incHour() {
		hour++;
		if(hour > 23) {
			hour = 0;
		}
		if(hour < 10) {
			hourLbl.setText("0" + hour);
		} else {
			hourLbl.setText(String.valueOf(hour));
		}
	}
	
	// When "v" button is pressed, hour is incremented.
	// Edge cases: a) If hour is less than 0, carry over to 23.
	//             b) If hour is a single digit number, add a 0 to the front in the label.
	@FXML
	protected void decHour() {
		hour--;
		if(hour < 0) {
			hour = 23;
		}
		if(hour < 10) {
			hourLbl.setText("0" + hour);
		} else {
			hourLbl.setText(String.valueOf(hour));
		}
	}
	
	// When "^" button is pressed, minute is incremented.
	// Edge cases: a) If minute is greater than 59, carry over to 0.
	//             b) If minute is a single digit number, add a 0 to the front in the label.
	@FXML
	protected void incMinute() {
		minute++;
		if(minute > 59) {
			minute = 0;
		}
		if(minute < 10) {
			minuteLbl.setText("0" + minute);
		} else {
			minuteLbl.setText(String.valueOf(minute));
		}
	}
	
	// When "v" button is pressed, minute is decremented.
	// Edge cases: a) If minute is less than 0, carry over to 23.
	//             b) If minute is a single digit number, add a 0 to the front in the label.
	@FXML
	protected void decMinute() {
		minute--;
		if(minute < 0) {
			minute = 59;
		}
		if(minute < 10) {
			minuteLbl.setText("0" + minute);
		} else {
			minuteLbl.setText(String.valueOf(minute));
		}
	}
	
}
