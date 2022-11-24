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
public class LocationIT {
    
    public LocationIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Location.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Location instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHero method, of class Location.
     */
    @Test
    public void testGetHero() {
        System.out.println("getHero");
        Location instance = null;
        Hero expResult = null;
        Hero result = instance.getHero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addExits method, of class Location.
     */
    @Test
    public void testAddExits() {
        System.out.println("addExits");
        Exit newExit = null;
        Location instance = null;
        instance.addExits(newExit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of takeExit method, of class Location.
     */
    @Test
    public void testTakeExit() {
        System.out.println("takeExit");
        String locationName = "";
        Hero hero = null;
        Location instance = null;
        Location expResult = null;
        Location result = instance.takeExit(locationName, hero);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of look method, of class Location.
     */
    @Test
    public void testLook() {
        System.out.println("look");
        Location instance = null;
        instance.look();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEnemy method, of class Location.
     */
    @Test
    public void testAddEnemy() {
        System.out.println("addEnemy");
        Enemy newEnemy = null;
        Location instance = null;
        instance.addEnemy(newEnemy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEnemy method, of class Location.
     */
    @Test
    public void testDeleteEnemy() {
        System.out.println("deleteEnemy");
        Enemy deletedEnemy = null;
        Location instance = null;
        instance.deleteEnemy(deletedEnemy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnemyByName method, of class Location.
     */
    @Test
    public void testGetEnemyByName() {
        System.out.println("getEnemyByName");
        String name = "";
        Location instance = null;
        Enemy expResult = null;
        Enemy result = instance.getEnemyByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemFromString method, of class Location.
     */
    @Test
    public void testGetItemFromString() {
        System.out.println("getItemFromString");
        String name = "";
        Location instance = null;
        Item expResult = null;
        Item result = instance.getItemFromString(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addItems method, of class Location.
     */
    @Test
    public void testAddItems() {
        System.out.println("addItems");
        Item newItem = null;
        Location instance = null;
        instance.addItems(newItem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
