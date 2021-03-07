package lifeRPG;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This class represents a character (or player) in a Role Playing Game.
 * Contains various objects and variables commonly associated with a Player in a
 * Role Playing Game. The purpose of this Player object is to represent a person
 * in real life accomplishing various tasks and gaining experience and skills by
 * accomplishing those tasks.
 * 
 * THINGS TO IMPLEMENT/FIX: - Quest object holding a variety of Tasks that, when
 * completed, gives a bonus to XP by completing the Quest itself. Perhaps
 * attaching a Difficulty to a Quest object and depending on the difficulty of
 * the Quest, the experience points rewarded change. - DnDPlayer class that is
 * an extension of Player. Has more specific specifications and Objects that are
 * associated with Dungeons and Dragons. - Saving and loading Tasks - Saving and
 * loading Skills - A getSkill(String) method, so that users can find a Skill
 * that exists already and just place that in whatever they're doing. - Consider
 * changing completedTasks and completedQuests to Maps rather than Lists. This
 * would remove the problem of having to iterate through the entire collection
 * of tasks or quests.
 * 
 * NOTES: - The Player class doubles the nextLevelXP every time the Player gains
 * a level. This is different than the DnDPlayer object. It will be important to
 * make that distinction.
 * 
 * @author Andre LaFleur
 * 
 */
public class Player {

	private String name;
	private String title;

	private int level;
	private int currentXP;
	private int nextLevelXP;

	private Map<Attribute, Integer> attributes;
	private Map<Skill, Integer> skills;

	private List<Task> tasksToComplete;
	private List<Task> completedTasks;

	private List<Quest> questsToComplete;
	private List<Quest> completedQuests;

	/**
	 * Constructor method of Player. This method is meant to be used to create a
	 * completely new instance of the Player class. Throws an
	 * IllegalArgumentException if the integer array does not have a length of
	 * 6.
	 * 
	 * @param _name
	 * @param _title
	 * 
	 * @throws IllegalArgumentException
	 */
	public Player(String _name, String _title, int[] attributeValues) throws IllegalArgumentException {
		if (attributeValues.length != 6)
			throw new IllegalArgumentException(
					"int[] attributeValues must contain only 6 integers");

		name = _name;
		title = _title;

		level = 1;
		currentXP = 0;
		nextLevelXP = 100;

		attributes = new TreeMap<Attribute, Integer>();

		for (int i = 0; i < 6; i++) {
			attributes.put(Attribute.toAttribute(i), attributeValues[i]);
		}

		skills = new HashMap<Skill, Integer>();

		tasksToComplete = new ArrayList<Task>();
		completedTasks = new ArrayList<Task>();

		questsToComplete = new ArrayList<Quest>();
		completedQuests = new ArrayList<Quest>();
	}

	/**
	 * Constructor method of class Player. This constructor is meant to be used
	 * to create an instance of Player that existed before (i.e. a saved Player
	 * from a file).
	 * 
	 * @param String
	 *            _name
	 * @param String
	 *            _title
	 * @param Integer
	 *            _level
	 * @param Integer
	 *            _currentXP
	 * @param Integer
	 *            _nextLevelXP
	 * @param Map
	 *            <Attribute, Integer> _attributes
	 * @param Map
	 *            <Skill, Integer> _skills
	 * @param List
	 *            <Task> _tasksToComplete
	 * @param List
	 *            <Task> _completedTasks
	 */
	public Player(String _name, String _title, int _level, int _currentXP,
			int _nextLevelXP, Map<Attribute, Integer> _attributes,
			Map<Skill, Integer> _skills, List<Task> _tasksToComplete,
			List<Task> _completedTasks, List<Quest> _questsToComplete,
			List<Quest> _completedQuests) {

		name = _name;
		title = _title;
		level = _level;
		currentXP = _currentXP;
		nextLevelXP = _nextLevelXP;
		attributes = _attributes;
		skills = _skills;
		tasksToComplete = _tasksToComplete;
		completedTasks = _tasksToComplete;
		questsToComplete = _questsToComplete;
		completedQuests = _completedQuests;
	}

	/**
	 * Levels up the current Player if and only if the Player's current amount
	 * of experience points is greater than or equal to the amount necessary to
	 * gain a level.
	 * 
	 * @return 0 if leveling up didn't happen; 1 if it did
	 */
	public int levelUp() {
		if (currentXP >= nextLevelXP) {
			level++;
			nextLevelXP *= 2;
			return 1;
		}

		return 0;
	}

	/**
	 * Gives the Task object matching the String taskName passed to the method.
	 * If a Task matching the String is found, this method returns that.
	 * Otherwise returns null.
	 * 
	 * @param taskName
	 * @return Task requested or null
	 */
	public Task getTaskToComplete(String taskName) {
		if (taskName == null)
			return null;

		for (Task task : tasksToComplete)
			if (task.getName().equals(taskName)) {
				return task;
			}

		return null;
	}

	/**
	 * Gives the task matching the Task requested.
	 * 
	 * @param t
	 * @return
	 */
	public Task getTaskToComplete(Task t) {
		if (t == null)
			return null;

		for (Task task : tasksToComplete) {
			if (task.equals(t)) {
				return task;
			}
		}

		return null;
	}

	/**
	 * Returns the completed task with a name that matches the String passed.
	 * Returns null if the taskName passed does not match a task name within the
	 * List of completed tasks.
	 * 
	 * @param String
	 *            taskName
	 * @return Task matching or null
	 */
	public Task getCompletedTask(String taskName) {
		if (taskName == null)
			return null;

		for (Task task : completedTasks)
			if (task.getName().equals(taskName))
				return task;

		return null;
	}

	/**
	 * Returns the completed task that matches the Task passed. Returns null if
	 * the two tasks aren't the same.
	 * 
	 * @param Task
	 *            t
	 * @return Task object or null
	 */
	public Task getCompletedTask(Task t) {
		for (Task task : completedTasks)
			if (task.equals(task))
				return task;

		return null;
	}

	/**
	 * Adds the Task object passed to the collection of Tasks to complete. If
	 * null is passed, this method does nothing.
	 * 
	 * @param Task
	 *            to add
	 */
	public void addTaskToComplete(Task t) {
		if (t == null)
			return;

		tasksToComplete.add(t);
	}

	/**
	 * Completes the Task in the tasksToComplete list with a name matching the
	 * String passed. This Player's current experience point value goes up
	 * according to the amount attached to the Task found, the Task found is
	 * added to the List of completed tasks, the Skill attached to the Task is
	 * added to the list of Skills, and the Task requested is removed from the
	 * list of Tasks to complete.
	 * 
	 * @param String
	 *            name of Task to complete
	 */
	public void completeTask(String s) {
		for (int i = 0; i < tasksToComplete.size(); i++)
			if (tasksToComplete.get(i).getName().equals(s)) {
				Task t = tasksToComplete.get(i);

				currentXP += t.getExperience();
				addSkill(t.getRelatedSkill());
				completedTasks.add(t);
				tasksToComplete.remove(i);
				levelUp();
				return;
			}
	}

	/**
	 * Completes the Task passed to the method. Adds the experience points
	 * attached to the Task t to the current amount of experience points that
	 * the Player has, adds the Skill attached to the Task to the list of skills
	 * of this Player, adds the Task passed to the list of completed tasks, and
	 * removes the Task passed to the list.
	 * 
	 * @param Task
	 *            to complete
	 */
	public void completeTask(Task t) {
		if (tasksToComplete.contains(t)) {
			currentXP += t.getExperience();
			addSkill(t.getRelatedSkill());
			completedTasks.add(t);
			tasksToComplete.remove(t);
			levelUp();
			return;
		}
	}

	/**
	 * Removes the Task from the list of tasks to complete that has the same
	 * name as the String passed
	 * 
	 * @param Name
	 *            of Task to remove
	 */
	public void removeTask(String s) {
		for (int i = 0; i < tasksToComplete.size(); i++)
			if (tasksToComplete.get(i).getName().equals(s)) {
				tasksToComplete.remove(i);
				return;
			}
	}

	/**
	 * Removes Task passed from list of tasks to complete.
	 * 
	 * @param Task
	 *            to remove
	 */
	public void removeTask(Task t) {
		tasksToComplete.remove(t);
	}

	/**
	 * Removes all the Tasks in both toComplete and completedTasks
	 */
	public void removeAllTasks() {
		tasksToComplete = new ArrayList<Task>();
		completedTasks = new ArrayList<Task>();
	}

	/**
	 * Removes all the tasks for this player to complete.
	 */
	public void removeAllTasksToComplete() {
		tasksToComplete = new ArrayList<Task>();
	}

	/**
	 * Removes all the tasks that have already been completed.
	 */
	public void removeAllCompletedTasks() {
		completedTasks = new ArrayList<Task>();
	}

	/**
	 * Returns how many tasks there are left to complete for this Player.
	 * 
	 * @return number of tasks left
	 */
	public int howManyTasksToComplete() {
		return tasksToComplete.size();
	}

	/**
	 * Returns how many tasks have been completed by this Player
	 * 
	 * @return number of tasks completed
	 */
	public int howManyCompletedTasks() {
		return completedTasks.size();
	}

	/**
	 * Adds the Skill passed to the skills collection. If the Skill passed
	 * already exists within the Map<Skills, Integer> skills, then the value of
	 * that particular Skill within the map goes up by one. Otherwise, the
	 * passed Skill is added to skills with a value of 1.
	 * 
	 * @param Skill
	 *            to add
	 */
	public void addSkill(Skill newSkill) {
		if (skills.containsKey(newSkill)) {
			skills.put(newSkill, skills.get(newSkill) + 1);
		} else
			skills.put(newSkill, 1);
	}

	/**
	 * Gives the integer value attached to the skill passed (or the Skill level,
	 * if you will).
	 * 
	 * @param aSkill
	 * @return integer skill level
	 */
	public int getSkillValue(Skill aSkill) {
		return skills.get(aSkill);
	}

	/**
	 * Sets the value of the Skill passed to the value passed. Does nothing if
	 * the Skill passed isn't within the collection of skills, or if the value
	 * passed is less than or equal to zero.
	 * 
	 * @param Skill
	 *            to set
	 * @param Integer
	 *            value to set
	 */
	public void setSkillValue(Skill skill, int value) {
		if (!skills.containsKey(skill) || value <= 0)
			return;
		else
			skills.put(skill, value);
	}

	/**
	 * Removes the passed skill from the skills collection.
	 * 
	 * @param Skill
	 *            to remove
	 */
	public void removeSkill(Skill skill) {
		skills.remove(skill);
	}

	/**
	 * Adds the Quest passed to the collection of Quest objects held within this
	 * Player.
	 * 
	 * @param Quest
	 *            to add
	 */
	public void addQuest(Quest q) {
		questsToComplete.add(q);
	}

	/**
	 * Add the Task passed to the Quest passed.
	 * 
	 * @param Quest
	 *            to add the Task to
	 * @param Task
	 *            to add
	 */
	public void addTaskToQuest(Quest q, Task t) {
		if (!questsToComplete.contains(q))
			return;

		int index = questsToComplete.indexOf(q);
		questsToComplete.get(index).addTask(t);
	}

	/**
	 * Completes the Task passed within the Quest object passed, if and only if
	 * both exist within their respective collections. Otherwise, this method
	 * does nothing.
	 * 
	 * @param Quest
	 *            to complete a task in
	 * @param Task
	 *            to complete
	 */
	public void completeTaskInQuest(Quest q, Task t) {
		if (questsToComplete.contains(q)) {
			int index = questsToComplete.indexOf(q);
			questsToComplete.get(index).completeTask(t);
		}
	}

	/**
	 * Completes the Task that matches the task name passed within the Quest
	 * object passed if and only if the Task matching the task name passed
	 * exists and if the Quest object passed exists. Otherwise, this method does
	 * nothing.
	 * 
	 * @param Quest
	 *            to complete a task in
	 * @param String
	 *            name of task to complete
	 */
	public void completeTaskInQuest(Quest q, String taskName) {
		if (questsToComplete.contains(q)) {
			int index = questsToComplete.indexOf(q);

			for (Task t : questsToComplete.get(index).getTasksToComplete())
				if (t.getName().equals(taskName)) {
					questsToComplete.get(index).completeTask(t);
					return;
				}

		}
	}

	/**
	 * Completes the Task matching the task name passed within the Quest object
	 * that the quest name passed matches. This method does nothing if there are
	 * no matches between each of the parameters and the collection of quests
	 * and tasks within those quests.
	 * 
	 * @param String
	 *            name of quest
	 * @param String
	 *            name of task to complete within the quest requested
	 */
	public void completeTaskInQuest(String questName, String taskName) {
		for (Quest q : questsToComplete)
			if (q.getQuestName().equals(questName))
				for (Task t : q.getTasksToComplete())
					if (t.getName().equals(taskName)) {
						q.completeTask(t);
						return;
					}
	}

	/**
	 * Removes the task passed to the quest passed. If either doesn't exist in
	 * their respective collections, this method does nothing.
	 * 
	 * @param Quest
	 *            to remove task from
	 * @param Task
	 *            to remove
	 */
	public void removeTaskFromQuest(Quest q, Task t) {
		if (questsToComplete.contains(q)) {
			int index = questsToComplete.indexOf(q);
			questsToComplete.get(index).removeTask(t);
		}
	}

	/**
	 * Removes the Task within the Quest passed matching the task name passed.
	 * If the Quest doesn't exist within the collection of quests to complete,
	 * or if the Task matching the task name passed isn't within that Quest,
	 * then this method does nothing.
	 * 
	 * @param Quest
	 *            to remove task from
	 * @param String
	 *            task to remove
	 */
	public void removeTaskFromQuest(Quest q, String taskName) {
		if (questsToComplete.contains(q)) {
			int index = questsToComplete.indexOf(q);
			for (Task t : questsToComplete.get(index).getTasksToComplete())
				if (t.getName().equals(taskName)) {
					questsToComplete.get(index).removeTask(t);
					return;
				}
		}
	}

	/**
	 * Removes the Task matching the task name passed from the Quest matching
	 * the questName passed. If neither of these two names match anything within
	 * their respective collections, then this method does nothing.
	 * 
	 * @param questName
	 * @param taskName
	 */
	public void removeTaskFromQuest(String questName, String taskName) {
		for (Quest q : questsToComplete)
			if (q.getQuestName().equals(questName))
				for (Task t : q.getTasksToComplete())
					if (t.getName().equals(taskName)) {
						q.removeTask(t);
						return;
					}
	}

	/**
	 * Completes the Quest passed. If the Quest is not completed, or if the
	 * Quest does not exist within this Player object, this method does nothing.
	 * 
	 * @param Quest
	 *            to complete.
	 */
	public void completeQuest(Quest q) {
		if (!questsToComplete.contains(q))
			return;

		int index = questsToComplete.indexOf(q);

		if (questsToComplete.get(index).howManyCompletedTasks() == 0
				|| questsToComplete.get(index).howManyTasksToComplete() != 0)
			return;

		// Now that we know for certain that the Quest requested is inside the
		// collection of tasks and that that Quest is completed, we can extract
		// everything we need, and add it to this Player object.

		// Get all the XP and skills from each of the tasks completed in the
		// Quest
		for (Task t : q.getCompletedTasks()) {
			currentXP += t.getExperience();
			addSkill(t.getRelatedSkill());
		}

		// Get all the Skills from this Quest
		for (Skill s : q.getAllRelatedSkills())
			addSkill(s);

		// Add the XP
		currentXP += q.getRewardXP();

		// Remove the quest/add the quest properly
		completedQuests.add(q);
		questsToComplete.remove(q);

		levelUp();
	}

	/**
	 * Removes the Quest object within the collection of quests to complete
	 * matching the String quest name passed.
	 * 
	 * @param String
	 *            name of quest
	 */
	public void removeQuest(String questName) {
		for (Quest q : questsToComplete)
			if (q.getQuestName().equals(questName)) {
				int index = questsToComplete.indexOf(q);
				questsToComplete.remove(index);
				return;
			}
	}

	/**
	 * Removes the Quest passed from the collection of Quest object held within
	 * this Player.
	 * 
	 * @param Quest
	 *            to remove
	 */
	public void removeQuest(Quest q) {
		questsToComplete.remove(q);
	}

	/**
	 * Saves the current instance of the Player class. This method will
	 * automatically save the Player to the root directory of this project or
	 * jar file.
	 */
	public void savePlayer() {
		String saveString = saveString();

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(name
					+ ".plr"));
			out.write(saveString);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Saves the current instance of Player class. This method will save the
	 * current instance of the player method to the File passed.
	 * 
	 * @param File
	 *            to save the player to
	 * 
	 *            TO BE IMPLEMENTED
	 */
	public void savePlayer(File f) {

	}

	/**
	 * Returns the String that is used to save the Player in each of the save()
	 * methods.
	 * 
	 * @return the save String
	 */
	public String saveString() {
		// Player name, title, level, currentXP, and nextLevelXP
		String result = name.replace(' ', '_') + " " + title.replace(' ', '_')
				+ "\n";
		result += level + " " + currentXP + " " + nextLevelXP + "\n";

		// Attributes and values
		Iterator<?> it = attributes.entrySet().iterator();

		while (it.hasNext()) {
			// Because we got the right file and stuff, we can suppress the
			// warning
			// Java tosses at us with @SuppressWarnings
			@SuppressWarnings("unchecked")
			Map.Entry<Attribute, Integer> outAttributes = (Entry<Attribute, Integer>) it
					.next();
			result += outAttributes.getKey().toString() + " "
					+ outAttributes.getValue() + " ";
		}

		// Skills and values
		result += "\n" + skills.size() + "\n";
		it = skills.entrySet().iterator();

		while (it.hasNext()) {
			// We suppress the warning again here.
			@SuppressWarnings("unchecked")
			Entry<Skill, Integer> next = (Entry<Skill, Integer>) it.next();
			Map.Entry<Skill, Integer> outSkills = next;
			result += outSkills.getKey().toString().replace(' ', '_') + " "
					+ outSkills.getValue() + "\n";
		}

		// tasksToComplete
		result += "\n" + tasksToComplete.size() + "\n";
		for (Task t : tasksToComplete)
			result += t.getName().replace(' ', '_') + " "
					+ t.getDifficulty().toString().replace(' ', '_') + "\n";

		// completedTasks
		result += "\n" + completedTasks.size() + "\n";
		for (Task t : completedTasks)
			result += t.getName().replace(' ', '_') + " "
					+ t.getDifficulty().toString().replace(' ', '_') + "\n";

		return result;
	}

	/**
	 * Loads an instance of the Player class.
	 * 
	 * @param File
	 *            of the Player (.plr extension)
	 * @throws FileNotFoundException
	 */
	public void loadPlayer(File f) throws FileNotFoundException {
		Scanner s = new Scanner(f);

		name = s.next().replace('_', ' ');
		title = s.next().replace('_', ' ');

		level = s.nextInt();
		currentXP = s.nextInt();
		nextLevelXP = s.nextInt();

		attributes = new TreeMap<Attribute, Integer>();
		skills = new HashMap<Skill, Integer>();

		for (int i = 1; i <= 6; i++)
			attributes.put(Attribute.toAttribute(s.next()), s.nextInt());

		int numberOfSkills = s.nextInt();

		for (int i = 0; i < numberOfSkills; i++) {
			String skillName = s.next().replace('_', ' ');
			String attributeName = s.next();
			int skillLevel = s.nextInt();

			if (attributeName.equals("null"))
				skills.put(new Skill(skillName, null), skillLevel);
			else
				skills.put(
						new Skill(skillName, Attribute
								.toAttribute(attributeName)), skillLevel);
		}

		tasksToComplete = new ArrayList<Task>();

		int numberOfTasks = s.nextInt();

		for (int i = 0; i < numberOfTasks; i++)
			tasksToComplete.add(new Task(s.next().replace('_', ' '), Difficulty
					.toDifficulty(s.next().replace('_', ' '))));

		completedTasks = new ArrayList<Task>();

		int numberOfCompletedTasks = s.nextInt();

		for (int i = 0; i < numberOfCompletedTasks; i++)
			completedTasks.add(new Task(s.next().replace('_', ' '), Difficulty
					.toDifficulty(s.next().replace('_', ' '))));
	}

	/**
	 * Compares this Player to another Player object.
	 * 
	 * TO BE IMPLEMENTED
	 * 
	 * @param Player
	 *            p
	 * @return Integer
	 */
	public int compareTo(Player p) {

		return 0;// a stub DO NOT RETURN 0
	}

	/**
	 * Tests to see if the object passed is equivalent to this instance of the
	 * Player class. Does so by checking equality in name, title and level.
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Player))
			return false;

		Player p = (Player) o;

		if (!name.equals(p.getName()))
			return false;
		if (!title.equals(p.getTitle()))
			return false;
		if (level != p.getLevel())
			return false;

		return true;
	}

	/**
	 * Converts current instance of Player to a String.
	 * 
	 * TO BE IMPLEMENTED
	 */
	public String toString() {
		String str = "Name: " + name + "\nTitle: " + title;
		str += "\nLevel " + level + "XP: " + currentXP + "\nTasks:\n";

		for (Task t : tasksToComplete)
			str += t.getName() + "\n";

		str += "\nSkills:\n";

		for (Skill s : skills.keySet()) {
			int value = skills.get(s);
			str += s.getName() + "\t" + value + "\n";
		}

		str += "\nQuests:\n";

		for (Quest q : questsToComplete)
			str += q.getQuestName();

		return str;// a stub; DO NOT RETURN NULL
	}

	/**
	 * Returns the name of this Player object.
	 * 
	 * @return String name of player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this Player to the name passed.
	 * 
	 * @param String
	 *            new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the title of this Player object.
	 * 
	 * @return String title of player
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets this player's title to the String passed.
	 * 
	 * @param String
	 *            new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns an Integer of this Player's current level.
	 * 
	 * @return Integer Player level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets this Player's level to the integer passed.
	 * 
	 * @param Integer
	 *            new Player's level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Returns the current amount of XP this Player has.
	 * 
	 * @return Integer current amount of experience points
	 */
	public int getCurrentXP() {
		return currentXP;
	}

	/**
	 * Sets the current XP of this Player to the Integer passed.
	 * 
	 * @param Integer
	 *            new value of current experience points
	 */
	public void setCurrentXP(int currentXP) {
		this.currentXP = currentXP;
	}

	/**
	 * Returns the amount of XP necessary for this Player to go to the next
	 * level.
	 * 
	 * @return Integer amount of experience points necessary to gain a level
	 */
	public int getNextLevelXP() {
		return nextLevelXP;
	}

	/**
	 * Returns the Map<Attribute, Integer> object associated with the Attribute
	 * scores of this Player object.
	 * 
	 * @return Map<Attribute, Integer> of this Player's Attribute scores
	 */
	public Map<Attribute, Integer> getAttributes() {
		return attributes;
	}

	/**
	 * Returns a Map<Skill, Integer> object with this Player's Skills and skill
	 * level data inside.
	 * 
	 * @return Map<Skill, Integer> this Player's skill data
	 */
	public Map<Skill, Integer> getSkills() {
		return skills;
	}

	/**
	 * 
	 * @return
	 */
	public List<Task> getCurrentTasks() {
		return tasksToComplete;
	}

	public List<Task> getCompletedTasks() {
		return completedTasks;
	}

	public List<Quest> getQuestsToComplete() {
		return questsToComplete;
	}

	public List<Quest> getCompletedQuests() {
		return completedQuests;
	}
}