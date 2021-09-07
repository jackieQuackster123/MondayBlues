package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
/**This is a ZombieAttackAction class. It is an attack action for zombie.
 * It has a bite intrinsic weapon and punch.
 * @author Jaclyn
 *
 */
public class ZombieAttackAction extends AttackAction{
	
	/**
	 * 
	 * @param target the actor that the zombie is going to hurt
	 */
	public ZombieAttackAction(Actor target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		if(weapon.verb().equals("bites")) {
			if(rand.nextDouble()<0.75) { 
				return actor + " misses " + target + ".";
			}
			else {
				actor.heal(5);
			}
		}
		
		else if(weapon.verb().equals("punches")) {
			if (rand.nextBoolean()) {
				return actor + " misses " + target + ".";
			}
		}
		
		int damage = weapon.damage();

		((Human)target).resetRifle();
		
		
		return super.isDead(actor, target, weapon, damage, map);

	}
}
