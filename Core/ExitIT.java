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
public class ExitIT {
    Location locA;
    Location locB;
    Exit ab;
    
    
    @Before
    public void setUp(){
        locA = new Location("SalleA", "");
        locB = new Location("SalleB", "");
        ab = new Exit(locA, locB);
    }

    /**
     * Test of ableToMoveThrough method, of class Exit.
     */
    @Test
    public void testAbleToMoveThrough(){
        assertTrue(ab.ableToMoveThrough());
    }

    /**
     * Test of getLocationA method, of class Exit.
     */
    @Test
    public void testGetLocationA(){
        assertEquals(locA, ab.getLocationA());
    }

    /**
     * Test of getLocationB method, of class Exit.
     */
    @Test
    public void testGetLocationB(){
        assertEquals(locB, ab.getLocationB());
    }

    /**
     * Test of getLocation method, of class Exit.
     */
    @Test
    public void testGetLocation1() {
        assertEquals(locA, ab.getLocation(locA.getName()));
        assertEquals(locB, ab.getLocation(locB.getName()));
    }
    
    @Test
    public void testGetLocation2() {
        assertNull(ab.getLocation(""));
    }
    
    @Test
    public void testGetLocationNull() {
        assertNull(ab.getLocation(null));
    }

    /**
     * Test of getOtherLocation method, of class Exit.
     */
    @Test
    public void testGetOtherLocation1() {
        assertEquals(locB, ab.getOtherLocation(locA.getName()));
        assertEquals(locA, ab.getOtherLocation(locB.getName()));
    }
    
    @Test
    public void testGetOtherLocation2() {
        assertNull(ab.getLocation(""));
    }
    
     @Test
    public void testGetOtherLocationNull() {
        assertNull(ab.getLocation(null));
    }
}
