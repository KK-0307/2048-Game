/*
 * 
 * Description: Tile class which implements the TileInterface which stimulates a 
 *              a tile in 2048
 */

public class Tile implements TileInterface {
    //the number on the tile
    private int number;
    //x-coordinate of tile
    private int x;
    //y-coordinate of tile
    private int y;
    //fixed dimension of tile
    public static final int DIMENSION = 50;
    
    /* Description: constructor which creates a new tile of either 2 or 4 in value 
     *              at the specified probability. (Tile 2 > 0.5) (Tile 4 < 0.5)
     * Input: none
     * Output: none
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        double probability = Math.random();
        if (probability < 0.5) {
            number = 4;
        } else {
            number = 2;
        }
    }
    
    /* Description: getter to get the number of a given tile
     * Input: none
     * Output: the number of the tile
     */
    public int getNum() {
        return number;
    }
    
    /* Description: getter to get the x-coordinate of the tile
     * Input: none
     * Output: the x-coordinate of the tile
     */
    public int getX() {
        return x;
    }
    
    /* Description: getter to get the y-coordinate of the tile
     * Input: none
     * Output: the y-coordinate of the tile
     */
    public int getY() {
        return y;
    }
    
    /* Description: sets the tile's x value to the new x-coordinate
     * Input: the new x-coordinate
     * Output: none
     */
    public void setX(int newX) {
        x = newX;
    }
    
    /* Description: sets the tile's y value to the new y-coordinate
     * Input: the new y-coordinate
     * Output: none
     */
    public void setY(int newY) {
        y = newY;
    }
    
    /* Description: adds the input addX to the x-coordinate
     * Input: int addX
     * Output: none
     */ 
    public void plusX(int addX) {
        x += addX;
    }
    
    /* Description: adds the input addY to the y-coordinate
     * Input: int addY
     * Output: none
     */
    public void plusY(int addY) {
        y += addY;
    }
    
    /* Description: returns true if the identified tile and the input current tile 
     *              have the same number
     * Input: the current tile which we will compare to see if it is able to merge
     * Output: returns true if the number is the same and false otherwise
     */
    public boolean isMergeableWith(Tile current) {
        return this.number == current.number;
    }
    
    /* Description: doubles the number on the identified tile
     * Input: current tile to merge with the identified tile
     * Output: none
     */
    public void mergeWith(Tile current) {
        this.number += current.number;
    }
    
    /* Description: sets the color of each tile based on the number of the tile
     * Input: none
     * Output: none
     */
    private void setColor() {
        if (number == 2) {
            PennDraw.setPenColor(238, 228, 218);
        } else if (number == 4) {
            PennDraw.setPenColor(237, 224, 200);
        } else if (number == 8) {
            PennDraw.setPenColor(242, 177, 121);
        } else if (number == 16) {
            PennDraw.setPenColor(245, 149, 99);
        } else if (number == 32) {
            PennDraw.setPenColor(246, 124, 95);
        } else if (number == 64) {
            PennDraw.setPenColor(246, 94, 59);
        } else if (number == 128) {
            PennDraw.setPenColor(237, 207, 114);
        } else if (number == 256) {
            PennDraw.setPenColor(237, 204, 97);
        } else if (number == 512) {
            PennDraw.setPenColor(237, 200, 80);
        } else if (number == 1024) {
            PennDraw.setPenColor(237, 197, 63);
        } else if (number == 2048) {
            PennDraw.setPenColor(237, 194, 46);
        }
    }
    
    /* Description: draws a tile at the specified loaction with a set dimension of 50
     * Input: none
     * Output: none
     */
    public void draw() {
        PennDraw.setPenRadius(0.005 / 3);
        setColor();
        PennDraw.filledSquare(x, y, DIMENSION);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(x, y, DIMENSION);
        PennDraw.text(x, y, "" + number);
    }
    
    /* Description: draws out the background of the tile to make it seem like it 
     *              would appear
     * Input: none
     * Output: none
     */
    public void drawApparition() {
        PennDraw.enableAnimation(100);
        for (int i = 2; i <= 15; i++) {
            if (PennDraw.hasNextKeyTyped()) {
                return;
            }
            setColor();
            PennDraw.filledSquare(x, y, i * DIMENSION / 15.0);
            PennDraw.advance();
        }
    }
}
  