import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cellebrite.analytics.repository.mongodb.TestConnectionPoolListener;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoClientWrapper {

	private static PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
	private static MongoClient mongoClient = createMongoClient();

	public static MongoClient getMongoClient()
	{
		return mongoClient;		
	}
	
	private static MongoClient createMongoClient()
	{
		//credentials:
		String mongoUser = propertiesLoader.getProperty( "mongo.user" );
		String mongoPassword = propertiesLoader.getProperty( "mongo.pwd" );
//		mongo.db.name=forensic
		MongoCredential credential = MongoCredential.createCredential(mongoUser, "admin", mongoPassword.toCharArray());

		MongoClientOptions options = MongoClientOptions.builder()
		        .addConnectionPoolListener(new TestConnectionPoolListener())
		        .connectTimeout(20000)
		        .maxConnectionIdleTime(0)		//A zero value indicates no limit to the life time.
		        .maxConnectionLifeTime(0)		//A zero value indicates no limit to the life time.
		        .maxWaitTime(120000)				//Sets the maximum time that a thread will block waiting for a connection.
		        .build();

		String seedsAsString = propertiesLoader.getProperty( "mongo.nodes" );
        String[] splittedSeeds = seedsAsString.split(",");
        List<ServerAddress> seeds = new ArrayList<>();
        for(String seed : splittedSeeds)
        {
            String[] hostPort = seed.split(":");
            ServerAddress serverAddress = new ServerAddress( hostPort[0], Integer.parseInt(hostPort[1]) );
            seeds.add(serverAddress);
        }

		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient(
				seeds								//replica-set
		        ,Arrays.asList(credential)
		        ,options
		        );
		
		return mongoClient;		
	}
	
}
