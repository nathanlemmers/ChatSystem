package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Model.Annuaire;
import Model.Conversation;
import Model.User;

public class ClientTCP {

	    private Socket clientSocket;
	    private PrintWriter out;
	    private BufferedReader in;
	    private User u ;
	    private int port ;
	    private String ip ;

	    
	    // -1 si erreur catch,  0 si connexion impossible, 1 sinon. COnnexion faite sur le port 4000 
	    public int startConnection(User u) {
	        try {
	        	this.u = u ;
	        	System.out.println("Connexion au serveur principal") ;
	        	ip =  Annuaire.getInstance().getIP(u) ;
	        	System.out.println(ip) ;
				clientSocket = new Socket(ip, 6667);
				out = new PrintWriter(clientSocket.getOutputStream(), true);
		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        out.println("new");
		        String PortServeurTCP = in.readLine();
				System.out.println(PortServeurTCP) ;
		        if (PortServeurTCP.equals("erreur")) {
		        	System.out.println("Error") ;
		        	stopConnection();
		        	return 0 ;
		        } else {
		        	System.out.println("bon debut") ;
		        	stopConnection();
		        	return Integer.valueOf(PortServeurTCP) ;
		        }
		        
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block$
				System.out.println("Erreur unknown catch ClientTCP") ;
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur catch ClientTCP") ;
				e.printStackTrace();
			}
	        return -1  ;
	        }

	    public String sendMessage(String msg, int p) {
	    	System.out.println("Port d'envoi : " + p + " IP d'envoi : " + ip) ; 
	        String resp = null;
			try {
				JOptionPane.showMessageDialog(null, "Attente", "attente", JOptionPane.INFORMATION_MESSAGE, null);
				clientSocket = new Socket(ip, p);
				out = new PrintWriter(clientSocket.getOutputStream(), true);
		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        out.println(msg);
				resp = in.readLine();
				System.out.println(resp) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return resp;
	    }

	    public void stopConnection() {
	        try {
				in.close();
				out.close();
		        clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
	
	
	
