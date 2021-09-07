package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * An abstract class for ground type objects that age over time.
 * @author Vanessa
 *
 */
public abstract class GrowableGround extends Ground {
	private int age = 0;
	
	/**
	 * Constructor to make a GrowableGround type object.
	 * 
	 * @param displayChar the character to be displayed on the game map
	 */
	public GrowableGround(char displayChar) {
		super(displayChar);
	}
	
	/**
	 * Getter for age.
	 * 
	 * @return the ground's age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Changes display character when its age reaches 10 and changes again when age reaches 20.
	 * 
	 * @param location the location of the GrowableGround object
	 * @param midDisplay the displayChar when its age reaches 10
	 * @param olderDisplay the displayChar when its age reaches 20
	 */
	public void tick(Location location, char midDisplay, char olderDisplay) {
		super.tick(location);
		
		age++;
		if (age >= 10)
			displayChar = midDisplay;
		if (age >= 20)
			displayChar = olderDisplay;
	}
}
