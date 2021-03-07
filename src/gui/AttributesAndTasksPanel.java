package gui;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import lifeRPG.Player;
import lifeRPG.Quest;
import lifeRPG.Skill;
import lifeRPG.Task;

/**
 * THINGS TO IMPLEMENT/FIX
 * 
 * - Adding/removing tasks and skills is on O(n). You should look into SQLite,
 * Toad or another database file to be able to push it to O(1). Although you
 * may want to consider just keeping track of your data in this class like a 
 * HashMap of String skillName and Integer tableIndex, and then when you need
 * to remove/update something, you get the value out of the Map and then use
 * it to remove/update the table.
 * @author Dr.Dre
 *
 */
@SuppressWarnings("serial")
public class AttributesAndTasksPanel extends JPanel {
	
	private JLabel STR;
	private JLabel CON;
	private JLabel DEX;
	private JLabel INT;
	private JLabel WIS;
	private JLabel CHA;
	
	private JTable skillsTable;
	private DefaultTableModel skillsModel;
	
	private JTable tasksTable;
	private DefaultTableModel tasksModel;
	
	private JTable questsTable;
	private DefaultTableModel questsModel;
	
	private LifeRPG motherFrame;
	
	public AttributesAndTasksPanel(LifeRPG _motherFrame){
		// Some specifications
		setLayout(new GridLayout(1, 2));
		
		// Initialization of instance variables
		motherFrame = _motherFrame;
		
		STR = new JLabel("__");
		CON = new JLabel("__");
		DEX = new JLabel("__");
		INT = new JLabel("__");
		WIS = new JLabel("__");
		CHA = new JLabel("__");
		
		skillsTable = new JTable();
		skillsModel = new DefaultTableModel(new Object[]{"Name", 
				"Attribute", "Level"}, 0);
		skillsTable.setModel(skillsModel);
		
		tasksTable = new JTable();
		tasksModel = new DefaultTableModel(new Object[]{"Task", "Related Skill",
				"Attribute", "Difficulty"}, 0);
		tasksTable.setModel(tasksModel);
		
		questsTable = new JTable();
		questsModel = new DefaultTableModel(new Object[]{"Quest Name", 
				"XP Value", "Tasks to Complete"}, 0);
		questsTable.setModel(questsModel);
		
		
		// The JTabbedPanes this panel
		JTabbedPane attributesAndSkillsPane = new JTabbedPane();
		JTabbedPane tasksAndQuestsPane = new JTabbedPane();
		
		// Attributes
		JPanel attributesPanel = new JPanel();
		attributesPanel.setLayout(new GridLayout(6, 2));
		
		attributesPanel.add(new JLabel("STR"));
		attributesPanel.add(STR);
		
		attributesPanel.add(new JLabel("CON"));
		attributesPanel.add(CON);
		
		attributesPanel.add(new JLabel("DEX"));
		attributesPanel.add(DEX);
		
		attributesPanel.add(new JLabel("INT"));
		attributesPanel.add(INT);
		
		attributesPanel.add(new JLabel("WIS"));
		attributesPanel.add(WIS);
		
		attributesPanel.add(new JLabel("CHA"));
		attributesPanel.add(CHA);
		
		attributesAndSkillsPane.addTab("Attributes", attributesPanel);
		
		// Skills
		JScrollPane skillsScrollPane = new JScrollPane(skillsTable);
		attributesAndSkillsPane.addTab("Skills", skillsScrollPane);
		
		// Tasks
		JScrollPane tasksScrollPane = new JScrollPane(tasksTable);
		tasksAndQuestsPane.addTab("Tasks", tasksScrollPane);
		
		// Quests
		JScrollPane questsScrollPane = new JScrollPane(questsTable);
		tasksAndQuestsPane.addTab("Quests", questsScrollPane);
		
		// Construct the Panel
		add(attributesAndSkillsPane);
		add(tasksAndQuestsPane);
	}
	
	/**
	 * Adds the task passed to the table of tasks.
	 * 
	 * @param Task to be added
	 */
	public void addTask(Task t){
		Skill s = t.getRelatedSkill();
		
		tasksModel.addRow(new Object[] {t.getName(), s.getName(), 
				s.getAttribute().toString(), t.getDifficulty().toString()});
	}
	
	/**
	 * Adds all the current tasks to the table of tasks.
	 * 
	 * @param Player p
	 */
	public void addAllTasks(Player p){
		for(Task t : p.getCurrentTasks()){
			Skill s = t.getRelatedSkill();
			
			if(s == null)
				tasksModel.addRow(new Object[] {t.getName(), "",
				"", t.getDifficulty().toString()});
			else
				tasksModel.addRow(new Object[] {t.getName(), s.getName(),
				s.getAttribute().toString(), t.getDifficulty().toString()});				
		}
	}
	
	/**
	 * Removes the task passed from the table of tasks.
	 * 
	 * @param Task to be removed
	 */
	public void removeTask(Task t){
		for(int i = 0; i < tasksModel.getRowCount(); i++){
			if(tasksModel.getValueAt(i, 0).equals(t.getName())){
				tasksModel.removeRow(i);
				break;
			}
		}
	}
	
	/**
	 * Removes all the tasks from the table of tasks.
	 */
	public void removeAllTasks(){
		for(int i = 0; i < tasksModel.getRowCount(); i++)
			tasksModel.removeRow(i);
	}
	
	/**
	 * Adds the Skill passed to the table of skills.
	 * 
	 * @param Skill to be added
	 */
	public void addSkill(Skill s, int value){
		// Search the table
		for(int i = 0; i < tasksModel.getRowCount(); i++){
			// If the table contains the skill, 
			if(tasksModel.getValueAt(i, 0).equals(s.getName())){
				skillsModel.setValueAt(value, i, 2);
				return;
			}
		}
		
		if(s.getAttribute() != null && s.getName() != null)
			skillsModel.addRow(new Object[] {s.getName(), s.getAttribute().toString(), value});
		else
			skillsModel.addRow(new Object[] {"", "", value});
	}
	
	/**
	 * Adds all Skills belonging to the current player to the table of skills.
	 * 
	 * @param Player current player.
	 */
	public void addAllSkills(Player p){
		for(Entry<Skill, Integer> skill : p.getSkills().entrySet()){
			
			if(skill.getKey().getAttribute() != null && skill.getKey().getName() != null)
				skillsModel.addRow(new Object[] {skill.getKey().getName(), skill.getKey().getAttribute().toString(), skill.getValue()});
			else
				skillsModel.addRow(new Object[] {"", "", skill.getValue()});
		}
	}
	
	/**
	 * Removes the Skill from the table.
	 * 
	 * @param Skill to be removed
	 */
	public void removeSkill(Skill s){
		for(int i = 0; i < tasksModel.getRowCount(); i++){
			// If the table contains the skill, 
			if(tasksModel.getValueAt(i, 0).equals(s.getName())){
				skillsModel.removeRow(i);
				return;
			}
		}
	}
	
	/**
	 * Adds the Quest passed to the table of quests.
	 * 
	 * TO BE IMPLEMENTED
	 * 
	 * @param Quest to be added
	 */
	public void addQuest(Quest q){
		
	}
	
	public void removeQuest(Quest q){
		
	}
	
	/**
	 * Redraws the Attributes within this panel to match those of the Player
	 * Object passed.
	 * 
	 * @param Player p
	 */
	public void redrawAttributes(Player p){
		int[] attributeValues = new int[6];
		int index = 0;
		
		for(Integer i : p.getAttributes().values()){
			attributeValues[index] = i;
			index++;
		}
		
		STR.setText(Integer.toString(attributeValues[0]));
		CON.setText(Integer.toString(attributeValues[1]));
		DEX.setText(Integer.toString(attributeValues[2]));
		INT.setText(Integer.toString(attributeValues[3]));
		WIS.setText(Integer.toString(attributeValues[4]));
		CHA.setText(Integer.toString(attributeValues[5]));
	}
	
	/**
	 * Redraws the skills in this panel
	 * 
	 * TO BE IMPLEMENTED
	 */
	public void redrawSkills(){
		
	}
	
	/**
	 * Redraws the tasks in this panel
	 * 
	 * TO BE IMPLEMENTED
	 */
	public void redrawTasks(){
		
	}
	
	/**
	 * Redraws the quests in this panel
	 * 
	 * TO BE IMPLEMENTED
	 */
	public void redrawQuests(){
		
	}
}
