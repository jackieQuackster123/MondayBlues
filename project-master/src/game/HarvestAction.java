package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Action class for harvesting ripe crops.
 * @author Vanessa
 *
 */
public class HarvestAction extends Action {
	Location cropLocation;
	
	/**
	 * Constructor for HarvestAction.
	 * 
	 * @param cropLocation the location on which the crop grows on
	 */
	public HarvestAction(Location cropLocation) {
		this.cropLocation = cropLocation;
	}

	/**
	 * Creates a Food object and resets the ground at cropLocation back to dirt.
	 * 
	 * @param actor the actor which is harvesting the crop
	 * @param map the map on which the crop is on
	 * @return a String displayed on console 
	 */
	@Override
	public String execute(Actor actor, GameMap map){		
		cropLocation.setGround(new Dirt());
		Food food = new Food(20);
		if (actor instanceof Farmer) {
			cropLocation.addItem(food);
		} else {
			actor.addItemToInventory(food);
		}
		return actor + " harvested a crop";
	}

	/**
	 * Description of actor harvesting a crop.
	 * 
	 * @param actor the actor which is doing the harvesting
	 * @return a string description of who harvests a crop
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvests a crop";
	}
	
	
}
