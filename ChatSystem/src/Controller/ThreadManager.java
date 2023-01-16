package Controller;

import Model.User;

public abstract class ThreadManager {

	private static ClientUDP client ;
	
	public static void newConnexion(User u) {
		new Thread(new ServeurUDP(u)).start() ;
		System.out.println(u) ;
		client = new ClientUDP(u) ;
		client.sendUser();
		client.close();
	}
	
	public static void changePseudo(User u) {
		client = new ClientUDP(u) ;
		client.sendUser();
		client.close();
	}

	public static void deconnexion(User u) {
		client = new ClientUDP(new User("deconnexion")) ;
		client.sendUser();
		client.close();
		ServeurUDP serveur = new ServeurUDP(u) ;
		serveur.stop();
	}
	
	
	
}
