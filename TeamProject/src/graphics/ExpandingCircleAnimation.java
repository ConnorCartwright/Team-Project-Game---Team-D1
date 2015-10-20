package graphics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represent an animation of an circle expanding until it reaches the max size and stops.
 * 
 * @author Anh Pham.
 */
public class ExpandingCircleAnimation extends Animation {
	
	private float maxRadius;
	private int x;
	private int y;
	private float radius;
	private float growth;
	private Color color;

	/**
	 * Create an expanding circle animation with the default colour (WHITE).
	 * 
	 * @param x
	 *            The x coordinate of the centre of the animation.
	 * @param y
	 *            The y coordinate of the centre of the animation.
	 * @param finalRadius
	 *            The max size that the circle will reach.
	 * @param life
	 *            The duration of the animation.
	 * @param delay
	 *            The delay before the animation starts playing.
	 */
	public ExpandingCircleAnimation(int x, int y, float finalRadius, long life, long delay) {
		this(x, y, finalRadius, life, delay, Color.WHITE);
	}

	/**
	 * Create an expanding circle animation with a custom colour.
	 * 
	 * @param x
	 *            The x coordinate of the centre of the animation.
	 * @param y
	 *            The y coordinate of the centre of the animation.
	 * @param finalRadius
	 *            The max size that the circle will reach.
	 * @param life
	 *            The duration of the animation.
	 * @param delay
	 *            The delay before the animation starts playing.
	 * @param color
	 *            The color of the animation.
	 */
	public ExpandingCircleAnimation(int x, int y, float finalRadius, long life, long delay, Color color) {
		super(life, delay);
		this.maxRadius = finalRadius;
		this.x = x;
		this.y = y;
		float startGrowth = maxRadius / (life * 3 / 4) * 16;
		this.growth = startGrowth;
		this.color = color;
	}

	@Override
	public boolean update() {
		if (super.update())
			return true;
		if (delay <= 0) {
			radius = Math.min(maxRadius, radius + growth);
			if (radius >= maxRadius * 0.8)
				growth = Math.max(0.25f, growth * 0.95f);
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		int radius = Math.round(this.radius);
		if (radius > 0)
			g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
	}
	
}
