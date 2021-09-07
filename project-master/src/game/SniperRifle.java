package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;

/**
 * This class represents the sniperRifle and it's special methods
 * @author Jaclyn
 *
 */
public class SniperRifle extends RangedWeapon{
	
	private int aiming=0;
	private Actor actorTarget=null;
	private int damage=damage();
	
	/**
	 * Name of the weapon is SniperRifle and the displayChar is }
	 */
	public SniperRifle() {
		super("SniperRifle", '}');
		// TODO Auto-generated constructor stub
	}
	
	/**if there was an initial target and the initial target is the current actor getting aimed at, the counter(aiming) increases
	 * else, the target getting aimed at(target) is now actorTarget.
	 * 
	 * @param target actor getting aim
	 */
	public void aim(Actor target) {
		if (actorTarget!=null && target==actorTarget) {
			aiming+=1;
		}
		else {
			actorTarget=target;
			aiming=1;
		}
	}
	/**
	 * 
	 * @return the number of aim
	 */
	public int getAim() {return aiming;}
	
	/**
	 * 
	 * @return the current target that got aimed
	 */
	public Actor getCurrentTarget() {return actorTarget;}
	
	/**
	 * This method is overriden.
	 * If aiming is 0, the normal damage is return. If aiming is 1, the normal damage double. If aiming is 2 or more, it is an insta kill
	 */
	@Override
	public int damage() {
		if(aiming==0) {
			return damage;
			}
		else if(aiming==1) {
			return damage*2;
			}
		else {
			return 100;}
	}
	
	/**
	 * reset everything back to normal
	 * after an attack, or player loss concentration or player gets hurt.
	 */
	public void reset() {
		aiming=0;
		actorTarget=null;
		this.removeCapability(RangedWeaponCapability.SHOOT);
	}

}
