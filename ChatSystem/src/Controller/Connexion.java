package Controller;

import Model.User;

public abstract class Connexion {
	
	public static int etat = 1;

	public static int ConnexionCompte(String pseudo, String mdp) {
		if (DatabaseManager.verifyLogin(pseudo, mdp)==1) {
			System.out.println("BBBBBBBBBB") ;
			ThreadManager.newConnexion(new User(pseudo));
			return 1 ;
		} else {
			System.out.println("Connexion impossible") ;
			return 0 ;
		}
	}
	
	public static int NewConnexion(String pseudo, String mdp ) {
		if (DatabaseManager.NewUser(pseudo, mdp)==1) {
			ThreadManager.newConnexion(new User(pseudo));
			return 1 ;
		} else {
			System.out.println("Nouvelle connexion impossible") ;
			return 0 ;
		}
	}
	
	public static void Deconnexion(String pseudo, String mdp) {
		ThreadManager.deconnexion(new User(pseudo));
		etat = 0 ;
	}
	
	public static int ChangerPseudo (String apseudo, String mdp, String npseudo) {
		if (DatabaseManager.ChangerPseudo(apseudo, mdp, npseudo)==1) {
			ThreadManager.changePseudo(new User (npseudo));
			return 1 ;
		} else {
			System.out.println("Nouvelle connexion impossible") ;
			return 0 ;
		}
	}
	
	public static int Changermdp (String pseudo, String mdp, String nmdp) {
		if (DatabaseManager.ChangerMdp(pseudo, mdp, nmdp)==1) {
			return 1 ;
		} else {
			System.out.println("Nouvelle connexion impossible") ;
			return 0 ;
		}
	}
	
}
