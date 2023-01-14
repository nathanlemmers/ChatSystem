package Model;

import java.util.ArrayList;
import java.util.HashMap;


public class Conversation {

	public HashMap<User, ArrayList<String>> conversation ;
	private static final Conversation instance = new Conversation();
	private static int wait = 0 ;
	
	private Conversation() {
		conversation = new HashMap<User, ArrayList<String>>() ;
	}
	
	public int addMessage(User u, String mess) {
		while(wait==1) {
			System.out.print("a");
		}
		wait = 1 ;
		return addMessage1(u, mess) ;
	}
	
	private int addMessage1(User u, String mess) {
		System.out.println("voila ce qu'on après : "+ conversation.get(u)) ;
		ArrayList<String> list = conversation.get(u) ;
		if (list==null) {
			System.out.println("on passe par là.") ;
			list = new ArrayList<String>() ;
		}
		list.add(mess) ;
		conversation.remove(u);
		conversation.put(u, list) ;
		System.out.println("voila ce qu'on avant : "+ conversation.get(u)) ;
		if (conversation.get(u)==null){
			System.out.printf("Erreur") ;
			wait = 0 ;
			return 0 ;
		} else 
			wait = 0 ;
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
	
	public static void main (String args[]) {
		String greeting = "Yo" ;
		User u = new User("Nico") ;
		Conversation.getInstance().addMessage(u, u.toString()+ " : " + greeting) ;
		Conversation.getInstance().printConversation();
	}
	
}
