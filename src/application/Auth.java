package application;

import org.bson.Document;

import com.mongodb.client.model.Filters;

public final class Auth {

	public static User currentUser;
	
	// Logs in user given that username and password are correct.
	public static boolean login(String username, String password) {
		Document user = Db.db.findOne("users", Filters.and(Filters.eq("username", username), Filters.eq("password", password)));
		if (user != null) {
			currentUser = new User(user);
			RemindersManager.init();
			return true;
		}
		
		return false;
	}
	
	// Creates new account for user. Returns true if is successful, returns false if username is already taken.
	public static boolean createUser(String username, String password, String email) {
		Document user = Db.db.findOne("users", Filters.eq("username", username));
		if (user == null) {
			currentUser = new User(username, password, email);
			Db.db.addItemToDB("users", currentUser.getDocument());
			RemindersManager.init();
			return true;
		}
		
		return false;
	}
	
	// Logs out of account.
	public static void logout() {
		RemindersManager.stop();
		currentUser = null;
	}
}
