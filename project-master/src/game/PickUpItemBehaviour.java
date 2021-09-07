package game;

import java.util.Collection;
import java.util.Iterator;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * 
 * @author Jaclyn
 * 
 * A class that generates a PickUpItemAction is the actor is standing on top of it
 * 
 *
 */
public class PickUpItemBehaviour implements Behaviour{
	
	private Class<?> pickUpItemClass;
	/**
	 * 
	 * @param newPickUpItemClass is the Class that the actor is allowed to pick up
	 */
	
	public PickUpItemBehaviour(Class<?> newPickUpItemClass){
		this.pickUpItemClass=newPickUpItemClass;
	}
	
	/**
	 * @param actor is the actor
	 * @param map is the GameMap
	 * @return PickUpItemAction(item) if actor is standing on item
	 * @return null is there's no item on the actor's location or the item is not a subclass or the same class as
	 * pickUpItemClass
	 * 
	 * This method first get the location of the actor and checks whether the current location has any items or not.
	 * If there is item and the item is the same class or a child class of pickUpItemClass, a new pickUpItemAction for that item
	 * is returned 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Location location=map.locationOf(actor);
		Collection<Item> items=location.getItems();
		if (items.size()>0){
			for(Item item:items){
				//if the item class is equals or is a subclass to pickUpItemClass
				if (item.getClass().equals(pickUpItemClass) || pickUpItemClass.isAssignableFrom(item.getClass())){
					return new PickUpItemAction(item);
				}
			}
			return null;
		}
		return null;		
	}

}
