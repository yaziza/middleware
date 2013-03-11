package de.tud.in.middleware.businessLogic;

import java.util.ArrayDeque;

import de.tud.in.middleware.snapshot.Snapshot;

public final class SnapshotRingBuffer {
	private final ArrayDeque<Snapshot> deque = new ArrayDeque<Snapshot>();
	private final int limit;
	
	public SnapshotRingBuffer(int maxSize){
		this.limit = maxSize;
	}
	
	public void add(Snapshot snap){
		synchronized (deque) {
			if(deque.size() == limit){
				deque.poll();
			}
			deque.add(snap);
		}
	}
	
	public Snapshot getSnapshot(Integer id){
		synchronized (deque) {
			for(Snapshot snap : deque){
				if(snap.id.equals(id)){
					return snap;
				}
			}
		}
		return null;
	}
}
