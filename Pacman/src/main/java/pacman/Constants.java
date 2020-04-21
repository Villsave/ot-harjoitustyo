
package pacman;

import java.util.ArrayList;

public class Constants {
    
    public static final int stageWidth = 1000;
    public static final int stageHeight = 600;
    public static final int wallSize = 32;
    public static final int widthInBlocks = 27;
    public static final int heightInBlocks = 14;
    public static final int foodSize = 8;
    public static final int characterSize = 27;
    public static final short[] screenData = new short[widthInBlocks * heightInBlocks];
    public static ArrayList <Objects> walls = new ArrayList<>();
    public static ArrayList <Objects> foods = new ArrayList<>();
    public static ArrayList <Objects> ghosts = new ArrayList<>();
    public static ArrayList <Objects> lives = new ArrayList<>();
}
