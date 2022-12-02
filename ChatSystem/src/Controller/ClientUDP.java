package Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Model.Annuaire;
import Model.User;

public class ClientUDP {
	private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;
    private User u ;
	
    public ClientUDP(User u) {
    	try {
    		this.u = u ;
			socket = new DatagramSocket() ;
			address = InetAddress.getByName("localhost") ;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    public void sendUser () {
    	try {
    		String pseudo = u.GetPseudo() ;
     		buf = pseudo.getBytes() ;
        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445) ;
			socket.send(packet);
			packet = new DatagramPacket(buf, buf.length) ;
			socket.receive(packet);
			String receive = new String (packet.getData(), 0, packet.getLength()) ;
			//Pour test
			System.out.println(receive) ;
			//
			Annuaire.getInstance().addAnuaire(new User(receive)) ;
			//Annuaire.addAnuaire(new User(receive)) ;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    }
    
    public void close() {
    	socket.close();
    }
    
    
	
}
