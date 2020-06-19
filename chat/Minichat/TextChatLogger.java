package Minichat;

public class TextChatLogger implements IChatLogger {

	@Override
	public void clientConnected(String ip) {
		System.out.println("Client connecte avec l'adresse ip : "+ ip);
	}

	@Override
	public void clientDisconnected(String ip, String name) {
		System.out.println("Client "+name+"adresse ip : "+ip);
	}

	@Override
	public void clientGotName(String ip, String name) {
		System.out.println("client"+ip+" obtient un nom : "+name);
	}

	@Override
	public void clientGotCommand(String name, int command) {
		System.out.println("client : "+name+" command : "+command);
	}

	@Override
	public void publicChat(String from, String msg) {
		System.out.println("public Msg : "+from+" : " + msg);
	}

	@Override
	public void privateChat(String from, String to, String msg) {
		System.out.println("private Msg : "+from+" to "+to+" : "+ msg);
	}

	@Override
	public void systemMessage(String msg) {
		System.out.println("system msg : "+ msg);	
	}



}
