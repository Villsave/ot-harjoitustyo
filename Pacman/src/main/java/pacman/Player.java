
package pacman;

public class Player extends Objects {
    
    public int lives;
    
    
    public Player() {
        super("file:pacman_1.png", 27, 27, 100, 100);
        lives = 3;      
    }
    
    public int checkLives() {
        return this.lives;
    }
    
    public void death() {
        this.lives--;
        setPosition(100, 100);
        setVelocity(0, 0);
    }   
}
    
    
 
    
 
