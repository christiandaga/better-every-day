package application;

import org.bson.Document;

public class Category {
	private String name;
	private int achievements;
	
	// Constructor sets name
	Category(String name) {
		this.name = name;
		achievements = 0;
	}
	
	// Constructor sets name and achievements from Document
	Category(Document category) {
		name = category.getString("name");
		achievements = category.getInteger("achievements", 0);
	}
	
	// returns name
	public String getName() {
		return name;
	}
	
	// returns achievements
	public int getAchievements() {
		return achievements;
	}
	
	// increments achievements
	public void addAchievement() {
		achievements++;
	}
	
	// returns Document version
	public Document getDocument() {
		return new Document("name", name)
				.append("achievements", achievements);
	}
}
