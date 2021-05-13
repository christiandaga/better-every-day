package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mongodb.client.model.Filters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class CreateHabitController implements Initializable {
	
	private ArrayList<Integer> days;
	private String catSelected;
	
	@FXML
	private TextField name;
	@FXML
	private Label hourText;
	@FXML
	private Label minuteText;
	@FXML
	private Label ampmText;
	@FXML
	private MenuButton categoryLbl;
	@FXML
	private Label daySelect;
	
	// Goes to the Home Screen.
	@FXML
	protected void goHome() throws IOException {
		FXRouter.goTo("home");
	}
	
	// Goes to the Profile Screen.
	@FXML
	protected void goToProfile() throws IOException {
		FXRouter.goTo("profile");
	}
	
	// Goes to the Settings Screen.
	@FXML
	protected void goToSettings() throws IOException {
		FXRouter.goTo("settings");
	}
	
	// Logs the user out of their account and goes back to the Login Screen.
	@FXML
	protected void logout() throws IOException {
		Auth.logout();
		FXRouter.goTo("login");
	}
	
	// Changes ui when selecting category
	@FXML
	protected void slctDiet() {
		categoryLbl.setText("Diet");
		catSelected = "diet";
	}
	@FXML
	protected void slctEducation() {
		categoryLbl.setText("Education");
		catSelected = "education";
	}
	@FXML
	protected void slctExercise() {
		categoryLbl.setText("Exercise");
		catSelected = "exercise";
	}
	@FXML
	protected void slctCustom() {
		categoryLbl.setText("Custom");
		catSelected = "custom";
	}
	
	void selectDay(int day) {
		if (days.contains(day)) {
			days.remove(day);
		}
		else {
			days.add(day);
		}
	}
	
	// changes ui when selecting day
	@FXML
	protected void m() { 
		selectDay(1); 
		daySelect.setText("Day selected: Monday");
	}
	
	@FXML
	protected void t() { 
		selectDay(2); 
		daySelect.setText("Day selected: Tuesday");
	}
	@FXML
	protected void w() { 
		selectDay(3); 
		daySelect.setText("Day selected: Wednesday");
	}
	@FXML
	protected void th() { 
		selectDay(4); 
		daySelect.setText("Day selected: Thursday");
	}
	@FXML
	protected void f() { 
		selectDay(5); 
		daySelect.setText("Day selected: Friday");
	}
	@FXML
	protected void sat() { 
		selectDay(6); 
		daySelect.setText("Day selected: Saturday");
	}
	@FXML
	protected void sun() { 
		selectDay(0); 
		daySelect.setText("Day selected: Sunday");
	}
	
	private String addZero(int time) {
		String newText = String.valueOf(time);
		if (newText.length() == 1) newText = "0" + String.valueOf(time);
		return newText;
	}
	
	// changes ui when changing time
	@FXML
	protected void hourUp() {
		int newHour = getHour() + 1;
		if (newHour > 12) {
			newHour -= 12;
		}
		if (newHour == 12) ampmSwitch();
		hourText.setText(addZero(newHour));
	}
	@FXML
	protected void minUp() { 
		int newMin = getMin() + 1;
		if (newMin > 59) {
			newMin = 0;
			hourUp();
		}
		minuteText.setText(addZero(newMin));
	}
	@FXML
	protected void ampmSwitch() {
		if(ampmText.getText().equals("AM")) ampmText.setText("PM");
		else ampmText.setText("AM");
	}
	@FXML
	protected void hourDown() { 
		int newHour = getHour() - 1;
		if (newHour < 1) {
			newHour += 12;
		}
		if (newHour == 11) ampmSwitch();
		hourText.setText(addZero(newHour));
	}
	@FXML
	protected void minDown() {
		int newMin = getMin() - 1;
		if (newMin < 0) {
			newMin = 59;
			hourDown();
		}
		minuteText.setText(addZero(newMin));
	}

	
	private int getHour() {
		return Integer.parseInt(hourText.getText());
	}
	
	private int getMin() {
		return Integer.parseInt(minuteText.getText());
	}
	
	// Adds habit and reminder to db and goes home.
	@FXML
	protected void createHabit() throws IOException {
		Habit habit = new Habit(name.getText(), 1, catSelected);  // 1 point for each habit
		Db.db.addItemToDB("habits", habit.getDocument().append("username", Auth.currentUser.getUsername()));
		
		if (Db.db.findOne("categories", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("name", catSelected))) == null) {
			Db.db.addItemToDB("categories", new Category(catSelected).getDocument().append("username", Auth.currentUser.getUsername()));
		}
		
		int hour = getHour();
		if (hour == 12) hour = 0;
		if (ampmText.getText().equals("PM")) hour += 12;
		RemindersManager.addReminders(name.getText(), days, hour, getMin());
		
		FXRouter.goTo("home");
	}
	
	// Habit is not created and user is returned to home.
	@FXML
	protected void cancel() throws IOException {
		FXRouter.goTo("home");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		days = new ArrayList<Integer>();
		catSelected = "";
	}
}
