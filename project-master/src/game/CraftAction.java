package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**
 * An Action that allows player to craft weapon
 * 
 */
public class CraftAction extends Action{
	
	private FallenZombiePart zombieLimb;
	
	/**
	 * 
	 * @param zombieLimb which is the zombie limb that turn into a weapon
	 */
	
	public CraftAction(FallenZombiePart zombieLimb) {
		this.zombieLimb=zombieLimb;
	}
	
	/** This method will first check whether zombieLimb is a Hand('H') or a Leg('L'). It will then remove the 
	 * zombieLimb from the actor's inventory and add the newly crafted weapon into the player's inventory. 
	 *
	 * @param actor is the actor that will craft this weapon
	 * @param map is the Gamemap
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		actor.removeItemFromInventory(zombieLimb);
		if(zombieLimb.getBodyType()=='H'){
			actor.addItemToInventory(new ZombieClubs(zombieLimb.getName()+" Weapon"));
		}
		else if(zombieLimb.getBodyType()=='L'){
			actor.addItemToInventory(new ZombieMace(zombieLimb.getName()+" Weapon"));
		}
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+" creates new Weapon: "+ zombieLimb.getName()+" Weapon";
	}

}
