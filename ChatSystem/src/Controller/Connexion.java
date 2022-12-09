package Controller;

import Model.User;

public class Connexion {
	
	public static int etat = 1;

	public int ConnexionCompte(String pseudo, String mdp) {
		if (DatabaseManager.verifyLogin(pseudo, mdp)==1) {
			ThreadManager.newConnexion(new User(pseudo));
			return 1 ;
		} else {
			System.out.println("Connexion impossible") ;
			return 0 ;
		}
	}
	
	public int NewConnexion(String pseudo, String mdp ) {
		if (DatabaseManager.NewUser(pseudo, mdp)==1) {
			ThreadManager.newConnexion(new User(pseudo));
			return 1 ;
		} else {
			System.out.println("Nouvelle connexion impossible") ;
			return 0 ;
		}
	}
	
	public void Deconnexion(String pseudo, String mdp) {
		ThreadManager.deconnexion();
		etat = 0 ;
	}
	
	public int Changermdp (String apseudo, String npseudo, String mdp) {
		if (DatabaseManager.ChangerMdp(apseudo, npseudo, mdp)==1) {
			ThreadManager.changePseudo(new User (npseudo));
			return 1 ;
		} else {
			System.out.println("Nouvelle conexion impossible") ;
			return 0 ;
		}
	}
	
}
