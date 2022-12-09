package Test;

import Controller.NetworkManager;
import Model.Annuaire;
import Model.User;

public class testTCP {

	
	public static void main (String args[]) {
		NetworkManager net = new NetworkManager() ;
    	User me = new User("me") ;
    	Annuaire.getInstance().addAnuaire("10.1.5.78", me) ;
    	System.out.println("debut client") ;
    	net.sendMessage(me, "bonjour") ;
	}
}

//problème dans le in, on ne recoit jamais rien, tester le truc simple : https://www.baeldung.com/a-guide-to-java-sockets