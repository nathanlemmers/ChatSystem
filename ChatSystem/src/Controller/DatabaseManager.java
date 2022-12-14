package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Message;

public abstract class DatabaseManager {
	static Connection con = null ;
	static Statement statement ;
	static ResultSet rs ;
	static int new_id ;
	
	
	public static void Setup() {
		
		String req = "CREATE TABLE IF NOT EXISTS login " + 
					"(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					" pseudo VARCHAR(255), " +
					" mdp VARCHAR(255))";
		try {
			statement = con.createStatement() ;
			statement.executeUpdate(req) ;
			statement.close();
			String query = "CREATE TABLE IF NOT EXISTS message" +
							"(user1 VARCHAR(255), user2 VARCHAR(255), date TIMESTAMP, contenu VARCHAR(255), PRIMARY KEY ( user1, user2, date ))" ;
			statement=con.createStatement() ;
			statement.executeUpdate(query) ;
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void create() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		if (con==null) {
			con = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_java4ir_001","tp_java4ir_001","vaX3ahxu") ;
		}
		else {
			con.close();
			con = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_java4ir_001\",\"tp_java4ir_001\",\"vaX3ahxu") ;
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
	
	public static ResultSet execute(String query) {
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
	
	
	public static void reset() {
		execute("DROP TABLE login") ;
		execute("DROP TABLE message") ;
	}
	
	//Cette fonction renvoie -1 si erreur, 0 si mauvais mot de passe et 1 si on se connecte, 2 si mauvais pseudo
	public static int verifyLogin(String pseudo, String motdepasse) {
		int result = -1 ;
		try {
			statement= con.createStatement() ;
			String req = "SELECT mdp FROM login WHERE pseudo= '" + pseudo + "'" ;
			ResultSet rs = statement.executeQuery(req) ;
			if (rs.next()) {
				if (motdepasse.equals(rs.getString("mdp"))) {
					result=1 ;
					System.out.println("Mot de passe correct");
					//Peut ??tre acc??s ?? son profil, via user manager
				}
				else {
					System.out.println ("Mauvais mot de passe") ;
					result=0 ;
				}
			} else {
				System.out.println("Pseudo inconnu.") ;
				result = 2 ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	
	
	//Renvoie -1 si erreur, 0 si User existe d??j??, 1 si c'est bon
	public static int NewUser (String pseudo, String motdepasse) {
		//Ajout d'un nouveau User
		int result =-1 ;
		try {
			String req = "SELECT pseudo FROM login WHERE pseudo= '" + pseudo + "'" ;
			statement=con.createStatement() ;
			rs = statement.executeQuery(req) ;
			if (rs.next()) {
				result = 0 ;
				System.out.println("Ce login est d??j?? utilis??");
				statement.close();
			}
			else {
			String query = "INSERT INTO login (pseudo, mdp) VALUES ('" + pseudo + "', '" + motdepasse + "')" ;
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
	public static int ArchivageMessage(Message message) {
		int result = -1 ;
		String query = "INSERT INTO message VALUES ('" + message.getEmetteur() + "', '" + message.getReceveur() + "', '"+ message.getDate() + "', '" + message.getContenu() + "')" ;
		try {
			statement = con.createStatement() ;
			statement.executeUpdate(query) ;
			result = 1 ;
			statement.close() ;
			System.out.println("Message Archiv??") ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	
	//1 si on change le pseudo, 0 sinon
	public static int ChangerPseudo (String AncienPseudo, String mdp, String NewPseudo) {
		if (verifyLogin(AncienPseudo, mdp)==1) {
			String query = "UPDATE login SET pseudo = '"+NewPseudo+"' WHERE pseudo = '" + AncienPseudo +"'" ;
			try {
				statement = con.createStatement() ;
				statement.executeUpdate(query) ;
				statement.close() ;
				System.out.println("Pseudo modifi?? avec succ??s.") ;
				return 1 ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0 ;
			}
		} else {
			System.out.println("Impossible de changer le pseudo.") ;
			return 0 ;
		}
	}
	
	
	public static int ChangerMdp (String Pseudo, String AncienMdp, String NewMdp) {
		if (verifyLogin(Pseudo, AncienMdp)==1) {
			String query = "UPDATE login SET mdp = '"+NewMdp+"' WHERE pseudo = '" + Pseudo +"'" ;
			try {
				statement = con.createStatement() ;
				statement.executeUpdate(query) ;
				statement.close() ;
				System.out.println("Mot de passe modifi?? avec succ??s.") ;
				return 1 ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0 ;
			}
		} else {
			System.out.println("Impossible de changer le mot de passe.") ;
			return 0 ;
		}
	}
	
	public static void main (String args[]) {
		create() ;
		reset() ;
		Setup() ;
		NewUser("Nicolas", "prolol") ;
		NewUser("Shivaree", "Inox") ;
		ChangerMdp("Nicolas", "prolol","prolog") ;
		}
	
}
