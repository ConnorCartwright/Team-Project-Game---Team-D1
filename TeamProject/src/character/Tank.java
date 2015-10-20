package character;

import weapon.Pistol;
import weapon.PrimaryWeapon;
import weapon.SecondaryWeapon;
import weapon.Shotgun;

/**
 * The <code>Tank</code> class defines how a <code>ControlledCharacter</code> of type
 * <code>Tank</code> will behave, e.g. the characters speed, view angle, etc. The <code>Tank</code>
 * is a type of <code>ControlledCharacter</code>.
 * 
 * @see ControlledCharacter
 * @author Team D1
 * @author Connor Cartwright
 *
 */
public class Tank extends ControlledCharacter {

	public static final int typeID = 3; // the type ID, used in lobby and for swapping
	private static final double healthPoints = 120; // defines the healthPoints of this class
	private static final double stamina = 40; // defines the stamina of this class
	private static final double speed = 1.2; // defines the movement speed of this class
	private static final int viewRange = 320; // defines the length of the line of sight
	private static final int viewAngle = 100; // defines the angle of the line of sight
	private static final int radius = 10; // defines the size of the character
	private static final double noise = 35;
	private static final PrimaryWeapon primary = new Shotgun(); // gives this class an Assault Rifle
	private static final SecondaryWeapon secondary = new Pistol(); // gives this class a pistol

	/**
	 * Creating a new <code>ControlledCharacter</code> of type <code>Tank</code> using the set
	 * fields defined above.
	 */
	public Tank(int id, int team) {
		super(id, typeID, team, healthPoints, stamina, speed, noise, viewRange, viewAngle, radius, primary, secondary);
	}

}
