
package domain;

public class Block extends Sprite{
    public enum BlockType {
        FOOD, WALL
    }  
    private BlockType blocktype;
    
    public Block(BlockType blocktype) {
        this.blocktype = blocktype;
    }
}
