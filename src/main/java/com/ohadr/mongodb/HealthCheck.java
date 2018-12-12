package com.ohadr.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.cellebrite.analytics.repository.mongodb.MongoServerDetails;
import com.mongodb.MongoClientWrapper;
import com.mongodb.ServerAddress;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ClusterType;
import com.mongodb.connection.ServerConnectionState;
import com.mongodb.connection.ServerDescription;
import com.mongodb.connection.ServerType;

public class HealthCheck {

    public static List<MongoServerDetails> checkMongoHealth() 
    {
        Cluster cluster = MongoClientWrapper.getMongoCluster();
        ClusterDescription clusterDescription = cluster.getDescription();
        ClusterType clusterType = clusterDescription.getType();
        List<ServerDescription> serverDescriptions = clusterDescription.getServerDescriptions();
    	List<MongoServerDetails> retVal = new ArrayList<MongoServerDetails>();
        for(ServerDescription sd : serverDescriptions)
        {
        	ServerAddress sa = sd.getAddress();
        	ServerType serverType = sd.getType();
        	ServerConnectionState serverState = sd.getState();
        	MongoServerDetails mongoServerDetails = new MongoServerDetails(sa, serverType, serverState);
        	System.out.println(mongoServerDetails);
        	retVal.add(mongoServerDetails);
        }
        return retVal;
    }

}
