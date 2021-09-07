package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {
	private ZombieWorld zombieWorld;
	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 * @param zombieWorld the world the player is in 
	 */
	public Player(String name, char displayChar, int hitPoints, ZombieWorld zombieWorld) {
		super(name, displayChar, hitPoints);
		this.zombieWorld = zombieWorld;
	}

	/**
	 * Returns possible actions for this turn.
	 * 
	 * @param actions 		actions that can be performed this turn
	 * @param lastAction 	the last action performed by player
	 * @param map			the map the player is on
	 * @param display		the user interface
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (int i=0;i<super.getInventory().size();i++) {
			if (super.getInventory().get(i) instanceof FallenZombiePart) {
				actions.add(new CraftAction((FallenZombiePart) super.getInventory().get(i)));
			}
		}
		
		//Player should be able to interact with the ground they're standing on
		actions.add(map.locationOf(this).getGround().allowableActions(this, map.locationOf(this), ""));
		
		if(this.hasAmmunition(AmmunitionShotgun.class)&&this.hasRangedWeapon(Shotgun.class)) {
			actions.add(new DisplayShotgunAction(display,this.getRangedWeapon(Shotgun.class), this.getAmmunition(AmmunitionShotgun.class)));
		}

		if(this.hasAmmunition(AmmunitionRifle.class)&&this.hasRangedWeapon(SniperRifle.class)) {
			actions.add(new DisplayRifleAction(display,this.getRangedWeapon(SniperRifle.class), this.getAmmunition(AmmunitionRifle.class)));
		}
		
		actions.add(new QuitAction(zombieWorld));
		
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		
		if(lastAction instanceof RifleAttackAction ==false) {
			this.resetRifle();
		}
		return menu.showMenu(this, actions, display);
	}
	

	
}
