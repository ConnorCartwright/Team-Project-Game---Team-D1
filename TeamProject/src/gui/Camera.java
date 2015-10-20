package gui;

import java.awt.Component;
import java.awt.Rectangle;

import network.FullCharacterData;
import core.Arena;

/**
 * Follows the player as he moves to display the appropriate area of the screen.
 * 
 * @author Anh Pham
 */
public class Camera {

	private int x;
	private int y;
	private final int arenaWidthPixel;
	private final int arenaHeightPixel;

	/**
	 * Creates a camera which will follow the player
	 * 
	 * @param arena
	 *            The arena in the game
	 */
	public Camera(Arena arena) {
		this.arenaWidthPixel = arena.getWidthPixel();
		this.arenaHeightPixel = arena.getHeightPixel();
		x = arenaWidthPixel / 2;
		y = arenaHeightPixel / 2;
	}

	/**
	 * Updates the camera
	 * 
	 * @param parent
	 *            The parent
	 * @param player
	 *            The player which the camera is following
	 */
	public void update(Component parent, FullCharacterData player) {
		if (arenaWidthPixel > parent.getWidth()) {
			x = Math.min(player.x, arenaWidthPixel - parent.getWidth() / 2);
			x = Math.max(x, parent.getWidth() / 2);
		}

		if (arenaHeightPixel > parent.getHeight()) {
			y = Math.min(player.y, arenaHeightPixel - parent.getHeight() / 2);
			y = Math.max(y, parent.getHeight() / 2);
		}
	}

	/**
	 * Gets the X coordinate of the camera
	 * 
	 * @return Returns the X coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the Y coordinate of the camera
	 * 
	 * @return Returns the Y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the top left X coordinate of the camera
	 * 
	 * @param parent
	 *            The parent
	 * @return Returns the top left X coordinate of the camera
	 */
	public int getTopLeftX(Component parent) {
		return x - parent.getWidth() / 2;
	}

	/**
	 * Get the top left X coordinate of the camera
	 * 
	 * @param parent
	 *            The parent
	 * @return Returns the top left X coordinate of the camera
	 */
	public int getTopLeftY(Component parent) {
		return y - parent.getHeight() / 2;
	}

	/**
<<<<<<< .mine
	 * Get the rendering "window" of the game world
	 * @param parent The parent component that is rendered into
	 * @return A rectangle representing the draw area of the game world
=======
	 * Get the draw area of the camera
	 * 
	 * @param parent
	 *            The parent
	 * @return Returns a rectangle of the draw area of the camera
>>>>>>> .r128
	 */
	public Rectangle getDrawArea(Component parent) {
		return new Rectangle(getTopLeftX(parent), getTopLeftY(parent), parent.getWidth(), parent.getHeight());
	}

}
