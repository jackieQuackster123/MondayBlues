package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

public class HumanAttackAction extends AttackAction{

	public HumanAttackAction(Actor target) {
		super(target);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 	If the actor is a human(which is the player), it will first determine the number of limbs the target(zombie) has.
	 *  If the zombie has more than 0 limbs, a random generator is used to determine whether the zombie will lose its limbs or not.
	 *  If so,loseLimbs() is then called to make the zombie loss limbs.
	 *  The limb that the zombie lose will then be a new FallenZombiePart and it will be added to the map.
	 *  If the zombie loses its arms and has one arm left, it may lose one of its item in its inventory.
	 *  If the zombie loses its arms and has no arm left, it will lose all its item in its inventory.
	 *  If the zombie loses its leg and has one leg left, its movement will be halved.
	 *  If the zombie loses its leg and has no leg left, it cannot move at all though it can still bite or punch.
	 *  The movement control of the zombie is done in the zombie's playAction().
	 */
	public String execute(Actor actor,GameMap map,Weapon weapon, Double chances) {
		if (rand.nextDouble() > chances){
			return actor + " misses " + target + ".";
		}
		else {
			if(target instanceof Zombie) {
				if(rand.nextDouble()<=0.33 & ((Zombie) target).getNoOfLimbs()>0) {
					String limb;
					try {
						limb = ((Zombie) target).loseLimbs();
				
						System.out.println(target+" loses "+limb);
						
						//if limb=hand
						if(((Zombie)target).isHand(limb)) {
							//if(limb.substring(limb.length()-4, limb.length()).equals("Hand")) {
					
							Item hand = new FallenZombiePart(target + " " + limb, 'h','H');
							map.locationOf(target).addItem(hand);
					
							//50% drop of item
							if (((Zombie) target).getNoOfHands()==1) {
								if(rand.nextDouble()>0.5 && target.getInventory().size()>0) {
									int size=((Zombie)target).getInventory().size();
									((Zombie) target).removeItemFromInventory(((Zombie)target).getInventory().get(rand.nextInt(size)));
								}
							}
							//100% drop of item
							else {
								for(Item item:target.getInventory()) {
									((Zombie) target).removeItemFromInventory(item);
								}
							}
						}

						//if limb=leg
						else {
							Item leg = new FallenZombiePart(target+ " " + limb, 'l','L');
							map.locationOf(target).addItem(leg);
							((Zombie) target).lossLegs();
						}
					}
			
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		}
	
	//

	int damage = weapon.damage();
	return super.isDead(actor, target, weapon, damage, map);
	}

}
