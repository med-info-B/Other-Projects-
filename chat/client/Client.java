package client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;




public class Client {
	public  boolean connected = false;
	private ClientHandleConnection connection;
	Socket sock;
	private int port;
	public String ConnectAddress = "";
	public String nick="test";
	public JList<String> UsersList;
	private ArrayList<Observer> observers;
	public String from="", msgR="";
	public Client(int port, String connectAddress) {
		this.port = port;
		this.ConnectAddress = connectAddress;
		this.observers = new ArrayList<>();
	}
	
	public void register(Observer o) {
		observers.add(o);
	}
	
	public void unregister(Observer o) {
		observers.remove(o);
	}
	
	private void notifyObserver(String from, String msgR) {
		for(Observer ob : observers) {
			ob.notifyObserverMessageSent(from,msgR);
		}
	
	}
	
	private void notifyObserverClientConnected(String clients) {
		for(Observer ob : observers) {
			ob.nottifyObserverClientConnected(clients);
		}
	
	}
	public void connect(){
	
		if(connected) return;
			try {
				sock = new Socket(ConnectAddress,port);
				connected = true;
				connection = new ClientHandleConnection(this, sock);
				connection.start();
			}catch (IOException e) {
			}
			
			Random r = new Random();
			if(nick.equals("test")) {
				nick = nick + Integer.toString(r.nextInt());
				connection.sendName(nick);
				connection.askUserList();
			}
			
		//	}
	}


	public void disconnected() {
		connection.sendQuit();
		connected = false;
		
	}
	

	public void quit() {
		connection.sendQuit();
	}
	
	public void acceptName() {
		JOptionPane d = new JOptionPane();
		JOptionPane.showMessageDialog( new JFrame(), "Name ok", 
		      "Name OK", JOptionPane.NO_OPTION);
	}
	
	public void fillUserList(Collection<String> list) {
	//	UsersList.setListData(list.toArray(new String[0]));
		for(String client : list) {
			notifyObserverClientConnected(client);
		}
	}
	
	public  void envoieMessage(String msg){
		connection.chatMessage(nick, msg);
	}
	
	
	public void envoieMessagePrive(String msg, String to) {
		connection.privateChatMessage(nick, to, msg);
	}
	public synchronized void messageRecu(String from, String msg) {
		this.from = from;
		this.msgR =msg;
		notifyObserver(from, msg);
	}
    
	
	public void createSalle(String room) {
		connection.createRoom(room);
	}

	public void RCreateRoom(String room, String info) {		
		JOptionPane d = new JOptionPane();
		JOptionPane.showMessageDialog( new JFrame(),room, 
		      " : "+info, JOptionPane.NO_OPTION);
	}

	
}
