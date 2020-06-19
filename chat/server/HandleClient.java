package server;

import java.io.IOException;
import java.net.Socket;

import Minichat.IChatLogger;
import entree_sortie.ChatInput;
import entree_sortie.ChatOutput;
import protocol.ChatEvents;
import protocol.ChatModel;
import protocol.ChatProtocol;
import protocol.ChatProtocolException;


public class HandleClient implements Runnable, ChatProtocol, ChatModelEvents {
	
	private final Socket s;
	private ChatOutput cho;
	private ChatInput chi;
	private String name = "";
	private enum ClientState {
		ST_INIT, ST_NORMAL
	};
	private ClientState state = ClientState.ST_INIT;
	private boolean stop = false;
	
	public HandleClient(Socket s) throws IOException {
		this.s = s;		
	}

	
	@Override
	public void sendAskUserList() {
		this.askUList();
	}
	

	@Override
	public void sendPrivateMessage(String from, String to, String msg) {
		ChatModel.sendPrivateChatMessage(from, to, msg);
	}
	@Override
	public void sendQuit() {
		ChatModel.unregisterUser(name);
		this.finish();
		
	}
	@Override
	public void userListChanged() {
		if (state == ClientState.ST_INIT) return;
		cho.sendUserList(ChatModel.getUserNames());
	}

	@Override
	public void chatMessageSent(String from, String message) {
			cho.sendMessage(from, message);
	}

	@Override
	public void privateChatMessageSent(String from, String to, String message) {
		cho.sendPrivateMessage(from, to, message);
	}

	@Override
	public void shutdownRequested() {
		
	}

	@Override
	public void run() {
		System.out.println("handle client test run ");
		try (Socket s1 = s) {
			cho = new ChatOutput(s1.getOutputStream());
			chi = new ChatInput(s1.getInputStream(), this);
			chi.doRun();
		} catch (IOException ex) {
			if (!stop) {
				finish();
			}
		} catch (ChatProtocolException e1) {
				e1.printStackTrace();
		}
	}
	/**
	 *  Enregistrement des clients 
	 */
	public void sendName(String name) {
		System.out.println("send name serveur");
		String newName = name;
		if (ChatModel.existUserName(newName)) {
			cho.sendNameBad();
		} else {
			if (state == ClientState.ST_INIT) {
				ChatModel.registerUser(newName, this);
			state = ClientState.ST_NORMAL;
			} else {
			ChatModel.renameUser(name, newName, this);
		}
		this.name = newName;
		cho.sendNameOK();
		}
	}
	
	public void askUList() {
		if (state == ClientState.ST_INIT) return;
		cho.sendUserList(ChatModel.getUserNames());
	}
	
	public void sendMessage(String user, String msg) {
		if (state == ClientState.ST_INIT) return;
		ChatModel.sendChatMessage(name, msg);
	}
	
//	public void chatMessage(String from, String msg) {
//		if (from != name) {
//			//cho.sendMessage(from, msg);
//		}
//	}
	
	
	public synchronized void finish(){
		if (!stop) {
			stop = true;
		try {
			s.close();
		} catch (IOException ex) { ex.printStackTrace(); }
			if (name != null)
				ChatModel.unregisterUser(name);
		}
	}
	
	
	public void sendCreateRoom(String room) {
		if (state == ClientState.ST_INIT) {
		return;
		}
		if(ChatModel.existRoom(room)) cho.sendRoomBad(room);
		else {
			ChatModel.addRoom(room, name);
			cho.sendRoomOk(room);
		}
	}

	@Override
	public void roomUserListChanged(String room) {
		cho.sendRUList(room, ChatModel.getUserRoom(room));
	}

	
	public void sendRoomMessage(String room, String from, String message){
		if (state == ClientState.ST_INIT) {
			cho.sendERR( "Not initialized...");
		}
		if (ChatModel.roomHasUser(room, name)) {
			ChatModel.roomSendChatMessage(room, name, message);
		} else {
			cho.sendERR( "Not in room...");
		}
	}



	@Override
	public void roomListChanged() {
		cho.sendRList(ChatModel.getListRoom());
	}


	@Override
	public void roomChatMessageSent(String room, String from, String message) {
		cho.sendRoomMessage(room, from, message);
	}

  

	
	

}
