package View;

	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;

	import Controller.DatabaseManager;

	public class InterfaceConnexion {
	    
	    JPanel interfacePanel;
	    JFrame interfaceFrame;
	    JLabel log; JLabel pw; JLabel authen; JLabel mess; JLabel errormess; JLabel connectedmess;
	    JButton connexionB; JButton pseudoB;
	    JTextField logIn; JTextField pwd; JTextField pseudoField;
	    JPasswordField pwF;
	    
	    //constructeur
	    public InterfaceConnexion() {
	    
	  
	    interfacePanel = new JPanel(null);
	    interfaceFrame = new JFrame("Authentification");
	    
	           //affichage fenêtre
	    interfaceFrame.setVisible(true);
	    interfacePanel.setBackground(Color.blue);
	    interfacePanel.setOpaque(false); //car sinon par defaut c'est transparent
	    
	    
	    
	    interfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    interfaceFrame.setLayout(null);
	    
	 
	    authen = new JLabel("Authentification");
	    interfaceFrame.getContentPane().add(authen);
	    log = new JLabel("Pseudo :");
	    log.setBounds(200,260,200,40);
	    log.setForeground(Color.WHITE);
	    
	    pw = new JLabel("Mot de passe :");
	    pw.setBounds(200, 360, 200, 40);
	    pw.setForeground(Color.WHITE);
	    
	    errormess.setForeground(Color.WHITE);
	    
	    //bouton de connexion
	    connexionB = new JButton("Me connecter");
	    pseudoB = new JButton("Changer mon pseudo");
	    
	    //champs de connexion à remplir par l'user
	    logIn = new JTextField(); 
	    pwd = new JTextField();
	    
	    

	    //On ajoute au frame
	    interfaceFrame.add(connexionB);
	    interfaceFrame.add(authen);
	    interfaceFrame.add(pw);
	    interfaceFrame.add(log);
	    interfaceFrame.add(logIn);
	    interfaceFrame.add(pwd);
	    
	    mess.setForeground(Color.ORANGE);
	    
	    
	    //lier l'action de s'enregistrer au bouton 
	    
	    pwF = new JPasswordField();
	    errormess = new JLabel();
	    
	   
	    connexionB.addActionListener (new ActionListener () {
	        
	        
	        public void actionPerformed(ActionEvent event) {
	            
	            //on récupère ce que l'user tape dans les champs
	            String log = logIn.getText(); 
	            String mdp = String.valueOf(pwF.getPassword()); 
	            pwF.addActionListener(this);
	            
	            //On initialise les deux messages de connexion et d'erreur
	            mess.setText("");
	            connectedmess.setText("");
	            
	       
	            int eq = - 1;
	            
	            try {
	                //Recherche dans la base de données si la correspondance login/mdp est bonne
	                eq = DatabaseManager.verifyLogin(log,mdp);
	                
	                //Affichage du resultat de verfyLogin pour tester
	                System.out.println("Résultat de la recherche dans la base de données : " + Integer.toString(eq));
	            
	            } catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
	                // si erreur inconnue lors de l'execution
	                errormess.setText("Erreur inconnue lors de la connexion!");
	            }
	            //Soit le mot de passe ou l'id est incorrect 
	            //Soit le compte n'exitse pas dans ce cas, on le crée 
	            
	            if (eq == 0) {
	                //si verifyLogin renvoie 0 c'est une erreur de correspondance
	                errormess.setText("Erreur d'authentification, identifiant ou mot de passe incorrect");
	                connexionB.setText("Connexion failed");
	            } else {
	                    //Soit je me connecte soit je crée un nouvel user
	                    if (eq == 1) {
	                     //connexion autorisée, mdp correct
	                     connexionB.setText("CONNECTE !"); 
	                    } else {
	                        // cas à traiter ? ou non ? 
	                        errormess.setText("Identifiant inconnu");
	                    }
	                    connectedmess.setText("Connexion réussie, veuillez choisir un pseudo :");
	                    interfacePanel.add(pseudoField);
	                    interfacePanel.add(pseudoB);
	                    connexionB.setEnabled(false);
	                    logIn.setEnabled(false);
	                    pwd.setEnabled(false);
	                    
	               }
	            
	            //permet de remettre à jour la Frame 
	            interfaceFrame.revalidate();
	            interfaceFrame.repaint();
	        }
	    });
	    
	    //Changer notre pseudo 
	    
	    //c'est ici qu'on crée le bouton pour changer le pseudo 
	    
	    pseudoB = new JButton ( new AbstractAction("Verification du pseudo") {
	        
	        public void actionPerformed(ActionEvent event) {
	             // à finir 
	             // faire la partie de l'interface pour changer le pseudo
	        }
	        
	        
	    });
	    
	    
	   
	    //Dimensions 
	    
	   
	    
	    //dimension bouton et placement
	    connexionB.setBounds(300,500,200,40);
	    
	    //dimensions fields à remplir
	    logIn.setBounds(200,300,200,40);
	    pwd.setBounds(200, 400, 200, 40);
	    
	    /* dimensions à définir quand je pourrais tester
	    log.setBounds.setBounds();
	    pwF.setBounds()
	    connectedmess.setBounds()
	    errormess.setBounds()
	    pseudoB.setBounds()
	    
	    */
	    
	    
	    
	    //Il faut ensuite tout ajouter au Panel
	    interfacePanel.add(logIn);
	    interfacePanel.add(log);
	    interfacePanel.add(pwF);
	    interfaceFrame.repaint();
	    interfacePanel.add(pw);
	    interfacePanel.add(connexionB);
	    interfacePanel.add(connectedmess);
	    interfacePanel.add(errormess);
	 
	    interfaceFrame.getContentPane().add(interfacePanel, BorderLayout.CENTER);
	    
	     // dimension fenêtre & affichage 
	    interfaceFrame.setSize(800,600);
	    interfaceFrame.pack();
	    
	    
	}
	    
	    
	    
	    public static void main(String [] args) {
	        new InterfaceConnexion();
	    }
	    

	     
	}


