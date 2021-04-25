package application;

import java.util.Date;
import java.util.TimerTask;

import org.bson.Document;

public class Reminder extends TimerTask {

	private Date date;
	
	private String name;
	private int day;
	private int hour;
	private int minute;
	
	Reminder(String name, int day, int hour, int minute) {
		this.name = name;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}
	
	Reminder(Document reminder) {
		name = reminder.getString("name");
		day = reminder.getInteger("day");
		hour = reminder.getInteger("hour");
		minute = reminder.getInteger("minute");
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
		}
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
