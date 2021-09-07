package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

public class ZombieAttackBehaviour extends AttackBehaviour {

	public ZombieAttackBehaviour() {
		super(ZombieCapability.ALIVE);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * This code was modified. It was originally AttackAtction. It is now ZombieAttackAction
	 * If the actor is a zombie, it will either have a chance to punch or to bite. If a successful bite occurs, the
	 * zombie heals by 5 points.
	 */
	public Action getAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(ZombieCapability.ALIVE)) {
				return new ZombieAttackAction(e.getDestination().getActor());
			}
		}
		return null;
	}
}
