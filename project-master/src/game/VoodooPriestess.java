package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that represents a Voodoo priestess. She has a 5% chance per turn to appear on the map player is currently on and every 10 turns she will 
 * chant to spawn 5 new zombies on the map. If she is not killed, she will vanish after 30 turns. 
 * @author Vanessa
 *
 */
public class VoodooPriestess extends ZombieActor {
	private int chantCounter = 0;
	private int turnsOnMap = 0;
	private Behaviour[] behaviours = {new WanderBehaviour()};
	
	/**
	 * Constructor for Voodoo priestess. 
	 * 
	 * @param name	the name of the Voodoo priestess
	 */
	public VoodooPriestess(String name) {
		super(name, '&', 200, ZombieCapability.UNDEAD);
	}

	/**
	 * Returns an action. Every 10 turns she spends on the map, she will chant. Every other turn, she will wander. Once she has spent
	 * 30 turns on the map, she will be removed from the map.
	 * 
	 * @param actions		list of actions the Voodoo Priestess can do
	 * @param lastAction	the previous action done by this Voodoo Priestess
	 * @param map			the map the Voodoo Priestess is on
	 * @param display		the user interface that shows what has been done
	 * @return				an Action done this turn
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		turnsOnMap++;
		if (turnsOnMap == 30) {
			map.removeActor(this);
			turnsOnMap = 0;
		}
		if (turnsOnMap % 10 == 0) {
			chantCounter++;
			return new ChantAction(chantCounter);
		}	
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

}
