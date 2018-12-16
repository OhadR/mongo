package com.cellebrite.analytics.repository.mongodb;

import com.mongodb.ServerAddress;
import com.mongodb.connection.ServerConnectionState;
import com.mongodb.connection.ServerType;

public class MongoServerDetails {

	private ServerAddress		address;	//host + port
	private ServerType 			type;	//primary vs. secondary
	private ServerConnectionState state;	//connected etc.

	public MongoServerDetails(ServerAddress serverAddress, ServerType serverType, ServerConnectionState serverConnectionState) {
		this.address = serverAddress;
		this.type = serverType;
		this.state = serverConnectionState;
	}
	
	@Override
	public String toString()
	{
		return address + "/" + type + ", " + state;
		
	}

	public ServerAddress getAddress() {
		return address;
	}

	public void setAddress(ServerAddress address) {
		this.address = address;
	}

	public ServerType getType() {
		return type;
	}

	public void setType(ServerType type) {
		this.type = type;
	}

	public ServerConnectionState getState() {
		return state;
	}

	public void setState(ServerConnectionState state) {
		this.state = state;
	}

	
	
}
