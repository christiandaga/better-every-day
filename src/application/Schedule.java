package application;
import java.util.ArrayList; 


public class Schedule {
	
	String 	dayofWeek;
	String 	startTime;
	int 	duration;	// Minutes
	
	/*public Schedule() { 
		
		
		
	}*/
	
	public Schedule(String dayofWeek, String startTime, int duration ) { 
		
		dayofWeek = this.dayofWeek;
		startTime = this.startTime;
		duration = this.duration; 
		
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
	
	
	
}
