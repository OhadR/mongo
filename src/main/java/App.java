import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {

	private static final String EVENTS_COLL_NAME = "events";
	private static final String FORENSIC_DB_NAME = "forensic";

	public static void main(String[] args) {

		//credentials:
		MongoCredential credential = MongoCredential.createCredential("ayala", "admin", "q1w2e3r4!".toCharArray());

		MongoClient mongoClient = new MongoClient(
				new ServerAddress("localhost", 27017)		//replica-set
		        ,Arrays.asList(credential)
//		        ,options
		        );
		MongoDatabase database = mongoClient.getDatabase(FORENSIC_DB_NAME);
		MongoCollection<Document> collection = database.getCollection(EVENTS_COLL_NAME);
		System.out.println(collection.count());

	}

}
