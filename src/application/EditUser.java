package application;

import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class EditUser {
	
	// Edits user information. Only edits user information if a value is given.
	// Special case for editing username: Only change username if username is not yet taken.
	public static boolean editProfile(String username, String email, String password) {
		if(password != null) {
			Db.db.updateItem("users", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("password", password));
			Auth.currentUser.setPassword(password);
		}
		
		if(email != null) {
			Db.db.updateItem("users", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("email", email));
			Auth.currentUser.setEmail(email);
		}
		if(username != null) {
			Document user = Db.db.findOne("users", Filters.eq("username", username));
			if (user != null) {
				System.out.println("Username is taken");
				return false;
			} else {
				Db.db.updateItem("users", Filters.eq("username", Auth.currentUser.getUsername()), Updates.set("username", username));
				Auth.currentUser.setUsername(username);
			}
			
		}
		return true;
	}
	
	// Deletes user account.
	public static void deleteAccount() {
		Db.db.deleteItem("users", Filters.and(Filters.eq("username", Auth.currentUser.getUsername()), Filters.eq("password", Auth.currentUser.getPassword()), Filters.eq("email", Auth.currentUser.getEmail())));
	}
}
