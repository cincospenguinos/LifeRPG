package lifeRPG;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private Task eatFood, pukeFood, goToDoctor, getBetter;
	Skill eating, soberPuking, buttKicking;
	private Player andre;

	@Before
	public void setUp() throws Exception {
		int[] attributes = new int[6];

		for (int i = 0; i < attributes.length; i++)
			attributes[i] = 12;

		andre = new Player("AndrŽ C. LaFleur", "Typical Badass", attributes);

		eating = new Skill("Eating", null);
		soberPuking = new Skill("Sober Puking", Attribute.STR);
		buttKicking = new Skill("Butt Kicking", Attribute.STR);

		eatFood = new Task("Eat Food", eating, Difficulty.VERY_EASY);
		pukeFood = new Task("Puke Food", soberPuking, Difficulty.HARD);
		goToDoctor = new Task("Go to Doctor", null, Difficulty.MEDIUM);
		getBetter = new Task("Get Better", new Skill(
				"Slothfulness", Attribute.CON), Difficulty.EASY);
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Test
	// public void test() {
	// fail("Not yet implemented");
	// }

	// Let's test some shiz.
	/*
	 * Player
	 * 
	 * Complete: - addTaskToComplete() - getTaskToComplete(String) -
	 * getTaskToComplete(Task) - getCompletedTask(String) -
	 * getCompletedTask(Task) - completeTask(String) - completeTask(Task) -
	 * removeTask(String) - removeTask(Task) - removeAllTasksToComplete() -
	 * removeAllCompletedTasks() - removeAllTasks() - addSkill(Skill) -
	 * removeSkill(Skill)
	 * 
	 * Incomplete: - savePlayer() --> to be implemented - loadPlayer(File) -->
	 * to be implemented - compareTo(Player) --> to be implemented - toString()
	 */

	// addTaskToComplete()
	@Test
	public void addTaskToComplete1() {
		andre.addTaskToComplete(eatFood);

		assertEquals(1, andre.howManyTasksToComplete());
	}

	@Test
	public void addTaskToComplete2() {
		andre.addTaskToComplete(pukeFood);

		assertEquals(1, andre.howManyTasksToComplete());
	}

	@Test
	public void addTaskToComplete3() {
		andre.addTaskToComplete(goToDoctor);

		assertEquals(1, andre.howManyTasksToComplete());
	}

	@Test
	public void addTaskToComplete4() {
		andre.addTaskToComplete(getBetter);

		assertEquals(1, andre.howManyTasksToComplete());

	}

	// getTaskToComplete(String) and getTaskToComplete(Task)
	@Test
	public void getTaskToCompleteString1() {
		Task t = andre.getTaskToComplete("Herp");

		assertEquals(null, t);
	}

	@Test
	public void getTaskToCompleteString2() {
		andre.addTaskToComplete(goToDoctor);
		Task t = andre.getTaskToComplete("Go to Doctor");

		assertEquals(andre.getTaskToComplete(goToDoctor), t);
	}

	@Test
	public void getTaskToCompleteString3() {
		andre.addTaskToComplete(pukeFood);

		Task t = andre.getTaskToComplete("get Better");

		assertEquals(null, t);
	}

	// getCompletedTask()
	@Test
	public void getCompletedTask1() {
		andre.addTaskToComplete(eatFood);
		andre.completeTask(eatFood);

		Task t = andre.getCompletedTask(eatFood);

		assertEquals(eatFood, t);
	}

	@Test
	public void getCompletedTask2() {
		Task t = andre.getCompletedTask("herpdyderp");

		assertEquals(null, t);
	}

	@Test
	public void getCompletedTask3() {
		andre.addTaskToComplete(eatFood);
		andre.completeTask(eatFood);

		Task t = andre.getCompletedTask("Eat Food");

		assertEquals(eatFood, t);
	}

	// completeTask()
	@Test
	public void completeTask1() {
		andre.completeTask(eatFood);

		assertEquals(0, andre.getCurrentXP());
	}

	@Test
	public void completeTask2() {
		andre.addTaskToComplete(pukeFood);
		andre.completeTask("puke Food");

		assertEquals(0, andre.howManyCompletedTasks());
	}

	@Test
	public void completeTask3() {
		andre.addTaskToComplete(getBetter);
		andre.completeTask("Get Better");

		assertEquals(1, andre.howManyCompletedTasks());
	}

	@Test
	public void completeTask4() {
		andre.addTaskToComplete(goToDoctor);
		andre.completeTask(goToDoctor);

		assertEquals(1, andre.howManyCompletedTasks());
	}

	// removeTask(String) and removeTask(Task)

	@Test
	public void removeTask1() {
		andre.removeTask("Herp");

		assertEquals(0, andre.howManyTasksToComplete());
	}

	@Test
	public void removeTask2() {
		andre.addTaskToComplete(goToDoctor);
		andre.removeTask("Go to Doctor");

		assertEquals(0, andre.howManyTasksToComplete());
	}

	@Test
	public void removeTask3() {
		andre.addTaskToComplete(goToDoctor);
		andre.removeTask(goToDoctor);

		assertEquals(0, andre.howManyTasksToComplete());
	}

	@Test
	public void removeTask4() {
		andre.addTaskToComplete(eatFood);
		andre.removeTask(pukeFood);

		assertEquals(1, andre.howManyTasksToComplete());
	}

	// removeAllTasksToComplete(), removeAllCompletedTasks(), and
	// removeAllTasks()

	@Test
	public void removeAllTasksToComplete1() {
		andre.addTaskToComplete(eatFood);
		andre.removeAllTasksToComplete();

		assertEquals(0, andre.howManyTasksToComplete());
	}

	@Test
	public void removeAllTasksCompletedTasks1() {
		andre.addTaskToComplete(getBetter);
		andre.completeTask(getBetter);
		andre.removeAllCompletedTasks();

		assertEquals(0, andre.howManyCompletedTasks());
	}

	@Test
	public void removeAllTasks1() {
		andre.addTaskToComplete(eatFood);
		andre.addTaskToComplete(pukeFood);
		andre.addTaskToComplete(goToDoctor);
		andre.addTaskToComplete(getBetter);

		andre.completeTask(eatFood);
		andre.completeTask(pukeFood);

		andre.removeAllTasks();

		assertEquals(0,
				andre.howManyTasksToComplete() + andre.howManyCompletedTasks());
	}

	// addSkill() and removeSkill()

	@Test
	public void addSkill1() {
		andre.addSkill(buttKicking);

		assertEquals(1, andre.getSkills().size());
	}

	@Test
	public void addSkill2() {
		andre.addSkill(soberPuking);

		andre.addTaskToComplete(pukeFood);
		andre.completeTask(pukeFood);

		assertEquals(2, andre.getSkillValue(soberPuking));
	}

	@Test
	public void removeSkill1() {
		andre.addSkill(eating);
		andre.removeSkill(eating);

		assertEquals(0, andre.getSkills().size());
	}

	// saveString() test
	@Test
	public void saveStringTest() {
		andre.addTaskToComplete(eatFood);
		andre.addTaskToComplete(pukeFood);
		andre.addTaskToComplete(goToDoctor);
		andre.addTaskToComplete(getBetter);

		andre.addSkill(buttKicking);
		andre.addSkill(eating);

		andre.completeTask(eatFood);
		andre.completeTask(pukeFood);

		System.out.println(andre.saveString());

		assertTrue(true);
	}

	// save()
	@Test
	public void saveTest1() {
		boolean flag = true;
		try {
			andre.savePlayer();
		} catch (Exception e) {
			flag = false;
		}
		
		assertTrue(flag);
	}
	
	@Test
	public void attributeAndDifficultyEquals01(){
		Attribute a = Attribute.CHA;
		Attribute b = Attribute.CHA;
		
		Difficulty c = Difficulty.EASY;
		Difficulty d = Difficulty.EASY;
		
		
		assertEquals("Attribute didn't work", true, a.equals(b));
		assertEquals("Difficulty didn't work", true, c.equals(d));
	}
	
	@Test
	public void attributeAndDifficultyEquals02(){
		Attribute a = Attribute.CHA;
		Attribute b = Attribute.DEX;
		
		Difficulty c = Difficulty.EASY;
		Difficulty d = Difficulty.VERY_HARD;
		
		
		assertEquals("Attribute didn't work", false, a.equals(b));
		assertEquals("Difficulty didn't work", false, c.equals(d));
	}
}
