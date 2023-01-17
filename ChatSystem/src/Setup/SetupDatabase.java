package Setup;

import Controller.DatabaseManager;

public class SetupDatabase {
	
	
	public static void main(String[] args) {	
		DatabaseManager.create() ;
		DatabaseManager.reset() ;
		DatabaseManager.Setup() ;
	}

}
