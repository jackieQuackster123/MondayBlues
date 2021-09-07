package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action class for player to quit.
 * @author Vanessa
 *
 */
public class QuitAction extends Action{
	private ZombieWorld zombieWorld;
	
	/**
	 * Constructor for QuitAction. 
	 * 
	 * @param zombieWorld	the world we want to quit
	 */
	public QuitAction(ZombieWorld zombieWorld) {
		this.zombieWorld = zombieWorld;
	}
	
	/**
	 * Quits the game.
	 * 
	 * @return	a String notifying user that they have selected to quit.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		zombieWorld.quit();
		return menuDescription(actor);
	}

	/**
	 * Menu description for quiting the game.
	 * 
	 * @return a String "Quit"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Quit";
	}
	
	/**
	 * Hotkey for quitting. Player must press '0' to quit.
	 * 
	 * @return the character "0"
	 */
	@Override
	public String hotkey() {
		return "0";
	}

}
