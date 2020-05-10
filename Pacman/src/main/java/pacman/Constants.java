
package pacman;

import java.util.ArrayList;

/**
 * class to hold lists of objects on the board
 */
public class Constants {
    
    public static final int STAGEWIDTH = 1000;
    public static final int STAGEHEIGHT = 600;
    public static ArrayList<Wall> walls = new ArrayList<>();
    public static ArrayList<Food> foods = new ArrayList<>();
    public static ArrayList<Ghost> ghosts = new ArrayList<>();
}
