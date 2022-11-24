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
public class HeroIT {
    
    public HeroIT() {
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
     * Test of addToInventory method, of class Hero.
     */
    @Test
    public void testAddToInventory() {
        System.out.println("addToInventory");
        Item item = null;
        Hero instance = null;
        instance.addToInventory(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromInventory method, of class Hero.
     */
    @Test
    public void testRemoveFromInventory() {
        System.out.println("removeFromInventory");
        Item item = null;
        Hero instance = null;
        instance.removeFromInventory(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemFromInventory method, of class Hero.
     */
    @Test
    public void testGetItemFromInventory() {
        System.out.println("getItemFromInventory");
        String itemName = "";
        Hero instance = null;
        Item expResult = null;
        Item result = instance.getItemFromInventory(itemName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsGameFinished method, of class Hero.
     */
    @Test
    public void testGetIsGameFinished() {
        System.out.println("getIsGameFinished");
        Hero instance = null;
        boolean expResult = false;
        boolean result = instance.getIsGameFinished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of die method, of class Hero.
     */
    @Test
    public void testDie() {
        System.out.println("die");
        Hero instance = null;
        instance.die();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemByName method, of class Hero.
     */
    @Test
    public void testGetItemByName() {
        System.out.println("getItemByName");
        String itemName = "";
        Hero instance = null;
        Item expResult = null;
        Item result = instance.getItemByName(itemName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doTheCommand method, of class Hero.
     */
    @Test
    public void testDoTheCommand() {
        System.out.println("doTheCommand");
        String[] commandAndArgs = null;
        Hero instance = null;
        instance.doTheCommand(commandAndArgs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeLocation method, of class Hero.
     */
    @Test
    public void testChangeLocation() {
        System.out.println("changeLocation");
        String locationName = "";
        Hero instance = null;
        instance.changeLocation(locationName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printHelpCommands method, of class Hero.
     */
    @Test
    public void testPrintHelpCommands() {
        System.out.println("printHelpCommands");
        Hero instance = null;
        instance.printHelpCommands();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lookSomething method, of class Hero.
     */
    @Test
    public void testLookSomething() {
        System.out.println("lookSomething");
        String somethingName = "";
        Hero instance = null;
        instance.lookSomething(somethingName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of takeItem method, of class Hero.
     */
    @Test
    public void testTakeItem() {
        System.out.println("takeItem");
        String itemName = "";
        Hero instance = null;
        instance.takeItem(itemName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of useItem method, of class Hero.
     */
    @Test
    public void testUseItem() {
        System.out.println("useItem");
        String itemName = "";
        Hero instance = null;
        instance.useItem(itemName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of useItemOnItem method, of class Hero.
     */
    @Test
    public void testUseItemOnItem() {
        System.out.println("useItemOnItem");
        String item1Name = "";
        String item2Name = "";
        Hero instance = null;
        instance.useItemOnItem(item1Name, item2Name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of attackEnemy method, of class Hero.
     */
    @Test
    public void testAttackEnemy() {
        System.out.println("attackEnemy");
        String enemyName = "";
        Hero instance = null;
        instance.attackEnemy(enemyName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
