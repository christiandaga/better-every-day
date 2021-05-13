package application;

import java.util.ArrayList;
import java.util.Timer;

import org.bson.Document;

import com.mongodb.client.model.Filters;

public final class RemindersManager {

	private static ArrayList<Reminder> reminders;
	private static Timer t;
	
	// creates timer and pulls all the user's reminders from database
	public static void init() {
		t = new Timer();
		reminders = new ArrayList<Reminder>();
		Db.db.findMany("reminders", Filters.eq("username", Auth.currentUser.getUsername())).forEach((Document reminder) -> reminders.add(new Reminder(reminder)));
		reminders.forEach((Reminder reminder) -> t.scheduleAtFixedRate(reminder, 0, 10000));
	}
	
	// adds a reminder to db and timer
	public static void addReminder(Reminder reminder) {
		Db.db.addItemToDB("reminders", reminder.getDocument().append("username", Auth.currentUser.getUsername()));
		reminders.add(reminder);
		t.scheduleAtFixedRate(reminder, 0, 10000);
	}

	// adds reminders to each day
	public static void addReminders(String name, ArrayList<Integer> days, int hour, int minute) {
		for (int day : days) {
			addReminder(new Reminder(name, day, hour, minute));
		}
	}
	
	// deletes and cancels reminder
	public static void removeReminder(String name) {
		Db.db.deleteItem("reminders", Filters.eq("name", name));
		for (Reminder reminder : reminders) {
			if (reminder.getName().equals(name))
				reminder.cancel();
		}
		t.purge();
	}
	
	// stops timer
	public static void stop() {
		t.cancel();
	}
	
}
