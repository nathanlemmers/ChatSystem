package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Model.Annuaire;
import Model.User;

public class NetworkManager {
	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private User u ;
    public static int p = 1025 ;
	
	public void start() throws IOException {
    	while(Connexion.etat==1) {
    	
        serverSocket = new ServerSocket(4000);
        clientSocket = serverSocket.accept();
        System.out.println("debut start") ;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("En attente de message") ;
        String greeting = in.readLine();
        System.out.println(greeting) ;
            if ("new".equals(greeting)) {
                out.println(p);
                p = p+1 ;
                new Thread(new ServeurTCP(p, u)).start() ;
                System.out.println("Thread créé") ;
                //Pour test :
                Connexion.etat =  0;
            }
            else {
                out.println("erreur");
            }
    	}
    	stop() ;
    }

    public void stop() {
        try {
			in.close();
			out.close();
	        clientSocket.close();
	        serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // O si erreur, 1 si tout va bien
    public int sendMessage(User u, String msg) {
    	ClientTCP Client = new ClientTCP() ; 
    	System.out.println("debut connexion") ;
    	int port = Client.startConnection(u) ;
    	System.out.println(port) ;
    	if (port==1) {
    		Client.sendMessage(msg, port) ;
    		return 1 ;
    	} else {
    		System.out.println("Erreur au send") ;
    		return 0 ;
    	}
    }
    
    
    public static void main (String args[]) {
    	NetworkManager net = new NetworkManager() ;
    	User me = new User("me") ;
    	Annuaire.getInstance().addAnuaire("10.1.5.78", me) ;
    	try {
    		System.out.println("commençons") ;
			net.start() ;
			net.sendMessage(me, "bonjour") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
