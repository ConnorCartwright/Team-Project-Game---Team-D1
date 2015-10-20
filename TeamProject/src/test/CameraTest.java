package test;

import static org.junit.Assert.assertEquals;
import game.Game;
import gui.AbstractScreen;
import gui.Camera;
import gui.SplashScreen;

import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import core.Arena;

/**
 * Unit testing for Camera class.
 * 
 * @author Madyan Al-Jazaeri
 */
public class CameraTest {

	Arena arena;
	Camera camera;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		arena = new Arena("maze", false);
		camera = new Camera(arena);
	}

	/**
	 * Test method for {@link gui.Camera#Camera(core.Arena)}.
	 */
	@Test
	public void testCamera() {
		assertEquals("x coordinate is not correct", arena.getWidthPixel() / 2, camera.getX());
		assertEquals("y coordinate is not correct", arena.getHeightPixel() / 2, camera.getY());
	}

	/**
	 * Test method for {@link gui.Camera#getTopLeftX(java.awt.Component)}.
	 */
	@Test
	public void testGetTopLeftX() {
		AbstractScreen screen = new SplashScreen(new Game());

		assertEquals("Top left x coordinate is not correct", camera.getX() - (screen.getWidth() / 2), camera.getTopLeftX(screen));
	}

	/**
	 * Test method for {@link gui.Camera#getTopLeftY(java.awt.Component)}.
	 */
	@Test
	public void testGetTopLeftY() {
		AbstractScreen screen = new SplashScreen(new Game());

		assertEquals("Top left y coordinate is not correct", camera.getY() - (screen.getHeight() / 2), camera.getTopLeftY(screen));
	}

	/**
	 * Test method for {@link gui.Camera#getDrawArea(java.awt.Component)}.
	 */
	@Test
	public void testGetDrawArea() {
		AbstractScreen screen = new SplashScreen(new Game());
		Rectangle area = camera.getDrawArea(screen);

		assertEquals("x coordinate is not correct", camera.getTopLeftX(screen), area.getX(), 0.00000001);
		assertEquals("y coordinate is not correct", camera.getTopLeftY(screen), area.getY(), 0.00000001);
		assertEquals("Width is not correct", screen.getWidth(), area.getWidth(), 0.00000001);
		assertEquals("Height is not correct", screen.getHeight(), area.getHeight(), 0.00000001);
	}

}
