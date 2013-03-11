package de.tud.in.middleware.snapshot;

import java.util.HashSet;

public final class ClientSessionSet {
	public static final long TIMEOUT_LIMIT_MILIS = 2000;
	
	public final long timeout = System.currentTimeMillis() + TIMEOUT_LIMIT_MILIS;
	public final Integer snapshotID;
	
	private final int numberOfClients;
	private final HashSet<Integer> clients = new HashSet<Integer>();
	
	public ClientSessionSet(int numberOfClients, Integer snapshotID){
		this.numberOfClients = numberOfClients;
		this.snapshotID = snapshotID;
	}
	
	public void addClient(Integer id){
		clients.add(id);
	}
	
	public boolean complete(){
		return clients.size() == numberOfClients;
	}
}
