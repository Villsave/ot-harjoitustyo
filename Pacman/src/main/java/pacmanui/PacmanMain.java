
package pacmanui;

import dao.ScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pacman.*;


public class PacmanMain extends Application {
    
    public String username;
    public ArrayList<String> scores;
    
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
        startPane.setSpacing(10);
        startPane.setAlignment(Pos.CENTER);      
        Button startButton = new Button("start"); 
        Button scoreButton = new Button("scores");
        Button endButton = new Button("exit");
        startPane.getChildren().addAll(startGame, nameField, startButton, scoreButton, endButton);
        startingMenu.setCenter(startPane);
        Scene startScene = new Scene(startingMenu);
        stage.setScene(startScene);
        stage.show();
        
        stage.setTitle("pacman");
        
        ScoreDao scoredao = new ScoreDao();
        scoredao.createDatabase();  

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
                try {
                    Game game = new Game();
                    game.newGame(stage, username);
                } catch (SQLException ex) {
                    Logger.getLogger(PacmanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });
        
        endButton.setOnAction((event) -> {
            stage.close();
        });  

        scoreButton.setOnAction((event) -> {
            try {
                this.scores = scoredao.highScores();
            } catch (SQLException ex) {
                System.out.println("Error");;
            }
            for (int i = 0; i < 5; i++) {
                Text text = new Text(300, 50 * i + 1, scores.get(i));
                startPane.getChildren().add(text);
            }
        });
    }
}
    
