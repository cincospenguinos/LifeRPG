package lifeRPG;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuestTest {
	
	private Quest aQuest;
	private Player andre;
	
	private Task eat, puke, goToDoctor, getBetter;
	private Skill eating, soberPuking;
	
	@Before
	public void setUp() throws Exception {
		aQuest = new Quest("Epic Quest!");
		
		int[] attr = new int[6];
		
		for (int j = 0; j < attr.length; j++) {
			attr[j] = 12;
		}
		
		andre = new Player("AndrŽ C. LaFleur", "Typical Badass", attr);
		
		eating = new Skill("Eating", null);
		soberPuking = new Skill("Sober Puking", Attribute.STR);
		
		eat = new Task("Eat", eating, Difficulty.VERY_EASY);
		puke = new Task("Puke", soberPuking, Difficulty.HARD);
		goToDoctor = new Task("Go to Doctor", null, Difficulty.MEDIUM);
		getBetter = new Task("Get Better", new Skill(
				"Slothfulness", Attribute.CON), Difficulty.EASY);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 * Methods to test:
	 * All of them. srsly, we need to rigorously test this class.
	 */
	@Test
	public void generateCurrentRewardXP(){
		aQuest.addTask(eat);
		aQuest.addTask(puke);
		aQuest.addTask(goToDoctor);
		aQuest.addTask(getBetter);
		
		int beforeXP = andre.getCurrentXP();
		
		andre.addQuest(aQuest);
		
		andre.completeTaskInQuest(aQuest, eat);
		andre.completeTaskInQuest(aQuest, puke);
		andre.completeTaskInQuest(aQuest, goToDoctor);
		andre.completeTaskInQuest(aQuest, getBetter);
		
		andre.completeQuest(aQuest);
		
		int afterXP = andre.getCurrentXP();
		
		assertFalse(beforeXP == afterXP);
	}

}
