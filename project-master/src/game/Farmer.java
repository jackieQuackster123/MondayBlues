package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a farmer (human with farming abilities).
 * @author Vanessa
 *
 */
public class Farmer extends Human{
	private Behaviour[] behaviours = {
			new PickUpItemBehaviour(Food.class),
			new FarmingBehaviour(),
			new WanderBehaviour()
	};
	
	/**
	 * Constructor to create a Farmer
	 * 
	 * @param name the farmer's display name
	 */
	public Farmer(String name) {
		super(name, 'F', 80);
	}

	/**
	 * Returns an action from one of the farmer's behaviours. But if health is below a certain point, they should heal if they can.
	 * 
	 * @param actions 		list of possible Actions
	 * @param lastAction 	previous Action, if it was a multiturn action
	 * @param map 			the map where the current Farmer is
	 * @param display 		the Display where the Farmer's actions are shown
	 * @return 				an Action from one of its behaviours
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		return super.chooseAction(map, behaviours);
	}
}
