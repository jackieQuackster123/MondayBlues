package game;

import edu.monash.fit2099.engine.Location;

/**
 * A tree that starts as a sapling and grows into a large tree.
 * 
 * @author ram
 *
 */
public class Tree extends GrowableGround {

	public Tree() {
		super('+');
	}

	@Override
	public void tick(Location location) {
		super.tick(location, 't','T');
	}
}
