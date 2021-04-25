package application;

import org.bson.Document;

import com.mongodb.client.model.Filters;

public final class Auth {

	public static User currentUser;
	
	public static boolean login(String username, String password) {
		Document user = Db.db.findOne("users", Filters.and(Filters.eq("username", username), Filters.eq("password", password)));
		if (user != null) {
			currentUser = new User(user);
			RemindersManager.init();
			return true;
		}
		
		return false;
	}
	
	public static boolean createUser(String username, String password, String email) {
		Document user = Db.db.findOne("users", Filters.eq("username", username));
		if (user == null) {
			currentUser = new User(username, password, email);
			Db.db.addItemToDB("users", currentUser.getDocument());
			return true;
		}
		
		return false;
			
	}
	
	public static void logout() {
		RemindersManager.stop();
		currentUser = null;
	}
}
