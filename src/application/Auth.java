package application;

import org.bson.Document;

import com.mongodb.client.model.Filters;

public final class Auth {

	public static User currentUser;
	
	static int test;
	public static boolean login(String username, String password) {
		Document user = Db.db.findOne("users", Filters.and(Filters.eq("username", username), Filters.eq("password", password)));
		currentUser = new User(user);
		return user != null;
	}
	
	public static void logout() {
		currentUser = null;
	}
}
