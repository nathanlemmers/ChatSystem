package Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;

import Model.User;
import Model.Annuaire;

public class ServeurUDP implements Runnable {

	private boolean running ; //A voir comment arreter le processus
	private DatagramSocket socket;
	private User u ;
	private ArrayList<InetAddress> local = new ArrayList<InetAddress>() ;
	
	public ServeurUDP(User u) {
		try {
			this.u = u ;
			socket = new DatagramSocket(4446) ;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		running = true ;
		try {
			Enumeration<NetworkInterface> ntw = NetworkInterface.getNetworkInterfaces() ;
			while (ntw.hasMoreElements()) {
				for (InterfaceAddress intA : ntw.nextElement().getInterfaceAddresses()) 
					if (intA.getAddress().isSiteLocalAddress()) {
						local.add(intA.getAddress()) ;
					}
			}
			System.out.println(local) ;
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println();
		while (running) {
			byte[] buf = new byte[256];
			DatagramPacket packet  = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
				InetAddress address = packet.getAddress() ;
				System.out.println(address) ;
				int port = packet.getPort() ;
				String receive = new String(packet.getData(),0, packet.getLength()) ;
				if (Annuaire.getInstance().getAnnuaire().get(address.toString())==null) {
					Annuaire.getInstance().addAnuaire(address.toString(), new User(receive)) ;
					System.out.println("un nouveau") ;
				} else {
					System.out.println(receive) ;
					if(receive.equals("deconnexion")) {
						if (local.contains(address)) {
							System.out.println("fin") ;
							running = false ;
						} else {
							Annuaire.getInstance().delAnnuaire(address, Annuaire.getInstance().getUser(address)) ;
							System.out.println("deconnexion") ;
						}
					} else {
						Annuaire.getInstance().modifyAnnuaire(address, new User(receive));
						System.out.println("changement de pseudo") ;
					}
				}
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
