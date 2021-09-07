package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
/**
 * 
 * @author Jaclyn
 * This class inherits from portableItem. It represents the zombieLimbs that dropped when the zombie got attacked
 *
 */
public class FallenZombiePart extends PortableItem{
	
	private char bodyPartType;
	/**
	 *  
	 * @param name Name of the weapon
	 * @param displayChar The char that is representing the weapon when it gets displayed on the map
	 * @param bodyPartType 'H' for Hand or 'L' for Leg
	 */
	public FallenZombiePart(String name, char displayChar,char bodyPartType) {
		super(name, displayChar);
		this.bodyPartType=bodyPartType;
		//this.allowableActions.add(new CraftAction(this));
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @return bodyPartType ('H' for Hand or 'L' for Leg)
	 */
	public char getBodyType() {
		return bodyPartType;
	}
	/**
	 * 
	 * @return name of the weapon
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return displayChar (The char that is representing the weapon when it gets displayed on the map)
	 */
	public char getDisplayChar() {
		return displayChar;
	}
	
}
