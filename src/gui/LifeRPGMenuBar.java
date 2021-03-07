package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import lifeRPG.Player;

@SuppressWarnings("serial")
public class LifeRPGMenuBar extends JMenuBar implements ActionListener{
	
	// For File menu
	private JMenuItem newPlayer;
	private JMenuItem savePlayer;
	private JMenuItem loadPlayer;
	private JMenuItem quit;
	
	// For Help menu
	private JMenuItem about;
	
	private LifeRPG motherFrame;
	
	public LifeRPGMenuBar(LifeRPG _motherFrame){
		motherFrame = _motherFrame;
		
		newPlayer = new JMenuItem("New");
		newPlayer.setToolTipText("Create new Player");
		newPlayer.addActionListener(this);
		
		savePlayer = new JMenuItem("Save");
		savePlayer.setToolTipText("Save currently loaded player");
		savePlayer.addActionListener(this);
		
		loadPlayer = new JMenuItem("Load");
		loadPlayer.setToolTipText("Load another player");
		loadPlayer.addActionListener(this);
		
		quit = new JMenuItem("Close");
		quit.setToolTipText("Close this program");
		quit.addActionListener(this);
		
		about = new JMenuItem("About");
		about.addActionListener(this);
		
		JMenu file = new JMenu("File");
		file.add(newPlayer);
		file.add(savePlayer);
		file.add(loadPlayer);
		file.add(quit);
		
		JMenu edit = new JMenu("Edit");
		
		JMenu help = new JMenu("Help");
		help.add(about);
		
		add(file);
		add(edit);
		add(help);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem src = (JMenuItem) e.getSource();
		Player p = motherFrame.getPlayer();
		
		// Create a new player
		if(src.equals(newPlayer)){
			NewPlayerDialogFrame newDialog = new NewPlayerDialogFrame(motherFrame);
			newDialog.setVisible(true);
		}
		
		// Save the current player
		else if(src.equals(savePlayer)){
			p.savePlayer();
			motherFrame.getLastActionLabel().setText("Player saved.");
		}
		
		// Load a current player
		else if(src.equals(loadPlayer)){
			if(p == null){
				motherFrame.setPlayer(new Player("", "", new int[6]));
				p = motherFrame.getPlayer();
			}
			
			try {
				p.loadPlayer(loadDialog());
			} catch (NullPointerException exception) {
				JOptionPane.showMessageDialog(this, 
						"File could not be loaded.",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				motherFrame.setPlayer(null);
				exception.printStackTrace();
				return;
			} catch (FileNotFoundException exception) {
				JOptionPane.showMessageDialog(this, 
						"File could not be found.",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				motherFrame.setPlayer(null);
				exception.printStackTrace();
				return;
			}
			
			motherFrame.getLastActionLabel().setText("Player " 
					+ p.getName() + " loaded.");
			motherFrame.redrawPlayer();
			motherFrame.setComponentAbility(true);
		}
		
		// Quit the application
		else if(src.equals(quit)){
			if(quitDialog())
				System.exit(0);
		}
		
		// Show stuff about this application
		else if(src.equals(about)){
			about();
		}
	}
	
	/**
	 * Shows the load dialog. Permits the user to select a player to load up.
	 * This method returns null if the file doesn't end with the extension
	 * ".plr", otherwise returns the File object itself.
	 * 
	 * @return A File if the file selected has the extension ".plr"
	 */
	public File loadDialog(){
		JFileChooser chooser = new JFileChooser(".");
		chooser.showOpenDialog(this);
		
		try{
			File f = chooser.getSelectedFile();
			
			if(!f.getName().endsWith(".plr"))
				return null;
			
			return f;
		}catch(NullPointerException e){
			return null;
		}
	}
	
	/**
	 * A helper method that shows a quit dialog. Returns true if the user
	 * desires to exit the program.
	 * 
	 * @return true if the user chooses to quit the program
	 */
	public boolean quitDialog(){
		int i = JOptionPane.showConfirmDialog(null, 
				"Are you sure you want to quit?", "QUIT", 
				JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(i == JOptionPane.YES_OPTION)
			return true;
		
		return false;
	}
	
	/**
	 * Shows a little window about this application.
	 * 
	 * IDEA: Create your own about frame.
	 */
	public void about(){
		JOptionPane.showMessageDialog(this, "LifeRPG ver. 0.5\n"
				+ "Created by André LaFleur"
				+ "\nhttp://98.202.236.51");
	}
	
	/**
	 * Sets certain components within this JMenuBar to enabled or disabled.
	 * 
	 * @param Boolean enable or disable
	 */
	public void setComponentAbility(boolean b) {
		savePlayer.setEnabled(b);
	}
}
