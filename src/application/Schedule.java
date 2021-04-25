package application;
import java.util.ArrayList;

import org.bson.Document; 


public class Schedule {
	
	String 	dayofWeek;
	String 	startTime;
	int 	duration;	// Minutes
	
	/*public Schedule() { 
		
		
		
	}*/
	
	public Schedule(String dayofWeek, String startTime, int duration ) { 
		
		this.dayofWeek = dayofWeek;
		this.startTime = startTime;
		this.duration = duration;
		
	}
	
	public Schedule(Document schedule) {
		dayofWeek = schedule.getString("dayofWeek");
		startTime = schedule.getString("startTime");
		duration = schedule.getInteger("duration");
	}

	public String getDayofWeek() {
		return dayofWeek;
	}

	public void setDayofWeek(String dayofWeek) {
		this.dayofWeek = dayofWeek;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public Document getDocument() {
		return new Document("dayofWeek", dayofWeek)
				.append("startTime", startTime)
				.append("duration", duration);
	}
	
}
