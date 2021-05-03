package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class CreateHabitController implements Initializable {
	
	private ArrayList<Integer> days;
//	private Border selected = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	
	@FXML
	private TextField name;
	@FXML
	private Label hourText;
	@FXML
	private Label minuteText;
	@FXML
	private Label ampmText;
	
//	@FXML
//	private Button m;
//	@FXML
//	private Button t;
//	@FXML
//	private Button w;
//	@FXML
//	private Button th;
//	@FXML
//	private Button f;
//	@FXML
//	private Button sat;
//	@FXML
//	private Button sun;
	
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
	
	void selectDay(int day) {
		if (days.contains(day)) {
			days.remove(day);
//			switch(day) {
//			case 0:
//				sun.setBorder(selected);
//				break;
//			case 1:
//				m.setBorder(selected);
//				break;
//			case 2:
//				t.setBorder(selected);
//				break;
//			case 3:
//				w.setBorder(selected);
//				break;
//			case 4:
//				th.setBorder(selected);
//				break;
//			case 5:
//				f.setBorder(selected);
//				break;
//			case 6:
//				sat.setBorder(selected);
//				break;
//			}
		}
		else {
			days.add(day);
//			switch(day) {
//			case 0:
//				sun.setBorder(null);
//				break;
//			case 1:
//				m.setBorder(null);
//				break;
//			case 2:
//				t.setBorder(null);
//				break;
//			case 3:
//				w.setBorder(null);
//				break;
//			case 4:
//				th.setBorder(null);
//				break;
//			case 5:
//				f.setBorder(null);
//				break;
//			case 6:
//				sat.setBorder(null);
//				break;
//			}
		}
	}
	
	@FXML
	protected void m() { selectDay(1); }
	@FXML
	protected void t() { selectDay(2); }
	@FXML
	protected void w() { selectDay(3); }
	@FXML
	protected void th() { selectDay(4); }
	@FXML
	protected void f() { selectDay(5); }
	@FXML
	protected void sat() { selectDay(6); }
	@FXML
	protected void sun() { selectDay(0); }
	
	@FXML
	protected void hourUp() {
		int newHour = getHour() + 1;
		if (newHour > 12) {
			newHour -= 12;
		}
		if (newHour == 12) ampmSwitch();
		hourText.setText(String.valueOf(newHour));
	}
	@FXML
	protected void minUp() { 
		int newMin = getMin() + 1;
		if (newMin > 59) {
			newMin = 0;
			hourUp();
		}
		String newMinText = String.valueOf(newMin);
		if (newMinText.length() == 1) newMinText = "0" + String.valueOf(newMin);
		minuteText.setText(newMinText);
	}
	@FXML
	protected void ampmSwitch() {
		if(ampmText.getText().equals("am")) ampmText.setText("pm");
		else ampmText.setText("am");
	}
	@FXML
	protected void hourDown() { 
		int newHour = getHour() - 1;
		if (newHour < 1) {
			newHour += 12;
		}
		if (newHour == 11) ampmSwitch();
		hourText.setText(String.valueOf(newHour));
	}
	@FXML
	protected void minDown() {
		int newMin = getMin() - 1;
		if (newMin < 0) {
			newMin = 59;
			hourDown();
		}
		String newMinText = String.valueOf(newMin);
		if (newMinText.length() == 1) newMinText = "0" + String.valueOf(newMin);
		minuteText.setText(newMinText);
	}

	
	private int getHour() {
		return Integer.parseInt(hourText.getText());
	}
	
	private int getMin() {
		return Integer.parseInt(minuteText.getText());
	}
	
	// Adds habit to user's list of habits.
	@FXML
	protected void createHabit() throws IOException {
		Habit habit = new Habit(name.getText(), 100);
		Db.db.addItemToDB("habits", habit.getDocument().append("username", Auth.currentUser.getUsername()));
		
		int hour = getHour();
		if (hour == 12) hour = 0;
		if (ampmText.getText().equals("pm")) hour += 12;
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
	}
	
}
