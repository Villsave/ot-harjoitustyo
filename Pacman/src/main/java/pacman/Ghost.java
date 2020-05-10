
package pacman;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Objects {
    
    private String[] board;
    
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    private int index;
    private Direction direction;
    private Direction lastDirection;
    private ArrayList<Direction> possibleDirections;
    
    
    public Ghost(int x, int y) {
        super("file:ghost_1.png", 32, 32, x, y);        
        board = Level.getLevel();
    }
    
    public void checkDirection() {
        
        possibleDirections = new ArrayList<>();
        checkRight(lastDirection);
        checkLeft(lastDirection);
        checkUp(lastDirection);
        checkDown(lastDirection);
        
        if (possibleDirections.size() == 1) {
            index = 0;
            this.direction = possibleDirections.get(index);
            moveGhost(direction);
            lastDirection = direction;
        } else if (possibleDirections.size() > 1) {
            Random rnd = new Random();
            index = rnd.nextInt(possibleDirections.size());
            this.direction = possibleDirections.get(index);
            moveGhost(direction);
            lastDirection = direction;
        }
        
    }
    
    private void checkRight(Direction lastDirection) {
        if (this.getPositionX() > 900) {
            return;
        }      
        String row = board[((int) this.getPositionY() - 65) / 32];
        char column = row.charAt(((int) this.getPositionX() - 60) / 32 + 1);
        if (column != '0' && lastDirection != Direction.LEFT) {
            possibleDirections.add(Direction.RIGHT);
        }
    }
    
    private void checkLeft(Direction lastDirection) {
        if (this.getPositionX() < 100) {
            return;
        }
        
        String row = board[((int) this.getPositionY() - 65) / 32];
        char column = row.charAt(((int) this.getPositionX() - 60) / 32 - 1);
        if (column != '0' && lastDirection != Direction.RIGHT) {
            possibleDirections.add(Direction.LEFT);
        }
    }
    
    private void checkDown(Direction lastDirection) {
        if (this.getPositionY() > 500) {
            return;
        }
        
        String row = board[((int) this.getPositionY() - 65) / 32 + 1];
        char column = row.charAt(((int) this.getPositionX() - 60) / 32);
        if (column != '0' && lastDirection != Direction.UP) {
            possibleDirections.add(Direction.DOWN);
        }
    }
    
    private void checkUp(Direction lastDirection) {
        if (this.getPositionY() < 100) {
            return;
        }
        String row = board[((int) this.getPositionY() - 65) / 32 - 1];
        char column = row.charAt(((int) this.getPositionX() - 60) / 32);
        if (column != '0' && lastDirection != Direction.DOWN) {
            possibleDirections.add(Direction.UP);
        }
    }
    
    public void moveGhost(Direction direction) {
        switch (direction) {
            case RIGHT:
                this.setVelocity(50, 0);
                break;
            case LEFT:
                this.setVelocity(-50, 0);
                break;
            case DOWN:
                this.setVelocity(0, 50);
                break;
            case UP:
                this.setVelocity(0, -50);
                break;
            default:
                break;
        }
    }
    public ArrayList GetDirections() {
        return this.possibleDirections;
    }
}
