package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;

public class Vehicle extends Item{

	public Vehicle(String name, char displayChar) {
		super(name, displayChar, false);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param action the action that is going to be added
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}
	
	/**
	 * 
	 * @param map the Gamemap
	 * @param x coordinate
	 * @param y coordinate
	 * @param actor the actor that is going to be transported
	 * @param direction the string that represet where the actor is going
	 */
	public void addMoveAction(GameMap map,int x,int y,Actor actor,String direction) {
		if(!map.contains(actor)) {
			this.allowableActions.add(new MoveActorAction(map.at(x, y),direction));
		}
	}
}
