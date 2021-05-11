package application;

import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


public class Habit {
	private String name; // Name of the habit. 
	private int level; // Level of the habit.
	private int points; // Points gained by doing the habit.
	// private int pointsEarned; // each time a schedule is met, increase by points
	// private ArrayList<Schedule> schedule = new ArrayList<Schedule>(); 
	// private Schedule[] schedule;
	private int daysCompleted;
	private String categoryName;
	
	public Habit(String name, int points, String categoryName) { 
		this.name = name;
		this.points = points;
		this.level = 0;
		this.daysCompleted = 0;
		this.categoryName = categoryName;
	}
	
	Habit(Document habit) {
		name = habit.getString("name");
		points = habit.getInteger("points");
		level = habit.getInteger("level", 0);
		daysCompleted = habit.getInteger("daysCompleted", 0);
		categoryName = habit.getString("categoryName");
	}

	// Returns the habit level.
	public int getLevel() {
		return level;
	}

	// Sets the habit level to a new habit level.
	public void setLevel(int level) {
		int tempLevel = level; 
		if (this.points > 100) tempLevel = tempLevel + 1;
		
		this.level = tempLevel;
	}

	// Returns the points gained by doing the habit.
	public int getPoints() {
		return points;
	}
	
	// Sets the habit points to new habit points.
	public void setPoints(int points) {
		this.points = points;
	}

	// Returns the habit name.
	public String getName() {
		return name;
	}
	
	// Sets the habit name to a new habit name.
	public void setName(String theName) {
		name = theName;
	}
	
	public int getDaysCompleted() {
		return daysCompleted;
	}
	
	public void completeDay() {
		daysCompleted++;
		// idk if its best to do it here
		Db.db.updateItem("habits", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("name", name)), Updates.inc("daysCompleted", 1));
		if (daysCompleted % 5 == 0)
			Db.db.updateItem("categories", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("name", categoryName)), Updates.inc("achievements", 1));
	}

	public String getCategoryName() {
		return categoryName;
	}

	// Returns Document containing habit information
	public Document getDocument() {
		return new Document("name", name)
				.append("level", level)
				.append("points", points)
				.append("daysCompleted", daysCompleted)
				.append("categoryName", categoryName);
	}
	
//	public void addSchedule(String day, String startTime, int duration ) { 
//		Schedule x = new Schedule(day, startTime, duration);
//		schedule.add(x);
//	}
//	
//	public void dropSchedule(Schedule i) { 
//		schedule.remove(i);
//	}
//	
//	public void viewSchedules() { 
//		for (int i = 0; i < schedule.size(); i++) { 
//			System.out.println("DAY: " + schedule.get(i).getDayofWeek());
//			System.out.print(" START: " + schedule.get(i).getStartTime());
//			System.out.print(" DAY: " + schedule.get(i).getDuration());
//		}
//	}
	
}
