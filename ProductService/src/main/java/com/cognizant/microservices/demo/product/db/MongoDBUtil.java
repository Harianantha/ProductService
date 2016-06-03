package com.cognizant.microservices.demo.product.db;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDBUtil {
	
	private static Map<String,String> variableMap=null;
	
	public static String MONGODB_SERVER_NAME="MONGODB_SERVER_NAME";
	public static String MONGODB_SERVER_PORT="MONGODB_SERVER_PORT";
	public static String MONGODB_DB_NAME="MONGODB_DB_NAME";
	public static String MONGODB_DB_USERNAME="MONGODB_DB_USERNAME";
	public static String MONGODB_DB_PASSWORD="MONGODB_DB_PASSWORD";
	
	public static MongoClient getMongoDBClient() throws UnknownHostException{
		MongoClient mongoClient=null;
		//mongoClient = new MongoClient( new ServerAddress("127.0.0.1",27017));
		if(variableMap==null){
			populateEnvironmentVariableMap();
			
		}
		System.out.println("Map is:"+variableMap);
		MongoCredential credential = MongoCredential.createCredential(variableMap.get(MONGODB_DB_USERNAME),variableMap.get(MONGODB_DB_NAME), variableMap.get(MONGODB_DB_PASSWORD).toCharArray());

		mongoClient = new MongoClient( new ServerAddress(variableMap.get(MONGODB_SERVER_NAME),Integer.parseInt(variableMap.get(MONGODB_SERVER_PORT))),Arrays.asList(credential));
			return mongoClient;
	}
	
	private static void populateEnvironmentVariableMap(){
		
		if(variableMap==null){
			variableMap=new HashMap<String,String>();
			variableMap.put(MONGODB_SERVER_NAME, System.getenv(MONGODB_SERVER_NAME)) ;
			variableMap.put(MONGODB_SERVER_PORT, System.getenv(MONGODB_SERVER_PORT)) ;
			variableMap.put(MONGODB_DB_NAME, System.getenv(MONGODB_DB_NAME)) ;
			variableMap.put(MONGODB_DB_USERNAME, System.getenv(MONGODB_DB_USERNAME)) ;
			variableMap.put(MONGODB_DB_PASSWORD, System.getenv(MONGODB_DB_PASSWORD)) ;
		}
		
		
	}

}
