package entree_sortie;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;

import protocol.ChatProtocol;

public class ChatOutput implements ChatProtocol{
	
	PrintWriter os;
	public ChatOutput(OutputStream out) throws IOException {
	this.os = new PrintWriter(out, true);
	}
	
	public synchronized void sendName(String name){
		os.println("NAME");
		os.println(name);
	}
	
	public synchronized void sendUserList(Collection<String> ulist){
		os.println("ULIST");
		ulist.forEach(os::println);
		os.println(".");
	}

	@Override
	public synchronized void sendNameOK() {
		os.println("NAME OK");
	}

	@Override
	public synchronized void sendNameBad() {
		os.println("NAME Bad");

	}

	@Override
	public synchronized void sendMessage(String user, String msg) {
		os.println("MESSAGE");
		os.println(user);
		os.println(msg);
	}

	@Override
	public synchronized void sendAskUserList() {
		os.println("AULIST");
	}

	@Override
	public synchronized void sendPrivateMessage(String from, String to, String msg) {
		os.println("PRIVATE MESSAGE");
		os.println(from);
		os.println(to);
		os.println(msg);
	}

	@Override
	public synchronized void sendQuit() {
		os.println("QUIT");
	}

	
	// command pour les rooms
	@Override
	public void sendRoomBad(String name) {
		os.println("ROOM Bad");
		os.println(name);
	}

	@Override
	public void sendRoomOk(String room) {
		os.println("Room OK");
		os.println(room);
	}

	@Override
	public void sendERR(String err) {
		os.println("ERR");
		os.println(err);
	}

	@Override
	public void sendCreateRoom(String room) {
		os.println("Create Room");
		os.println(room);
	}
	
	@Override
	public void sendDeleteRoom(String room) {
		os.println("Delete Room");
		os.println(room);
	}

	@Override
	public void sendRList(Collection<String> rList) {
		os.println("RLIST");
		rList.forEach(os::println);
		os.println(".");
	}

	@Override
	public void sendARList() {
		os.println("ARList");
	}

	@Override
	public void sendRoomMessage(String room, String user, String msg) {
		os.println("Room Message");
		os.println(room);
		os.println(user);
		os.println(msg);
	}
	
	@Override
	public void sendEntreRoom(String room, String user) {
		os.println("Abonner Room");
		os.println(room);
		os.println(user);
	}
	
	@Override
	public void sendLeaveRoom(String room, String user) {
		os.println("Leave Room");
		os.println(room);
		os.println(user);
	}
	
	@Override
	public void sendARUList(String room) {
		os.println("ARUList");
		os.println(room);
	}
	
	@Override
	public void sendRUList(String room, Collection<String> rUList) {
		os.println("RUList");
		os.print(room);
		rUList.forEach(os::println);
		os.println(".");
		
	}
	



}
