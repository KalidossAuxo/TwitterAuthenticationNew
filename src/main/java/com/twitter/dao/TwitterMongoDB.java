package com.twitter.dao;

import org.bson.Document;

import com.twitter.db.DBConnMongo;


public class TwitterMongoDB {
	
  
	static DBConnMongo mongoService = new DBConnMongo();
	
	 public static void insert(Document document, String collectionName){
		 mongoService.insert(collectionName, document);
	    }
    

}
