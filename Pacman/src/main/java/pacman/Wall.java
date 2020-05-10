
package pacman;
/**
 * class to create walls
 */
public class Wall extends Sprite {
    
    public Wall(double x, double y) {
        super("file:brick.png", 32, 32, x, y);
    }
}
