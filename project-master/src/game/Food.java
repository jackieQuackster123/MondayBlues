package game;

import edu.monash.fit2099.engine.Action;

/**
 * Class that represents Food. Food objects are items that are created when a Crop is
 * harvested. A damaged human can eat this to heal damage.
 * @author Vanessa
 *
 */
public class Food extends PortableItem{
	private int healPoints;
	
	/**
	 * Constructor for Food. Food can heal.
	 * 
	 * @param healPoints the number of points this food can heal
	 */
	public Food(int healPoints) {
		super("food", '*');
		this.healPoints = healPoints;
		this.allowableActions.add(new HealAction(this, healPoints));
	}
	
	/**
	 * Eat the food. Creates a HealAction to heal some points.
	 * 
	 * @return a new HealAction
	 */
	public Action eat() {
		return new HealAction(this, healPoints);
	}
}
