
package pacman;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Constants {
    
    public static final int STAGEWIDTH = 1000;
    public static final int STAGEHEIGHT = 600;
    public static final int WALLSIZE = 32;
    public static final int WIDTHINBLOCKS = 27;
    public static final int HEIGHTINBLOCKS = 14;
    public static final int FOODSIZE = 8;
    public static final int CHARACTERSIZE = 27;
    public static final short[] SCREENDATA = new short[WIDTHINBLOCKS * HEIGHTINBLOCKS];
    public static ArrayList<Objects> walls = new ArrayList<>();
    public static ArrayList<Objects> foods = new ArrayList<>();
    public static ArrayList<Objects> ghosts = new ArrayList<>();
    public static TextField nameField;
    public static Stage stage;
    public static Button startButton;
}
