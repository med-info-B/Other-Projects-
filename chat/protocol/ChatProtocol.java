package protocol;

import java.util.Collection;

public interface ChatProtocol {
	default void sendName(String name){}
	default void sendNameOK(){}
	default void sendNameBad() {}
	default void sendMessage(String user, String msg){}
	default void sendAskUserList() {}
	default void sendUserList(Collection<String> ulist) {}
	default void sendPrivateMessage(String from, String to, String msg){}
	default void sendQuit(){}
	
	
	default void sendRoomBad(String room){};
	default void sendCreateRoom(String room){};
	default void sendRoomOk(String room){};
	default void sendDeleteRoom(String room){};
	default void sendARList(){};
	default void sendRList(Collection<String> rList){};
	default void sendRoomMessage(String room, String user, String msg){};
	default void sendEntreRoom(String room, String user) {};
	default void sendLeaveRoom(String room, String user) {};
	default void sendARUList(String room){};
	default void sendRUList(String room, Collection<String> rUList){};
	default void sendERR(String err){};
}
