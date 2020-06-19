package client;


public interface Observer {
	public void notifyObserverMessageSent(String from, String msgR);
	public void nottifyObserverClientConnected(String clients);
}
