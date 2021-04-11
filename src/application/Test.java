package application;

import com.mongodb.client.model.Filters;

// Use this class for any tests
public class Test {
	
	public static void main(String[] args) {
		Test test = new Test();
		test.dbTest();
	}
	
	private void dbTest() {
		// Only way to add a new user for now
//		User user = new User("userD", "passforuserD", "userD@d.info");
//		Habit habit = new Habit("workout", 500);
		
		Db db = new Db();
		
//		db.addItemToDB("users", user.getDocument());
//		db.addItemToDB("habits", habit.getDocument());
		
		System.out.println(db.findMany("users", Filters.empty()));
//		System.out.println(db.findMany("habits", Filters.empty()));
		System.out.println();
		
//		db.replaceItem("users", Filters.eq("username", "userD"), user.getDocument());
//		db.replaceItem("habits", Filters.eq("name", "workout"), habit.getDocument());
		db.deleteItem("users", Filters.eq("username", "userD"));
		
		System.out.println(db.findMany("users", Filters.eq("username", "userD")));
//		System.out.println(db.findOne("habits", Filters.eq("name", "workout")));
	}
}
