package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
/**This is a class to represent the action to display the submenu and all the actor it can shoot
 * within a range
 * 
 * @author Jaclyn
 *
 */
public class DisplayRifleAction extends Action{
	
	private Menu submenu = new Menu();
	private Display display;
	private Actions shootTarget = new Actions();
	protected RangedWeapon rifle;
	private Ammunition ammo;
	
	private int maxRange=100;
	private HashSet<Location> visitedLocations = new HashSet<Location>();
	private ArrayList<Location> getAllZombieLocation = new ArrayList<>();

	
	
	public DisplayRifleAction(Display display, RangedWeapon rifle, Ammunition ammo) {
		// TODO Auto-generated constructor stub
		this.display = display;
		this.rifle=rifle;
		this.ammo = ammo;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+" use rifle.";
	}
	
	public String execute(Actor actor,GameMap map) {
		Location loc=map.locationOf(actor);
		//System.out.println(getLocation(actor,loc).size());
		
		for (Location l:getLocation(actor,loc)) {
			shootTarget.add(new DisplayRifleSpecialAction(l.getActor(),rifle,ammo,display));
		}
		Action action = submenu.showMenu(actor, shootTarget, display);
		
		return action.execute(actor, map);
		
		//Action action=submenu.showMenu(actor, shootTarget, display);
		//return action.execute(actor, map);
		
	}
	public ArrayList<Location> getLocation(Actor actor, Location loc) {
		ArrayList<Location> returning=new ArrayList<Location>();
		//Location here=map.locationOf(actor);
		visitedLocations.clear();
		ArrayList<Location> now = new ArrayList<Location>();
		
		now.add(loc);
		
		ArrayList<ArrayList<Location>> layer = new ArrayList<ArrayList<Location>>();
		layer.add(now);

		for (int i = 0; i<maxRange; i++) {
			layer = getNextLayer(actor, layer);
			ArrayList<Location> there = search(layer);
			getAllZombieLocation.addAll(there);

		}
		return getAllZombieLocation;
	}
	

	/**The next 3 methods were taken from hunt behaviour.
	 * 
	 */

	private ArrayList<ArrayList<Location>> getNextLayer(Actor actor, ArrayList<ArrayList<Location>> layer) {
		ArrayList<ArrayList<Location>> nextLayer = new ArrayList<ArrayList<Location>>();

		for (ArrayList<Location> path : layer) {
			List<Exit> exits = new ArrayList<Exit>(path.get(path.size() - 1).getExits());
			Collections.shuffle(exits);
			for (Exit exit : path.get(path.size() - 1).getExits()) {
				Location destination = exit.getDestination();
				if (!destination.getGround().canActorEnter(actor) || visitedLocations.contains(destination))
					continue;
				visitedLocations.add(destination);
				ArrayList<Location> newPath = new ArrayList<Location>(path);
				newPath.add(destination);
				nextLayer.add(newPath);
			}
		}
		return nextLayer;
	}
	
	private ArrayList<Location> search(ArrayList<ArrayList<Location>> layer) {
		ArrayList<Location> loc=new ArrayList<Location>();
		for (ArrayList<Location> path : layer) {
			if (containsTarget(path.get(path.size() - 1))) {
				loc.add(path.get(path.size() - 1));
			}
		}
		return loc;
	}
	
	private boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				here.getActor().hasCapability(ZombieCapability.UNDEAD));
	}
	


}
