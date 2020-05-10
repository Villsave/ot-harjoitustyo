
package pacmanui;

import dao.ScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class PacmanMain extends Application {
    
    private String username;
    private int ghostAmount;
    private ArrayList<String> scores;
    private Text text;
    private Game game;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {    
        
        //Starting menu
        BorderPane startingMenu = new BorderPane();
        startingMenu.setPrefSize(1000, 600);
        Label startGame = new Label("Give player name:");
        TextField nameField = new TextField();
        nameField.setMaxSize(150, 30);
        VBox startPane = new VBox();
        startPane.setSpacing(10);
        startPane.setAlignment(Pos.CENTER);
        Button startButton = new Button("start");
        Button scoreButton = new Button("scores");
        Button endButton = new Button("exit");
        this.text = new Text("");
        startPane.getChildren().addAll(startGame, nameField, startButton, scoreButton, endButton, text);
        startingMenu.setCenter(startPane);
        Scene startScene = new Scene(startingMenu);
        stage.setScene(startScene);
        stage.show();
        
        //View for games ending
        BorderPane gameOverMenu = new BorderPane();
        gameOverMenu.setPrefSize(150, 130);
        VBox gameOver = new VBox();
        gameOver.setSpacing(10);
        gameOver.setAlignment(Pos.CENTER);
        Label endGame = new Label("Game over");
        Button okButton = new Button("Exit");
        gameOver.getChildren().addAll(endGame, okButton);
        gameOverMenu.setCenter(gameOver);
        Scene endScene = new Scene(gameOverMenu);
        
        stage.setTitle("pacman");
                
        ScoreDao scoredao = new ScoreDao();
        scoredao.createDatabase();
        startButton.setOnAction((event) -> {
            
            username = nameField.getText().replaceAll("\\s+", "");
            if (username.length() > 7) {
                startGame.setText("Username too long.");
            }
            if (username.equals("")) {
                startGame.setText("Username must contain at least one character.");
            } else if (!username.equals("") && username.length() <= 7) {
                Game game = new Game();
                game.newGame(stage, username, endScene);
            }
        });
        endButton.setOnAction((event) -> {
            stage.close();
        });
        scoreButton.setOnAction((event) -> {
            String s = "";
            this.scores = scoredao.highScores();
            if (scores.isEmpty()) {
                this.text.setText("No scores yet!");
            } else if (scores.size() > 5) {
                for (int i = 0; i < 5; i++) {
                    s += scores.get(i) + "\n";
                }
                this.text.setText(s);
            } else {
                for (int i = 0; i < scores.size(); i++) {
                    s += scores.get(i) + "\n";
                }
                this.text.setText(s);
            }
        });
        okButton.setOnAction((event) -> {
            stage.close();
        });
    }
}
    
