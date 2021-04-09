package application;

import org.bson.Document;

public class User {

	private String username;
	private String password;
	private String email;
	private int userLevel;
	
	User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	User(Document user) {
		username = user.getString("username");
		password = user.getString("password");
		email = user.getString("email");
		userLevel = (user.getInteger("userLevel") == null) ? 0 : user.getInteger("userLevel");
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getUserLevel() {
		return userLevel;
	}
	
	public void setUsername(String newUsername) {
		username = newUsername;
	}
	
	public void setPassword(String newPassword) {
		password = newPassword;
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	public void setUserLevel(int newUserLevel) {
		userLevel = newUserLevel;
	}
	
	public Document getDocument() {
		return new Document("username", username)
				.append("password", password)
				.append("email", email)
				.append("userLevel", userLevel);
	}
	
}
