package application;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class Test {
	
	public static void main(String[] args) {
		User user = new User("userC", "passforuserC", "userC@c.com");
		
		Db db = new Db();
		MongoCollection<Document> collection = db.database.getCollection("users");
		collection.insertOne(user.getDocument());
	}
}
