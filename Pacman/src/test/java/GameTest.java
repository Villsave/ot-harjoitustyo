
import java.sql.SQLException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.Block;
import pacman.Constants;
import pacmanui.Game;
import pacman.Level;
import pacman.Player;

public class GameTest {
    Game game;
    Player pacman;
    Stage stage;
    Scene scene;
    
    public GameTest() {
    }
    
    @Before
    public void setUp() throws SQLException {
        
    }
    
    @Test
    public void eatingFoodGivesPoints() {
        String[] board = Level.getLevel();
        int r = 0;
        for (int i = 0; i < board.length; i++) {
            String line = board[i];
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '0') {
                    Block wall = new Block(Block.BlockType.WALL);
                    r++;
                    Constants.walls.add(wall);
                }
            }
        }
        assertEquals(r, Constants.walls.size());
}

    
}
