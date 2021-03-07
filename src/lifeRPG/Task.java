package lifeRPG;

/**
 * A data representation of a Task that one may need to accomplish in real life.
 * A Task object contains a name, a certain difficulty, an amount of experience
 * points that are rewarded when the task is completed in which the amount of
 * points rewarded are dependent upon the Difficulty, and a Skill that is
 * improved/gained by completing that particular Task.
 * 
 * @author André C. LaFleur
 * 
 */
public class Task {

	private String name;
	private int experience;
	private Skill relatedSkill;
	private Difficulty difficulty;

	/**
	 * Creates instance of Task class. Contains a name, difficulty, rewarded
	 * experience and a related Skill of some sort. Note that the relatedSkill
	 * variable is an instance of the Skill class, therefore you must pass a
	 * relatedSkill into this constructor.
	 * 
	 * @param _name
	 * @param _diff
	 * @param _relatedSkill
	 */
	public Task(String _name, Skill _relatedSkill, Difficulty _diff) {
		name = _name;
		difficulty = _diff;

		switch (difficulty) {
		case VERY_EASY:
			experience = 5;
			break;
		case EASY:
			experience = 10;
			break;
		case MEDIUM:
			experience = 25;
			break;
		case HARD:
			experience = 50;
			break;
		case VERY_HARD:
			experience = 100;
		}

		relatedSkill = _relatedSkill;
	}

	/**
	 * Constructor of Task class. Use this constructor for a Task that does not
	 * have a related skill. The related skill object within this Task will be
	 * set to null.
	 * 
	 * @param _name
	 * @param _diff
	 */
	public Task(String _name, Difficulty _diff) {
		name = _name;
		difficulty = _diff;

		switch (difficulty) {
		case VERY_EASY:
			experience = 5;
			break;
		case EASY:
			experience = 10;
			break;
		case MEDIUM:
			experience = 25;
			break;
		case HARD:
			experience = 50;
			break;
		case VERY_HARD:
			experience = 100;
		}

		relatedSkill = null;
	}
	
	/**
	 * Returns the hash code of this Task object. This method uses name's
	 * hash code and relatedSkill's hash code.
	 */
	public int hashCode(){
		return name.hashCode() + relatedSkill.hashCode();
	}

	/**
	 * Tests to see if the this Player is identical to a passed Object.
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Task))
			return false;

		Task task = (Task) o;
		
		if(!name.equals(task.getName()) || !difficulty.equals(task.getDifficulty()))
			return false;
		
		if((relatedSkill == null && task.getRelatedSkill() == null) 
				|| relatedSkill.equals(task.getRelatedSkill()))
			return true;
		
		return false;
	}

	/**
	 * Returns String version of the Task class.
	 */
	public String toString() {
		return name + "\t" + difficulty.toString() + "\t" + experience;
	}

	/**
	 * Provides the name of this Task.
	 * 
	 * @return String name of Task
	 */
	public String getName() {
		return name;
	}

	/**
	 * Provides the Difficulty of this Task.
	 * 
	 * @return Difficulty of this Task
	 */
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	/**
	 * Returns the amount of experience points that are rewarded upon the
	 * completion of this Task.
	 * 
	 * @return An integer experience points
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * Returns the Skill object that is attached to this Task.
	 * 
	 * @return a Skill object
	 */
	public Skill getRelatedSkill() {
		return this.relatedSkill;
	}
}
