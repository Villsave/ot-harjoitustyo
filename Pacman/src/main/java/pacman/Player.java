
package pacman;

import javafx.scene.image.Image;

public class Player extends Objects {
    
    public int lives;
    
    public Player() {
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("file:pacman_1.png", 27, 27, false, false);
        imageArray[1] = new Image("file:pacman_2.png", 27, 27, false, false);
        frames = imageArray;
        duration = 0.100;
        setPosition(100, 100);
        lives = 3;
        
    }
    public Image getFrame(double time){
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
}
    
    
 
    
 
