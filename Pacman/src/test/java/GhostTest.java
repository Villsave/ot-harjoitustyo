
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.Ghost;
import pacman.Player;


public class GhostTest {
    Ghost ghost;
    
    @Before
    public void setUp() {
        ghost = new Ghost(150, 100);
    }
    
    @Test
    public void collidingWithGhostKills() {
        Player pacman = new Player();
        if (pacman.intersects(ghost)) {
            pacman.death();
        }
        pacman.setPosition(150, 100);
        assertEquals(2, pacman.checkLives());
    }
    
    @Test
    public void ghostMoves() {
        ghost.moveGhost(Ghost.Direction.DOWN);
        assertEquals(50, (int) ghost.getVelocityY());
    }
    
    @Test
    public void ghostDoesntMoveIntoWall() {
        ghost.setPosition(92, 97);
        ghost.checkDirection();
        assertEquals(2, ghost.GetDirections().size());
    }
    
}
