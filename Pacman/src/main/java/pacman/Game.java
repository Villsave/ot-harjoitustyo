
package pacman;

import dao.ScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static pacman.Constants.foods;
import static pacman.Constants.ghosts;
import static pacman.Constants.walls;

public class Game {
    
    private Pane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private AnimationTimer gameTimer;
    private Player pacman;
    private ArrayList<String> input;
    private GraphicsContext gc;
    public AtomicInteger points;
    private Text text;
    private Text lives;
    private ScoreDao scoredao;
    private String username;
    
    
    
    public Game() throws SQLException {
        this.scoredao = new ScoreDao();
        scoredao.createDatabase();       
        
        initStage();
        createKeyListeners();
    }
    private void createKeyListeners() {
        this.input = new ArrayList<>();

        gameScene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            if (!input.contains(code)) {
                input.add(code);
            }
        });
        gameScene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
    }
    
    public void initStage() {
        gamePane = new Pane();
        gameScene = new Scene(gamePane, 1000, 600);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }
    
    public void newGame(Stage menuStage, String username){
        this.username = username;
        this.menuStage = menuStage;
        menuStage.hide();
        createStage();
        createGameLoop();
        gameStage.show();
    }
    
    private void createStage() {
        
        Canvas canvas = new Canvas(Constants.STAGEWIDTH, Constants.STAGEHEIGHT);
        gamePane.getChildren().add(canvas); 

        this.gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        
        this.text = new Text(10, 20, "Points: 0");
        text.setFill(Color.BLUE);
        gamePane.getChildren().add(text);
        
        this.lives = new Text(10, 40, "Lives: 3");
        lives.setFill(Color.BLUE);
        gamePane.getChildren().add(lives);
        
        this.points = new AtomicInteger();        

        String[] board = Level.getLevel();
        for (int i = 0; i < board.length; i++) {
            String line = board[i];
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '0') {
                    Wall wall = new Wall(j * 32 + 60, i * 32 + 65);
                    Constants.walls.add(wall);
                }
                if (line.charAt(j) == '1') {
                    Food food = new Food(12 + j * 32 + 60,   12 + i * 32 + 65);
                    Constants.foods.add(food);
                }
                if (line.charAt(j) == '3') {
                    Ghost ghost = new Ghost(j * 32 + 60, i * 32 + 65);
                    Constants.ghosts.add(ghost);
                }
            }
        }      
        this.pacman = new Player();
    }
    
    private void createGameLoop() {
        LongValue lastNanoTime = new LongValue(System.nanoTime());
        
        Constants.ghosts.forEach(ghost ->
            ghost.checkDirection());
        
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                turnPacman();
                
                for (Ghost ghost : Constants.ghosts) {
                    if (((int) ghost.getPositionX() - 60) % 32 == 0 && ((int) ghost.getPositionY() - 65) % 32 == 0) {
                        ghost.checkDirection();             
                    }
                }
                
                pacman.move(elapsedTime);
                Constants.ghosts.forEach(ghost ->
                    ghost.move(elapsedTime));
                
                Iterator<Objects> wallIter = Constants.walls.iterator();
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
                            gameStage.close();
                            menuStage.show();
                            int score = points.get();
                            try {
                                scoredao.addScore(username, score);
                            } catch (SQLException ex) {
                                Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                            }
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
                
                pacman.render(gc);
            }
        };
        gameTimer.start();
    }
    
    private void turnPacman() {
        if (input.contains("UP")) {
            pacman.setVelocity(0, -50);
            pacman.setImage("file:pacman_4.png", 27, 27);
        }
        if (input.contains("DOWN")) {
            pacman.setVelocity(0, 50);
            pacman.setImage("file:pacman_3.png", 27, 27);
        }
        if (input.contains("RIGHT"))  {
            pacman.setVelocity(50, 0);
            pacman.setImage("file:pacman_1.png", 27, 27);
        }
        if (input.contains("LEFT"))  {
            pacman.setVelocity(-50, 0);
            pacman.setImage("file:pacman_2.png", 27, 27);
        }        
    } 
}
