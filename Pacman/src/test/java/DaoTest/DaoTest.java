
package DaoTest;

import dao.ScoreDao;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DaoTest {
    
    File testFile;
    ScoreDao scoredao;
    Connection connection;
    Statement stmt;
    ArrayList<String> scores;
    
    public DaoTest() {
    }
    
    
    @Before
    public void setUp() {
        try {
            testFile = new File("test:db");
            scoredao = new ScoreDao();
            this.connection = DriverManager.getConnection("jdbc:sqlite:test:db");
            this.stmt = connection.createStatement();
            
             try {
                stmt.execute("CREATE TABLE Test (id integer AUTO_INCREMENT PRIMARY KEY," 
                        + "Name varchar(10), Score varchar(10));");
            } catch (SQLException ex) {

            }
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Test (Name, Score) VALUES (?, ?)");
            statement.setString(1, "test guy");
            statement.setInt(2, 500);
            statement.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @After
    public void tearDown() {
        File f = new File("test:db");
        f.delete();
    }
    
    @Test
    public void returnScoreAndName() {
        try {
            this.scores = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Test ORDER BY Score DESC");
            ResultSet results = statement.executeQuery();
            int i = 1;
            while (results.next()) {
                scores.add(i + ". " + results.getInt("Score") + " - " + results.getString("Name") + "\n");
                i++;
            }
            assertEquals("1. 500 - test guy\n", scores.get(0));
        } catch (SQLException ex) {
            Logger.getLogger(DaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void addingWorks() {
        scoredao.createDatabase();
        scoredao.addScore("tester", 90);
        this.scores = scoredao.highScores();
        assertEquals("1. 90 - tester\n", scores.get(0));
    }
        
    }

    
    

