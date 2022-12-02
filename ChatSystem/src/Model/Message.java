package Model;

import java.sql.Timestamp;

public class Message {
	
	private User emetteur ;
	private User  receveur ;
	private Timestamp date ;
	private String contenu ; 

	public Message (User u1, User u2, String contenu) {
		this.emetteur = emetteur  ;
		this.receveur = receveur ;
		this.date = new Timestamp(System.currentTimeMillis()) ;
		this.contenu = contenu ;
	}
	
	public User getEmetteur() {
		return emetteur ;
	}
	
	public User getReceveur() {
		return receveur ;
	}
	
	public Timestamp getDate() {
		return date ;
	}
	
	public String getContenu() {
		return contenu ;
	}
	
}
