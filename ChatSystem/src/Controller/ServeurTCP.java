package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Model.Annuaire;
import Model.Message;
import Model.User;
import View.InterfaceManager;

public class ServeurTCP implements Runnable {
	
	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private User u ;
    private int port ;
    private User envoyer ;
    
    public ServeurTCP (int p, User u) {
    	this.u = u ;
    	this.port = p ;
    }
    
    public void run() {
    	boolean running = true ;
    	String greeting = null ;
    	while(running) {
        try {
        	System.out.println("On est dans le serveur") ;
			serverSocket = new ServerSocket(port);
			System.out.println("Le port d'ecoute est : " + port) ;
			clientSocket = serverSocket.accept();
			System.out.println("On passe le accept") ;
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        System.out.println("Serveur en ecoute") ;
	        greeting = in.readLine();
	        envoyer = Annuaire.getInstance().getUser(clientSocket.getInetAddress().toString());
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            if ("deconnexion".equals(greeting)) {
                out.println("deconnexion");
                running = false ;
                stop() ;
            }
            else {
                out.println("recu");
                System.out.println(greeting) ;
                System.out.println(u) ;
//                Conversation.getInstance().addMessage(envoyer, envoyer.toString()+ " : " + greeting) ;
                System.out.println("Archivage dans serveur") ;
                DatabaseManager.ArchivageMessage(new Message(envoyer, new User(InterfaceManager.getMe()), greeting)) ;
				System.out.println(DatabaseManager.getMessage(envoyer, new User("me"))) ;
                stop() ;
//                Conversation.getInstance().printConversation() ;
            }
    	}
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
    
}
	
