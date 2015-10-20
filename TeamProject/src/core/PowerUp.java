package core;

import java.awt.Image;
import java.awt.Point;
import java.util.List;

import network.GameEvent.PowerUpPickedUpEvent;
import weapon.PrimaryWeapon;
import character.ControlledCharacter;

/**
 * This class will handle power ups.
 * 
 * @author Madyan Al-Jazaeri
 * @author Morgan Rees
 */
public class PowerUp {

	private int x;
	private int y;
	private int type;
	private boolean display;
	private ControlledCharacter picker;
	private long timestamp;

	/**
	 * Creates a power-up.
	 * @param x
	 *            the power up x coordinate
	 * @param y
	 *            the power up y coordinate
	 * @param type
	 *            the power up type
	 * @param icon
	 *            the power up icon
	 */
	public PowerUp(int x, int y, int type, Image icon) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.display = true;
		this.picker = null;
		this.timestamp = 0;
	}

	/**
	 * Returns the x coordinate of the power-up.
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the x coordinate of the power-up.
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the type of the power-up.
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Returns a boolean stating if the power-up is displayed or not.
	 * @return whether power up is displayed or not
	 */
	public boolean isDisplayed() {
		return display;
	}

	/**
	 * Gets the id of the character who picks the power-up.
	 * @return the id of the picker otherwise -1
	 */
	public int getPickerId() {
		if (picker != null) {
			return picker.id;
		} else {
			return -1;
		}
	}

	/**
	 * Sets the x and y coordinates of the power-up.
	 * @param x
	 *            the x coordinate to set
	 * @param y
	 *            the y coordinate to set
	 */
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Updates the power-ups on the arena.
	 * @param world
	 *            game world
	 * @param powerUps
	 *            power ups array
	 * @param arena
	 *            game arena
	 * @param characters
	 *            character list
	 */
	public void update(World world, PowerUp[] powerUps, Arena arena, List<ControlledCharacter> characters) {
		if (display) {
			picker = findTouching(characters);
			if (picker != null) {
				display = false;
				timestamp = System.currentTimeMillis();
				activate(world);
			}
			return;
		}

		long timeDifference = System.currentTimeMillis() - timestamp;
		if (picker != null && timeDifference > PowerUpFactory.PERIODS[type] * 1000) {
			timestamp = System.currentTimeMillis();
			deactivate();
			picker = null;
		} else if (timeDifference > PowerUpFactory.REGENERATE_PERIOD * 1000) {
			Point coord = PowerUpFactory.getValidCoord(powerUps, arena, characters);
			int x = coord.x;
			int y = coord.y;
			setCoord(x, y);
			display = true;
		}
	}

	/**
	 * Returns the character that picks up the power up.
	 * @param characters
	 *            character list
	 * @return character touching power up
	 */
	private ControlledCharacter findTouching(List<ControlledCharacter> characters) {
		int realX = getX() * Tile.tileSize + PowerUpFactory.TILE_OFFSET;
		int realY = getY() * Tile.tileSize + PowerUpFactory.TILE_OFFSET;

		for (ControlledCharacter character : characters) {
			int deltaX = character.getIntX() - realX;
			int deltaY = character.getIntY() - realY;
			int min = character.getRadius() * -1;
			int max = character.getRadius() + PowerUpFactory.ICON_SIZE;
			if (deltaX >= min && deltaX <= max && deltaY >= min && deltaY <= max) {
				return character;
			}
		}

		return null;
	}

	/**
	 * Activates power up.
	 */
	private void activate(World world) {
		world.getEventListener().onEventReceived(new PowerUpPickedUpEvent(x, y, picker.id));

		switch (type) {
			case PowerUpFactory.HEALTH_KIT:
				picker.resetStats();
				break;

			case PowerUpFactory.NOISE_REDUCER:
				picker.increaseNoiseFactor(0.1);
				break;

			case PowerUpFactory.DAMAGE_BOOSTER:
				PrimaryWeapon primaryWeapon = picker.getPrimary();
				PrimaryWeapon secondaryWeapon = picker.getPrimary();
				primaryWeapon.setDamage(primaryWeapon.getDamage() * 1.25);
				secondaryWeapon.setDamage(secondaryWeapon.getDamage() * 1.25);
				break;

			default:
				break;
		}
	}

	/**
	 * Deactivates power up.
	 */
	private void deactivate() {
		switch (type) {
			case PowerUpFactory.HEALTH_KIT:
				// doesn't expire
				break;

			case PowerUpFactory.NOISE_REDUCER:
				picker.increaseNoiseFactor(1 / 0.1);
				break;

			case PowerUpFactory.DAMAGE_BOOSTER:
				PrimaryWeapon primaryWeapon = picker.getPrimary();
				PrimaryWeapon secondaryWeapon = picker.getPrimary();
				primaryWeapon.setDamage(primaryWeapon.getDamage() / 1.25);
				secondaryWeapon.setDamage(secondaryWeapon.getDamage() / 1.25);
				break;

			default:
				break;
		}
	}

}
