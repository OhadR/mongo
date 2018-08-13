import java.util.Arrays;

import org.bson.Document;

import com.cellebrite.analytics.repository.mongodb.TestConnectionPoolListener;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {

	private static final String EVENTS_COLL_NAME = "events";
	private static final String FORENSIC_DB_NAME = "forensic";

	public static void main(String[] args) throws InterruptedException {

		//credentials:
		MongoCredential credential = MongoCredential.createCredential("ayala", "admin", "q1w2e3r4!".toCharArray());

		MongoClientOptions options = MongoClientOptions.builder()
		        .addConnectionPoolListener(new TestConnectionPoolListener())
		        .connectTimeout(20000)
		        .maxConnectionIdleTime(0)		//A zero value indicates no limit to the life time.
		        .maxConnectionLifeTime(0)		//A zero value indicates no limit to the life time.
		        .maxWaitTime(120000)				//Sets the maximum time that a thread will block waiting for a connection.
		        .build();

		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient(
				new ServerAddress("localhost", 27017)		//replica-set
		        ,Arrays.asList(credential)
		        ,options
		        );
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
			Thread.sleep(3000);
		}	
		

	}

}
