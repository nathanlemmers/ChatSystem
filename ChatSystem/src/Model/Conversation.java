package Model;

import java.util.ArrayList;
import java.util.HashMap;


public class Conversation {

	public HashMap<User, ArrayList<String>> conversation ;
	private static final Conversation instance = new Conversation();
	
	private Conversation() {
		conversation = new HashMap<User, ArrayList<String>>() ;
	}
	
	public int addMessage(User u, String mess) {
		ArrayList<String> list = conversation.get(u) ;
		list.add(mess) ;
		conversation.put(u, list) ;
		if (conversation.get(u)==null){
			System.out.printf("Erreur") ;
			return 0 ;
		} else 
			return 1 ;
		}
	
	public ArrayList<String> getConversation(User u) {
		return conversation.get(u) ;
	}
	
	public void printConversation() {
		System.out.println(conversation.toString()) ;
	}
	
	public static Conversation getInstance() {
		return instance;
	}
	
}
