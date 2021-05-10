package application;

import org.bson.Document;

public class Category {
	private String name;
	private int achievements;
	
	Category(String name) {
		this.name = name;
	}
	
	Category(Document category) {
		name = category.getString("name");
		achievements = category.getInteger("achievements", 0);
	}
	
	public String getName() {
		return name;
	}
	
	public int getAchievements() {
		return achievements;
	}
	
	public void addAchievement() {
		achievements++;
	}
	
	public Document getDocument() {
		return new Document("name", name)
				.append("achievements", achievements);
	}
}
