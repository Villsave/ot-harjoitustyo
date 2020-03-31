

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.Level;
import pacman.PacmanMain;
import pacman.Player;

import pacman.Wall;


public class PacmanTest {
    Player player;
    
    public PacmanTest() {
    }
    
    
    @Before
    public void setUp() {
        player = new Player(100, 100);
    }
    
    @Test
    public void stageBuildingMakesTheRightAmountOfWalls() {
        String[] board = Level.getLevel();
        int x = 0;
        ArrayList<Wall> walls = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            String line = board[i];
            for(int j = 0; j < line.length(); j++) {
                if(line.charAt(j)=='0'){
                    x++;
                    Wall wall = new Wall(j * 32 + 60, i * 32 + 100);
                    walls.add(wall);
                }
            }
        }
        assertEquals(x, walls.size());           
    }
    
    @Test
    public void playerDoesNotMoveUntilButtonIsPressed() {
        assertEquals(100, player.getTranslateX());
        assertEquals(100, player.getTranslateY());
    }
}
