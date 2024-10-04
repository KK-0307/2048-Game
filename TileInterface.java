/* 
 * 
 * Description: the API for the Tile Class
 * 
 */

public interface TileInterface {
    
    /* Description: getter to get the number of a given tile
     * Input: none
     * Output: the number of the tile
     */
    int getNum();
    
    /* Description: getter to get the x-coordinate of the tile
     * Input: none
     * Output: the x-coordinate of the tile
     */
    int getX();
    
    /* Description: getter to get the y-coordinate of the tile
     * Input: none
     * Output: the y-coordinate of the tile
     */
    int getY();
    
    /* Description: sets the tile's x value to the new x-coordinate
     * Input: the new x-coordinate
     * Output: none
     */
    void setX(int newX);
    
    /* Description: sets the tile's y value to the new y-coordinate
     * Input: the new y-coordinate
     * Output: none
     */
    void setY(int newY);
    
    /* Description: adds the input addX to the x-coordinate
     * Input: int addX
     * Output: none
     */
    void plusX(int addX);
    
    /* Description: adds the input addY to the y-coordinate
     * Input: int addY
     * Output: none
     */
    void plusY(int addY);
    
    /* Description: returns true if the identified tile and the input current tile 
     *              have the same number
     * Input: the current tile which we will compare to see if it is able to merge
     * Output: returns true if the number is the same and false otherwise
     */
    boolean isMergeableWith(Tile current);
    
    /* Description: doubles the number on the identified tile
     * Input: current tile to merge with the identified tile
     * Output: none
     */
    void mergeWith(Tile current);
    
     /* Description: draws a tile at the specified loaction with a set dimension 
      *              of 50
     * Input: none
     * Output: none
     */
    void draw();
    
    /* Description: draws out the background of the tile to make it seem like it 
     *              would appear
     * Input: none
     * Output: none
     */
    void drawApparition();
    
}