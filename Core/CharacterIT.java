/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fetiveau
 */
public class CharacterIT {
    private Character charA;
    private Character charB;
    private Location locA;
    private String charAName;
    private int hpA;
    private int attackB;
    
    @Before
    public void setUp() {
        locA = new Location("Salle A"/*, "This is an empty room"*/);
        charAName = "Jack";
        hpA = 15;
        attackB = 6;
        charA = new Character(charAName, hpA, 5, locA) {
            @Override
            public void die() {
                charA = null;
            }
        };
        charB = new Character("Jhon", 18, attackB, locA) {
            @Override
            public void die() {
                charB = null;
            }
        };
    }

    /**
     * Test of getName method, of class Character.
     */
    @Test
    public void testGetName(){
        assertEquals( charAName, charA.getName());
    }

    /**
     * Test of getHealthPoints method, of class Character.
     */
    @Test
    public void testGetHealthPoints() {
        assertEquals(hpA, charA.getHealthPoints());
    }

    /**
     * Test of damageCharacter method, of class Character.
     */
    @Test
    public void testDamageCharacter() {
        charA.damageCharacter(5);
        assertEquals(hpA - 5, charA.getHealthPoints());
    }

    /**
     * Test of healCharacter method, of class Character.
     */
    @Test
    public void testHealCharacter() {
    	charA.damageCharacter(5);
        assertEquals(hpA - 5, charA.getHealthPoints());
        charA.healCharacter(5);
        assertEquals(hpA, charA.getHealthPoints());
    }

    /**
     * Test of attackCharacter method, of class Character.
     */
    @Test
    public void testAttackCharacterA() {
        charB.attackCharacter(charA);
        assertEquals(hpA - attackB,charA.getHealthPoints());
    }
    
    @Test
    public void testAttackCharacterB() {
        charA.attackCharacter(charB);
        assertEquals(hpA,charA.getHealthPoints());
    }

    @Test
    public void testAttackCharacterC() {
        charA.attackCharacter(charB);
        charA.attackCharacter(charB);
        charA.attackCharacter(charB);
        charA.attackCharacter(charB);
        
        assertNull(charB);
    }
    
    /**
     * Test of die method, of class Character.
     */
    @Test
    public void testDie() {
        charA.die();
        assertNull(charA);
    }
}
