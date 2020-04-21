
package pacman;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Rectangle2D;

public class Ghost extends Objects {
    
    private int direction;
    private ArrayList <Integer> possibleDirections;
    
    public Ghost( int x, int y) {
        setPosition(x, y);
        setImage("file:ghost_1.png", 32, 32);
        
    }
    
    public void checkDirection(){
        possibleDirections = new ArrayList<>();
        checkRight();
        checkLeft();
        checkUp();
        checkDown();
        
        if (possibleDirections.isEmpty()) {
            moveGhost(-this.direction);
        }
        
        if (possibleDirections.size() == 1){
            moveGhost(this.direction);
        }
        
        else if (possibleDirections.size() > 1){
            Random rnd = new Random();
            direction = rnd.nextInt(possibleDirections.size());
            moveGhost(possibleDirections.get(direction));
        }
        
    }
    
    private void checkRight () {
        for (Objects wall : Constants.walls) {
            if (touchWall() == false){
                continue;
            }
            if (touchWall() && this.direction != -1){
                possibleDirections.add(1);
            }
        }
    }
    
    private void checkLeft () {
        for (Objects wall : Constants.walls) {
            if (touchWall() == false){
                continue;
            }
            if (touchWall() && this.direction != 1) {
                possibleDirections.add(-1);
            }
        }
    }
    
    private void checkDown () {
        for (Objects wall : Constants.walls) {
            if (touchWall() == false){
                continue;
            }
            if (touchWall() && this.direction != -2) {
                possibleDirections.add(2);
            }
        }
    }
    
    private void checkUp () {
        for (Objects wall : Constants.walls) {
            if (touchWall() == false){
                continue;
            }
            if (touchWall() && this.direction != 2){
                possibleDirections.add(-2);
            }
        } 
    }
    
    private void moveGhost(int direction) {
        switch (direction) {
            case 1:
                this.setVelocity(50, 0);
                break;
            case -1:
                this.setVelocity(-50, 0);
                break;
            case 2:
                this.setVelocity(0, 50);
                break;
            case -2:
                this.setVelocity(0, -50);
                break;
            default:
                break;
        }
    }
    
    private boolean touchWall(){
        double nextX = this.getPositionX();
        double nextY = this.getPositionY();
        switch (direction) {
        case 1:
          nextX += 32 * 0.5;
          break;
        case -1:
          nextX -= 32 * 0.5;
          break;
        case 2:
          nextY -= 32 * 0.5;
          break;
        case -2:
          nextY += 32 * 0.5;
          break;
        default:
      }
      Rectangle2D test = new Rectangle2D (nextX, nextY, 27, 27); 
        for (Objects wall : Constants.walls) {
            if (test.intersects(wall.getBoundary())) {
                return false;
            }
        }
        return true;
    }
}
