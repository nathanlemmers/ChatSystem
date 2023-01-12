package Model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class Annuaire {
	
	public HashMap<String, User> annuaire ;
	private static final Annuaire instance = new Annuaire();
	private ArrayList<String> liste = new ArrayList<String>() ;
	
	private Annuaire() {
		annuaire = new HashMap<String,User>() ;
	}
	
	//0 si ok, -1 sinon
	public int addAnuaire(String adressIP, User u1) {
		User u = annuaire.get(adressIP) ;
		if (u==null) {
		annuaire.put(adressIP, u1) ;
		liste.add(u1.getPseudo()) ;
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
			liste.remove(u1.getPseudo()) ;
			return 0 ;
		}
		else {
			return -1 ;
		}
	}
	
	public void modifyAnnuaire(InetAddress address, User u) {
		liste.remove(annuaire.get(address.toString()).getPseudo() ) ;
		annuaire.replace(address.toString(), u) ;
		liste.add(u.getPseudo()) ;
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
	
	
	public int size() {
		return annuaire.size() ;
	}
	
	public String getListe() {
		return liste.toString() ;
	}
	
	public String getListUser(int i) {
		return liste.get(i) ;
	}
	
	
}