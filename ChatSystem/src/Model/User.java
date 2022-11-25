package Model;

public class User {

	private String id ;
	private String mdp ;
	private boolean etat ;
	
	public User(String id, String mdp, boolean etat) {
		this.id=id ;
		this.mdp = mdp ;
		this.etat=etat ;
	}
	
	public String GetID() {
		return id ;
	}
}
