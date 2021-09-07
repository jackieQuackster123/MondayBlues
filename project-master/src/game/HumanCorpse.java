package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing a human corpse. The corpse will turn into a Zombie within 5-10 turns.
 * @author Vanessa
 *
 */
public class HumanCorpse extends PortableItem{
	private int age = 0;
	private double chance = 0.0;
	private Random rand = new Random();
	private GameMap map;
	
	/**
	 * Constructor for a human corpse.
	 * 
	 * @param name the name of the human who died
	 * @param map the map on which the corpse is on
	 */
	public HumanCorpse(String name, GameMap map) {
		super("dead " + name, '%');
		this.map = map;
	}
	
	/**
	 * Ages the corpse in actor's inventory.
	 * 
	 * @param currentLocation the location of the actor carrying the human corpse
	 * @param actor the actor carrying the human corpse
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		List<Exit> exits = new ArrayList<Exit>(currentLocation.getExits());
		Collections.shuffle(exits);
		
		boolean trapped = true;
		for (Exit e: exits) {
			if (!(e.getDestination().canActorEnter(actor))) {
				continue;
			} else {
				trapped = false;
				if (becomingUndead(e.getDestination())) {
					actor.removeItemFromInventory(this);
					break;
				}
			}
		}
		if (trapped) {
			becomingUndead(currentLocation);
		}
	}
	
	/**
	 * Ages the corpse on the ground.
	 * 
	 * @param currentLocation location where the human corpse is located
	 */
	@Override
	public void tick(Location currentLocation) {
		if(becomingUndead(currentLocation)) {
			currentLocation.removeItem(this);
		}
	}
	
	/**
	 * Checks if the human corpse turns into a zombie. If it does, Zombie object is instantiated at location.
	 * If it can't add a zombie to the location, an actor might be standing in its way, so that actor is killed.
	 * 
	 * @param location the location where the zombie is created
	 * @return true if the corpse has turned into a zombie, otherwise false
	 */
	private boolean becomingUndead(Location location) {		
		age++;
		if (age > 5 && age <= 10) {
			chance += 0.2;
		}
		
		if (rand.nextDouble() < chance) {
			Zombie zombie = new Zombie("Un" + this.toString());
			try {
				location.addActor(zombie);
			} catch (Exception e) { 
				// there is something blocking at location, cannot add zombie to location
				// it must be either player is standing at location because is trapped with zombie
				// or another actor is currently standing over the corpse when it turns.
				Actor blocking = location.getActor();
				map.removeActor(location.getActor()); // So new zombie kills actor in its way
				location.addActor(zombie);
			
				System.out.println(blocking.toString() + " is killed because they were in " + zombie.toString() + "'s way.");
			}
			System.out.println(this.toString() + " HAS RISEN FROM THE DEAD");
			return true;
		}
		return false;
	}
	
}
