package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	
	
	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}
	
	/**
	 * @param actor is the actor
	 * @param map is the Gamemap
	 *  
	 * 
	 */
		
	public String execute(Actor actor, GameMap map,Weapon weapon,Double chances) {
		if (rand.nextDouble() > chances){
			return actor + " misses " + target + ".";
		}
		int damage = weapon.damage();
		return isDead(actor,target,weapon,damage,map);
		
	}
	
	/**
	 *  @param actor the actor attacking
	 *  @param map Gamemap
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		return execute(actor,map,weapon);
	}
	/**
	 * 
	 * @param actor the actor attacking
	 * @param map Gamemap
	 * @param weapon weapon used by actor to attack target
	 * @param damage damage that the target will received
	 * @return isDead(actor,target,weapon,damage,map)
	 */
	public String execute(Actor actor,GameMap map,Weapon weapon,int damage) {
		return isDead(actor,target,weapon,damage,map);
	}
	
	
	/**
	 * 
	 * @param actor the actor attacking
	 * @param map Gamemap
	 * @param weapon weapon used by actor to attack target
	 * @return it calls execute(actor, map, weapon, 0.5)
	 */
	public String execute(Actor actor, GameMap map, Weapon weapon) {
		return execute(actor, map, weapon, 0.5);
	}
	
	/**
	 * 
	 * @param actor the actor attacking
	 * @param target the actor getting attacked
	 * @param weapon weapon used by actor to attack target
	 * @param damage amount of damage the target is receiving 
	 * @param map Gamemap
	 * @return a string
	 */
	public String isDead(Actor actor,Actor target,Weapon weapon, int damage,GameMap map) {
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		target.hurt(damage);

		if (!target.isConscious()) {
			PortableItem corpse;
			if (target instanceof Human) {
				corpse = new HumanCorpse(target.toString(), map);
			} else {
				corpse = new PortableItem("dead" + target.toString(), '%');
			}
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

	return result;
	}
		
		/*
		 * 		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		target.hurt(damage);

		if (!target.isConscious()) {
			PortableItem corpse;
			if (target instanceof Human) {
				corpse = new HumanCorpse(target.toString(), map);
			} else {
				corpse = new PortableItem("dead" + target.toString(), '%');
			}
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

	return result;
	}
		 */
		
	

	/*
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		if(actor instanceof Zombie) {
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
		}
			
		//Human
		else {
			if (rand.nextBoolean()){
				return actor + " misses " + target + ".";
			}
			else {
				if(rand.nextDouble()<=0.25 & ((Zombie) target).getNoOfLimbs()>0) {
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
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		target.hurt(damage);

		if (!target.isConscious()) {
			PortableItem corpse;
			if (target instanceof Human) {
				corpse = new HumanCorpse(target.toString(), map);
			} else {
				corpse = new PortableItem("dead" + target.toString(), '%');
			}
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

	return result;
	}
	
	*/

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
