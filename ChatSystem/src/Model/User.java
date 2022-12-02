package Model;

public class User {

	private String pseudo ;
	private String mdp ;
	private boolean etat ;
	
	public User(String pseudo, String mdp, boolean etat) {
		this.pseudo=pseudo ;
		this.mdp = mdp ;
		this.etat=etat ;
	}
	
	public String GetPseudo() {
		return pseudo ;
	}
}
