package application;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import com.mongodb.client.MongoCollection;

// Use this class for any tests
public class Test {
	
	public static void main(String[] args) {
		// Only way to add a new user for now
		User user = new User("userC", "passforuserC", "userC@c.com");
		
		Db db = new Db();
		MongoCollection<Document> collection = db.database.getCollection("users");
		collection.insertOne(user.getDocument());
		
	}
}
