package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Model.Conversation;
import Model.User;

public class ServeurTCP implements Runnable {
	
	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private User u ;
    private int port ;
    
    public ServeurTCP (int p, User u) {
    	this.u = u ;
    	this.port = p ;
    }
    
    public void run() {
    	this.u = u ;
    	boolean running = true ;
    	String greeting = null ;
    	while(running) {
        try {
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        System.out.println("Serveur en ecoute") ;
	        greeting = in.readLine();
	        System.out.println(greeting) ;
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
                Conversation.getInstance().addMessage(u, u.toString()+ " : " + greeting) ;
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
	
