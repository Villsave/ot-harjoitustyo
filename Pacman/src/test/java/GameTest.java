
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.Game;
import pacman.Player;

public class GameTest {
    Game game;
    Player pacman;
    
    public GameTest() {
    }
    
    @Before
    public void setUp() throws SQLException {
        game = new Game();
    }

    @Test
    public void pointsAreLostUponDeath() {
        pacman = new Player();
        game.points.addAndGet(500);
        pacman.death();
        assertEquals(400, game.points.get());
    }
}
