package character;

import weapon.Pistol;
import weapon.PrimaryWeapon;
import weapon.SMG;
import weapon.SecondaryWeapon;

/**
 * The <code>SpecOps</code> class defines how a <code>Character</code> of type <code>SpecOps</code>
 * will behave, e.g. the characters speed, view angle, etc. The <code>SpecOps</code> is a type of
 * <code>Character</code>.
 * 
 * @see Character
 * @author Team D1
 * @author Connor Cartwright
 *
 */
public class SpecOps extends ControlledCharacter {

	public static final int typeID = 4; // the type ID, used in lobby and for swapping
	private static final double healthPoints = 90; // defines the healthPoints of this class
	private static final double stamina = 60; // defines the stamina of this class
	private static final double speed = 1.3; // defines the movement speed of this class
	private static final int viewRange = 450; // defines the length of the line of sight
	private static final int viewAngle = 60; // defines the angle of the line of sight
	private static final int radius = 9; // defines the size of the character
	private static final PrimaryWeapon primary = new SMG(); // gives this class a Sniper
	private static final SecondaryWeapon secondary = new Pistol(); // gives this class a pistol

	/**
	 * Creating a new <code>Character</code> of type <code>SpecOps</code> using the set fields
	 * defined above.
	 */
	public SpecOps(int id, int team) {
		super(id, typeID, team, healthPoints, stamina, speed, viewRange, viewAngle, radius, primary, secondary);
	}
	
}
