package View;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.DatabaseManager;

public class InterfaceManager extends JFrame {
    
	private JTextField newPseudo ;
    private JButton changerPseudo ;
	private JButton deconnexion ;
	
	public InterfaceManager() {
		
		this.setTitle("Clavardage");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setOpaque(false);
		
		this.newPseudo = new JTextField(15) ;
		this.changerPseudo = new JButton("Changer votre pseudo") ;
		this.deconnexion = new JButton("Me deconnecter") ;
		
		JPanel sousPanel0 = new JPanel();
		sousPanel0.setOpaque(false);
		sousPanel0.setLayout(new BoxLayout(sousPanel0, BoxLayout.LINE_AXIS));
		
		sousPanel0.add(newPseudo) ;
		sousPanel0.add(Box.createHorizontalStrut(10));
		sousPanel0.add(changerPseudo);
		sousPanel0.add(Box.createHorizontalStrut(30));
		sousPanel0.add(deconnexion) ;
		
		
		panel.add(sousPanel0) ;
		panel.add(Box.createVerticalStrut(450));
		
		
		JPanel finalPanel = new JPanel();
		finalPanel.setBackground(Color.decode("#afeeee"));
		finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.PAGE_AXIS));
		finalPanel.add(panel);
		
		this.add(finalPanel);
		this.setVisible(false) ;	
		
		
		// Il faut gerer et associer les boutons, facile, utilise ThreadManager !
		
	}
	
	private void showFrame() {
		this.setVisible(true);
	}
	
	public static void main(String[] args) {	
		new InterfaceManager().showFrame();
	}
	
	
	
	
	

}
