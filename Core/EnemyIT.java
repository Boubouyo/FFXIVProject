/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Core;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fetiveau
 */
public class EnemyIT {
    private Hero theHero;
    private int attackEnemy;
    private int heroMaxHp;
    private Location locA;
    private Location locB;
    private final String enemyDescription = "Green shitty looking mob";
    @Before
    public void setUp() {
        locB = new Location("Salle 2", "salut c'est moi");
        locA = new Location("Salle", "On s'en branle");
        attackEnemy = 5;
        heroMaxHp = 50;
        locB.addEnemy("GobelinB", 15, attackEnemy, enemyDescription);
        locA.addEnemy("GobelinA", 15, attackEnemy, enemyDescription);
        theHero = new Hero("Ardbert", heroMaxHp, 15, locA);
    }
    

    /**
     * Test of attackHero method, of class Enemy.
     */
    @Test
    public void testAttackHero1() {
        locA.getEnemyByName("GobelinA").attackHero();
        assertEquals(heroMaxHp - attackEnemy, theHero.getHealthPoints());
    }
    
    @Test
    public void testAttackHero2() {
        locB.getEnemyByName("GobelinB").attackHero();
        assertEquals(heroMaxHp, theHero.getHealthPoints());
    }

    /**
     * Test of die method, of class Enemy.
     */
    @Test
    public void testDie(){
        theHero.attackEnemy("GobelinA");
        assertNull(locA.getEnemyByName("GobelinA"));
    }

   
    
}
