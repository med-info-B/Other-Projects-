package entree_sortie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import protocol.ChatProtocol;
import protocol.ChatProtocolException;

public class ChatInput {
	ChatProtocol handler;
	InputStream in;
	boolean stop = false;
	public ChatInput(InputStream in, ChatProtocol handler) throws IOException {
		this.in = in;
		this.handler = handler;
	}
	
	public void doRun() throws IOException, ChatProtocolException {
		String strMsg, strName, strName1;
		ArrayList<String> userList, roomList, roomUserList;
		try (BufferedReader is = new BufferedReader(new InputStreamReader(in))) {
			while (!stop) {
				String line = is.readLine();
				switch (line) {
					case "NAME":  
								  strName = is.readLine();
								  handler.sendName(strName);
					break;
				    case "MESSAGE":
				    			  strName = is.readLine();
				    			  strMsg = is.readLine();
				    			  handler.sendMessage(strName, strMsg);
				    break;
				    case "ULIST":
				    			  userList = new ArrayList<>();
				    			  String x;
				    			  while (!(x = is.readLine()).equals(".")) {
				    				  userList.add(x);
				    			  }
				    			  handler.sendUserList(userList);
				    break;
				    case "NAME OK" :  handler.sendNameOK();
				    break ;
				    case "NAME Bad" : handler.sendNameBad();
				    break ;
				    case "AULIST" : handler.sendAskUserList();
				    break;
				    case  "PRIVATE MESSAGE":    strName = is.readLine();
				    							strName1 = is.readLine();	
				    							strMsg = is.readLine();
				    							handler.sendPrivateMessage(strName,strName1,strMsg); 
				    break;
				    case "QUIT" :  		this.stop = true;
				    					handler.sendQuit();
				    					
				    case "ROOM Bad" :   strName1 = is.readLine();
				    					handler.sendRoomBad(strName1);
				    break;
				    case "Room OK" : 	 strName1 = is.readLine();
				    					 handler.sendRoomOk(strName1);
				    break;
				    case "ERR" : 		 strName1= is.readLine();
				    					 handler.sendERR(strName1);	
				    break;
				    case "Create Room" : strName1 = is.readLine();
				    					 handler.sendCreateRoom(strName1);
				    break;
				    case "Delete Room" : strName1 = is.readLine();	
				    					 handler.sendDeleteRoom(strName1);
				    break;
				    case "ARList" :      handler.sendARList();
				    break;
				    case "RLIST" :        roomList = new ArrayList<>();
	    			  					  String x1;
	    			  					  while (!(x1 = is.readLine()).equals(".")) {
	    			  						roomList.add(x1);
	    			                      }
	    			  					  handler.sendRList(roomList);
	    			break;
				    case "Room Message" : strName = is.readLine();
				    					  strName1 = is.readLine();
				    					  strMsg = is.readLine();
				    					  handler.sendRoomMessage(strName, strName1, strMsg);
				   break;
				    case "Abonner Room"  : strName = is.readLine();
				    					   strName1 = is.readLine();
				    					   handler.sendEntreRoom(strName, strName1);
				    break;
				    case "Leave Room" :    strName = is.readLine();
				    					   strName1 = is.readLine();
				    					   handler.sendLeaveRoom(strName, strName1);
				    break;
				    
				    case "ARUList" :       strName = is.readLine();
				    					   handler.sendARUList(strName);
				    break;
				    case "RUList" :        roomUserList = new ArrayList<String>();
				    					   strName = is.readLine();
				    					   String x2;
				    					   while (!(x2 = is.readLine()).equals(".")) {
				    						   roomUserList.add(x2);
				    					   }
				    					   handler.sendRUList(strName, roomUserList);
				    					
				    default:    	throw new ChatProtocolException("Invalid input");
				} // switch
		} // while 
	  }
	} // doRun
}
