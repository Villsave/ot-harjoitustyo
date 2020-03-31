
package pacman;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;

public class PacmanMain extends Application {
    
    boolean goUp, goDown, goRight, goLeft;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Pane level = new Pane();
        level.setPrefSize(1000, 600);
        Rectangle r = new Rectangle(1000, 600);
        r.setFill(Color.BLACK);
        level.getChildren().add(r);
        
        Player player = new Player(100, 100);
        level.getChildren().add(player.getObject());
        
        String[] board = Level.getLevel();
        ArrayList<Wall> walls = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String line = board[i];
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '0') {
                    Wall wall = new Wall(j * 32 + 60, i * 32 + 100);
                    walls.add(wall);
                    level.getChildren().add(wall.getObject());
                }
            }
        }
                      
        
        Scene scene = new Scene(level);
        stage.setTitle("pacman");
        stage.setScene(scene);
        stage.show();
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:    goUp = true;
                break;
                case DOWN:  goDown = true; 
                break;
                case LEFT:  goLeft  = true;
                break;
                case RIGHT: goRight  = true;
                break;
            }
        });
 
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:    goUp = false;
                break;
                case DOWN:  goDown = false; 
                break;
                case LEFT:  goLeft  = false; 
                break;
                case RIGHT: goRight  = false; 
                break;
            }
        });
 
        stage.setScene(scene);
        stage.show();
 
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dx = 0, dy = 0;
 
                if (goUp) {
                    dy -= 0.5;
                }
                if (goDown) {
                    dy += 0.5;
                }
                if (goRight)  {
                    dx += 0.5;
                }
                if (goLeft)  {
                    dx -= 0.5;
                }
 
                player.movePlayer(dx, dy);
                player.move();    
                
                walls.forEach(wall -> {
                    if (player.collision(wall) && wall.getTranslateX() >= player.getTranslateX() + 21) {
                        player.setTranslateX(player.getTranslateX() - 1);
                    }
                    
                    if (player.collision(wall) && wall.getTranslateX() <= player.getTranslateX())  {
                        player.setTranslateX(player.getTranslateX() + 1);
                    }
                    
                    if (player.collision(wall) && wall.getTranslateY() <= player.getTranslateY() + 21) {
                        player.setTranslateY(player.getTranslateY() + 1);
                    }
                    
                    if (player.collision(wall) && wall.getTranslateY() >= player.getTranslateY() + 21) {
                        player.setTranslateY(player.getTranslateY() - 1);
                    }
                    
                    
                }); 
            }
        };
        timer.start();
    }
    public static void main(String[] args) {
        launch(args);
    }


    
}
    
