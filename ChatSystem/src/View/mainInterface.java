package View;

import Controller.DatabaseManager;
import Controller.UserManager;

public class mainInterface {
	
   
	//toutes nos variables pour l'affichage des boutons et des labels
		interfaceFrame = new JFrame("Chat");
		msg = new JTextField(10);
		coUsr = new JLabel("Connected Users : ");
		send = new JButton("Send");
		select = new JButton("Select");
		logOut = new JButton("Log out");
		refresh = new JButton("Refresh");
		send.setEnabled(false);

		txt = new JTextArea(200, 100); // lignes, colonnes

		txt.setFont(new Font("Serif", Font.PLAIN, 25));
		txt.setText("Veuillez choisir un utilisateur:");

		interfaceFrame.setLayout(new BorderLayout());
		interfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		interfaceFrame.setSize(600, 600);
		interfaceFrame.getContentPane().setLayout(null);
		
		ChoixUser = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox = new JComboBox<String>(ChoixUser);

    // on va devoir actualiser les conversations et les users connectés (SCROLL BAR à implémenter
    //on va créer un panel général puis on ajoute tout au panel et on mets les dimensions de la fenêtre 
    
    // + run 
}
