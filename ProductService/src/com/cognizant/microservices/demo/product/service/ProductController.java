package com.cognizant.microservices.demo.product.service;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.microservices.demo.product.db.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@RestController
@RequestMapping("/api")
public class ProductController {

	private final static Logger LOGGER = Logger.getLogger(ProductController.class.getName()); 
    private final static String CLASSNAME="ProductController" ;

    
  /*  @RequestMapping(method=RequestMethod.GET)
    public List<Product> findAll() {
    	String methodName="findAll";
    	LOGGER.entering(CLASSNAME, methodName);
    	
       
        return null;
        
        
        
    }*/
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    String findById(@PathVariable("id") long id) {
    	String methodName="findById";
    	LOGGER.entering(CLASSNAME, methodName);
    	LOGGER.logp(Level.INFO, CLASSNAME, methodName, "Getting product with ID:"+id);
    	
    	String returnValue = getCatentryFromMongo(id);
		
		return returnValue;
    }
    
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    String findItemById(@PathVariable("id") long id) {
    	String methodName="findById";
    	LOGGER.entering(CLASSNAME, methodName);
    	LOGGER.logp(Level.INFO, CLASSNAME, methodName, "Getting product with ID:"+id);
    	
    	String returnValue = getCatentryFromMongo(id);
		
		return returnValue;
    }


private String getCatentryFromMongo(long id) {
	MongoClient mongoClient;
	String returnValue=null;
	try {
		mongoClient = MongoDBUtil.getMongoDBClient();
	
	DB db = mongoClient.getDB( "test" );	
	
	BasicDBObject fields = null;
	DBCollection collection=db.getCollection("CATENTRY");
	fields = new BasicDBObject();
	fields.put("CATENTRY_ID", id);
	DBObject catentryObject=collection.findOne(fields);
	returnValue= catentryObject.toString();
	} catch (UnknownHostException e) {
		returnValue="Exception occured while processing the request";
		LOGGER.log(Level.SEVERE, "Exception while getting productData", e);
		e.printStackTrace();
	}
	return returnValue;
}
    
    
}
