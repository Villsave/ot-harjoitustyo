
package dao;

import java.util.*;
import java.sql.*;

public class ScoreDao {
    private Connection connection;
    private Statement stmt;
    
    public ScoreDao() throws SQLException {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:scores:db");
            this.stmt = connection.createStatement();
        } catch (SQLException ex) {
            
        }
    }
        
    public void createDatabase() {
            
        try {
            stmt.execute("CREATE TABLE Leaderboard (id integer AUTO_INCREMENT PRIMARY KEY," 
                    + "Name varchar(10), Score varchar(10));");
        } catch (SQLException ex) {
            
        }
    }
        
    public void addScore(String name, int points) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO Leaderboard (Name, Score) VALUES (?, ?)");
        statement.setString(1, name);
        statement.setInt(2, points);
        statement.executeUpdate();
    }
        
    public ArrayList highScores() throws SQLException {
        ArrayList<String> scores = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM Leaderboard ORDER BY Score DESC");
        ResultSet results = statement.executeQuery();
        int i = 1;
        while (results.next()) {
            scores.add(i + ". " + results.getInt("Score") + " - " + results.getString("Name") + "\n");
            i++;
        }
        return scores;
    }
}

