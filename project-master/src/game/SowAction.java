package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Action class to sow crops.
 * @author Vanessa
 *
 */
public class SowAction extends Action{
	private Location cropLocation;
	
	/**
	 * Constructor for SowAction.
	 * 
	 * @param cropLocation the location where the crop is to be sown
	 */
	public SowAction(Location cropLocation) {
		this.cropLocation = cropLocation;
	}

	/**
	 * Creates a crop object at cropLocation.
	 * 
	 * @param actor the actor that sows the crop
	 * @param map the map which crop is to be sown on
	 * @return a String displayed on the console
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		cropLocation.setGround(new Crop());
		return actor + " sowed a crop";
	}

	/**
	 * Description of who sows a crop.
	 * 
	 * @param actor the actor that sows the crop
	 * @return a string description of who sows a crop
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sows a crop";
	}

}
