package com.cognizant.microservices.demo.product.db;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDBUtil {
	
	
	public static MongoClient getMongoDBClient() throws UnknownHostException{
		MongoClient mongoClient=null;
		//mongoClient = new MongoClient( new ServerAddress("127.0.0.1",27017));
		MongoCredential credential = MongoCredential.createCredential("HariharanAnantharaman", "samplecommerce", "mangal123".toCharArray());

		mongoClient = new MongoClient( new ServerAddress("ds019481.mlab.com",19481),Arrays.asList(credential));
		return mongoClient;
	}

}
