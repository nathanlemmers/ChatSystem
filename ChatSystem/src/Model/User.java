package Model;

import java.io.IOException;

public class User {

	private String pseudo ;
	
	public User(String pseudo) {
		if (pseudo.equals(null)) {
			this.pseudo = " " ;
		} else {
		this.pseudo=pseudo ;
		}
	}
	
	public String getPseudo() {
		try {
			if (pseudo.equals(null)) {
				return " " ;
			} else {
			return pseudo ;
			}
		}
		catch (Exception e) {
			return " " ;
		}
	}
		
	public String toString() {
		return pseudo ;
	}
}
