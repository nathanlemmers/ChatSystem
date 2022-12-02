package Model;

import java.util.ArrayList;

public class Annuaire {
	
	public static ArrayList<User> annuaire ;
	private static final Annuaire instance = new Annuaire();
	
	private Annuaire() {
		annuaire = new ArrayList<User>() ;
	}
	
	//0 si ok, -1 sinon
	public int addAnuaire(User u1) {
		System.out.println(u1);
		int index = annuaire.indexOf(u1) ;
		if (index!=-1) {
		annuaire.add(u1) ;
		return 0 ;
		} else {
			return -1 ;
		}
	}
	
	//Renvoie 0 si on a supprimé, -1 si il n'existe pas dans la liste.
	public static int delAnnuaire(User u1) {
		int index = annuaire.indexOf(u1) ;
		if (index!=-1) {
			annuaire.remove(index) ;
			return 0 ;
		}
		else {
			return -1 ;
		}
	}
	
	public ArrayList<User> getAnnuaire() {
		return annuaire ;
	}
	
	public static Annuaire getInstance() {
		return instance;
	}
	
}
