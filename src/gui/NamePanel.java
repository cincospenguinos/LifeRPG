package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lifeRPG.Player;




@SuppressWarnings("serial")
public class NamePanel extends JPanel {
	
	private JLabel name;
	private JLabel title;
	private JLabel level;
	private JLabel currentXP;
	private JLabel nextLevelXP;
	
	public NamePanel(){
		name = new JLabel("NAME");
		title = new JLabel("TITLE");
		
		level = new JLabel("___");
		level.setForeground(Color.RED);
		
		currentXP = new JLabel("_____");
		currentXP.setForeground(Color.RED);
		
		nextLevelXP = new JLabel("_____");
		nextLevelXP.setForeground(Color.RED);
		
		setLayout(new GridLayout(2, 1));
		
		JPanel namePanel = new JPanel();// for the name only
		namePanel.setLayout(new FlowLayout());
		
		namePanel.add(name);
		namePanel.add(title);
		
		JPanel levelAndXPPanel = new JPanel();// Holds level and XP
		levelAndXPPanel.setLayout(new FlowLayout());
		
		JPanel levelPanel = new JPanel();// for the level only
		levelPanel.setLayout(new FlowLayout());
		levelPanel.add(new JLabel("Level "));
		levelPanel.add(level);
		
		levelAndXPPanel.add(levelPanel);
		
		JPanel xpPanel = new JPanel();// for XP only
		xpPanel.setLayout(new FlowLayout());
		xpPanel.add(new JLabel("XP:"));
		xpPanel.add(currentXP);
		xpPanel.add(new JLabel("Next Level:"));
		xpPanel.add(nextLevelXP);
		
		levelAndXPPanel.add(xpPanel);
		
		add(namePanel);
		add(levelAndXPPanel);
	}
	
	public void redrawPlayerStats(Player p){
		name.setText(p.getName());
		title.setText(p.getTitle());
		level.setText(Integer.toString(p.getLevel()));
		currentXP.setText(Integer.toString(p.getCurrentXP()));
		nextLevelXP.setText(Integer.toString(p.getNextLevelXP()));
	}
}
