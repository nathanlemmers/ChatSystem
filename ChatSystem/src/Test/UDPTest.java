package Test;

import Controller.ClientUDP;
import Controller.ServeurUDP;
import Model.User;

public class UDPTest {
	
	private static ClientUDP client ;
	private static User nouveau = new User("Nouveau") ;
	private static User ancien = new User ("Ancien") ;
	
	public static void setup() {
		new Thread(new ServeurUDP(nouveau)).start() ;
		client = new ClientUDP(ancien) ;
	}
	
	public  static void test() {
		client.sendUser();
		client.close();
	}
	
	public static void main (String args[]) {
	setup() ;
	test() ;
	}
	
	
	
}
