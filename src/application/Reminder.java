package application;

import java.util.Date;
import java.util.TimerTask;

import org.bson.Document;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;

public class Reminder extends TimerTask {
	
	private Date date;
	
	private String name;
	private int day;
	private int hour;
	private int minute;
	
	private Popup popup;
	private boolean shown;
	
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
		shown = false;
		
		GridPane pane = new GridPane();
		Label label = new Label(name + " Reminder!");
		Button button = new Button("Close");
		popup = new Popup();

		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		pane.setStyle(" -fx-background-color: silver;");
		pane.add(label, 0, 1);
		pane.add(button, 0, 2);
		
		popup.getContent().add(pane);
		
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
			if (!shown) {
				System.out.println(name + " reminder");
				shown = true;
				Platform.runLater(() -> popup.show(FXRouter.window));
			}
		} else {
			shown = false;
		}
//		try {
//			Thread.sleep(10000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}

}
