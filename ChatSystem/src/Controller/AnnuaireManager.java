package Controller;
import Model.User ;
import java.util.ArrayList;

public class AnnuaireManager {

	private ArrayList<User> ListUser = new ArrayList<User>() ;
	
	
	public void addUser(User u) {
		ListUser.add(u) ;
	}
	
	public int removeUser(User u) {
		if (ListUser.indexOf(u)==-1) {
			ListUser.remove(u) ;
			return 0 ;
			}
		else return -1 ;
		}
	
	public ArrayList<User> getAnnuaire() {
		return ListUser ;
	}
	
	
	
	
}
