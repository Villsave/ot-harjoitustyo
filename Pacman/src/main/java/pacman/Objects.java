
package pacman;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

public abstract class Objects {
    
    private Shape object;
    private Color color;
    private Point2D movement;
    
    public Objects(Shape shape, double x, double y, Color color) {
        
        this.object = shape;
        this.object.setTranslateX(x);
        this.object.setTranslateY(y);
        this.object.setFill(color);
        this.movement = new Point2D(0, 0);
    }
    
    public Shape getObject() {
        
        return this.object;
    }
    
    public int getTranslateX() {
        return (int) this.object.getTranslateX();
    }
    
    public void setTranslateX(int x) {
        this.object.setTranslateX(x);
    }
    
    public int getTranslateY() {
        return (int) this.object.getTranslateY();
    }
    
    public void setTranslateY(int y) {
        this.object.setTranslateY(y);
    }
    
    public boolean collision(Objects other) {
        Shape collision = Shape.intersect(this.object, other.getObject());
        return collision.getBoundsInLocal().getWidth() != -1;
    }
    
    public void move() {
        this.object.setTranslateX(this.object.getTranslateX() + this.movement.getX());
        this.object.setTranslateY(this.object.getTranslateY() + this.movement.getY());
        
        if (this.object.getTranslateX() < 0) {
            this.object.setTranslateX(this.object.getTranslateX() + 1000);
        }
        if (this.object.getTranslateX() > 1000) {
            this.object.setTranslateX(this.object.getTranslateX() % 1000);
        }
        if (this.object.getTranslateY() < 0) {
            this.object.setTranslateY(this.object.getTranslateY() + 600);
        }
        if (this.object.getTranslateY() > 600) {
            this.object.setTranslateY(this.object.getTranslateY() % 600);
        }
        
    }
    
    public void setMovement(Point2D newMovement) {
        this.movement = newMovement;
    }
    
    public Point2D getMovement() {
        return this.movement;
    }
}
