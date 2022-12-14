package Controller;

import Model.User;
import java.util.ArrayList;

public class UserManager {
	
	private static ArrayList<User> UserTab = new ArrayList<User>();

	//l'user peut avoir acc?s ? son pseudo
	public static String monPseudo() {
		
		String pseudo = ""; //on instancie toujours notre chaine de caract?re
		
		try {
			pseudo = UserTab.get(0).getPseudo();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Erreur recherche pseudo");
		}
		return pseudo;
	}
	
	public static ArrayList<String> pseudoTab() {
		ArrayList<String> pseudoTab = new ArrayList<String>();
		for (int i = 1; i < UserTab.size(); i++) {
			pseudoTab.add(UserTab.get(i).getPseudo());
		}
		return pseudoTab;
	}
	
}
