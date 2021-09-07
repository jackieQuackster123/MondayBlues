package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *The Zombie has 2 hands,2 legs and 4 body limbs in tottal.
 *new PickUpItemBehaviour(WeaponItem.class) was added to the list of behaviour.
 *
 */
public class Zombie extends ZombieActor {
	private int counter=0;
	private Behaviour[] behaviours = {
			new PickUpItemBehaviour(WeaponItem.class),
			new ZombieAttackBehaviour(),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	//	7/5/2020 12.24pm
	
	private ArrayList<String> zombieLimbs= setZombieLimbs();	
	private int noOfHands=2;
	private int noOfLegs=2;
	private int noOfLimbs=4;

	//private ZombieLimbs zombieLimbs=new ZombieLimbs();
	//
	
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}
	/**
	 * If the number of zombie arms equals to 2, there's a 50-50 chance of returning bite to punch
	 * If the number of zombie arms equals to 1, there's a 75-50 chance of returning bite to punch
	 * If the number of zombie arms equals to 0, it is certain to return bite
	 */

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		//
		double biting_chances = Math.random();
		if (noOfHands==1){
			if (biting_chances>=0.75) {
				return new IntrinsicWeapon(10, "punches");
				//return new IntrinsicWeapon(15,"bites");
			}
		}
		else if(noOfHands==2) {
			if(biting_chances>=0.5) {
				return new IntrinsicWeapon(10, "punches");
				//return new IntrinsicWeapon(15,"bites");
			}
		}
		//
		return new IntrinsicWeapon(15,"bites");
		//return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		double say=Math.random();
		if (say<=0.10) {
			System.out.println("Braaaaaains");
		}
		//if zombie got 0 leg
		if(getNoOfLegs()==0) {
			return getActionForNotMoving(map);
		}
		//if zombie got 1 leg
		else if(getNoOfLegs()==1) {
			if (counter%2==0) {
				counter+=1;
				return getActionForMoving(map);
			}
			else {
				counter+=1;
				return getActionForNotMoving(map);
			}
		}
		
		else {
			return getActionForMoving(map);
		}
	}
	
	/**
	 * This method the first action that the zombie could do. Movement is not restricted
	 * This method is to make playTurn() to be more readable
	 */
	public Action getActionForMoving(GameMap map) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
	/**
	 * This method the first action that the zombie could do. Movement is restricted so 
	 * new HuntBehaviour(Human.class, 10)
	 * new WanderBehaviour()
	 * will not be returned
	 * 
	 * This method is to make playTurn() to be more readable
	 */
	public Action getActionForNotMoving(GameMap map) {
		for (int i=0;i<2;i++) {
			Action action = behaviours[i].getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
	/**
	 * This method is to set the body parts of a zombie
	 * @return an arrayList of zombieParts(string)
	 */
	public ArrayList<String> setZombieLimbs() {
		ArrayList<String> zombieLimb= new ArrayList<String>(4);
		zombieLimb.add("rightHand");
		zombieLimb.add("leftHand");
		zombieLimb.add("rightLeg");
		zombieLimb.add("leftLeg");
		return zombieLimb;
	}
	
	/**
	 * 
	 * @return the limb(string) that it will lose. The arrayList of body parts along with noOfLimbs and
	 * noOfHands/noOfLegs will be updated accordingly. 
	 * If the number of leg is 1, the counter is used to make sure that the zombie can only move once every 2 turn
	 * @throws Exception if the body parts is the zombie is going to lose is neither hand or leg
	 */
	public String loseLimbs() throws Exception {
		int limbIndex=(int)(Math.random()*((noOfLimbs)-0)+0);
		String limb=zombieLimbs.get(limbIndex);
		zombieLimbs.remove(limbIndex);
		if(isHand(limb)){
			noOfHands-=1;
			noOfLimbs-=1;	
		}
		
		else if(isLeg(limb)) {
			noOfLegs-=1;
			noOfLimbs-=1;
		}
		else {
			throw new Exception("This limb is neither hand or leg");
		}
		return limb;
	}
	/**
	 * 
	 * @param string
	 * @return true is string's last 4 letter is Hand
	 * @return false if otherwise
	 */
	public boolean isHand(String string) {
		if(string.substring(string.length()-4, string.length()).equals("Hand")){
			return true;	
		}
		return false;
	}
	/**
	 * 
	 * @param string
	 * @return true is string's last 3 letter is Leg
	 * @return false if otherwise
	 */
	public boolean isLeg(String string) {
		if(string.substring(string.length()-3, string.length()).equals("Leg")){
			return true;	
		}
		return false;
	}
	/**
	 * 
	 * @return noOfHands
	 */
	public int getNoOfHands() {
		return noOfHands;
	}
	/**
	 * 
	 * @return noOfLegs
	 */
	public int getNoOfLegs() {
		return noOfLegs;
	}
	/**
	 * 
	 * @return noOfLimbs
	 */
	public int getNoOfLimbs() {
		return noOfLimbs;
	}
	/**
	 * Set counter to 1 and counter is ensure the zombie with 1 leg can only move once every 2 turns 
	 */
	public void lossLegs() {
		counter+=1;
	}
	
	/**
	 * It overrides getAllowableAction in zombieActors. Now, zombie is attacked by HumanAttackAction.
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(this, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new HumanAttackAction(this));
		return list;
	}
}
