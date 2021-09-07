package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Abstract class for ranged weapons that can shoot and whack.
 * @author Vanessa
 *
 */
public abstract class RangedWeapon extends WeaponItem{	
	
	/**
	 * Constructor for a ranged weapon. By default, the weapon can be used as a melee weapon (can use it to whack).
	 * 
	 * @param name			name of the ranged weapon
	 * @param displayChar	character that represents the ranged weapon on the display
	 */
	public RangedWeapon(String name, char displayChar) {
		super(name, displayChar, 10, "uses the " + name + " to ");
		this.addCapability(RangedWeaponCapability.WHACK);
	}
	
	/**
	 * Returns different default damages depending on whether it is being used to shoot or to whack.
	 * 
	 * @return 15 if shooting, 10 if using to whack
	 */
	@Override
	public int damage() {
		if (this.hasCapability(RangedWeaponCapability.SHOOT)){
			return 15;
		} else {
			return 10;
		}
	}
	
	/**
	 * Returns a string of what the weapon is being used for.
	 * 
	 * @return a string saying weapon is being used to shoot or whack.
	 */
	@Override
	public String verb() {
		if (this.hasCapability(RangedWeaponCapability.SHOOT)) {
			return super.verb() + "shoot";
		} else {
			return super.verb() + "whack";
		}
	}
		
}
