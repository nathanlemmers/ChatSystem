package Test;

import Controller.ClientUDP;
import Controller.ServeurUDP;
import Controller.ThreadManager;
import Model.User;

public class UDPTest {
	//Ceci représente la premère connexion et la récupération de l'adresse IP de tout le monde
	private static ClientUDP client ;
	private static User nouveau = new User("Nouveau") ;
	private static User ancien = new User("Ancien") ;
	
	public static void setup() {
		new Thread(new ServeurUDP(nouveau)).start() ;
		client = new ClientUDP(nouveau) ;
	}
	
	public  static void test() {
		client.sendUser();
		client.close();
	}
	
	public static void main (String args[]) {
		ThreadManager.newConnexion(nouveau) ;
		ThreadManager.changePseudo(ancien);
		ThreadManager.deconnexion();
	}
	
	
	
}
