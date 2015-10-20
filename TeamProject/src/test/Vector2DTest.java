package test;

import static org.junit.Assert.assertEquals;
import graphics.Vector2D;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit testing for Vector2D class.
 * 
 * @author Madyan Al-Jazaeri
 */
public class Vector2DTest {

	Vector2D vector2D;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vector2D = new Vector2D(1.0, 2.0);
	}

	/**
	 * Test method for {@link graphics.Vector2D#add(double)}.
	 */
	@Test
	public void testAddDouble() {
		vector2D.add(1.0);
		assertEquals("Wrong addition result with 1.0 in x", 2.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong addition result with 1.0 in y", 3.0, vector2D.y, 0.0000000001);

		vector2D.add(0.0);
		assertEquals("Wrong addition result with 0.0 in x", 2.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong addition result with 0.0 in y", 3.0, vector2D.y, 0.0000000001);

		vector2D.add(-1.0);
		assertEquals("Wrong addition result with -1.0 in x", 1.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong addition result with -1.0 in y", 2.0, vector2D.y, 0.0000000001);
	}

	/**
	 * Test method for {@link graphics.Vector2D#sub(double)}.
	 */
	@Test
	public void testSubDouble() {
		vector2D.sub(1.0);
		assertEquals("Wrong subtraction result with 1.0 in x", 0.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong subtraction result with 1.0 in y", 1.0, vector2D.y, 0.0000000001);

		vector2D.sub(0.0);
		assertEquals("Wrong subtraction result with 0.0 in x", 0.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong subtraction result with 0.0 in y", 1.0, vector2D.y, 0.0000000001);

		vector2D.sub(-1.0);
		assertEquals("Wrong subtraction result with -1.0 in x", 1.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong subtraction result with -1.0 in y", 2.0, vector2D.y, 0.0000000001);
	}

	/**
	 * Test method for {@link graphics.Vector2D#div(double)}.
	 */
	@Test
	public void testDivDouble() {
		vector2D.div(2.0);
		assertEquals("Wrong division result with 2.0 in x", 0.5, vector2D.x, 0.0000000001);
		assertEquals("Wrong division result with 2.0 in y", 1.0, vector2D.y, 0.0000000001);

		vector2D.div(1.0);
		assertEquals("Wrong division result with 1.0 in x", 0.5, vector2D.x, 0.0000000001);
		assertEquals("Wrong division result with 1.0 in y", 1.0, vector2D.y, 0.0000000001);

		vector2D.div(-2.0);
		assertEquals("Wrong division result with -2.0 in x", -0.25, vector2D.x, 0.0000000001);
		assertEquals("Wrong division result with -2.0 in y", -0.5, vector2D.y, 0.0000000001);

		vector2D.div(-0.5);
		assertEquals("Wrong division result with -0.5 in x", 0.5, vector2D.x, 0.0000000001);
		assertEquals("Wrong division result with -0.5 in y", 1.0, vector2D.y, 0.0000000001);
	}

	/**
	 * Test method for {@link graphics.Vector2D#mult(double)}.
	 */
	@Test
	public void testMultDouble() {
		vector2D.mult(2.0);
		assertEquals("Wrong multiplication result with 2.0 in x", 2.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong multiplication result with 2.0 in y", 4.0, vector2D.y, 0.0000000001);

		vector2D.mult(1.0);
		assertEquals("Wrong multiplication result with 1.0 in x", 2.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong multiplication result with 1.0 in y", 4.0, vector2D.y, 0.0000000001);

		vector2D.mult(-2.0);
		assertEquals("Wrong multiplication result with -2.0 in x", -4.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong multiplication result with -2.0 in y", -8.0, vector2D.y, 0.0000000001);

		vector2D.mult(-0.5);
		assertEquals("Wrong multiplication result with -0.5 in x", 2.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong multiplication result with -0.5 in y", 4.0, vector2D.y, 0.0000000001);

		vector2D.mult(0.0);
		assertEquals("Wrong multiplication result with 0.0 in x", 0.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong multiplication result with 0.0 in y", 0.0, vector2D.y, 0.0000000001);
	}

	/**
	 * Test method for {@link graphics.Vector2D#add(graphics.Vector2D)}.
	 */
	@Test
	public void testAddVector2D() {
		vector2D.add(new Vector2D(0.1, -1.0));
		assertEquals("Wrong addition result with 1.0 in x", 1.1, vector2D.x, 0.0000000001);
		assertEquals("Wrong addition result with -1.0 in y", 1.0, vector2D.y, 0.0000000001);

		vector2D.add(new Vector2D(0.0, 0.0));
		assertEquals("Wrong addition result with 0.0 in x", 1.1, vector2D.x, 0.0000000001);
		assertEquals("Wrong addition result with 0.0 in y", 1.0, vector2D.y, 0.0000000001);
	}

	/**
	 * Test method for {@link graphics.Vector2D#sub(graphics.Vector2D)}.
	 */
	@Test
	public void testSubVector2D() {
		vector2D.sub(new Vector2D(0.1, -1.0));
		assertEquals("Wrong subtraction result with 0.1 in x", 0.9, vector2D.x, 0.0000000001);
		assertEquals("Wrong subtraction result with -1.0 in y", 3.0, vector2D.y, 0.0000000001);

		vector2D.sub(new Vector2D(0.0, 0.0));
		assertEquals("Wrong subtraction result with 0.0 in x", 0.9, vector2D.x, 0.0000000001);
		assertEquals("Wrong subtraction result with 0.0 in y", 3.0, vector2D.y, 0.0000000001);
	}

	/**
	 * Test method for {@link graphics.Vector2D#div(graphics.Vector2D)}.
	 */
	@Test
	public void testDivVector2D() {
		vector2D.div(new Vector2D(2.0, 1.0));
		assertEquals("Wrong division result with 2.0 in x", 0.5, vector2D.x, 0.0000000001);
		assertEquals("Wrong division result with 1.0 in y", 2.0, vector2D.y, 0.0000000001);

		vector2D.div(new Vector2D(-2.0, -0.5));
		assertEquals("Wrong division result with -2.0 in x", -0.25, vector2D.x, 0.0000000001);
		assertEquals("Wrong division result with -0.5 in y", -4.0, vector2D.y, 0.0000000001);
	}

	/**
	 * Test method for {@link graphics.Vector2D#mult(graphics.Vector2D)}.
	 */
	@Test
	public void testMultVector2D() {
		vector2D.mult(new Vector2D(2.0, 1.0));
		assertEquals("Wrong multiplication result with 2.0 in x", 2.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong multiplication result with 1.0 in y", 2.0, vector2D.y, 0.0000000001);

		vector2D.mult(new Vector2D(-2.0, -0.5));
		assertEquals("Wrong multiplication result with -2.0 in x", -4.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong multiplication result with -0.5 in y", -1.0, vector2D.y, 0.0000000001);

		vector2D.mult(new Vector2D(0.0, 0.0));
		assertEquals("Wrong multiplication result with 0.0 in x", 0.0, vector2D.x, 0.0000000001);
		assertEquals("Wrong multiplication result with 0.0 in y", 0.0, vector2D.y, 0.0000000001);
	}

}
