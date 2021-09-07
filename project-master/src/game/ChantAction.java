package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action class for chanting. 5 new Zombies are spawned randomly on the map.
 * @author Vanessa
 *
 */
public class ChantAction extends Action{
	private int chantCounter = 0;
	private Random rand = new Random();
	
	/**
	 * Constructor for ChantAction.
	 * 
	 * @param chantCounter	the number of times ChantAction has been called by the actor doing this action
	 */
	public ChantAction(int chantCounter) {
		this.chantCounter = chantCounter;
	}
	
	/**
	 * Creates and places five new zombies randomly on the map.
	 * 
	 * @param actor	the actor doing this ChantAction
	 * @param map	the map where the zombies will be spawned (also the map where actor is on)
	 * @return 		a descriptive String to be displayed on the console.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int noOfZombies = 0;
		
		while (noOfZombies < 5) {
			noOfZombies++;
			String name = "Zombie Minion " + chantCounter + "." + noOfZombies;
			Zombie zombie = new Zombie(name);
			
			int x = rand.nextInt(map.getXRange().max());
			int y = rand.nextInt(map.getYRange().max());
			
			while (!map.at(x, y).canActorEnter(zombie)) {
				x = rand.nextInt(map.getXRange().max());
				y = rand.nextInt(map.getYRange().max());
			};
			map.at(x, y).addActor(zombie);
		}
		
		return menuDescription(actor);
	}

	/**
	 * Description of who chants.
	 * 
	 * @param actor	the actor doing this ChantAction
	 * @return		a descriptive string to be displayed on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " chants, causing 5 new zombies to rise from the dead.";
	}

}
