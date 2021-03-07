package lifeRPG;

/**
 * A representation of the six base attributes in a typical Role Playing Game,
 * but more specifically Dungeons and Dragons.
 * 
 * @author Andre C. LaFleur
 *
 */
public enum Attribute {
	
	STR, CON, DEX, INT, WIS, CHA;
	
	/**
	 * Converts current Attribute into a String representation.
	 */
	public String toString(){
		switch(this){
		case STR:
			return "STR";
		case CON:
			return "CON";
		case DEX:
			return "DEX";
		case INT:
			return "INT";
		case WIS:
			return "WIS";
		case CHA:
			return "CHA";
		default:
			return "";
		}
	}
	
	/**
	 * Returns the Attribute equivalent to the String passed. String s
	 * may be upper case, lower case or mixed case. If the String s
	 * does not match an Attribute, this method returns null.
	 * 
	 * @param s
	 * @return Equivalent Attribute or null
	 */
	public static Attribute toAttribute(String s){
		if(s == null)
			return null;
		
		s = s.toUpperCase();
		
		if(s.equals("STR"))
			return Attribute.STR;
		else if(s.equals("CON"))
			return Attribute.CON;
		else if(s.equals("DEX"))
			return Attribute.DEX;
		else if(s.equals("INT"))
			return Attribute.INT;
		else if(s.equals("WIS"))
			return Attribute.WIS;
		else if(s.equals("CHA"))
			return Attribute.CHA;
		else
			return null;
	}
	
	/**
	 * Returns the Attribute associated with the integer passed. If the integer
	 * passed is not between 0 and 5, this method returns null.
	 * 
	 * @param Integer
	 * @return Attribute or null if the integer passed isn't within range
	 */
	public static Attribute toAttribute(int i){
		switch(i){
		case 0:
			return STR;
		case 1:
			return CON;
		case 2:
			return DEX;
		case 3:
			return INT;
		case 4:
			return WIS;
		case 5:
			return CHA;
		default:
			return null;
		}
	}
}
