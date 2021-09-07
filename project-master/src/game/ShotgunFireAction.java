package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Action class for shooting the shotgun.
 * @author Vanessa
 *
 */
public class ShotgunFireAction extends Action{
	private Exit direction;
	private RangedWeapon shotgun;
	private Ammunition ammo;
	static final String NORTH = "8";
	static final String NORTH_EAST = "9";
	static final String EAST = "6";
	static final String SOUTH_EAST = "3";
	static final String SOUTH = "2";
	static final String SOUTH_WEST = "1";
	static final String WEST = "4";
	static final String NORTH_WEST = "7";
	
	/**
	 * Constructor. Adds capability RangedWeaponCapability.SHOOT to let the item know
	 * it's currently being used to shoot.
	 * 
	 * @param e			the direction the shotgun was fired
	 * @param shotgun	the weapon used to fire
	 * @param ammo		the ammunition used to fire
	 */
	public ShotgunFireAction(Exit e, RangedWeapon shotgun, Ammunition ammo) {
		this.direction = e;
		this.shotgun = shotgun;
		this.ammo = ammo;
		shotgun.addCapability(RangedWeaponCapability.SHOOT);
	}
	
	/**
	 * For every actor in the range, there's a 75% chance of hitting them. RangedWeaponCapability.SHOOT 
	 * is removed from the shotgun's set of capabilities after so that the shotgun knows that it is done firing.
	 * If ammo doesn't have anymore rounds, remove from actor's inventory.
	 * 
	 * @param actor		the actor firing the shotgun
	 * @param map		the map the actor is on
	 * @return			a string saying which direction shotgun is fired in and the result of shooting the actors in this range.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " fires the shotgun " + direction.getName();
		List<Location> range = getRange(map);
		for (Location l : range) {
			 if (l.containsAnActor()){
			    Actor target=l.getActor();
			    HumanAttackAction shoot = new HumanAttackAction(target);
			    result += "\n" + shoot.execute(actor, map, shotgun, 0.75);      
			 	}
			
			 }
		shotgun.removeCapability(RangedWeaponCapability.SHOOT);
		
		ammo.shotFired();
		if (ammo.getRounds() == 0) {
			actor.removeItemFromInventory(ammo);		
			result += "\nThis ammunition has no more rounds.";
		}
		
		return result;
		}

	/**
	 * Returns a string saying which direction to fire the shotgun.
	 * 
	 * @param actor the actor firing the shotgun
	 * @return 		a string saying to fire the shotgun in the direction.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Fire the shotgun " + direction.getName();
	}
	
	/**
	 * Follows the exit's hotkey for user's convenience.
	 * 
	 * @return the exit's hotkey
	 */
	@Override
	public String hotkey() {
		return direction.getHotKey();
	}
	
	/**
	 * Gets the locations in the range of the direction the shotgun was fired in.
	 * 
	 * @param map	the map the shotgun was fired in
	 * @return		a list of all the map locations within range
	 */
	private ArrayList<Location> getRange(GameMap map) {
		ArrayList<Location> range = new ArrayList<Location>();
		int x = direction.getDestination().x();
		int y = direction.getDestination().y();
		int right = 0;
		int up = 0;
		if (direction.getHotKey() == NORTH || direction.getHotKey() == SOUTH) {
			if (direction.getHotKey() == NORTH) {
				up = -1;
			} else {
				up = 1;
			}
			
			int width;
			for (int height = 0; height < 3; height++) {
				width = -height;
				for (int count = 0; count < (height*2+1); count++) {
					try {
						range.add(map.at(x + width, y + (up * height)));
					} catch (Exception e) {
						continue;
					}
					width++;
				}
			}
		} else if (direction.getHotKey() == EAST || direction.getHotKey() == WEST) {
			if (direction.getHotKey() == EAST) {
				right = 1;
			} else {
				right = -1;
			}
			
			int height;
			for (int width = 0; width < 3; width++) {
				height = -width;
				for (int count = 0; count < (width*2+1); count++) {
					try {
						range.add(map.at(x + (right * width), y + height));
					} catch (Exception e) {
						continue;
					}
					height++;
				}
			}
		} else {
			if (direction.getHotKey() == NORTH_EAST) {
				up = -1;
				right = 1;
			} else if (direction.getHotKey() == NORTH_WEST) {
				up = -1;
				right = -1;
			} else if (direction.getHotKey() == SOUTH_EAST) {
				up = 1;
				right = 1;
			} else {
				up = 1;
				right = -1;
			}
			for (int width = 0; width < 3; width++) {
				for (int height = 0; height < 3; height++) {
					try {
						range.add(map.at(x + (right*width), y + (up*height)));
					} catch (Exception e) {
						continue;
					}
				}
			}
		}
		
		return range;
	}
		
}
