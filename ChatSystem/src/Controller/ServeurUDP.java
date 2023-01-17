package Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import Model.User;
import Model.Annuaire;

public class ServeurUDP implements Runnable {

	private static boolean running ; //A voir comment arreter le processus
	private DatagramSocket socket;
	private User u ;
	private ArrayList<InetAddress> local = new ArrayList<InetAddress>() ;
	
	public ServeurUDP(User u) {
//		try {
			this.u = u ;
			System.out.println(u) ;
//		} catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void run() {
		running = true ;
		
		try {
			System.out.println("AAAAAAAAAAA") ;
			socket = new DatagramSocket(4446) ;
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
		while (running) {
			byte[] buf = new byte[256];
			DatagramPacket packet  = new DatagramPacket(buf, buf.length);
			try {
				System.out.println("On attend le packet...") ;
				socket.receive(packet);
				InetAddress address = packet.getAddress() ;
				System.out.println("NOUVELLE ITERATION") ;
				int port = 4446 ;
				String receive = new String(packet.getData(),0, packet.getLength()) ;
				
//				Annuaire.addAnuaire(new User(receive)) ;
//				String monpseudo = new String(u.getPseudo().getBytes(), 0, 7) ;
				byte[] buf1 = u.getPseudo().getBytes() ;
				System.out.println("DDDDDDDDDD      "+ buf1.length ) ;
				DatagramPacket packet1 = new DatagramPacket(buf1, buf1.length, address, port) ;
				packet.setLength(7);
				System.out.println(receive) ;
				User add = Annuaire.getInstance().getAnnuaire().get(address.toString()) ;
				if (add==null ) {
					if (receive.equals(u.getPseudo())) {
						
					} else {
						socket.send(packet1);
					}
				}
				else {
					if (receive.equals(u.getPseudo()) || receive.equals(add.getPseudo())) {
						
					} else {
						socket.send(packet1);
					}
				}
				
				if (Annuaire.getInstance().getAnnuaire().get(address.toString())==null) {
					Annuaire.getInstance().addAnuaire(address.toString(), new User(receive)) ;
					System.out.println("ON RECOIT CA : " + receive) ;
				} else {
					System.out.println(receive) ;
					if(receive.equals("deconnexion")) {
						if (local.contains(address)) {
							System.out.println("fin") ;
							running = false ;
						} else {
							Annuaire.getInstance().delAnnuaire(address, Annuaire.getInstance().getUser(address.toString())) ;
							System.out.println("deconnexion") ;
							System.out.println(Annuaire.getInstance().getAnnuaire()) ;
						}
					} else {
						Annuaire.getInstance().modifyAnnuaire(address, new User(receive));
						System.out.println("changement de pseudo") ;
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		socket.close();
		System.out.println("on sort du while") ;
		
		
	}
	
	public void stop() {
		running =false ;
	}
	
	
}
