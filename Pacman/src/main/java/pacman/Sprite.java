
package pacman;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

/**
 * Class for creating sprites 
 */

public abstract class Sprite {
    
    private Image image;
    private double positionX;
    private double positionY;
    private int width;
    private int height;
    private double velocityX;
    private double velocityY;

    /**
     * Creating a new Sprite
     */
    public Sprite() {  
       
    }
    /**
     * Setting the image to represent a block in the game
     * @param filename imagefile
     * @param width width of the image
     * @param height height of the image
     */
    public void setImage(String filename, int width, int height) {
        this.image = new Image(filename, width, height, false, false);
    }
    /**
     * Setting the sprite to correct spot in the screen
     * @param x location on x axis
     */    
    public void setPositionX(double x) {
        positionX = x;
    }
    
    /**
     * @param y location on y axis
     */
    
    public void setPositionY(double y) {
        positionY = y;
    }
    
    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }
    
    public double getPositionX() {
        return positionX;
    }
    
    public double getPositionY() {
        return positionY;
    }
    
    /**
     * moving the sprite based on the passed time and velocity of the sprite
     * @param time the time that has passed since last movement
     */
    public void move(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;  
        
    }
    
    /**
     * Drawing the image on the board
     * @param gc 
     */

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    /**
     * Method that returns the hitbox of the sprite
     * @return returns the hitbox
     */
    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, 27, 27);
    }

    /**
     * Method for detecting collisions
     * @param s Another sprite this sprite collides with
     * @return true if collision happens, otherwise returns false
     */
    public boolean intersects(Sprite s) {
        return this.getBoundary().intersects(s.getBoundary());
    }
    
    /**
     * Method to set the velocity of sprite
     * @param vx velocity on x axis
     * @param vy velocity on y axis
     */
    public void setVelocity(double vx, double vy) {
        velocityX = vx;
        velocityY = vy;
    }

    public double getVelocityY() {
        return this.velocityY;
    }
    
    public void setVelocityY(double y) {
        this.velocityY = y;
    }
    
    public int getVelocityX() {
        return (int) this.velocityX;
    }
    
    public void setVelocityX(double x) {
        this.velocityX = x;
    }
    
}
