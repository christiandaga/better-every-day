package application;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Db {
	public MongoClient client;	// MongoDB client
	public MongoDatabase database;	// The MongoDB database better-every-day-db
	
	// Connects to db and sets client and database
	Db() {
		try {
			client = new MongoClient(new MongoClientURI("mongodb+srv://christianuser:christianuser@better-every-day-cluste.ttuir.mongodb.net/better-every-day-db?retryWrites=true&w=majority"));
			database = client.getDatabase("better-every-day-db");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Add an item to a collection
	public void addItemToDB(String collectionName, Document item) {
		database.getCollection(collectionName).insertOne(item);
	}
	
	// Add multiple items to collection
	public void addItemsToDB(String collectionName, List<Document> items) {
		database.getCollection(collectionName).insertMany(items);
	}
	
	// Returns the first item that matches filter
	public Document findOne(String collectionName, Bson filter) {
		Iterable<Document> ret = database.getCollection(collectionName).find(filter);
		return ret.iterator().next();
	}
	
	// Find and return all the items that match filter
	public Iterable<Document> findMany(String collectionName, Bson filter) {
		return database.getCollection(collectionName).find(filter);
	}
	
}
