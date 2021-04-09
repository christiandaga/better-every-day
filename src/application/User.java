package application;

import org.bson.Document;

public class User {

	private String username;
	private String password;
	
	User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	User(Document user) {
		username = (String) user.get("username");
		password = (String) user.get("password");
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String newUsername) {
		username = newUsername;
	}
	
	public void setPassword(String newPassword) {
		password = newPassword;
	}
	
	public Document getDocument() {
		return new Document("username", username)
				.append("password", password);
	}
	
}
