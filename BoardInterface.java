/* 
 * 
 * Description: the API for the Board Class
 * 
 */

public interface BoardInterface {
    
    /* Description: draws out the Tiles of the board
     * Input: none
     * Output: none
     */ 
    void draw();
    
    /* Description: updates the 2D array so that the position matches the tiles new
     *              position after all moving in the direction described by the key.
     *              Also creates a random new tile of either 2 or 4 and places it at 
     *              an unoccupied position.an
     * Input: the character key which describes the direction to move
     * Output: none
     */
    void shift(char c);
    
    /* Description: returns true if the board is full of tiles and there are no more
     *              possible moves that the user can make, which means that the game
     *              is over and false otherwise
     * Input: none
     * Output: return true when the board is full and all adjacengt tiles are 
     *         different from one another and false otherwise
     */
    boolean isGameOver();
    
    /* Description: returns true when the user has merged 2 tiles of 1024 so that 
     *              the tile of 2048 enters the board.
     * Input: none
     * Output: returns true if user has made 2048 so that they have won and false 
     *         otherwise
     */
    boolean hasWonGame();
    
    /* Description: when 2048 tile enters the game and the user has won, this screen
     *              will appear and presents the number of moves it took with a 
     *              victory message
     * Input: none
     * Output: none
     */
    void winDashboard();
    
    /* Description: when board is full and no more moves can be made then the game 
     *              is over and this will appear on the screen with the number of 
     *              moves the user took
     * Input: none
     * Output: none
     */
    void lostDashboard();
}