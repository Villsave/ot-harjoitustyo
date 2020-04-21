
package pacman;

public class Wall extends Objects {
    
    public Wall(double x, double y) {
        setPosition(x, y);
        setImage("file:brick.png", Constants.wallSize, Constants.wallSize);
    }
}
