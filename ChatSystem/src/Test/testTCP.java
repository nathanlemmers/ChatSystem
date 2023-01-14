package Test;

import Controller.NetworkManager;
import Model.Annuaire;
import Model.Conversation;
import Model.User;

public class testTCP {

	
	public static void main (String args[]) {
		NetworkManager net = new NetworkManager() ;
		new Thread(new NetworkManager()).start();
    	User me = new User("me") ;
    	Annuaire.getInstance().addAnuaire("127.0.0.1", me) ;
    	//Annuaire.getInstance().printAnnuaire();
    	System.out.println("debut client") ;
    	if (net.sendMessage(me, "test")==1 & net.sendMessage(me,"Comment vas-tu ?")==1) {
    		System.out.println("Test OK") ;
    	}
    	}
}
