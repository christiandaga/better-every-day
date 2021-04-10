package application;

public class Habit {
	private String name; // Name of the habit. 
	private int level; // Level of the habit.
	private int points; // Points gained by doing the habit.
	
	public Habit() { 
		
	}

	// Returns the habit level.
	public int getLevel() {
		return level;
	}

	// Sets the habit level to a new habit level.
	public void setLevel(int level) {
		this.level = level;
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
}
