package application;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Connects to MongoDB
 */
final class Db {
	public MongoClient client;	// MongoDB client
	public MongoDatabase database;	// The MongoDB database better-every-day-db
	public static Db db = new Db();
	
	/**
	 * Connects to db and initializes client and database
	 */
	private Db() {
		try {
			client = new MongoClient(new MongoClientURI("mongodb+srv://christianuser:christianuser@better-every-day-cluste.ttuir.mongodb.net/better-every-day-db?retryWrites=true&w=majority"));
			database = client.getDatabase("better-every-day-db");
		} catch(Exception e) {
			System.out.println("Cannot connect to MongoDB");
		}
	}
	
	/**
	 * Adds and item to a collection
	 * @param collectionName the name of the collection
	 * @param item the item as a Document
	 */
	public void addItemToDB(String collectionName, Document item) {
		try {
			database.getCollection(collectionName).insertOne(item);
		} catch(Exception e) {
			System.out.println("Failed to add item to DB");
		}
	}
	
	/**
	 * Adds multiple items to a collection
	 * @param collectionName the name of the collection
	 * @param items the items as a list of Documents
	 */
	public void addItemsToDB(String collectionName, List<Document> items) {
		try {
			database.getCollection(collectionName).insertMany(items);
		} catch(Exception e) {
			System.out.println("Failed to add items to DB");
		}
	}
	
	/**
	 * Returns the first item that matches filter
	 * @param collectionName the name of the collection
	 * @param filter the Bson filter ex: Filters.eq("username", "bob")
	 * @return the first item that matches filter
	 */
	public Document findOne(String collectionName, Bson filter) {
		try {
			Iterable<Document> ret = database.getCollection(collectionName).find(filter);
			return ret.iterator().next();
		} catch(Exception e) {
			System.out.println("Failed to find item");
			return null;
		}
	}
	
	/**
	 * Find and return all the items that match filter
	 * @param collectionName the name of the collection
	 * @param filter the Bson filter ex: Filters.eq("username", "bob")
	 * @return a list of Documents
	 */
	public List<Document> findMany(String collectionName, Bson filter) {
		try {
			List<Document> list = new ArrayList<Document>();
			database.getCollection(collectionName).find(filter).forEach(list::add);
			return list;
		} catch(Exception e) {
			System.out.println("Failed to find items");
			return null;
		}
		
	}
	
	/**
	 * Finds and replaces an item
	 * @param collectionName the name of the collection
	 * @param filter the Bson filter ex: Filters.eq("username", "bob")
	 * @param item the replacement Document
	 */
	public void replaceItem(String collectionName, Bson filter, Document item) {
		try {
			database.getCollection(collectionName).findOneAndReplace(filter, item);
		} catch(Exception e) {
			System.out.println("Failed to replace item");
		}
	}
	
	/**
	 * Finds and deletes an item
	 * @param collectionName the name of the collection
	 * @param filter the Bson filter ex: Filters.eq("username", "bob")
	 */
	public void deleteItem(String collectionName, Bson filter) {
		try {
			database.getCollection(collectionName).findOneAndDelete(filter);
		} catch(Exception e) {
			System.out.println("Failed to delete item");
		}
	}
	
	/**
	 * Finds and updates an item
	 * @param collectionName the name of the collection
	 * @param filter the Bson filter ex: Filters.eq("username", "bob")
	 * @param update the Bson update ex: Updates.set("username", "Bob")
	 */
	public void updateItem(String collectionName, Bson filter, Bson update) {
		try {
			database.getCollection(collectionName).updateOne(filter, update);
		} catch(Exception e) {
			System.out.println("Failed to update item");
		}
	}
}
