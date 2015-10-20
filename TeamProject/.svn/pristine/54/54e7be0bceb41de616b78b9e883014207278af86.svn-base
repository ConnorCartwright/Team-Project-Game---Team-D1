package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import weapon.AssaultRifle;
import weapon.DBarrelPistol;
import weapon.Pistol;
import weapon.SMG;
import weapon.Shotgun;
import weapon.SniperRifle;

/**
 * Unit testing for the Weapon Classes.
 * 
 * Covers all of the 9 classes in the weapon package.
 * 
 * @author Connor Cartwright
 */
public class WeaponTest {

	AssaultRifle assaultRifle;
	DBarrelPistol dBarrelPistol;
	Pistol pistol;
	Shotgun shotgun;
	SMG smg;
	SniperRifle sniper;

	@Before
	public void setUp() throws Exception {
		assaultRifle = new AssaultRifle();
		dBarrelPistol = new DBarrelPistol();
		pistol = new Pistol();
		shotgun = new Shotgun();
		smg = new SMG();
		sniper = new SniperRifle();
	}

	@Test
	public void testAssaultRifleAccessors() {
		// assault rifle class
		assertEquals("AR Accuracy", 79.0, assaultRifle.getAccuracy(), 0.01);
		assertEquals("AR Damage", 40, assaultRifle.getDamage(), 0.01);
		assertEquals("AR Cooldown", 300, assaultRifle.getCooldown(), 0.01);
		assertEquals("AR Range", 800, assaultRifle.getRange(), 0.01);
		assertEquals(1, assaultRifle.getNumProjectiles());
		assertEquals("AR Proj Speed", 30, assaultRifle.getProjectileSpeed(), 0.01);
		assertEquals("AR Reload Time", 800, assaultRifle.getReloadTime(), 0.01);
		assertEquals(25, assaultRifle.getMagSize());
		assertEquals(100, assaultRifle.getMaxAmmo());
	}

	@Test
	public void testDbarrelPistolAccessors() {
		// d barrel pistol class
		assertEquals("DB Pistol Accuracy", 84, dBarrelPistol.getAccuracy(), 0.01);
		assertEquals("DB Pistol Damage", 45, dBarrelPistol.getDamage(), 0.01);
		assertEquals("DB Pistol Cooldown", 1000, dBarrelPistol.getCooldown(), 0.01);
		assertEquals("DB Pistol Range", 800, dBarrelPistol.getRange(), 0.01);
		assertEquals(2, dBarrelPistol.getNumProjectiles());
		assertEquals("DB Pistol Proj Speed", 30, dBarrelPistol.getProjectileSpeed(), 0.01);
		assertEquals("DB Pistol Reload Time", 1000, dBarrelPistol.getReloadTime(), 0.01);
		assertEquals(8, dBarrelPistol.getMagSize());
		assertEquals(32, dBarrelPistol.getMaxAmmo());
	}

	@Test
	public void testPistolAccessors() {
		// pistol class
		assertEquals("Pistol Accuracy", 85, pistol.getAccuracy(), 0.01);
		assertEquals("Pistol Damage", 14, pistol.getDamage(), 0.01);
		assertEquals("Pistol Cooldown", 380, pistol.getCooldown(), 0.01);
		assertEquals("Pistol Range", 450, pistol.getRange(), 0.01);
		assertEquals(1, pistol.getNumProjectiles());
		assertEquals("Pistol Proj Speed", 19, pistol.getProjectileSpeed(), 0.01);
		assertEquals("Pistol Reload Time", 640, pistol.getReloadTime(), 0.01);
		assertEquals(10, pistol.getMagSize());
		assertEquals(40, pistol.getMaxAmmo());
	}

	@Test
	public void testShotgunAccessors() {
		// shotgun class
		assertEquals("Shotgun Accuracy", 70, shotgun.getAccuracy(), 0.01);
		assertEquals("Shotgun Damage", 30, shotgun.getDamage(), 0.01);
		assertEquals("Shotgun Cooldown", 1500, shotgun.getCooldown(), 0.01);
		assertEquals("Shotgun Range", 700, shotgun.getRange(), 0.01);
		assertEquals(5, shotgun.getNumProjectiles());
		assertEquals("Shotgun Proj Speed", 25, shotgun.getProjectileSpeed(), 0.01);
		assertEquals("Shotgun Reload Time", 2000, shotgun.getReloadTime(), 0.01);
		assertEquals(8, shotgun.getMagSize());
		assertEquals(24, shotgun.getMaxAmmo());
	}

	@Test
	public void testSubMachineGunAccessors() {
		// smg class
		assertEquals("SMG Accuracy", 76, smg.getAccuracy(), 0.01);
		assertEquals("SMG Damage", 12.1, smg.getDamage(), 0.01);
		assertEquals("SMG Cooldown", 150, smg.getCooldown(), 0.01);
		assertEquals("SMG Range", 600, smg.getRange(), 0.01);
		assertEquals(1, smg.getNumProjectiles());
		assertEquals("SMG Proj Speed", 22, smg.getProjectileSpeed(), 0.01);
		assertEquals("SMG Reload Time", 280, smg.getReloadTime(), 0.01);
		assertEquals(30, smg.getMagSize());
		assertEquals(120, smg.getMaxAmmo());
	}

	@Test
	public void testSniperRifleAccessors() {
		// sniper rifle class
		assertEquals("Sniper Accuracy", 96, sniper.getAccuracy(), 0.01);
		assertEquals("Sniper Damage", 99, sniper.getDamage(), 0.01);
		assertEquals("Sniper Cooldown", 2000, sniper.getCooldown(), 0.01);
		assertEquals("Sniper Range", 1500, sniper.getRange(), 0.01);
		assertEquals(1, sniper.getNumProjectiles());
		assertEquals("Sniper Proj Speed", 50, sniper.getProjectileSpeed(), 0.01);
		assertEquals("Sniper Reload Time", 3000, sniper.getReloadTime(), 0.01);
		assertEquals(5, sniper.getMagSize());
		assertEquals(20, sniper.getMaxAmmo());
	}

	@Test
	public void testMutators() {
		// as each subclass inherits from the abstract Weapon class,
		// it is only necessary to check that the mutator methods work
		// for a single core weapon class.
		assaultRifle.setAccuracy(50);
		assertEquals("AR Accuracy", 50.0, assaultRifle.getAccuracy(), 0.01);

		assaultRifle.setDamage(54.4);
		assertEquals("AR Damage", 54.4, assaultRifle.getDamage(), 0.01);

		assaultRifle.setCooldown(172);
		assertEquals("AR Cooldown", 172, assaultRifle.getCooldown(), 0.01);

		assaultRifle.setRange(545);
		assertEquals("AR Range", 545, assaultRifle.getRange(), 0.01);

		assaultRifle.setNumProjectiles(3);
		assertEquals(3, assaultRifle.getNumProjectiles());

		assaultRifle.setProjectileSpeed(500);
		assertEquals("AR Proj Speed", 500, assaultRifle.getProjectileSpeed(), 0.01);

		assaultRifle.setReloadTime(675);
		assertEquals("AR Reload Time", 675, assaultRifle.getReloadTime(), 0.01);
	}

}
