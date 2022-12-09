package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
				clientSocket = new Socket(ip, 4000);
				out = new PrintWriter(clientSocket.getOutputStream(), true);
		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        String receive = sendMessage(String.valueOf(NetworkManager.p+1), 4000) ;
		        System.out.println(receive) ;
		        NetworkManager.p ++ ;
		        if (receive=="erreur") {
		        	System.out.println("Error") ;
		        	stopConnection();
		        	return 0 ;
		        } else {
		        	port = Integer.parseInt(receive) ;
		        	return port ;
		        }
		        
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return -1  ;
	        }

	    public String sendMessage(String msg, int p) {
	    	try {
	    		System.out.println(p) ;
				clientSocket = new Socket(ip, p);
				out = new PrintWriter(clientSocket.getOutputStream(), true);
		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        out.println(msg);
		        System.out.println("erreur sur le send") ;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	        String resp = null;
			try {
				resp = in.readLine();
				System.out.println("J'arrive là quand même") ;
				System.out.println(resp) ;
				if (resp=="recu") {
					//Mauvais user ici, ça doit être nous même. A voir comment faire
					Conversation.getInstance().addMessage(u, u.toString()+ " : " + msg) ;
				}
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
	
	
	
