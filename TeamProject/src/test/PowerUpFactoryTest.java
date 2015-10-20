package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import character.ControlledCharacter;
import character.Tank;
import core.Arena;
import core.PowerUp;
import core.PowerUpFactory;

/**
 * Unit testing for PowerUpFactory class.
 * 
 * @author Shobitha Shivakumar
 */
public class PowerUpFactoryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PowerUpFactory.initializeIcons();
	}

	/**
	 * Test method for {@link core.PowerUpFactory#initializeIcons()}.
	 */
	@Test
	public void testInitializeIcons() {
		for (int i = 0; i < PowerUpFactory.NUM_OF_TYPES; i++) {
			assertNotNull("Expected icon of type " + i + " to be initialized", PowerUpFactory.getIcon(i));
		}
	}

	/**
	 * Test method for {@link core.PowerUpFactory#getIcon(int)}.
	 */
	@Test
	public void testGetIcon() {
		int type = PowerUpFactory.HEALTH_KIT;
		assertNotNull("Expected health kit icon to be returned", PowerUpFactory.getIcon(type));
	}

	/**
	 * Test method for {@link core.PowerUpFactory#generatePowerUps(core.Arena, java.util.List)}.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Test
	public void testGeneratePowerUps() throws FileNotFoundException, IOException {
		Arena arena = new Arena("maze", false);
		List<ControlledCharacter> characters = new ArrayList<ControlledCharacter>();
		for (int i = 0; i < 5; i++) {
			characters.add(new Tank(i, 0));
		}
		PowerUp[] powerUps = PowerUpFactory.generatePowerUps(arena, characters);

		for (int i = 0; i < powerUps.length; i++) {
			int x = powerUps[i].getX();
			int y = powerUps[i].getY();

			boolean isXWithinBoundries = x >= 0 && x < arena.getWidth();
			boolean isYWithinBoundries = y >= 0 && x < arena.getHeight();

			boolean isWalkable = arena.get(x, y).isWalkable();

			boolean arePowerUpsDistant = true;
			for (int j = 0; j < i; j++) {
				int deltaX = x - powerUps[j].getX();
				int deltaY = y - powerUps[j].getY();
				double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				if (distance < PowerUpFactory.MIN_DISTANCE) {
					arePowerUpsDistant = false;
				}
			}

			boolean areCharactersDistant = true;
			for (ControlledCharacter character : characters) {
				int deltaX = x - character.getIntX();
				int deltaY = y - character.getIntY();
				double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				if (distance < PowerUpFactory.MIN_DISTANCE) {
					areCharactersDistant = false;
				}
			}

			assertTrue("Expected coordinates to be within arena boundries", isXWithinBoundries && isYWithinBoundries);
			assertTrue("Expected coordinates to be of a walkable tile", isWalkable);
			assertTrue("Expected coordinates to be distant from other power ups", arePowerUpsDistant);
			assertTrue("Expected coordinates to be distant from all characters", areCharactersDistant);
		}

		int expected = (arena.getWidth() * arena.getHeight()) / 200;
		int actual = powerUps.length;
		assertEquals("Expected number of power ups generated to be " + expected + " not " + actual, expected, actual);
	}

	/**
	 * Test method for
	 * {@link core.PowerUpFactory#getValidCoord(core.PowerUp[], core.Arena, java.util.List)}.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Test
	public void testGetValidCoord() throws FileNotFoundException, IOException {
		Arena arena = new Arena("maze", false);
		List<ControlledCharacter> characters = new ArrayList<ControlledCharacter>();
		for (int i = 0; i < 5; i++) {
			characters.add(new Tank(i, 0));
		}
		PowerUp[] powerUps = PowerUpFactory.generatePowerUps(arena, characters);

		Point coord = PowerUpFactory.getValidCoord(powerUps, arena, characters);
		int x = coord.x;
		int y = coord.y;

		boolean isXWithinBoundries = x >= 0 && x < arena.getWidth();
		boolean isYWithinBoundries = y >= 0 && x < arena.getHeight();

		boolean isWalkable = arena.get(coord).isWalkable();

		boolean arePowerUpsDistant = true;
		for (int i = 0; i < powerUps.length; i++) {
			int deltaX = x - powerUps[i].getX();
			int deltaY = y - powerUps[i].getY();
			double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			if (distance < PowerUpFactory.MIN_DISTANCE) {
				arePowerUpsDistant = false;
			}
		}

		boolean areCharactersDistant = true;
		for (ControlledCharacter character : characters) {
			int deltaX = x - character.getIntX();
			int deltaY = y - character.getIntY();
			double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			if (distance < PowerUpFactory.MIN_DISTANCE) {
				areCharactersDistant = false;
			}
		}

		assertTrue("Expected coordinate to be within arena boundries", isXWithinBoundries && isYWithinBoundries);
		assertTrue("Expected coordinate to be of a walkable tile", isWalkable);
		assertTrue("Expected coordinate to be distant from other power ups", arePowerUpsDistant);
		assertTrue("Expected coordinate to be distant from all characters", areCharactersDistant);
	}

}
