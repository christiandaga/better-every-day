package application;

import java.util.Date;
import java.util.TimerTask;

import org.bson.Document;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;

public class Reminder extends TimerTask {
	
	private Date date;
	
	private String name;
	private int day;
	private int hour;
	private int minute;
	
	private Popup popup;
	
	Reminder(String name, int day, int hour, int minute) {
		this.name = name;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		
		createPopup();
	}
	
	Reminder(Document reminder) {
		name = reminder.getString("name");
		day = reminder.getInteger("day");
		hour = reminder.getInteger("hour");
		minute = reminder.getInteger("minute");
		
		createPopup();
	}
	
	private void createPopup() {
		Label label = new Label(name + " Reminder!");
		Button button = new Button("Close");
		popup = new Popup();
		label.setStyle(" -fx-background-color: white;");
		label.setMinWidth(100);
		label.setMinHeight(150);
		popup.getContent().add(label);
		popup.getContent().add(button);
		EventHandler<ActionEvent> event = 
				new EventHandler<ActionEvent>() {
			        public void handle(ActionEvent e) {
			        	Platform.runLater(() -> popup.hide());
		            }
		        };
		   
		button.setOnAction(event);
	}
	
	public Document getDocument() {
		return new Document("name", name)
				.append("day", day)
				.append("hour", hour)
				.append("minute", minute);
	}
	
	@Override
	public void run() {
		date = new Date();
		if (date.getDay() == day && date.getHours() == hour && date.getMinutes() == minute) {
			System.out.println(name + " reminder");
			Platform.runLater(() -> popup.show(FXRouter.window));
		}
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
