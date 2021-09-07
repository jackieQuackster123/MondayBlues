package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Action class for fertilizing crops (aging them by 10 turns).
 * @author Vanessa
 *
 */
public class FertilizeAction extends Action {
	private Location cropLocation;
	
	/**
	 * Constructor for FertilizeAction.
	 *
	 * @param cropLocation the location of the crop to be fertilized
	 */
	public FertilizeAction(Location cropLocation) {
		this.cropLocation = cropLocation;
	}

	/**
	 * Ages the crop by 10 turns.
	 * 
	 * @param actor the actor that fertilizes the crop
	 * @param map the map which crop is on
	 * @return a String displayed on the console to show actor has fertilized crop
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Ground crop = cropLocation.getGround();
		for (int i = 1; i <= 10; i++) {
			crop.tick(cropLocation);
		}
		return actor + " fertilized a crop";
	}

	/**
	 * Description of the actor fertilizing a crop.
	 * 
	 * @param actor the actor that fertilizes the crop
	 * @return a string description of who fertilizes a crop
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilizes a crop";
	}
	
}
