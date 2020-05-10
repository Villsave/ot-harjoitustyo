package GameTest;


import org.junit.Before;
import org.junit.Test;
import domain.Player;
import static org.junit.Assert.*;


public class PlayerTest {
    Player pacman;
    
    @Before
    public void setUp() {
        pacman = new Player();
    }
    
   
    @Test
    public void pacmanHasThreeLives() {
        assertEquals(3, pacman.checkLives());
    }
    
    @Test
    public void dyingRemovesLife() {
        pacman.death();
        assertEquals(2, pacman.checkLives());
    }
    
    @Test
    public void pacmanDoesntMoveAfterDeath() {
        pacman.death();
        assertEquals(100, (int) pacman.getPositionX());
        assertEquals(100, (int) pacman.getPositionY());
        assertEquals(0, pacman.getVelocityX());
        assertEquals(0, (int) pacman.getVelocityY());
        
    }
}
