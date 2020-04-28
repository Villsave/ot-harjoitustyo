

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.Player;
import pacmanui.PacmanMain;




public class PacmanTest {
    Player pacman;
    
    public PacmanTest() {
    }
    
    
    @Before
    public void setUp() {
        Player pacman = new Player();
    }
    /*
    @Test
    public void pacmanIsTheRightSize() {
        assertEquals(27, pacman.getHeight());
        assertEquals(27, pacman.getWidth());
    }
    
    @Test
    public void playerDoesNotMoveUntilButtonIsPressed() {
        assertEquals(100, (int) pacman.getPositionX());
        assertEquals(100, (int) pacman.getPositionY());
    }
    
    @Test
    public void movingLeftWorks() {
        pacman.setVelocity(-50, 0);
        assertEquals(true, pacman.getPositionX() < 100);
    }
    
    @Test
    public void movingDownWorks() {
        pacman.setVelocity(0, 50);
        assertEquals(true, pacman.getPositionY() > 100);
    }
*/
}
