package View;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.DatabaseManager;
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
	
	public InterfaceManager(String pseudo, String mdp) {
		
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
		
		JPanel contact = new JPanel();
		contact.setLayout(new BoxLayout(contact, BoxLayout.PAGE_AXIS));
		contact.setOpaque(false);
		contact.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		System.out.println(nbrContact) ;
		System.out.println(Annuaire.getInstance().getListe()) ;
		for (int i=0; i<nbrContact ; i++) {
			this.cont =  new JButton(Annuaire.getInstance().getListUser(i)) ;
			contact.add(cont) ;
			System.out.println("On passe par là") ;
		}
	
		
		finalPanel.add(sousPanel0);
		finalPanel.add(Box.createVerticalStrut(10)) ;
 		finalPanel.add(contact) ;
		
		this.add(finalPanel);
		this.setVisible(false) ;	
		
		deconnexion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				Connexion.Deconnexion(pseudo, mdp);
				new InterfaceConnexion().showFrame();
				dispose() ;
			}
		}) ;
		
		changerPseudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String newpseudo = newPseudo.getText() ;
				if (newpseudo==null) {
					JOptionPane.showMessageDialog(null, "Vous devez écrire un nouveau pseudo", "Erreur", JOptionPane.INFORMATION_MESSAGE);;
				} else {
					int test = Connexion.ChangerPseudo(pseudo, mdp, newpseudo) ;
					System.out.println(test) ;
				}
				
			}
		}) ;
	}
	
	public void showFrame() {
		this.setVisible(true);
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
