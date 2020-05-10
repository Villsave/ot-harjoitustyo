
package domain;

/**
 * Class to create pacman
 */
public class Player extends Sprite {
    
    private int lives;
   
    public Player() {
        this.lives = 3;      
    }
    
    public int checkLives() {
        return this.lives;
    }
    
    /**
     * Method for losing a life and resetting the position on the board
     */
    public void death() {
        this.lives--;
        setPosition(100, 100);
        setVelocity(0, 0);
    }   
}
    
    
 
    
 
