package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

public class DisplayRifleSpecialAction extends Action{
	
	private Menu submenu = new Menu();
	private Display display;
	private Actions chooseAction = new Actions();
	protected RangedWeapon rifle;
	private Ammunition ammo;
	private Actor target;
	
	public DisplayRifleSpecialAction(Actor target,RangedWeapon rifle,Ammunition ammo,Display display) {
		this.target=target;
		this.rifle=rifle;
		this.ammo=ammo;
		this.display=display;
	}
	
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		chooseAction.add(new RifleAimAction(target,rifle));
		chooseAction.add(new RifleAttackAction(target,rifle, ammo));
		Action action = submenu.showMenu(actor, chooseAction, display);
		return action.execute(actor, map);
	}
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Choose "+target;
	}
	
	
	

}
