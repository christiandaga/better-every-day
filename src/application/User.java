package application;

import org.bson.Document;

public class User {

	private String username; // Username of the user.
	private String password; // Password of the user.
	private String email; // Email of the user.
	private int userLevel; // Level of the user.
	
	// Constructor to set the user's username, password, and email.
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
	
	// Returns the user's username.
	public String getUsername() {
		return username;
	}
	
	// Returns the user's email.
	public String getEmail() {
		return email;
	}
	
	// Returns the user's level.
	public int getUserLevel() {
		return userLevel;
	}
	
	// Sets the user's username to a new username.
	public void setUsername(String newUsername) {
		username = newUsername;
	}
	
	// Sets the user's password to a new password.
	public void setPassword(String newPassword) {
		password = newPassword;
	}
	
	// Sets the user's email to a new email.
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	// Sets the user's level to a new level.
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
