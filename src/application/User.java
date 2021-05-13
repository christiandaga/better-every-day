package application;

import org.bson.Document;
import java.util.ArrayList; 

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
		this.userLevel = 0;
		
	}
	
	// Constructor sets username, password, and email from Document
	User(Document user) {
		username = user.getString("username");
		password = user.getString("password");
		email = user.getString("email");
		userLevel = user.getInteger("userLevel", 0);
	}
	
	
	// Returns the user's username.
	public String getUsername() {
		return username;
	}
	
	// Returns the user's password.
	public String getPassword()
	{
		return password;
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
	
	// Returns Document containing user information
	public Document getDocument() {
		return new Document("username", username)
				.append("password", password)
				.append("email", email)
				.append("userLevel", userLevel);
	}
}
