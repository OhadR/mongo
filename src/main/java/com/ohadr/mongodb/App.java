package com.ohadr.mongodb;

import java.io.File;
import java.util.List;

import org.bson.Document;

import com.cellebrite.analytics.repository.mongodb.MongoServerDetails;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientWrapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ohadr.common.utils.JsonUtils;

public class App {

	private static final String EVENTS_COLL_NAME = "events";
	private static final String FORENSIC_DB_NAME = "forensic";
	private static PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();

	
	public static void main(String[] args) throws InterruptedException {
		
		getJsonFromMongoServerDetails();
		
		int interval = propertiesLoader.getIntProperty( "interval" );
        MongoClient mongoClient = MongoClientWrapper.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase(FORENSIC_DB_NAME);
		
		
		for(int i = 0; i < 100; ++i)
		{
			long startTime = System.currentTimeMillis();
			try 
			{
				MongoCollection<Document> collection = database.getCollection(EVENTS_COLL_NAME);
				long count = collection.count();
				System.out.println("iteration " + i + ", size=" + count);
			} 
			catch (MongoException me) 
			{
				long diff = System.currentTimeMillis() - startTime;
				System.err.println(me + " $$$ iteration " + i + ", time took=" + diff);
			}
			
			List<MongoServerDetails> mongoStatus = HealthCheck.checkMongoHealth();
			System.out.println(mongoStatus);
			
			Thread.sleep(interval);
		}	
		

	}

	public static void getJsonFromMongoServerDetails()
	{
		List<MongoServerDetails> mongoStatus = HealthCheck.checkMongoHealth();
		String json = JsonUtils.convertToJson(mongoStatus);
		System.out.println(json);
		
	}
}
