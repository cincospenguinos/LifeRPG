package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lifeRPG.Attribute;
import lifeRPG.Difficulty;
import lifeRPG.Player;
import lifeRPG.Skill;
import lifeRPG.Task;

@SuppressWarnings("serial")
public class TaskManagerPanel extends JPanel implements ActionListener{
	
	private JTextField taskName;
	private JTextField skillName;
	
	private JComboBox difficultyChoices;
	private String[] difficulties;
	
	private JComboBox attributeChoices;
	private String[] attributes;
	
	private JButton addTask;
	private JButton completeTask;
	private JButton removeTask;
	private JButton manageQuests;
	
	private LifeRPG motherFrame;
	
	public TaskManagerPanel(LifeRPG _motherFrame){
		// GUI specifications
		setLayout(new GridLayout(3, 4));
		
		motherFrame = _motherFrame;
		
		// Create the buttons
		addTask = new JButton("Add Task");
		addTask.setToolTipText("Add task matching this information to current player");
		addTask.addActionListener(this);
		
		completeTask = new JButton("Complete Task");
		completeTask.setToolTipText("Complete a task matching information above");
		completeTask.addActionListener(this);
		
		removeTask = new JButton("Remove Task");
		removeTask.setToolTipText("Remove task mathching this information from the current player");
		removeTask.addActionListener(this);
		
		manageQuests = new JButton("Manage Quests");
		manageQuests.setToolTipText("Manage all current quests");
		manageQuests.addActionListener(this);
		
		// Setup the text fields
		taskName = new JTextField();
		taskName.setToolTipText("Type the name of a task");
		
		skillName = new JTextField();
		skillName.setToolTipText("Type the name of a related skill");
		
		// Setup the ComboBoxes
		difficulties = new String[5];
		for(int i = 0; i < 5; i ++)
			difficulties[i] = Difficulty.toDifficulty(i).toString();
		
		difficultyChoices = new JComboBox(difficulties);
		difficultyChoices.setToolTipText("Select a difficulty associated with a task");
		
		attributes = new String[6];
		for(int i = 0; i < 6; i ++)
			attributes[i] = Attribute.toAttribute(i).toString();
		
		attributeChoices = new JComboBox(attributes);
		attributeChoices.setToolTipText("Select a difficulty associated with a task");
		
		// Put it all together
		
		add(new JLabel("Task Name"));
		add(new JLabel("Skill"));
		add(new JLabel("Attribute"));
		add(new JLabel("Difficulty"));
		add(taskName);
		add(skillName);
		add(attributeChoices);
		add(difficultyChoices);
		add(addTask);
		add(removeTask);
		add(manageQuests);
		add(addTask);
		add(completeTask);
		add(removeTask);
		add(manageQuests);
	}
	
	public void setComponentAbility(boolean b){
		taskName.setEnabled(b);
		skillName.setEnabled(b);
		attributeChoices.setEnabled(b);
		difficultyChoices.setEnabled(b);
		addTask.setEnabled(b);
		completeTask.setEnabled(b);
		removeTask.setEnabled(b);
		manageQuests.setEnabled(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		Player p = motherFrame.getPlayer();
		
		// Show QuestManagerFrame
		if(src.equals(manageQuests)){
			
			return;
		}
		
		Task t = getTask();
		
		if(t == null)
			return;
		
		// Add a task to this player
		if(src.equals(addTask)){
			p.addTaskToComplete(t);
			motherFrame.getAttributesAndTasksPanel().addTask(t);
		}
		
		else if(src.equals(completeTask)){
			p.completeTask(t);
			
			// Remove the task from table and add it to the Skill table
			Skill s = t.getRelatedSkill();
			int value = p.getSkillValue(s);
			
			motherFrame.getAttributesAndTasksPanel().removeTask(t);
			motherFrame.getAttributesAndTasksPanel().addSkill(s, value);
		}
		
		// Remove a task from this player
		else if(src.equals(removeTask)){
			p.removeTask(t);
			
			// Remove the task from the table
			motherFrame.getAttributesAndTasksPanel().removeTask(t);
		}
	}
	
	/**
	 * Extracts the Task from this panel. If the error message is thrown,
	 * this method returns null.
	 * 
	 * @return Task object or null 
	 */
	private Task getTask(){
		if(taskName.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "You must enter a task name.", 
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		String tName = taskName.getText();
		String sName = skillName.getText();
		
		String diffName = difficultyChoices.getSelectedItem().toString();
		diffName.replace(' ', '_');
		
		Difficulty diff = Difficulty.toDifficulty(diffName);
		Attribute attr = Attribute.toAttribute(attributeChoices.getSelectedItem().toString());
		
		
		return new Task(tName, new Skill(sName, attr), diff);
	}
}