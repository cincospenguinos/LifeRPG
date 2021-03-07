package lifeRPG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class that is meant to represent a Quest in a Role Playing Game. A quest is
 * really just a collection of tasks. And so, this class holds a collection of
 * tasks, figures out an experience point bonus for completing a Quest, and
 * holds a collection of skills related to the quest that get improved also.
 * 
 * @author Andre C. LaFleur
 * 
 */
public class Quest {

	private int rewardXP;

	private String questName;

	private List<Task> tasksToComplete;
	private List<Task> completedTasks;
	private Map<Skill, Integer> relatedSkills;

	/**
	 * Constructor method. Creates Quest object.
	 */
	public Quest(String _questName) {
		rewardXP = 0;// This will be set and changed as things roll along.

		questName = _questName;

		tasksToComplete = new ArrayList<Task>();
		completedTasks = new ArrayList<Task>();
		relatedSkills = new HashMap<Skill, Integer>();
	}

	/**
	 * Sets rewardXP to the proper amount relating to the number of tasks to
	 * complete and difficulty of tasks that are within this Quest object.
	 */
	public void generateCurrentRewardXP() {
		if (tasksToComplete.size() == 0)
			return;

		int average = 0;

		// Get the XP amount in tasksToComplete
		for (Task t : tasksToComplete)
			average += t.getExperience();

		// Get the XP amount in completedTasks
		if (completedTasks.size() != 0)
			for (Task t : completedTasks)
				average += t.getExperience();

		rewardXP = average / tasksToComplete.size();
	}

	/**
	 * Adds the Task object passed to the list of Tasks held within this Quest
	 * object.
	 * 
	 * @param Task
	 *            to add to list
	 */
	public void addTask(Task t) {
		tasksToComplete.add(t);
	}

	/**
	 * Completes the task, or moves the task from the tasks to complete list and
	 * puts it into the completed tasks list. This method does nothing if the
	 * Task does not exist within the list of tasks to complete.
	 * 
	 * @param Tasks
	 *            to complete
	 */
	public void completeTask(Task t) {
		if (!tasksToComplete.contains(t))
			return;

		// Get to the task over to completedTasks
		int index = tasksToComplete.indexOf(t);
		completedTasks.add(tasksToComplete.get(index));

		// Remove the Task from the tasksToComplete list
		tasksToComplete.remove(index);
	}

	/**
	 * Removes the Task object passed from the list of Tasks held within this
	 * Quest object.
	 * 
	 * @param Task
	 *            to remove
	 */
	public void removeTask(Task t) {
		tasksToComplete.remove(t);
	}

	/**
	 * Adds the Skill passed to the collection of related skills within this
	 * quest object. If the Skill object passed exists within the the list of
	 * related skills, then this method adds one to its value within the list of
	 * Skills.
	 * 
	 * @param Skill
	 *            to add
	 */
	public void addRelatedSkill(Skill s) {
		if (!relatedSkills.containsKey(s))
			relatedSkills.put(s, 1);
		else
			relatedSkills.put(s, relatedSkills.get(s) + 1);
	}

	public void removeRelatedSkill(Skill s) {
		if (!relatedSkills.containsKey(s))
			return;

		if (relatedSkills.get(s) == 1)
			relatedSkills.remove(s);
		else
			relatedSkills.put(s, relatedSkills.get(s) - 1);
	}

	/**
	 * Returns the name of this Quest.
	 * 
	 * @return String name of quest
	 */
	public String getQuestName() {
		return questName;
	}

	/**
	 * Returns the integer amount of experience points rewarded upon finishing
	 * this quest.
	 * 
	 * @return Integer extra experience rewarded.
	 */
	public int getRewardXP() {
		generateCurrentRewardXP();

		return rewardXP;
	}

	/**
	 * Returns a Set collection of all the related skills held within this Quest
	 * object. Please note that the amount of times each Skill object is
	 * contained within this Quest object is not included in this set.
	 * 
	 * @return Set<Skill> collection of related skills
	 */
	public Set<Skill> getAllRelatedSkills() {
		return relatedSkills.keySet();
	}
	
	/**
	 * Sets the Quest name to the String passed.
	 * 
	 * @param String name of quest
	 */
	public void setQuestName(String questName) {
		this.questName = questName;
	}
	
	/**
	 * Returns the list of tasks to complete.
	 * 
	 * @return List<Task> list of tasks to complete
	 */
	public List<Task> getTasksToComplete(){
		return tasksToComplete;
	}
	
	/**
	 * Returns the list of tasks that are already completed.
	 * 
	 * @return List<Task> list of completed tasks
	 */
	public List<Task> getCompletedTasks(){
		return completedTasks;
	}
	
	/**
	 * Sets the tasks to complete to the list of tasks passed.
	 * 
	 * @param List<Task> of tasks to complete
	 */
	public void setTasksToComplete(List<Task> tasksToComplete) {
		this.tasksToComplete = tasksToComplete;
	}
	
	/**
	 * Set the tasks completed to the list of tasks passed.
	 * 
	 * @param List<Task> of tasks to complete
	 */
	public void setCompletedTasks(List<Task> completedTasks) {
		this.completedTasks = completedTasks;
	}

	public void setRelatedSkills(Map<Skill, Integer> relatedSkills) {
		this.relatedSkills = relatedSkills;
	}

	/**
	 * Returns the amount of Tasks needed to complete in order to complete 
	 * this quest.
	 * 
	 * @return Integer how many tasks are left to complete
	 */
	public int howManyTasksToComplete(){
		return tasksToComplete.size();
	}
	
	/**
	 * Returns the amount of Tasks completed.
	 * 
	 * @return Integer how many tasks are completed
	 */
	public int howManyCompletedTasks(){
		return completedTasks.size();
	}
}
