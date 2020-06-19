package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Minichat.IChatLogger;
import Minichat.TextChatLogger;
import protocol.ChatModel;




public class ServerCore extends Thread {
	
	private int port;
	ServerSocket ss;
	private boolean stop = false;
	public ServerCore(int port) throws IOException {
		this.port = port;
		System.out.println("Server Connected");
		this.start();
	}

	public void run() {
		try (ServerSocket ss = new ServerSocket(port)) {
			ss.setSoTimeout(1000);
			while (!stop) {
				try {
				
					System.out.println("pas encore");
					Socket s = ss.accept();
					System.out.println("Client connected ");
					new Thread(new HandleClient(s)).start();
				} catch (SocketTimeoutException ex) {

				}
			}
		} catch (IOException e) {
			Logger.getLogger(ServerCore.class.getName()).log(Level.SEVERE,null, e);
		}
	}

	public synchronized void finish() {
		ChatModel.clearAll();
	}
}
