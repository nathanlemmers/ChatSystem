package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.User;


// A AJOUTER https://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
public class DatabaseManager {
	static Connection con = null ;
	static Statement statement ;
	static ResultSet rs ;
	
	
	public void Setup() {
		String req = "CREATE TABLE login" + 
					"(id STRING not NULL, " +
					"mdp STRING not NULL) " ;
		try {
			statement = con.createStatement() ;
			statement.executeUpdate(req) ;
			statement.close();
			String query = "CREATE TABLE message" +
							"(user1 USER, user2 USER, date INT, contenu STRING not NULL)" ;
			statement=con.createStatement() ;
			statement.executeUpdate(query) ;
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void create() {
		try {
			Class.forName("A COMPLETER SERVEUR INSA");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		if (con==null) {
			con = DriverManager.getConnection("A COMPLETER") ;
		}
		else {
			con.close();
			con = DriverManager.getConnection("A COMPLETER") ;
		}
		Statement statement = con.createStatement() ;
		statement.setQueryTimeout(5);
		statement.close();
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(1);
		}
	}
	
	public ResultSet execute(String query) {
		ResultSet rs = null ;
		try {
		if (query.indexOf("SELECT")==-1) {
				statement = con.createStatement() ;
				statement.executeUpdate(query) ;
		}
		else {
			statement = con.createStatement() ;
			rs = statement.executeQuery(query);
			statement.close();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1) ;
		}
		return rs ;
		}
	
	
	//Cette fonction renvoie -1 si erreur, 0 si mauvais mot de passe et 1 si on se connecte
	public static int verifyLogin(String pseudo, String motdepasse) {
		int result = -1 ;
		try {
			statement= con.createStatement() ;
			String req = "SELECT id FROM login WHERE id= '" + pseudo + "'" ;
			rs = statement.executeQuery(req) ;
			
			if (rs.next()) {
				if (motdepasse.equals(rs.getString("mdp"))) {
					result=1 ;
					//Peut être accès à son profil, via user manager
				}
				else {
					System.out.println ("Mauvais mot de passe") ;
					result=0 ;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	
	
	//Renvoie -1 si erreur, 0 si User existe déjà, 1 si c'est bon
	public int NewUser (String pseudo, String motdepasse) {
		//Ajout d'un nouveau User
		int result =-1 ;
		try {
			String req = "SELECT id FROM login WHERE id= '" + pseudo + "'" ;
			statement=con.createStatement() ;
			rs = statement.executeQuery(req) ;
			if (rs.next()) {
				result = 0 ;
				System.out.println("Ce login est déjà utilisé");
				statement.close();
			}
			else {
			String query = "INSERT INTO login VALUES ('" + pseudo + "', '" + motdepasse + "')" ;
			statement = con.createStatement() ;
			statement.executeUpdate(query) ;
			System.out.println("Bienvenue au nouvel utilisateur !") ;
			result = 1 ; 
			statement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	
	//Renvoie -1 si erreur, 1 sinon
	//DATE A COMPLETER, CHANGER LE TYPE, ETC
	public int ArchivageMessage(User u1, User u2, String cont) {
		int result = -1 ;
		int date = 0 ;
		String query = "INSERT INTO message VALUES ('" + u1.GetID() + "', '" + u2.GetID() + "', '"+ String.valueOf(date) + "', '" + cont + "')" ;
		try {
			statement = con.createStatement() ;
			statement.executeUpdate(query) ;
			result = 1 ;
			statement.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	
	
	
}
