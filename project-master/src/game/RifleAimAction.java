package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;
/**
 * This class represents the aim action for the rifle to fire
 * @author Jaclyn
 *
 */
public class RifleAimAction extends Action{

	private Actor target;
	
	private RangedWeapon rifle;
	
	/**
	 * 
	 * @param target the actor getting hurt by the rifle
	 * @param rifle the actual weapon getting hurt
	 */
	public RifleAimAction(Actor target, RangedWeapon rifle) {
		// TODO Auto-generated constructor stub
		this.target=target;
		this.rifle=rifle;
		System.out.println(rifle);
	}
	/**
	 * calls the aim method in SniperRifle
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		((SniperRifle)rifle).aim(target);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor +" aims at "+target;
	}

}
