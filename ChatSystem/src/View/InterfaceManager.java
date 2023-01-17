package View;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controller.DatabaseManager;
import Controller.NetworkManager;
import Controller.ThreadManager;
import Controller.Connexion;
import Model.Annuaire;
import Model.User;

public class InterfaceManager extends JFrame {
    
	private JTextField newPseudo ;
    private JButton changerPseudo ;
	private JButton deconnexion ;
	
	private JButton cont ; //le bouton des contacts
	private int nbrContact = Annuaire.getInstance().size() ;
	private User contact_du_moment = null ;
	
	//partie envoie message
	private JTextField message ;
	private JButton send ;
	private NetworkManager net = new NetworkManager() ;
	private JButton refresh ;
	ArrayList<String> list = new ArrayList<>() ;
	
	private JTextField contenu ;
	private JPanel mess ;
	private JScrollPane scroll = new JScrollPane();
	private static String me ;
	
	private JPanel contact ;
	
	public InterfaceManager(String pseudo, String mdp) {
		
//		Image background = Toolkit.getDefaultToolkit().createImage(s"./Image/Background.png");
//		ImagePanel img = new ImagePanel( new ImageIcon ("./Image/2926475d12521038.png").getImage()) ;
		this.me = pseudo ;
		this.setTitle("Clavardage");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.newPseudo = new JTextField(15) ;
		newPseudo.setMaximumSize(newPseudo.getPreferredSize());
		this.changerPseudo = new JButton("Changer votre pseudo") ;
		this.deconnexion = new JButton("Me deconnecter") ;
		
		JPanel sousPanel0 = new JPanel();
		sousPanel0.setOpaque(false);
		sousPanel0.setLayout(new BoxLayout(sousPanel0, BoxLayout.LINE_AXIS));
		
		sousPanel0.add(newPseudo) ;
		sousPanel0.add(Box.createVerticalStrut(10));
		sousPanel0.add(changerPseudo);
		sousPanel0.add(Box.createVerticalStrut(10));
		sousPanel0.add(deconnexion) ;
		sousPanel0.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel finalPanel = new JPanel();
		finalPanel.setBackground(Color.decode("#afeeee"));
		finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.PAGE_AXIS));
		
		
		// Au dessus c'est l'entête, là on rentre dans le corps
		
		contact = new JPanel();
		contact.setLayout(new BoxLayout(contact, BoxLayout.LINE_AXIS));
		contact.setOpaque(false);
		contact.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		for (int i=0; i<nbrContact ; i++) {
			String nom = new String (Annuaire.getInstance().getListUser(i)) ;
			this.cont =  new JButton(nom) ;
			contact.add(cont) ;
			System.out.println("On passe par là") ;
		}
		
		mess = new JPanel() ;
		mess.setOpaque(false);
		mess.setLayout(new BoxLayout(mess, BoxLayout.PAGE_AXIS));
		scroll = new JScrollPane(mess) ;
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setMaximumSize(newPseudo.getPreferredSize());
		
		JPanel envoi = new JPanel() ;
		envoi.setLayout(new BoxLayout(envoi, BoxLayout.LINE_AXIS));
		envoi.setOpaque(false);
		
		this.send = new JButton("Envoyer") ;
		this.message = new JTextField(100) ;
		message.setMaximumSize(newPseudo.getPreferredSize());
		this.refresh = new JButton ("Refresh") ;
		envoi.add(message) ;
		envoi.add(send) ;
		envoi.add(refresh) ;
		
		
//		JPanel mess = new JPanel();
//		mess.setOpaque(false);
//		mess.setLayout(new BoxLayout(mess, BoxLayout.PAGE_AXIS));
//		contenu = new JTextField("") ;
//		contenu.setMaximumSize(newPseudo.getPreferredSize());
//		mess.add(contenu) ;
		
		
		finalPanel.add(sousPanel0);
		finalPanel.add(Box.createVerticalStrut(10)) ;
 		finalPanel.add(contact) ;
 		finalPanel.add(Box.createVerticalStrut(10)) ;
 		finalPanel.add(Box.createVerticalGlue()) ;
 		finalPanel.add(scroll) ;
 		finalPanel.add(envoi) ;
 		finalPanel.add(Box.createVerticalStrut(15)) ;

// 		finalPanel.add(mess) ;
		
		this.add(finalPanel);
		this.setVisible(false) ;	
		
		deconnexion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				Connexion.Deconnexion(me, mdp);
				new InterfaceConnexion().showFrame();
				dispose() ;
			}
		}) ;
		
		changerPseudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String newpseudo = newPseudo.getText() ;
				if (newpseudo==null) {
					JOptionPane.showMessageDialog(null, "Vous devez écrire un nouveau pseudo", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					int test = Connexion.ChangerPseudo(me, mdp, newpseudo) ;
					me = newpseudo ;
					System.out.println(test) ;
				}
				
			}
		}) ;
		
		
		cont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("AAAAAAAAAA") ;
				if (mess==null) {
					mess = new JPanel() ;
				}
				else {
					mess.removeAll() ;
				}
				mess.setOpaque(false);
				mess.setLayout(new BoxLayout(mess, BoxLayout.PAGE_AXIS));
				contact_du_moment = new User(cont.getText()) ;
				System.out.println(Annuaire.getInstance().getIP(contact_du_moment)) ;
				System.out.println(Annuaire.getInstance().annuaire);
				
				list = DatabaseManager.getMessage(contact_du_moment, new User("me")) ;
				
				for (int i= 0; i<list.size() ; i++) {
					contenu = new JTextField(list.get(i)) ;
					contenu.setMaximumSize(newPseudo.getPreferredSize());
					mess.add(contenu) ;
					mess.add(Box.createVerticalStrut(5)) ;
					System.out.println(list.get(i)) ;
				}
				showFrame();
			}
		});
		
		
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				contact.removeAll() ;
				contact.setLayout(new BoxLayout(contact, BoxLayout.LINE_AXIS));
				contact.setOpaque(false);
				contact.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				nbrContact = Annuaire.getInstance().size() ;
				
				for (int i=0; i<nbrContact ; i++) {
					System.out.println(Annuaire.getInstance().getListUser(i)) ;
					String nom = new String (Annuaire.getInstance().getListUser(i)) ;
					cont =  new JButton(nom) ;
					cont.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("AAAAAAAAAA") ;
							if (mess==null) {
								mess = new JPanel() ;
							}
							else {
								mess.removeAll() ;
							}
							mess.setOpaque(false);
							mess.setLayout(new BoxLayout(mess, BoxLayout.PAGE_AXIS));
							contact_du_moment = new User(cont.getText()) ;
							System.out.println(Annuaire.getInstance().getIP(contact_du_moment)) ;
							System.out.println(Annuaire.getInstance().annuaire);
							
							list = DatabaseManager.getMessage(contact_du_moment, new User("me")) ;
							
							for (int i= 0; i<list.size() ; i++) {
								contenu = new JTextField(list.get(i)) ;
								contenu.setMaximumSize(newPseudo.getPreferredSize());
								mess.add(contenu) ;
								mess.add(Box.createVerticalStrut(5)) ;
								System.out.println(list.get(i)) ;
							}
							showFrame();
						}
					});
					contact.add(cont) ;
					System.out.println("BBB" +nbrContact) ;
				}
				if (mess!= null ) {
					mess.removeAll() ;
				} else {
					mess = new JPanel();
				}
				mess.setOpaque(false);
				mess.setLayout(new BoxLayout(mess, BoxLayout.PAGE_AXIS));
				list = DatabaseManager.getMessage(contact_du_moment, new User(me)) ;
				for (int i= 0; i<list.size() ; i++) {
					contenu = new JTextField(list.get(i)) ;
					contenu.setMaximumSize(newPseudo.getPreferredSize());
					mess.add(contenu) ;
					mess.add(Box.createVerticalStrut(5)) ;
					System.out.println(list.get(i)) ;
				}
				showFrame();
				System.out.println(Annuaire.getInstance().getListe()) ;
				System.out.println(Annuaire.getInstance().getAnnuaire()) ;
				System.out.println(DatabaseManager.getMessage(new User(me), contact_du_moment)) ;
			}
		});
		
		
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mess = message.getText() ;
				if (contact_du_moment==null) {
					JOptionPane.showMessageDialog(null, "Vous devez choisir un contact", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else if (mess.equals("")) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas écrit de message", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else {
					net.sendMessage(contact_du_moment, mess) ;
					System.out.println("CCCCCC") ;
					System.out.println(DatabaseManager.getMessage(contact_du_moment, new User(me))) ;
				}		
			}
		});
	}
	
	public void showFrame() {
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public static String getMe() {
		return me ;
	}
	
	
	public static void main(String[] args) {	
		DatabaseManager.create() ;
		DatabaseManager.reset() ;
		DatabaseManager.Setup() ;
		DatabaseManager.NewUser("Nicolas", "prolol") ;
		DatabaseManager.NewUser("Shivaree", "Inox") ;
		DatabaseManager.NewUser("n", "n") ;
		new InterfaceConnexion().showFrame();
	}
	
	
	
	
	

}
