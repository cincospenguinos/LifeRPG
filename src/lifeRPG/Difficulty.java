package lifeRPG;

public enum Difficulty {
	
	VERY_EASY, EASY, MEDIUM, HARD, VERY_HARD;
	
	
	/**
	 * Converts current Difficulty into a String representation.
	 */
	public String toString(){
		switch(this){
		case VERY_EASY:
			return "Very Easy";
		case EASY:
			return "Easy";
		case MEDIUM:
			return "Medium";
		case HARD:
			return "Hard";
		case VERY_HARD:
			return "Very Hard";
		default:
			return "";
		}
	}
	
	/**
	 * Gets the Difficulty enumeration that corresponds to the String. Returns
	 * null if the String s cannot be tied to a Difficulty. If a difficulty
	 * cannot be found, this method returns null.
	 * 
	 * @param String s
	 * @return Difficulty or null
	 */
	public static Difficulty toDifficulty(String s){
		if(s.equals("Very Easy") || s.equals("Very_Easy"))
			return Difficulty.VERY_EASY;
		else if(s.equals("Easy"))
			return Difficulty.EASY;
		else if(s.equals("Medium"))
			return Difficulty.MEDIUM;
		else if(s.equals("Hard"))
			return Difficulty.HARD;
		else if(s.equals("Very Hard") || s.equals("Very_Hard"))
			return Difficulty.VERY_HARD;
		else{
			return null;
		}
	}
	
	/**
	 * Gets the Difficulty enumeration that is equivalent to the integer passed.
	 * The Integer must satisfy conditions i > 0 || i < 5 to receive a Difficulty
	 * enumeration back, otherwise this method returns null. 
	 * 
	 * Difficulties and their respective integer values:
	 * 
	 * 0 = Difficulty.Very_Easy
	 * 1 = Difficulty.Easy
	 * 2 = Difficulty.Medium
	 * 3 = Difficulty.Hard
	 * 4 = Difficulty.Very_Hard
	 * 
	 * @param Integer i
	 * @return Difficulty of Integer i or null
	 */
	public static Difficulty toDifficulty(int i){
		switch(i){
		case 0:
			return Difficulty.VERY_EASY;
		case 1:
			return Difficulty.EASY;
		case 2:
			return Difficulty.MEDIUM;
		case 3:
			return Difficulty.HARD;
		case 4:
			return Difficulty.VERY_HARD;
		}
		
		return null;
	}
}
