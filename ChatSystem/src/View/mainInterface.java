package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


import Controller.DatabaseManager;
import Controller.UserManager;
import Controller.NetworkManager;

public class mainInterface {
	
	JFrame interfaceFrame;
	JTextField msg;
	JLabel coUsr;
	JPanel panel;
	JButton envoyer, selection, logOut, refresh;
	JComboBox<String> usrComboBox;
	String[] ChoixUser;
	JScrollPane scroll;
	JTextArea txt;

	String selectedUser = null;
	
	
	private void updateConnectedUsers() {
		usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox.setModel(new DefaultComboBoxModel<String>(usersToChoose));
	}
	
	public mainInterface() {
   
	//toutes nos variables pour l'affichage des boutons et des labels
	
		interfaceFrame = new JFrame("Chat");
		msg = new JTextField(10);
		coUsr = new JLabel("Connected Users : ");
		envoyer = new JButton("Send");
		selection = new JButton("Sélection");
		logOut = new JButton("Log out");
		refresh = new JButton("Refresh");
		envoyer.setEnabled(false);

		txt = new JTextArea(200, 100); // lignes, colonnes

		txt.setFont(new Font("Serif", Font.PLAIN, 25));
		txt.setText("Veuillez choisir un utilisateur:");

		interfaceFrame.setLayout(new BorderLayout());
		interfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		interfaceFrame.setSize(600, 600);
		interfaceFrame.getContentPane().setLayout(null);
		
		ChoixUser = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox = new JComboBox<String>(ChoixUser);
		
		// ajouter un panel pour scroler les contacts 
		
			scroll = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			//on met ses dimensions
			scroll.setBounds(30, 70, 1200, 420);
			//on l'ajoute à l'interface
			interfaceFrame.getContentPane().add(scroll);
			
			selection.addActionListener (new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					selectedUser = usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
					txt.setFont(new Font("Serif", Font.PLAIN, 15));
					txt.setCaretPosition(txt.getText().length() - 1);
					envoyer.setEnabled(true);
				}
				
				
			
			});
			//on ajoute nos actionListener sur chacun des boutons 
			logOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					interfaceFrame.setVisible(false);
					interfaceFrame.dispose();
				}
			});

			refresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateConnectedUsers();
					txt.setCaretPosition(txt.getText().length() - 1);
				}
			});

			envoyer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message = msg.getText();

					if (NetworkManager.sendMessage(selectedUser, message) == 0) {
						msg.setText("");
					} else {
						txt.setText("User " + selectedUser + " disconnected ! Choose another user");
						selectedUser = null;
						updateConnectedUsers();
						envoyer.setEnabled(false);

					}
				}
			});

		
		
		}
	
		
			
		}



