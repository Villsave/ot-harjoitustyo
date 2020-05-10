
package dao;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to create and manage the database for high scores
 */

public class ScoreDao {
    private Connection connection;
    private Statement stmt;
    private  ArrayList<String> scores;
    
    public ScoreDao() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:scores:db");
            this.stmt = connection.createStatement();
        } catch (SQLException ex) {
            
        }
    }
    /**
     * creating a new database if one doesn't exist
     */
    public void createDatabase() {
            
        try {
            stmt.execute("CREATE TABLE Leaderboard (id integer AUTO_INCREMENT PRIMARY KEY," 
                    + "Name varchar(10), Score varchar(10));");
        } catch (SQLException ex) {
            
        }
    }
    /**
     * adding a ne score to the database
     * @param name name of the player
     * @param points players score
     */  
    public void addScore(String name, int points) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Leaderboard (Name, Score) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setInt(2, points);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error");;
        }
    }
        
    /**
     * Method to read the high scores from the database
     * @return high scores as an arraylist
     */
    public ArrayList highScores() {
        try {
            this.scores = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Leaderboard ORDER BY Score DESC");
            ResultSet results = statement.executeQuery();
            int i = 1;
            while (results.next()) {
                scores.add(i + ". " + results.getInt("Score") + " - " + results.getString("Name") + "\n");
                i++;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error");;
        }
        return scores;
    }
}

