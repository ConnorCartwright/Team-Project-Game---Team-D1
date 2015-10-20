package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import network.FullCharacterData;
import character.CharacterFactory;
import core.PowerUpFactory;

/**
 * Supposed to be the graphics "engine" of the game, this class provides all the rendering methods
 * needed to draw things on the gui.
 * 
 * @author Anh Pham
 * @author Madyan Al-Jazaeri
 */
public class Renderer {

	public static void renderMainCharacter(Graphics2D g2D, FullCharacterData player, int typeId) {
		// render the character
		renderCharacter(g2D, player.x, player.y, player.radius, typeId, true);

		// render the health bar
		g2D.setStroke(new BasicStroke(4));
		g2D.setColor(new Color(255, 50, 50));
		int length = (int) (0.2 * player.healthPoints);
		int topy = player.y - player.radius - 7;
		g2D.drawLine(player.x - length / 2, topy, player.x + length / 2, topy);

		// render the reload bar
		if (player.reloadPercent < 1) {
			topy += 4;
			g2D.setStroke(new BasicStroke(3));
			g2D.setColor(Color.WHITE);
			int x = player.x - player.radius;
			g2D.drawLine(x, topy, x + (int) (player.radius * 2 * player.reloadPercent), topy);
		}
	}

	public static void renderCharacter(Graphics2D g2D, int x, int y, int r, int typeId, boolean friendly) {
		g2D.setStroke(new BasicStroke(2));
		g2D.setColor(Color.BLACK);
		g2D.fillOval(x - r, y - r, 2 * r, 2 * r);
		int color;
		if (friendly) {
			g2D.setColor(Color.GREEN);
			color = 1;
		} else {
			g2D.setColor(Color.RED);
			color = 0;
		}
		g2D.drawOval(x - r, y - r, 2 * r, 2 * r);
		int sqrt = (int) (r / Math.sqrt(2) - 1);
		g2D.drawImage(CharacterFactory.getImage(typeId, color), x - sqrt, y - sqrt, 2 * sqrt, 2 * sqrt, null);
		g2D.setStroke(new BasicStroke(1));
	}

	public static void renderProjectile(Graphics2D g2D, int px, int py, int dx, int dy) {
		g2D.setColor(Color.WHITE);
		g2D.setStroke(new BasicStroke(2));
		g2D.drawLine(px, py, px - dx, py - dy);
	}

	public static void renderPowerUp(Graphics2D g2D, int x, int y, int type) {
		g2D.drawImage(PowerUpFactory.getIcon(type), x, y, null);
	}

	public static void renderPowerUpRing(Graphics2D g2D, int x, int y, int radius, int type) {
		g2D.setColor(PowerUpFactory.COLORS[type]);
		int ringX = x - radius - 2;
		int ringY = y - radius - 2;
		int size = 2 * radius + 4;
		g2D.drawOval(ringX, ringY, size, size);
	}

}
