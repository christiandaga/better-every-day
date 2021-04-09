package application;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Db {
	public MongoClient client;
	public MongoDatabase database;
	
	Db() {
		try {
			client = new MongoClient(new MongoClientURI("mongodb+srv://christianuser:christianuser@better-every-day-cluste.ttuir.mongodb.net/better-every-day-db?retryWrites=true&w=majority"));
			database = client.getDatabase("better-every-day-db");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addItemToDB(String collectionName, Document item) {
		database.getCollection(collectionName).insertOne(item);
	}
	
	public void addItemsToDB(String collectionName, List<Document> items) {
		database.getCollection(collectionName).insertMany(items);
	}
	
	public Document findOne(String collectionName, Bson filter) {
		Iterable<Document> ret = database.getCollection(collectionName).find(filter);
		return ret.iterator().next();
	}
	
	public Iterable<Document> findMany(String collectionName, Bson filter) {
		return database.getCollection(collectionName).find(filter);
	}
	
}
