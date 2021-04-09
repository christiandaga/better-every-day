package application;

public class Habit {
	private String Name; 
	private int level;
	private int points; 
	
	public Habit() { 
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}
	

	public void setPoints(int points) {
		this.points = points;
	}

	
	public String getName() {
		return Name;
	}
	

	public void setName(String name) {
		Name = name;
	}
}
