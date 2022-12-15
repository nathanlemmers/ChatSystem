package View;

import java.awt.*;
import java.sql.*;
import javax.swing.*;


import Controller.DatabaseManager;
import Controller.UserManager;

public class mainInterface {
	
	JFrame interfaceFrame;
	JTextField msg;
	JLabel coUsr;
	JPanel panel;
	JButton send, select, logOut, refresh;
	JComboBox<String> usrComboBox;
	String[] ChoixUser;
	JScrollPane scroll;
	JTextArea txt;

	String selectedUser = null;
	
	
	
	public mainInterface() {
   
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
		
		}
			
		}



