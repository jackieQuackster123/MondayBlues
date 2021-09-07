package game;

/**
 * Class representing ammunition for ranged weapons.
 * @author Vanessa
 *
 */
public abstract class Ammunition extends PortableItem{
	private int rounds;
	
	/**
	 * Constructor. Each ammunition has 10 rounds by default.
	 * 
	 * @param name			the name of the ammunition (the type)
	 * @param displayChar	the character that represents it on the display
	 */
	public Ammunition(String name, char displayChar) {
		super(name, displayChar);
		rounds = 10;
	}
	
	/**
	 * Getter for rounds.
	 * 
	 * @return	the number of rounds it has left.
	 */
	public int getRounds() {
		return rounds;
	}
	
	/**
	 * Called when shots are fired. Decreases the number of rounds by one.
	 */
	public void shotFired() {
		rounds -= 1;
	}
}
