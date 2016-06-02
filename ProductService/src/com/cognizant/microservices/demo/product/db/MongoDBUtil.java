package com.cognizant.microservices.demo.product.db;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoDBUtil {
	
	
	public static MongoClient getMongoDBClient() throws UnknownHostException{
		MongoClient mongoClient=null;
		mongoClient = new MongoClient( new ServerAddress("127.0.0.1",27017));
		return mongoClient;
	}

}
