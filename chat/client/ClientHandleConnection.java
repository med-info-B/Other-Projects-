package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import entree_sortie.ChatInput;
import entree_sortie.ChatOutput;
import protocol.ChatProtocol;
import protocol.ChatProtocolException;

public class ClientHandleConnection extends Thread implements ChatProtocol{
	private final Socket s;
	private Client client;
	private ChatInput chi;
	private ChatOutput cho;
	
	public ClientHandleConnection(Client f, Socket s ) throws IOException {
		this.client  = f ;
		this.s =s;
		cho =  new ChatOutput(s.getOutputStream());
	}
	
	
	public void sendNameOK() {
		client.acceptName();
	}
	 
	@Override
	public void sendName(String name) {
		cho.sendName(name);
	}
	public void sendUserList(Collection<String> ulist) {
		client.fillUserList(ulist);
	}
	
	
	public void chatMessage(String user, String msg) {
		cho.sendMessage(user, msg);			
	}
	
	public void privateChatMessage(String user, String to, String msg) {
		cho.sendPrivateMessage(user, to, msg);
	}
	
	@Override
	public void sendMessage(String user, String msg) {
		client.messageRecu(user, msg);
	}


	@Override
	public void sendPrivateMessage(String from, String to, String msg) {
		client.messageRecu(from, msg);
		
	}


	public void run() {
		try (Socket s1 = s) {
			chi = new ChatInput(s1.getInputStream(), this);
			chi.doRun();
		} catch (IOException ex) {
			finish();
		} catch (ChatProtocolException e) {
			e.printStackTrace();
		}
	}
	
	
	private void finish() {
		
	}
	public void askUserList() {
		cho.sendAskUserList();
	}
//	
	@Override
	public void sendQuit() {
		cho.sendQuit();
	}
	
	public void createRoom(String room) {
		cho.sendCreateRoom(room);
	}
	
	@Override
	public void sendRoomBad(String room) {
		client.RCreateRoom(room, "bad");
	}
	
	@Override
	public void sendRoomOk(String room) {
		client.RCreateRoom(room, "ok");
	}
	
}
