
package pacmanui;

import dao.ScoreDao;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pacman.*;


public class PacmanMain extends Application {
    
    public String username;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws SQLException {    
        
        BorderPane startingMenu = new BorderPane();
        startingMenu.setPrefSize(1000, 600);
        Label startGame = new Label("Give player name:");
             
        TextField nameField = new TextField(); 
        nameField.setMaxSize(150, 30);
        VBox startPane = new VBox();  
        startPane.setSpacing(50);
        startPane.setAlignment(Pos.CENTER);      
        Button startButton = new Button("start");         
        startPane.getChildren().addAll(startGame, nameField, startButton);
        startingMenu.setCenter(startPane);
        Scene startScene = new Scene(startingMenu);
        stage.setScene(startScene);
        stage.show();
        
        ScoreDao scoredao = new ScoreDao();
        //scoredao.createDatabase();       
        
        stage.setTitle("pacman");

        Pane root = new Pane();
        Scene scene = new Scene(root);
        
        startButton.setOnAction((event) -> {
            username = nameField.getText();         
            if (username.length() > 7) {
                startGame.setText("Username too long.");
                
            }
            if (username.equals("")) {
                startGame.setText("Username must contain at least one character.");
                
            } else if (!username.equals("") && username.length() <= 7) {
                stage.setScene(scene);
            }
        });

        Canvas canvas = new Canvas(Constants.STAGEWIDTH, Constants.STAGEHEIGHT);
        root.getChildren().add(canvas); 

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        
        Text text = new Text(10, 20, "Points: 0");
        text.setFill(Color.BLUE);
        root.getChildren().add(text);
        
        Text lives = new Text(10, 40, "Lives: 3");
        lives.setFill(Color.BLUE);
        root.getChildren().add(lives);
        
        AtomicInteger points = new AtomicInteger();
        
        ArrayList<Objects> walls = new ArrayList<>();
        ArrayList<Objects> foods = new ArrayList<>();
        ArrayList<Ghost> ghosts = new ArrayList<>();
        
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
                    Food food = new Food(12 + j * 32 + 60,   12 + i * 32 + 65);
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

        scene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            if (!input.contains(code)) {
                input.add(code);
            }
        });
        scene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
        
        LongValue lastNanoTime = new LongValue(System.nanoTime());
          
        ghosts.forEach(ghost ->
            ghost.checkDirection());

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                
 
                if (input.contains("UP")) {
                    pacman.setVelocity(0, -50);
                }
                if (input.contains("DOWN")) {
                    pacman.setVelocity(0, 50);
                }
                if (input.contains("RIGHT"))  {
                    pacman.setVelocity(50, 0);
                }
                if (input.contains("LEFT"))  {
                    pacman.setVelocity(-50, 0);
                }
                
                for (Ghost ghost : ghosts) {
                    if (((int) ghost.getPositionX() - 60) % 32 == 0 && ((int) ghost.getPositionY() - 65) % 32 == 0) {
                        ghost.checkDirection();             
                    }
                }
                

                pacman.move(elapsedTime);
                ghosts.forEach(ghost ->
                    ghost.move(elapsedTime));
                
                Iterator<Objects> wallIter = walls.iterator();
                while (wallIter.hasNext()) {
                    
                    Objects wall = wallIter.next();
                    if (pacman.intersects(wall) &&  pacman.getPositionY() < wall.getPositionY()) {
                        pacman.setPosition(pacman.getPositionX(), pacman.getPositionY() - 1);
                    }
                   
                    if (pacman.intersects(wall) &&  pacman.getPositionY() > wall.getPositionY()) {
                        pacman.setPosition(pacman.getPositionX(), pacman.getPositionY() + 1);
                    }                  
                    
                    if (pacman.intersects(wall) &&  pacman.getPositionX() < wall.getPositionX()) {
                        pacman.setPosition(pacman.getPositionX() - 1, pacman.getPositionY());
                    }

                    if (pacman.intersects(wall) &&  pacman.getPositionX() > wall.getPositionX()) {
                        pacman.setPosition(pacman.getPositionX() + 1, pacman.getPositionY());                       
                    }
                                       
                }
                
                Iterator<Objects> foodIter = foods.iterator();
                while (foodIter.hasNext()) {
                    Objects food = foodIter.next();
                    if (pacman.intersects(food)) {
                        foodIter.remove();
                        text.setText("Points: " + points.addAndGet(10));
                    }
                }
                if (foods.isEmpty()) {
                    stop();
                }
                
                
                Iterator<Ghost> ghostIter = ghosts.iterator();
                while (ghostIter.hasNext()) {
                    Ghost ghost = ghostIter.next();
                    if (pacman.intersects(ghost)) {
                        pacman.death();
                        if (pacman.checkLives() == 0) {
                            stop();
                            int score = points.get();
                            /*try {
                                scoredao.addScore(username, score);
                            } catch (SQLException ex) {
                                System.out.println("Error");
                            }
                            */
                           
                        }
                        text.setText("Points: " + points.addAndGet(-100));
                        lives.setText("Lives: " + pacman.checkLives());
                    }
                }
                               
                gc.clearRect(0, 0, Constants.STAGEWIDTH, Constants.STAGEHEIGHT);
                
                gc.fillRect(0, 0, Constants.STAGEWIDTH, Constants.STAGEHEIGHT);
                
                walls.forEach((wall) -> {                  
                    wall.render(gc);
                });
                
                foods.forEach((food) -> {
                    food.render(gc);
                });
                
                ghosts.forEach((ghost) -> {
                    ghost.render(gc);
                });
                
                gc.drawImage(pacman.getFrame(elapsedTime), pacman.getPositionX(), pacman.getPositionY());
            }
        }
                
            .start();
        
        stage.show();
    }
}
    
