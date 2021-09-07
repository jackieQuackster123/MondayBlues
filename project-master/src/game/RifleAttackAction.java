package game;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;
/**This class represents the attack when player(or any actors that is using rifle) decides to shoot the zombies/
 * 
 * @author Jaclyn
 *
 */
public class RifleAttackAction extends AttackAction{
	private RangedWeapon rifle;
	private Random rand = new Random();
	private Ammunition ammo;
	
	/**
	 * 
	 * @param target the actor getting shot
	 * @param rifle the weapon itself
	 * @param ammo the ammunition
	 */
	public RifleAttackAction(Actor target, RangedWeapon rifle, Ammunition ammo) {
		super(target);
		this.rifle=rifle;
		this.ammo = ammo;
		//System.out.println(rifle);
		rifle.addCapability(RangedWeaponCapability.SHOOT);
	}
	
	/**
	 * If the aim is 0, there's the usual success percentage of a successful attack with usual amount of damage.
	 * If the aim is 1, there's a 90 success percentage chances of a successful attack with a double damage.
	 * If the aim is 2, it is certain that it is a successful attack with a instant kill.
	 * When the ammo hits 0, ammo is remove from the inventory.
	 */
	public String execute(Actor actor, GameMap map) {

		String result="";
		int aim=((SniperRifle)rifle).getAim();
		HumanAttackAction attack=new HumanAttackAction(target);

		if(aim==0) {
			result+=attack.execute(actor,map,rifle, 0.5);
		}
		else if(aim==1) {
			result+=attack.execute(actor,map,rifle, 0.9);
		}
		else {
			result+=attack.execute(actor,map,rifle, 1);
		}
		
		((SniperRifle)rifle).reset();
		
		ammo.shotFired();
		if (ammo.getRounds() == 0) {
			actor.removeItemFromInventory(ammo);		
			result += "\nThis ammunition has no more rounds.";
		}
		
		return result;
		
	}
	public String menuDescription(Actor actor) {
		return actor+" Fire the rifle at" + target;
	}
	
	

}
