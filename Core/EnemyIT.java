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
    private Enemy enemy1;
    private Enemy enemy2;
    private Hero theHero;
    private int attackEnemy;
    private int heroMaxHp;
    private Location locA;
    private final String enemyDescription = "Green shitty looking mob";
    @Before
    public void setUp() {
        Location locB = new Location("Salle 2", "salut c'est moi", "zut");
        locA = new Location("Salle", "On s'en branle", "alors");
        attackEnemy = 5;
        heroMaxHp = 50;
        enemy1 = new Enemy("GobelinA", 15, attackEnemy, locA, enemyDescription);
        enemy2 = new Enemy("GobelinB", 15, attackEnemy, locB, enemyDescription);
        theHero = new Hero("Ardbert", heroMaxHp, 15, locA);
    }
    

    /**
     * Test of attackHero method, of class Enemy.
     */
    @Test
    public void testAttackHero1() {
        enemy1.attackHero();
        assertEquals(heroMaxHp - attackEnemy, theHero.getHealthPoints());
    }
    
    @Test
    public void testAttackHero2() {
        enemy2.attackHero();
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
