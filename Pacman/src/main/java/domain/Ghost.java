
package domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for creating and moving enemies
 */

public class Ghost extends Sprite {
    
    private String[] board;
    
    /**
     * enums for directions the ghost can move in
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    private int index;
    private Direction direction;
    private Direction lastDirection;
    private ArrayList<Direction> possibleDirections;
    private double speed;
    
    /**
     * Creating a new ghost
     */    
    public Ghost() {    
        board = Level.getLevel();
        speed = 50;
    }
    
    /**
     * Checking possible directions for the ghost to move to
     */
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
    /**
     * Method to raise ghosts speed
     */
    public void raiseSpeed(){
        this.speed += 0.5;
    }
    
    /**
     * Moving ghost in a given direction
     * @param direction  a direction where the ghost will be moving
     */
    
    public void moveGhost(Direction direction) {
        switch (direction) {
            case RIGHT:
                this.setVelocity(speed, 0);
                break;
            case LEFT:
                this.setVelocity(-speed, 0);
                break;
            case DOWN:
                this.setVelocity(0, speed);
                break;
            case UP:
                this.setVelocity(0, -speed);
                break;
            default:
                break;
        }
    }
    
    public ArrayList GetDirections() {
        return this.possibleDirections;
    }
}
