package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import lifeRPG.Player;

public class NewPlayerDialogFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton confirm;
	
	private JTextField name;
	private JTextField title;
	private JTextField[] attributes;
	
	// For the Player object to be returned
	private int[] attributeScores;
	private String playerName;
	private String playerTitle;
	
	private LifeRPG motherFrame;
	
	/**
	 * Constructor method of NewPlayerDialogFrame. The LifeRPG frame is passed
	 * so that variables within the LifeRPG class can be mutated from this class.
	 * 
	 * @param LifeRPG frame
	 */
	public NewPlayerDialogFrame(LifeRPG frame){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 300);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setTitle("Create New Player");
		
		// Instantiate some variables
		motherFrame = frame;
		
		name = new JTextField();
		name.setToolTipText("Name of the new player to create");
		
		title = new JTextField();
		title.setToolTipText("Title of the new player to create");
		
		attributes = new JTextField[6];
		for(int i = 0; i < 6; i++){
			attributes[i] = new JTextField();
			attributes[i].setToolTipText("Type attribute score here");
		}
		
		confirm = new JButton("Create Player");
		confirm.addActionListener(this);
		
		attributeScores = null;
		playerName = null;
		playerTitle = null;
		
		// Set everything up
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		
		// namePanel
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(4, 1));
		namePanel.add(new JLabel("Name"));
		namePanel.add(name);
		namePanel.add(new JLabel("Title"));
		namePanel.add(title);
		
		// attributePanel
		JPanel attributePanel = new JPanel();
		attributePanel.setLayout(new GridLayout(1, 2));
		
		JPanel aPanel = new JPanel();
		aPanel.setLayout(new GridLayout(3, 2));
		
		JLabel str = new JLabel("STR");
		str.setToolTipText("Strength score");
		aPanel.add(str);
		aPanel.add(attributes[0]);
		
		JLabel con = new JLabel("CON");
		con.setToolTipText("Constitution score");
		aPanel.add(con);
		aPanel.add(attributes[1]);
		
		JLabel dex = new JLabel("DEX");
		dex.setToolTipText("Dexterity score");
		aPanel.add(dex);
		aPanel.add(attributes[2]);
		
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(3, 2));
		
		JLabel inT = new JLabel("INT");
		inT.setToolTipText("Intelligence score");
		bPanel.add(inT);
		bPanel.add(attributes[3]);
		
		JLabel wis = new JLabel("WIS");
		wis.setToolTipText("Wisdom score");
		bPanel.add(wis);
		bPanel.add(attributes[4]);
		
		JLabel cha = new JLabel("CHA");
		cha.setToolTipText("Charisma score");
		bPanel.add(cha);
		bPanel.add(attributes[5]);
		
		attributePanel.add(aPanel);
		attributePanel.add(bPanel);
		
		
		// Add it all up
		mainPanel.add(namePanel);
		mainPanel.add(attributePanel);
		add(mainPanel);
		
		add(confirm, BorderLayout.SOUTH);
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		playerName = name.getText();
		playerTitle = title.getText();
		
		attributeScores = getAttributeScores();
		
		if(attributeScores == null)
			return;
		
		Player p = getPlayer();
		
		if(p == null){
			JOptionPane.showMessageDialog(this, "You must fill all the fields to create"
					+ " the new player.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		setVisible(false);
		motherFrame.setPlayer(p);
		motherFrame.getLastActionLabel().setText("Player " + p.getName() 
				+ " has been created.");
		motherFrame.setComponentAbility(true);
		motherFrame.getAttributesAndTasksPanel().removeAllTasks();
	}
	
	private int[] getAttributeScores(){
		int[] attributeScores = new int[6];
		
		try{
			for(int i = 0; i < 6; i ++)
				attributeScores[i] = Integer.parseInt(attributes[i].getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, "You must pass an integer for each"
					+ " attribute score", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return attributeScores;
	}
	
	/**
	 * Returns the Player created from the information passed into this frame.
	 * If there are fields missing, this method returns null.
	 * 
	 * @return new Player object or null
	 */
	public Player getPlayer() {
		if(playerName.equals("") || playerTitle.equals("") || attributeScores.equals(""))
			return null;
		
		Player p = new Player(playerName, playerTitle, attributeScores);
		
		return p;
	}
}
