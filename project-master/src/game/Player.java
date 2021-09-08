//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

public class Player extends Human {
	private ZombieWorld zombieWorld;
	private Menu menu = new Menu();

	public Player(String name, char displayChar, int hitPoints, ZombieWorld zombieWorld) {
		super(name, displayChar, hitPoints);
		this.zombieWorld = zombieWorld;
	}

	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (!(lastAction instanceof DisplayRifleAction)) {
			this.resetRifle();
		}

		for(int i = 0; i < super.getInventory().size(); ++i) {
			if (super.getInventory().get(i) instanceof FallenZombiePart) {
				actions.add(new CraftAction((FallenZombiePart)super.getInventory().get(i)));
			}
		}

		actions.add(map.locationOf(this).getGround().allowableActions(this, map.locationOf(this), ""));
		if (this.hasAmmunition(AmmunitionShotgun.class) && this.hasRangedWeapon(Shotgun.class)) {
			actions.add(new DisplayShotgunAction(display, this.getRangedWeapon(Shotgun.class), this.getAmmunition(AmmunitionShotgun.class)));
		}

		if (this.hasAmmunition(AmmunitionRifle.class) && this.hasRangedWeapon(SniperRifle.class)) {
			actions.add(new DisplayRifleAction(display, this.getRangedWeapon(SniperRifle.class), this.getAmmunition(AmmunitionRifle.class)));
		}

		actions.add(new QuitAction(this.zombieWorld));
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		} else {
			return this.menu.showMenu(this, actions, display);
		}
	}
}
