package session;

import java.util.Collection;
import java.util.TreeMap;

public class RoomModel {
	private String master;
	private String roomName;
	
	private static final TreeMap<String, RoomEvents> roomObserverList = new TreeMap<>();

	public RoomModel(String master, String roomName, RoomEvents handle) {
		this.master = master;
		this.roomName = roomName;
	}
	
	
	private void notifyUserListChanged() {
		roomObserverList.values().forEach(c -> c.roomUserListChanged(roomName));
	}
	
	
	public synchronized void chatMessage(String from, String message) {
		roomObserverList.values().forEach(c -> c.roomChatMessageSent(roomName,from, message));
	}
	
	public synchronized void registerUser(String user, RoomEvents handler) {
		roomObserverList.put(user, handler);
		notifyUserListChanged();
	}
	
	public synchronized void unregisterUser(String user) {
		if (user.equals(master)) {
			master = null;
		}
		roomObserverList.remove(user);
		notifyUserListChanged();
	}
	
	public synchronized boolean canDelete(String name) {
		return this.master == name;
	}
	
	public synchronized Collection<String> userList(){
		return roomObserverList.keySet();
	}
	
	public synchronized int userCount() {
		return roomObserverList.size();
	}
	
	
	
	public synchronized boolean hasUser(String user) {
		return roomObserverList.containsKey(user);
	}
	
	public synchronized void userRenamed(String name, String newName) {
		if(!hasUser(name)) return;
		roomObserverList.put(newName, roomObserverList.remove(name));
	}
	
	
	
	
}
