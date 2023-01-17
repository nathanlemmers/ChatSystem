package Setup;

import Controller.DatabaseManager;
import View.InterfaceConnexion;

public class ConnexionUser {

	
	
	
	public static void main(String[] args) {
		DatabaseManager.create() ;
		DatabaseManager.Setup() ;
		new InterfaceConnexion().showFrame();

	}
}
