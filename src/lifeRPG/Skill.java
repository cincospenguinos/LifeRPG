package lifeRPG;

/**
 * Representation of a Skill in an RPG setting. Skill has a name, value (or
 * "skill level"), and an attribute associated with it. 
 * 
 * @author Dr.Dre
 * 
 */
public class Skill {

	private String name;
	private Attribute attribute;

	/**
	 * Constructor that creates instance of Skill class.
	 * 
	 * @param _name
	 * @param _value
	 */
	public Skill(String _name, Attribute _attr) {
		name = _name;
		attribute = _attr;
	}
	
	/**
	 * Returns the hash code of this object. The code is based on the name's hash
	 * code and the attribute's hash code.
	 */
	public int hashCode(){
		return name.hashCode() + attribute.hashCode();
	}
	
	/**
	 * Compares the equality of two Skills. Both skills must have the same name,
	 * and attribute to be considered equivalent.
	 */
	public boolean equals(Object o){
		if(!(o instanceof Skill))
			return false;
		
		Skill skill = (Skill) o;
		
		if(this.getName().equals(skill.getName()) 
				|| this.getAttribute().equals(skill.getAttribute()))
			return true;
		
		return false;
	}

	/**
	 * Returns a String representing the name of the skill.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the Attribute related to this Skill.
	 * 
	 * @return attr
	 */
	public Attribute getAttribute() {
		return attribute;
	}
	
	/**
	 * Returns a readable string of this Skill object.
	 */
	public String toString() {
		return name + "\t" + attribute;
	}
}
