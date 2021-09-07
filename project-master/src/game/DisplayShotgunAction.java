package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Weapon;

/**
 * Class for displaying the shotgun submenu for the directions to fire the shotgun if player chooses to do so.
 * @author Vanessa
 *
 */
public class DisplayShotgunAction extends Action{
		private Menu submenu = new Menu();
		private Display display;
		private Actions shootDirections = new Actions();
		private RangedWeapon shotgun;
		private Ammunition ammo;
		
		/**
		 * Constructor.
		 * 
		 * @param display	the display the submenu will be on
		 * @param shotgun	the shotgun used to shoot
		 * @param ammo		the ammunition used to fire
		 */
		public DisplayShotgunAction(Display display, RangedWeapon shotgun, Ammunition ammo) {
			this.display = display;
			this.shotgun=shotgun;
			this.ammo = ammo;
		}
		
		/**
		 * Adds a ShotgunFireAction for every exit from location where actor is at and adds it to submenu
		 * so that an action can be chosen from one of them. The action chosen is then executed.
		 * 
		 *  @param actor	the actor this menu display is presented to
		 *  @param map		the map the actor is on
		 *  @return			the result of the execution of the action chosen
		 */
		@Override
		public String execute(Actor actor, GameMap map) {
			List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
			
			for (Exit e: exits) {
				shootDirections.add(new ShotgunFireAction(e, shotgun, ammo));
			}
			Action action = submenu.showMenu(actor, shootDirections, display);
		
			return action.execute(actor, map);
		}
		
		/**
		 * Menu description to call this action.
		 * 
		 * @param actor	the actor doing this action
		 * @return 		a string saying actor fires the shotgun
		 */
		@Override
		public String menuDescription(Actor actor) {
			return actor + " fires the shotgun";
		}
}
