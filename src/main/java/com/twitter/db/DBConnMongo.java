package com.twitter.db;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.twitter.utils.Setup;

public class DBConnMongo {

	private static MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));;
	private static MongoDatabase database;
	private String dbString = Setup.DB_NAME;

	public MongoCollection getCollection(String collectionName) {
		database = mongoClient.getDatabase(dbString);
		return database.getCollection(collectionName);
	}

	public Document insert(String collectionName, Document document) {
		getCollection(collectionName).insertOne(document);
		return document;
	}

	public void update(String collectionName, Document queryDocument, Document updatedDocument) {
		getCollection(collectionName).replaceOne(queryDocument, updatedDocument);
	}

}
