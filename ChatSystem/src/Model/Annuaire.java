package Model;

import java.net.InetAddress;
import java.util.HashMap;

public class Annuaire {
	
	public HashMap<String, User> annuaire ;
	private static final Annuaire instance = new Annuaire();
	
	private Annuaire() {
		annuaire = new HashMap<String,User>() ;
	}
	
	//0 si ok, -1 sinon
	public int addAnuaire(String adressIP, User u1) {
		User u = annuaire.get(adressIP) ;
		if (u==null) {
		annuaire.put(adressIP, u1) ;
		return 0 ;
		} else {
			return -1 ;
		}
	}
	
	//Renvoie 0 si on a supprimé, -1 si il n'existe pas dans la liste.
	public int delAnnuaire(InetAddress address, User u1) {
		User u = annuaire.get(address) ;
		if (u!=null) {
			annuaire.remove(address) ;
			return 0 ;
		}
		else {
			return -1 ;
		}
	}
	
	public void modifyAnnuaire(InetAddress address, User u) {
		annuaire.replace(address.toString(), u) ;
	}
	
	public User getUser(String address) {
		return annuaire.get(address) ;
	}
	
	public String getIP(User u) {
		for (String ip : annuaire.keySet()) {
			if (annuaire.get(ip).equals(u)) {
				return ip ;
			}
		}
		return null ;
	}
	
	public HashMap<String, User> getAnnuaire() {
		return annuaire ;
	}
	
	public static Annuaire getInstance() {
		return instance;
	}
	
	public void printAnnuaire() {
		System.out.println(annuaire.toString()) ;
	}
	
	
	
	
}