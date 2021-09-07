package game;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/** Class representing the whole world of the Zombie game.
 * 
 * @author Vanessa
 *
 */
public class ZombieWorld extends World {
	private GameStatus currentStatus = GameStatus.RUNNING;
	private VoodooPriestess mamboMarie = new VoodooPriestess("Mambo Marie");
	private Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param display	the display for the game
	 */
	public ZombieWorld(Display display) {
		super(display);
	}
	
	/**
	 * Run the game.
	 * 
	 * On each iteration the gameloop does the following: - displays the player's
	 * map - processes the actions of every Actor in the game, regardless of map.
	 *
	 * There is a chance Mambo Marie is added if she is conscious and she is not currently on any map.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			
			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}
			
			//Add Mambo Marie to the gameMap
			if(mamboMarie.isConscious() && !actorLocations.contains(mamboMarie) && rand.nextDouble() <= 0.05) {
				GameMap map = actorLocations.locationOf(player).map();
				int x = 0;
				int y = 0;
				while (!map.at(x, y).canActorEnter(mamboMarie)) {
					x++;
					if (x > map.getXRange().max()) {
						x = 0;
						y++;
					}
				};
				map.at(x, y).addActor(mamboMarie);
			}
			
			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		display.println(endGameMessage());
	}
	
	/**
	 * Quit the game.
	 */
	public void quit() {
		currentStatus = GameStatus.QUIT;
	}
	
	/**
	 * Checks if the game should still be running.
	 * The game is lost if there are no more humans in any of the maps. The game is won if Mambo Marie has been defeated and there are no more zombies.
	 * 
	 * @return 	false if the game has been quit, won or lost and true otherwise
	 */
	@Override
	protected boolean stillRunning() {
		if (currentStatus == GameStatus.QUIT) {
			return false;
		}
		
		if (actorLocations.contains(player)) {			
			int humans = 0;
			int zombies = 0;
			for (Actor actor : actorLocations) {
				if (actor instanceof Player){
					continue;
				} else if (actor instanceof Human) {
					humans++;
				} else if (actor instanceof Zombie) {
					zombies++;
				}
			}
			
			if (humans == 0) {
				currentStatus = GameStatus.LOST;
				return false;
			} else if (zombies == 0 && !mamboMarie.isConscious()) {
				currentStatus = GameStatus.WON;
				return false;
			}	
			return true;
		}
		currentStatus = GameStatus.LOST;
		return false;
	}
	
	/**
	 * Returns a string for the result of the gameplay.
	 * 
	 * @return	a string when the game is lost, won or player has quit
	 * @throws	Error if the current status is not lost, won or quit but the game has ended.
	 */
	@Override
	protected String endGameMessage() {
		if (currentStatus == GameStatus.LOST) {
			return "GAME OVER. MAMBO MARIE AND THE ZOMBIES HAVE TAKEN OVER THE WORLD.";
		} else if (currentStatus == GameStatus.WON) {
			return "CONGRATULATIONS, YOU HAVE WON THE GAME! THE COMPOUND IS NOW SAFE THANKS TO YOU.";
		} else if (currentStatus == GameStatus.QUIT) {
			return "You have quit the game.";
		} else {
			throw new Error("The game has ended but player has not lost, won or quit.");
		}
	}
	
	/**
	 * Private enum class for the status of this world.
	 * 
	 * @author Vanessa
	 *
	 */
	private enum GameStatus {
		RUNNING, QUIT, LOST, WON
	}
}
