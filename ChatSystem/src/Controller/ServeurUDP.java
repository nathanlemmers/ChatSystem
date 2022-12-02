package Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import Model.User;
import Model.Annuaire;

public class ServeurUDP implements Runnable {

	private boolean running ; //A voir comment arreter le processus
	private DatagramSocket socket;
	private byte[] buf = new byte[256];
	private User u ;
	
	
	public ServeurUDP(User u) {
		try {
			this.u = u ;
			socket = new DatagramSocket(4445) ;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		running = true ;
		
		while (running) {
			DatagramPacket packet  = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
				InetAddress address = packet.getAddress() ;
				int port = packet.getPort() ;
				String receive = new String(packet.getData(),0, packet.getLength()) ;
				System.out.println(receive);
				if (receive.equals("Nouveau")) {
					System.out.println("fin") ;
					running = false ;
				}
				Annuaire.getInstance().addAnuaire(new User(receive)) ;
//				Annuaire.addAnuaire(new User(receive)) ;
				
				buf = u.GetPseudo().getBytes() ;
				packet = new DatagramPacket(buf, buf.length, address, port) ;
				socket.send(packet);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
}
