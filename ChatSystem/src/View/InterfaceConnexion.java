package View;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import Controller.DatabaseManager;
import Controller.ThreadManager;
import Model.User;

public class InterfaceConnexion extends JFrame {

	private JLabel log;
	private JLabel pw;
	private JLabel bienvenue ;
	private JButton connexionB;
	private JButton newConnexion;
	private JTextField logIn;
	private JPasswordField pwF;

	// constructeur
	public InterfaceConnexion() {
		this.setTitle("Authentification");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setOpaque(false);
		
		this.bienvenue = new JLabel("Bienvenue dans votre application de clavardage !") ;
		this.log = new JLabel("Pseudo :") ;
		this.logIn = new JTextField(15) ;
		this.pw = new JLabel("Mot de passe :") ;
		this.pwF = new JPasswordField(15) ;
		this.connexionB = new JButton("Me connecter");
		this.newConnexion = new JButton("S'inscrire");
		
		JPanel sousPanel0 = new JPanel();
		sousPanel0.setOpaque(false);
		JPanel sousPanel1 = new JPanel();
		sousPanel1.setOpaque(false);
		JPanel sousPanel2 = new JPanel();
		sousPanel2.setOpaque(false) ;
		JPanel sousPanel3 = new JPanel();
		sousPanel3.setOpaque(false) ;
		
		sousPanel0.setLayout(new BoxLayout(sousPanel0, BoxLayout.LINE_AXIS));
		sousPanel1.setLayout(new BoxLayout(sousPanel1, BoxLayout.LINE_AXIS));
		sousPanel2.setLayout(new BoxLayout(sousPanel2, BoxLayout.LINE_AXIS));
		sousPanel3.setLayout(new BoxLayout(sousPanel3, BoxLayout.LINE_AXIS));
		
		sousPanel0.add(bienvenue) ;
		
		sousPanel1.add(log) ;
		sousPanel1.add(Box.createHorizontalStrut(10));
		sousPanel1.add(logIn) ;
		
		sousPanel2.add(pw) ;
		sousPanel2.add(Box.createHorizontalStrut(10));
		sousPanel2.add(pwF) ;
		
		sousPanel3.add(connexionB) ;
		sousPanel3.add(Box.createHorizontalStrut(10));
		sousPanel3.add(newConnexion) ;
		
		
		panel.add(sousPanel0) ;
		panel.add(Box.createVerticalStrut(30));
		panel.add(sousPanel1);
		panel.add(Box.createVerticalStrut(10));
		panel.add(sousPanel2) ;
		panel.add(Box.createVerticalStrut(10));
		panel.add(sousPanel3) ;
		
		JPanel finalPanel = new JPanel();
		finalPanel.setBackground(Color.decode("#afeeee"));
		finalPanel.setLayout(new GridBagLayout());
		finalPanel.add(panel);
		
		this.add(finalPanel);
		
		this.setVisible(false);
		
		connexionB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				// on r�cup�re ce que l'user tape dans les champs
				String log = logIn.getText();
				String mdp = String.valueOf(pwF.getPassword());
				pwF.addActionListener(this);


				int eq = -1;

				try {
					DatabaseManager DM = new DatabaseManager();

					eq = DM.verifyLogin(log, mdp);

				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					// si erreur inconnue lors de l'execution
					JOptionPane.showMessageDialog(null, "Catch erreur inconnue", "Connexion", JOptionPane.ERROR_MESSAGE);
				}
				// Soit le mot de passe ou l'id est incorrect
				// Soit le compte n'exitse pas dans ce cas, on le cr�e

				if (eq == 0) {
					// si verifyLogin renvoie 0 c'est une erreur de correspondance
					JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect", "Connexion", JOptionPane.ERROR_MESSAGE);
				}
				else if (eq == 1) {
					// connexion autoris�e, mdp correct
					JOptionPane.showMessageDialog(null, "Connexion autorisée", "Connexion", JOptionPane.INFORMATION_MESSAGE);
					ThreadManager.newConnexion(new User(log)) ;
				} else if (eq == 2) {
					JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect", "Connexion", JOptionPane.INFORMATION_MESSAGE);;
				} else if (eq == -1) {
					JOptionPane.showMessageDialog(null, "Erreur serveur", "Connexion", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}) ;
		
		newConnexion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String log = logIn.getText();
				String mdp = String.valueOf(pwF.getPassword());
				pwF.addActionListener(this);
				
				int eq= DatabaseManager.NewUser(log, mdp) ;
				if (eq == 1) {
					JOptionPane.showMessageDialog(null, "Nouvel utilisateur créé", "Connexion", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (eq==0) {
					JOptionPane.showMessageDialog(null, "Pseudo déjà utilisé", "Connexion", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(eq==-1) {
					JOptionPane.showMessageDialog(null, "Erreur serveur", "Connexion", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}) ;

	}
	
	
	
	
	private void showFrame() {
		this.setVisible(true);
	}

	public static void main(String[] args) {
		DatabaseManager.create() ;
		DatabaseManager.reset() ;
		DatabaseManager.Setup() ;
		DatabaseManager.NewUser("Nicolas", "prolol") ;
		DatabaseManager.NewUser("Shivaree", "Inox") ;
		
		new InterfaceConnexion().showFrame();
	}

}
