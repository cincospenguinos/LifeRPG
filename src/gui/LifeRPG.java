package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import lifeRPG.Player;


/**
 * The class that runs the entire LifeRPG GUI.
 * 
 * THINGS TO IMPLEMENT/FIX:
 * - What if we allowed the user to put a picture of their face or something that
 * gets loaded and placed within the frame every time it's loaded? That would be pretty
 * sweet.
 * - Skills, Tasks and Quests need to be figured out. I'm thinking only Tasks in
 * progress and quests in progress will be shown, and if the player wanted to get
 * all the tasks and quests they finished, it would be shown in a new frame.
 * - Color each of the different tasks and quests according to their difficulty when
 * they are shown.
 * - Task table needs to have a marking for whether or not it is part of a Quest.
 * - Leveling up should be more exciting. Can you make a song or sound file play when
 * you level up? Like the Skyrim level up music or something. That would also be
 * pretty cool.
 * - Tables need to be fixed in AttributesAndTasksPanel --> rows need to be reset every
 * time, not just added on every time.
 * - Redraw everything function. One that actually goes through all of the player's
 * tasks, skills and quests and rewrites them up.
 * - Task table needs to include a colors for difficulty
 * - QuestManagerFrame needs to be figured out, as well as quests themselves
 * - Quest table needs to be figured out also - adding rows for tasks?
 * - Miniature version that's lightweight, and not intrusive, like an HUD.
 * - lastActionPanel isn't really being used. It either needs to be added, or
 * removed entirely.
 * 
 * @author Andr� LaFleur
 *
 */
@SuppressWarnings("serial")
public class LifeRPG extends JFrame {
	
	private NamePanel namePanel;
	private AttributesAndTasksPanel attributesAndTasksPanel;
	private TaskManagerPanel taskManagerPanel;
	private LifeRPGMenuBar menuBar;
	
	private JLabel lastAction;
	
	private Player currentPlayer;
	
	/**
	 * Constructor method that sets the GUI up.
	 */
	public LifeRPG(){
		// GUI specifications
		setSize(329, 316);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setTitle("LifeRPG --- version 0.5");
		
		// Instance variables
		currentPlayer = null;// No player is loaded
		
		namePanel = new NamePanel();// Displays name and level info
		attributesAndTasksPanel = new AttributesAndTasksPanel(this);// other player info
		taskManagerPanel = new TaskManagerPanel(this);// manages tasks
		
		menuBar = new LifeRPGMenuBar(this);// JMenuBar
		
		lastAction = new JLabel("");// Communicates to user
		
		JPanel playerPanel = new JPanel(); // Main panel that shows stuff
		playerPanel.setLayout(new BorderLayout());
		
		playerPanel.add(namePanel, BorderLayout.NORTH);
		playerPanel.add(attributesAndTasksPanel);

		add(playerPanel);
		add(taskManagerPanel, BorderLayout.SOUTH);	
		setJMenuBar(menuBar);
		
		setComponentAbility(false);
		
		pack();
	}
	
	public static void main(String[] args) {
		LifeRPG f = new LifeRPG();
		f.setVisible(true);
	}
	
	/**
	 * Gives the JLabel that communicates to the player the previous action.
	 * Meant to be used in relation to other frames.
	 * 
	 * @return JLabel lastAction variable
	 */
	public JLabel getLastActionLabel(){
		return lastAction;
	}
	
	/**
	 * Returns the currently loaded Player object. 
	 * 
	 * @return Player the current Player object loaded
	 */
	public Player getPlayer(){
		return currentPlayer;
	}
	
	/**
	 * Sets the Player of this class to the Player object passed, and redraws
	 * the output display.
	 * 
	 * @param Player to set the current player to
	 */
	public void setPlayer(Player p){
		currentPlayer = p;
		redrawPlayer();
	}
	
	public AttributesAndTasksPanel getAttributesAndTasksPanel(){
		return attributesAndTasksPanel;
	}
	
	public TaskManagerPanel getTaskManagerPanel(){
		return taskManagerPanel;
	}
	
	/**
	 * Enables or disables certain components so that no exceptions are thrown.
	 * 
	 * @param Boolean enable or disable
	 */
	public void setComponentAbility(boolean b){
		taskManagerPanel.setComponentAbility(b);
		menuBar.setComponentAbility(b);
	}
	
	/**
	 * Sets all the various JLabels to the variables of the Player object.
	 * This method does nothing if there isn't a Player object currently loaded.
	 * 
	 * THINGS TO IMPLEMENT/FIX WITH THIS METHOD:
	 * - Tasks --> Have different method to do that
	 * - Quests --> Have different method for that too
	 * - Skills --> And this one also
	 */
	public void redrawPlayer(){
		if(currentPlayer == null)
			return;
		
		redrawPlayerStats();
		redrawAttributes();
		redrawSkills();
		redrawTasks();
		redrawQuests();
	}
	
	/**
	 * A helper method that resets the Player's name, title, level and XP
	 */
	private void redrawPlayerStats(){
		if(currentPlayer == null)
			return;
		
		namePanel.redrawPlayerStats(currentPlayer);
	}
	
	/**
	 * Resets the panel with all the Attributes and values associated with it
	 * in the Player object.
	 */
	private void redrawAttributes(){
		if(currentPlayer == null)
			return;
		
		attributesAndTasksPanel.redrawAttributes(currentPlayer);
	}
	
	/**
	 * Resets the panel holding all the Tasks to the tasks currently held
	 * within the Player object.
	 * 
	 * TO BE IMPLEMENTED
	 */
	private void redrawTasks(){
		if(currentPlayer == null)
			return;
		
		getAttributesAndTasksPanel().removeAllTasks();
		getAttributesAndTasksPanel().addAllTasks(currentPlayer);
	}
	
	/**
	 * Resets the panel holding all the Quests within the Player object.
	 */
	public void redrawQuests(){
		if(currentPlayer == null)
			return;
		
		// Do some shiz
	}
	
	/**
	 * Resets the panel holding all the Skills to the Skills within the Player
	 * object.
	 */
	private void redrawSkills(){
		if(currentPlayer == null)
			return;
		
		// Do some shiz
	}
}