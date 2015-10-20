package graphics;

import java.awt.Graphics;

/**
 * Base class of every animation in the game. All subclasses must implement render and usually also
 * override update.
 * 
 * @author Anh Pham
 * @author Shobitha Shivakumar
 */
public abstract class Animation {

	protected Vector2D life;
	protected long lastTick = 0;
	protected long delay;

	public Animation(long life) {
		this(life, 0);
	}

	public Animation(long life, long delay) {
		this.life = new Vector2D(life, life);
		this.delay = delay;
		this.lastTick = System.currentTimeMillis();
	}

	/**
	 * Manage the life circle of the animation.
	 * 
	 * @return
	 */
	public boolean update() {
		long elapsed = System.currentTimeMillis() - lastTick;
		if (delay > 0) {
			delay -= elapsed;
		} else {
			life.x -= System.currentTimeMillis() - lastTick;
		}
		if (life.x < 0)
			return true;
		lastTick = System.currentTimeMillis();
		return false;
	}

	/**
	 * Render the animation to the graphics object.
	 * 
	 * @param g
	 *            The graphics object to be rendered into.
	 */
	abstract public void render(Graphics g);

}
