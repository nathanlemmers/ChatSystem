package Test;

import Controller.NetworkManager;
import Model.Annuaire;
import Model.Conversation;
import Model.User;

public class testTCP {

	
	public static void main (String args[]) {
		NetworkManager net = new NetworkManager() ;
    	User me = new User("me") ;
    	Annuaire.getInstance().addAnuaire("127.0.0.1", me) ;
    	Annuaire.getInstance().printAnnuaire();
    	System.out.println("debut client") ;
    	if (net.sendMessage(me, "bonjour")==1) {
    		System.out.println("Test OK") ;
    	}
    	}
}

//problème dans le in, on ne recoit jamais rien, tester le truc simple : https://www.baeldung.com/a-guide-to-java-sockets