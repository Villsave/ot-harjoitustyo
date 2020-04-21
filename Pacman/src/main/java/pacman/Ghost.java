
package pacman;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Objects {
    
    private String[] board;
    private int direction;
    private ArrayList <Integer> possibleDirections;
    
    public Ghost( int x, int y) {
        setPosition(x, y);
        setImage("file:ghost_1.png", 32, 32);        
        board = Level.getLevel();
    }
    
    public void checkDirection(){
        possibleDirections = new ArrayList<>();
        checkRight();
        checkLeft();
        checkUp();
        checkDown();
        
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
        if(this.direction == 2){
            return;
        }
        String row = board[((int) this.getPositionY() - 65) / 32];
        char column = row.charAt(((int)this.getPositionX() - 60) / 32 + 1);
        if (column != '0' && this.getVelocityX() != -50){
            possibleDirections.add(1);
        }
    }
    
    private void checkLeft () {
        String row = board[((int) this.getPositionY() - 65) / 32];
        char column = row.charAt(((int)this.getPositionX() - 60) / 32 - 1);
        if (column != '0' && this.getVelocityX() != 50){
            possibleDirections.add(2);
        }
    }
    
    private void checkDown () {
        if(this.direction == 4) {
            return;
        }
        String row = board[((int) this.getPositionY() - 65) / 32 + 1];
        char column = row.charAt(((int)this.getPositionX() - 60) / 32);
        if (column != '0' && this.getVelocityY() != -50){
            possibleDirections.add(3);
        }
    }
    
    private void checkUp () {
        if(this.direction == 3) {
            return;
        }
        String row = board[((int) this.getPositionY() - 65) / 32 - 1];
        char column = row.charAt(((int)this.getPositionX() - 60) / 32);
        if (column != '0' && this.getVelocityY() != 50){
            possibleDirections.add(4);
        }
    }
    
    private void moveGhost(int direction) {
        switch (direction) {
            case 1:
                this.setVelocity(50, 0);
                break;
            case 2:
                this.setVelocity(-50, 0);
                break;
            case 3:
                this.setVelocity(0, 50);
                break;
            case 4:
                this.setVelocity(0, -50);
                break;
            default:
                break;
        }
    }
}
