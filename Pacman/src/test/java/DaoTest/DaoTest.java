
package DaoTest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DaoTest {
    
    Connection connection;
    Statement stmt;
    
    public DaoTest() {
    }
    
    
    @Before
    public void setUp() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:test:db");
            this.stmt = connection.createStatement();
        } catch (SQLException ex) {
            
        }
         try {
            stmt.execute("CREATE TABLE Test (id integer AUTO_INCREMENT PRIMARY KEY," 
                    + "Name varchar(10), Score varchar(10));");
        } catch (SQLException ex) {
            
        }
    }
    
    @After
    public void tearDown() {
        File f = new File("test:db");
        f.delete();
    }

    
    
}
