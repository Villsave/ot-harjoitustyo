
package pacman;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Player extends Objects {
    
    private Circle character;
    
    public Player(int x, int y) {
        super(new Circle(30, 50, 13), x, y, Color.YELLOW);
    }
    
    public void movePlayer(double dx, double dy) {
        if (dx == 0 && dy == 0) {
            return;
        }
        
        Point2D newDirection = new Point2D(dx, dy);
        setMovement(newDirection);
    }
    public int u(){
        return 2;
    }
}
    
    
 
    
 
