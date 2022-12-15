package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Model.Annuaire;
import Model.Conversation;
import Model.User;

public class NetworkManager {
	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private User u = new User("me");
    public static int p = 1025 ;
	
	public void start() throws IOException {
		
		String greeting = null ;
    	while(Connexion.etat==1) {
    		try {
    			serverSocket = new ServerSocket(6667);
    			clientSocket = serverSocket.accept();
    	        out = new PrintWriter(clientSocket.getOutputStream(), true);
    	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	        System.out.println("Serveur ecoute sur port : 6667") ;
    	        greeting = in.readLine();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		 	System.out.println("Message recu : " +greeting) ;
            if (greeting.equals("new")) {
            	p = p+1 ;
                out.println(p);
                System.out.println("Thread créé") ;
                new Thread(new ServeurTCP(p, u)).start() ;
                //Pour test :
                Connexion.etat =  0;
            }
            else {
            	System.out.println("On a pas recu le bon message") ;
                out.println("erreur");
                Connexion.etat =0 ;
            }
    	}
    	System.out.println("On s'arrete") ;
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
    	System.out.println("On trouve comme port de destination : " + port) ;
    	if (port!=-1) {
    		String validation = Client.sendMessage(msg, port) ;
    		if(validation.equals("recu")) {
    		return 1 ;
    		}
    	} else {
    		System.out.println("Erreur au send") ;;
    	}
    	return 0 ;
    }
    
    
    public static void main (String args[]) {
    	NetworkManager net = new NetworkManager() ;
    	User u = new User("me") ;
    	Annuaire.getInstance().addAnuaire("10.1.5.78", u) ;
    	try {
    		
			net.start() ;
			Conversation.getInstance().printConversation() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
