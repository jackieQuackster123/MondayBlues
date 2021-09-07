package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Behaviour class for harvesting, fertilizing and sowing crops.
 * @author Vanessa
 *
 */
public class FarmingBehaviour implements Behaviour{
	private Random rand = new Random();
	
	/**
	 * Returns a farming action (HarvestAction, FertilizeAction or SowAction) depending on the location where they
	 * stand or the locations of their exits.
	 * 
	 * @param actor 	the actor which exhibits this farming behaviour
	 * @param map		the map the actor is currently on
	 * @return 			HarvestAction if there is a ripe crop, FertilizeAction if there is an unripe crop, 
	 * 					SowAction if ground is dirt, otherwise null.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location location = map.locationOf(actor);
		if (location.getGround() instanceof Crop) {
			Crop crop = (Crop) location.getGround();
			if (crop.getAge() >= 20 && rand.nextDouble() < 0.75) { // 75% chance of harvesting the crop
				return new HarvestAction(location);
			} else if (crop.getAge() < 20 && rand.nextDouble() < 0.4) { // 40% chance of fertilizing the crop
				return new FertilizeAction(location);
			}
		}
		
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			boolean noActor = !e.getDestination().containsAnActor();
			Ground ground = e.getDestination().getGround();
			
			if (noActor && ground instanceof Crop) {
				if (((Crop)e.getDestination().getGround()).getAge() >=20 && rand.nextDouble() < 0.75) // 75% chance of harvesting the crop
					return new HarvestAction(e.getDestination());
			} else if (noActor && ground instanceof Dirt && rand.nextDouble() <= 0.33) {
				return new SowAction(e.getDestination());
			}
		}

		
		return null;
	}

}
