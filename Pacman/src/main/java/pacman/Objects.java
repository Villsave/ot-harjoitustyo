
package pacman;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public abstract class Objects {
    
    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    private double velocityX;
    private double velocityY;
    public Image[] frames;
    public double duration;
    
    public Objects() {
        
        this.positionX = 0;
        this.positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(String filename, int x, int y) {
        this.image = new Image(filename, x, y, false, false);
    }
    
    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }
    
    public void setPositionX(double x) {
        positionX = x;
    }
    
    public void setPositionY(double y) {
        positionY = y;
    }
    
    public double getPositionX(){
        return positionX;
    }
    
    public double getPositionY(){
        return positionY;
    }
    
    public void move(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;  
        
    }

     public void render(GraphicsContext gc) {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, 27, 27);
    }

    public boolean intersects(Objects s) {
        return this.getBoundary().intersects( s.getBoundary() );
    }
    
    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
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
