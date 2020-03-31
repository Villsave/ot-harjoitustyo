
package pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Wall extends Objects {
    
    public Wall(int x, int y) {
        super(new Rectangle(32, 32), x, y, Color.BLUE);
    }
}
