package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action for items to heal actors.
 * @author Vanessa
 *
 */
public class HealAction extends Action {
	private Item healingItem;
	private int pointsHealed;
	
	/**
	 * Constructor for HealAction.
	 * 
	 * @param healingItem the item that heals
	 * @param pointsHealed the amount of points that is healed when healingItem is used
	 */
	public HealAction(Item healingItem, int pointsHealed) {
		this.healingItem = healingItem;
		this.pointsHealed = pointsHealed;
	}
	
	/**
	 * Execution of HealAction. actor is healed and healingItem is removed from 
	 * actor's inventory
	 * 
	 * @param actor the actor that is healed
	 * @param map the map the actor is on
	 * @retunr a string describing which actor is healed by how many points and by what item
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.heal(pointsHealed);
		actor.removeItemFromInventory(healingItem);
		return actor + " is healed " + pointsHealed + " points by "+ healingItem;
	}
	
	/**
	 * Description for which item heals and how many points it heals.
	 * 
	 * @param actor the actor that can be healed
	 * @return a string to describe which actor can been healed by how many points and by what item
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " can be healed " + pointsHealed + " points by "+ healingItem;
	}
}
