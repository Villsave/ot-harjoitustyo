
package pacman;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class PacmanMain extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle( "pacman" );

        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(Constants.stageWidth, Constants.stageHeight);
        root.getChildren().add(canvas); 

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        
        Text text = new Text(10, 20, "Points: 0");
        text.setFill(Color.BLUE);
        root.getChildren().add(text);
        
        AtomicInteger points = new AtomicInteger();
        
        ArrayList<Objects> walls = new ArrayList<>();
        ArrayList<Objects> foods = new ArrayList<>();
        ArrayList<Ghost> ghosts = new ArrayList<>();
        ArrayList<Objects> lives = new ArrayList<>();
        
        String[] board = Level.getLevel();
        for (int i = 0; i < board.length; i++) {
            String line = board[i];
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '0') {
                    Wall wall = new Wall(j * 32 + 60, i * 32 + 65);
                    walls.add(wall);
                    Constants.walls.add(wall);
                }
                if (line.charAt(j) == '1') {
                    Food food = new Food(12 +j * 32 + 60,   12 + i * 32 + 65);
                    foods.add(food);
                }
                if (line.charAt(j) == '3') {
                    Ghost ghost = new Ghost(j * 32 + 60, i * 32 + 65);
                    ghosts.add(ghost);
                }
            }
        }
        
        Player pacman = new Player();
        
        ArrayList<String> input = new ArrayList<>();

        scene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });
        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });
        
          LongValue lastNanoTime = new LongValue( System.nanoTime() );
          
          ghosts.forEach(ghost ->
                    ghost.checkDirection());

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                
 
                if (input.contains("UP")) {
                    pacman.setVelocity(0,-50);
                }
                if (input.contains("DOWN")) {
                    pacman.setVelocity(0,50);
                }
                if (input.contains("RIGHT"))  {
                    pacman.setVelocity(50,0);
                }
                if (input.contains("LEFT"))  {
                    pacman.setVelocity(-50,0);
                }
                

                pacman.move(elapsedTime);
                ghosts.forEach(ghost ->
                    ghost.move(elapsedTime));
                
                Iterator<Objects> wallIter = walls.iterator();
                while ( wallIter.hasNext() ){
                    
                    Objects wall = wallIter.next();
                    if ( pacman.intersects(wall) &&  pacman.getPositionY() < wall.getPositionY() ) {
                        pacman.setPosition(pacman.getPositionX(), pacman.getPositionY() - 1 );
                    }
                    for (Ghost ghost : ghosts){
                        if ( ghost.intersects(wall) &&  ghost.getPositionY() < wall.getPositionY() ) {
                        ghost.setPosition(ghost.getPositionX(), ghost.getPositionY() - 1 );
                        ghost.checkDirection();
                    }
                        
                    }
                    if ( pacman.intersects(wall) &&  pacman.getPositionY() > wall.getPositionY() ) {
                        pacman.setPosition(pacman.getPositionX(), pacman.getPositionY() + 1);
                    }
                    for (Ghost ghost : ghosts){
                        if ( ghost.intersects(wall) &&  ghost.getPositionY() > wall.getPositionY() ) {
                        ghost.setPosition(ghost.getPositionX(), ghost.getPositionY() + 1 );
                        ghost.checkDirection();
                    }
                    }
                    
                    if ( pacman.intersects(wall) &&  pacman.getPositionX() < wall.getPositionX() ) {
                        pacman.setPosition(pacman.getPositionX() - 1, pacman.getPositionY());
                    }
                    for (Ghost ghost : ghosts){
                        if ( ghost.intersects(wall) &&  ghost.getPositionX() < wall.getPositionX() ) {
                        ghost.setPosition(ghost.getPositionX() -1, ghost.getPositionY());
                        ghost.checkDirection();
                    }
                    }
                    if ( pacman.intersects(wall) &&  pacman.getPositionX() > wall.getPositionX()) {
                        pacman.setPosition(pacman.getPositionX() + 1, pacman.getPositionY());                       
                    }
                    for (Ghost ghost : ghosts){
                        if ( ghost.intersects(wall) &&  ghost.getPositionX() > wall.getPositionX() ) {
                        ghost.setPosition(ghost.getPositionX() +1, ghost.getPositionY());
                        ghost.checkDirection();
                    }
                    }                    
                }
                
                Iterator<Objects> foodIter = foods.iterator();
                while(foodIter.hasNext()){
                    Objects food = foodIter.next();
                    if(pacman.intersects(food)){
                        foodIter.remove();
                        text.setText("Points: " + points.addAndGet(10));
                    }
                }
                
                
                
                gc.clearRect(0, 0, Constants.stageWidth, Constants.stageHeight);
                
                gc.fillRect(0, 0, Constants.stageWidth, Constants.stageHeight);
                
                walls.forEach((wall) -> {                  
                    wall.render( gc );
                });
                
                foods.forEach((food) -> {
                    food.render(gc);
                });
                
                ghosts.forEach((ghost) -> {
                    ghost.render(gc);
                });
                
                lives.forEach((life) -> {
                    life.render(gc);
                });
                
                gc.drawImage(pacman.getFrame(elapsedTime), pacman.getPositionX(), pacman.getPositionY());
            }
        }
        .start();
        
        stage.show();
    }
}
    
