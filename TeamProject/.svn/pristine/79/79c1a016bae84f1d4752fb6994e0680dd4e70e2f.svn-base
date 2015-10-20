package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.lang.reflect.Field;
import java.util.List;

import network.GameDataPackets.WorldStatePacket;
import network.GameEvent.GameEventListener;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import character.AbstractCharacter;
import character.Commando;
import character.ControlledCharacter;
import character.Scout;
import character.Tank;
import core.Arena;
import core.PowerUp;
import core.PowerUpFactory;
import core.Projectile;
import core.Tile;
import core.World;

/**
 * Unit testing for World class.
 * 
 * @author Madyan Al-Jazaeri
 */
public class WorldTest {

	Arena arena;
	GameEventListener listener;
	World world;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// initializing power up icons
		PowerUpFactory.initializeIcons();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		arena = new Arena("maze", false);
		world = new World(arena, null);

		for (int i = 1; i < 5; i++) {
			ControlledCharacter character = new Commando(i, 0);
			Projectile projectile = new Projectile(character, 16.3, 16.4, 17.3, 15.1);
			world.addPlayer(character);
			world.addProjectile(projectile);
		}

		for (int i = 5; i < 9; i++) {
			ControlledCharacter character = new Tank(i, 1);
			Projectile projectile = new Projectile(character, 16.3, 16.4, 17.3, 15.1);
			world.addPlayer(character);
			world.addProjectile(projectile);
		}
	}

	/**
	 * Test method for {@link core.World#addPlayer(character.ControlledCharacter)}.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAddPlayer() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// adding character
		ControlledCharacter c = new Scout(0, 0);
		world.addPlayer(c);

		// getting characters list
		Field field = world.getClass().getDeclaredField("characters");
		field.setAccessible(true);
		@SuppressWarnings("unchecked")
		List<ControlledCharacter> characters = (List<ControlledCharacter>) field.get(world);

		// looking for added character in list
		boolean isFound = false;
		for (ControlledCharacter character : characters) {
			if (c.equals(character)) {
				isFound = true;
			}
		}

		// checking if character location is walkable
		boolean isValidLocation = false;
		List<Point> spawns = arena.getSpawn(0);
		for (Point spawn : spawns) {
			if (c.getX() == spawn.getX() * Tile.tileSize && c.getY() == spawn.getY() * Tile.tileSize) {
				isValidLocation = true;
			}
		}

		assertTrue("Expected character to be added to list", isFound);
		assertTrue("Expected character location to be in spawn list", isValidLocation);
	}

	/**
	 * Test method for {@link core.World#addProjectile(core.Projectile)}.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAddProjectile() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// adding projectile
		Projectile p = new Projectile(new Tank(0, 0), 10.5, 13.4, 12.6, 12.4);
		world.addProjectile(p);

		// getting projectiles list
		Field field = world.getClass().getDeclaredField("projectiles");
		field.setAccessible(true);
		@SuppressWarnings("unchecked")
		List<Projectile> projectiles = (List<Projectile>) field.get(world);

		// looking for added projectile in list
		boolean isFound = false;
		for (Projectile projectile : projectiles) {
			if (p.equals(projectile)) {
				isFound = true;
			}
		}

		assertTrue("Expected projectile to be added to list", isFound);
	}

	/**
	 * Test method for {@link core.World#generateState()}.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGenerateState() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		WorldStatePacket wsp = world.generateState();

		// getting characters list
		Field field = world.getClass().getDeclaredField("characters");
		field.setAccessible(true);
		@SuppressWarnings("unchecked")
		List<ControlledCharacter> characters = (List<ControlledCharacter>) field.get(world);

		// getting projectiles list
		field = world.getClass().getDeclaredField("projectiles");
		field.setAccessible(true);
		@SuppressWarnings("unchecked")
		List<Projectile> projectiles = (List<Projectile>) field.get(world);

		// getting powerUps array
		field = world.getClass().getDeclaredField("powerUps");
		field.setAccessible(true);
		PowerUp[] powerUps = (PowerUp[]) field.get(world);

		assertEquals("Number of characters generated is different", characters.size(), wsp.characters.size());
		assertEquals("Number of projectiles generated is different", projectiles.size(), wsp.projectiles.size());
		assertEquals("Number of power ups generated is different", powerUps.length, wsp.projectiles.size());
	}

	/**
	 * Test method for {@link core.World#generateVisibleCharacters(character.AbstractCharacter)}.
	 */
	@Test
	public void testGenerateVisibleCharacters() {
		// adding character and generating visible list
		ControlledCharacter c = new Scout(0, 0);
		world.addPlayer(c);
		List<AbstractCharacter> characters = world.generateVisibleCharacters(c);

		// ensuring all characters are in visible region
		boolean areValid = true;
		for (AbstractCharacter character : characters) {
			double x1 = character.getX() - character.getRadius();
			double y1 = character.getY() - character.getRadius();
			if (Point2D.distance(x1, y1, c.getX(), c.getY()) >= c.getViewRange()) {
				areValid = false;
			}
		}

		assertTrue("Expected all characters to be at visible positions", areValid);
	}

}
