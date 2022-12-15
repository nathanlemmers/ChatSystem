package Controller;

import Model.User;
import java.util.ArrayList;

public class UserManager {
	
	private static ArrayList<User> UserTab = new ArrayList<User>();

	//l'user peut avoir accès à son pseudo
	public static String monPseudo() {
		
		String pseudo = ""; //on instancie toujours notre chaine de caractère
		
		try {
			pseudo = UserTab.get(0).getPseudo();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Erreur recherche pseudo");
		}
		return pseudo;
	}
	
	
}
