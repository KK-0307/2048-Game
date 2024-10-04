/* 
 * 
 * Description: Board class that implements the BoardInterface which stimulates
 *              the board in the 2048 game. 
 * 
 */

public class Board implements BoardInterface {
    //array of Tiles representing the current grid 
    private Tile[][] board;
    //variable that keeps track of the number of moves made by the user
    private int numberOfMoves;
    //number of tiles currently on the board
    private int numberOfTiles;
    // the x-position of each corresponding Tile in the 2D array board
    public static final int[] XCOORD = {70, 190, 310, 430};
    // the y-position of each corresponding Tile in the 2D array board
    public static final int[] YCOORD = {70, 190, 310, 430};
    // number of Tiles per column / row
    public static final int GRID = 4;
    
    /* Description: Default constructor for the Board that sets up 4x4 matrix
     * Input: none
     * Output: none (builds the board)
     */
    public Board() {
        board = new Tile[4][4];
        numberOfMoves = 0;
        newTile();
        newTile();
    }
    
    /* Description: draws out the Tiles of the board
     * Input: none
     * Output: none
     */ 
    public void draw() {
        PennDraw.setFontSize(30);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].draw();
                }
            }
        }
    }
    
    /* Description: After every move, adds a new tile that is a 2 or 4 at a random 
     *             unoccupied position on the board.
     * Input: none
     * Output: none
     */
     public void newTile() {
        boolean isEmpty = true;
        while (isEmpty) {
            int i = (int) (Math.random() * 4);
            int j = (int) (Math.random() * 4);
            double probability = Math.random();
            if (board[i][j] == null) {
                board[i][j] = new Tile(XCOORD[j], YCOORD[i]);
                numberOfTiles++;
                board[i][j].drawApparition();
                return;
            }
        }
    }
    
    /* Description: updates the x and y coordinate of each tile to ensure that their
     *              position on the board matches their position on the list
     * Input: none
     * Output: none
     */
    private void updateCoord() {
        for (int i = 0; i < GRID; i++) {
            for (int j = 0; j < GRID; j++) {
                if (board[i][j] != null) {
                    board[i][j].setX(XCOORD[j]);
                    board[i][j].setY(YCOORD[i]);
                }
            }
        }
    }
    
    /* Description: returns true if the key pressed is able to be processed. For 
     *              example a possible move can be made.
     * Input: the character of the key that has been pressed
     * Output: returns true if the key can be typed and false otherwise
     */
    private boolean isKeyPermitted(char key) {
        if (key == 'a' || key == 'd' || key == 's' || key == 'w') {
            for (int i = 0; i < GRID; i++) {
                for (int j = 0; j < GRID; j++) {
                    if (canMove(i, j, key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /* Description: returns true if the current tile in the board is able to move in
     *              the direction of the character of the key (not blocked by 
     *              another tile or is not at the edge of the board)
     * Input: int i, int j identifies the tile in the 2D array board that we want
     *        to move. char key is the direction that we want to move the tile in.
     * Output: returns true if the identified tile can move and false otherwise.
     */
    private boolean canMove(int i, int j, char key) {
        if (board[i][j] == null) {
            return false;
        }
        if (atEdge(i, j, key)) {
            return false;
        }
        if (isBlocked(i, j, key)) {
            return false;
        }
        return true;
    }
    
    /* Description: returns the tile that comes after the identified tile at 
     *              position i and j in the 2D array board after the move by the key
     * Input: int i, int j identifies the tile in the 2D array board that we want
     *        to find the next tile of. char key is the direction that we want to 
     *        move the tile in.
     * Output: the tile that comes after the identified tile after the move by 
     *         the key
     */
    private Tile nextTile(int i, int j, char key) {
        if (key == 'a') {
            if (j - 1 >= 0) {
                return board[i][j - 1];
            }
        } else if (key == 'd') {
            if (j + 1 < GRID) {
                return board[i][j + 1];
            }
        } else if (key == 'w') {
            if (i + 1 < GRID) {
                return board[i + 1][j];
            }
        } else if (key == 's') {
            if (i - 1 >= 0) {
                return board[i - 1][j];
            }
        } 
        return null;
    }
    
    /* Description: returns true if a tile is blocking the tile at the position i 
     *              j in the 2D array in the direction described by the key
     * Input: int i, int j identifies the tile in the 2D array board that we want
     *        to find if it hits a tile of a different number. char key is the 
     *        direction that we want to move the tile in.
     * Output: returns true if the identified tile is blocked by a tile with a 
     *         different number and false otherwise.
     */
    private boolean isBlocked(int i, int j, char key) {
        if (nextTile(i, j, key) == null) {
            return false;
        }
        if (nextTile(i, j, key).isMergeableWith(board[i][j])) {
            return false;
        }
        return true;
    }
    
    /* Description: returns true if the tile identified at position i and j in the 
     *              2D array is blocked by an edge when moving in the direction of 
     *              the key
     * Input: int i, int j identifies the tile in the 2D array board that we want
     *        to determine if can move. char key is the direction that we want to 
     *        move the tile in.
     * Output: returns true if the tile is blocked by an edge in the direction of 
     *         the key and false otherwise.
     */
    private boolean atEdge(int i, int j, char key) {
       if (key == 'w') {
           if (i >= GRID - 1) {
               return true;
           }
       }
       if (key == 's') {
           if (i <= 0) {
               return true;
           }
       }
       if (key == 'a') {
           if (j <= 0) {
               return true;
           }
       }
       if (key == 'd') {
           if (j >= GRID - 1) {
               return true;
           }
       }
        return false;
    }
    
    /* Description: returns true if the tile in the position i and j in the 2D array 
     *              encounters an obstacle like an edge or another tile of a
     *              different number when moving in the direction of key
     * Input: int i, int j identifies the tile in the 2D array board that we want
     *        to determine if encounters an obstacle. char key is the direction 
     *        that we want to move the tile in.
     * Output: true is the identified tile does encounter an obstacle like an edge 
     *         or a different numbered tile and false otherwise
     */
    private boolean doesEncounterObst(int i, int j, char key) {
        return atEdge(i, j, key) || (nextTile(i, j, key) != null);
    }
    
    /* Description: when the key is pressed, all the tiles in the board will move in
     *              this direction until it reaches an obstacle like an edge or 
     *              a different numbered tile.a
     * Input: the character key which describes the direction of movement
     * Output: none
     */
    public void motionOfKey(char key) {
        if (key == 'a') {
            for (int j = 0; j < GRID; j++) {
                for (int i = 0; i < GRID; i++) {
                    int k = j;
                    while (!doesEncounterObst(i, k, key)) {
                        board[i][k - 1] = board[i][k];
                        board[i][k] = null;
                        k--;
                    }
                }
            }
        } else if (key == 'd') {
            for (int j = GRID - 1; j >= 0; j--) {
                for (int i = 0; i < GRID; i++) {
                    int k = j;
                    while (!doesEncounterObst(i, k, key)) {
                        board[i][k + 1] = board[i][k];
                        board[i][k] = null;
                        k++;
                    }
                }
            }
        } else if (key == 'w') {
            for (int i = GRID - 1; i >= 0; i--) {
                for (int j = 0; j < GRID; j++) {
                    int k = i;
                    while (!doesEncounterObst(k, j, key)) {
                        board[k + 1][j] = board[k][j];
                        board[k][j] = null;
                        k++;
                    }
                }
            }
        } else if (key == 's') {
            for (int i = 0; i < GRID; i++) {
                for (int j = 0; j < GRID; j++) {
                    int k = i;
                    while (!doesEncounterObst(k, j, key)) {
                        board[k - 1][j] = board[k][j];
                        board[k][j] = null;
                        k--;
                    }
                }
            }
        }
    }
    
    /* Description: returns true if the tile at position i and j in the 2D array 
     *              board is next to a tile of the same number in the direction that
     *              the key describes
     * Input: int i, int j identifies the tile in the 2D array board that we want
     *        to determine if it can merge. char key is the direction that we want 
     *        to move the tile in.
     * Output: returns true if the identified tile can merge and false otherwise.
     */
    private boolean isAbleToMerge(int i, int j, char key) {
        if (board[i][j] == null) {
            return false;
        }
        if (nextTile(i, j, key) == null) {
            return false;
        }
        return nextTile(i, j, key).isMergeableWith(board[i][j]);
    }
    
    /* Description: if the tile is able to merge in the direction described by the 
     *              key
     * Input: the character key that describes the direction to move in 
     * Output: none
     */
    private void aggregate(char key) {
        if (key == 'a') {
            for (int j = 0; j < GRID; j++) {
                for (int i = 0; i < GRID; i++) {
                    if (isAbleToMerge(i, j, key)) {
                        nextTile(i, j, key).mergeWith(board[i][j]);
                        board[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (key == 'd') {
            for (int j = GRID - 1; j >= 0; j--) {
                for (int i = 0; i < GRID; i++) {
                    if (isAbleToMerge(i, j, key)) {
                        nextTile(i, j, key).mergeWith(board[i][j]);
                        board[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (key == 'w') {
            for (int i = GRID - 1; i >= 0; i--) {
                for (int j = 0; j < GRID; j++) {
                    if (isAbleToMerge(i, j, key)) {
                        nextTile(i, j, key).mergeWith(board[i][j]);
                        board[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (key == 's') {
            for (int i = 0; i < GRID; i++) {
                for (int j = 0; j < GRID; j++) {
                    if (isAbleToMerge(i, j, key)) {
                        nextTile(i, j, key).mergeWith(board[i][j]);
                        board[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        }
    }
    
    /* Description: when the position of the tile doesn't match their position in 
     *              the board, the tiles will slide to this position on the screen
     * Input: none
     * Output: none
     */
    private void slide() {
        PennDraw.enableAnimation(100);
        int[][] xDist = new int[4][4];
        int[][] yDist = new int[4][4];
        for (int i = 0; i < GRID; i++) {
            for (int j = 0; j < GRID; j++) {
                if (board[i][j] != null) {
                    xDist[i][j] = XCOORD[j] - board[i][j].getX();
                    yDist[i][j] = YCOORD[i] - board[i][j].getY();
                }
            }
        }
        for (int k = 0; k < 20; k++) {
            for (int i = 0; i < GRID; i++) {
                for (int j = 0; j < GRID; j++) {
                    if (board[i][j] != null) {
                        board[i][j].plusX(xDist[i][j] / 20);
                        board[i][j].plusY(yDist[i][j] / 20);
                    }
                }
            }
            if (PennDraw.hasNextKeyTyped()) {
                return;
            }
            TwentyFourtyEight.background();
            draw();
            drawDashboard();
            PennDraw.advance();
        }
    }
    
    /* Description: updates the 2D array so that the position matches the tiles new
     *              position after all moving in the direction described by the key.
     *              Also creates a random new tile of either 2 or 4 and places it at 
     *              an unoccupied position.an
     * Input: the character key which describes the direction to move
     * Output: none
     */
    public void shift(char key) {
        if (isKeyPermitted(key)) {
            motionOfKey(key);
            slide();
            aggregate(key);
            slide();
            motionOfKey(key);
            slide();
            updateCoord();
            newTile();
            numberOfMoves++;
        }
    }
    
    /* Description: returns true if the board is full of tiles and there are no more
     *              possible moves that the user can make, which means that the game
     *              is over and false otherwise
     * Input: none
     * Output: return true when the board is full and all adjacengt tiles are 
     *         different from one another and false otherwise
     */
    public boolean isGameOver() {
        if (numberOfTiles <= 15) {
            return false;
        }
        
        for (int i = 0; i < GRID; i++) {
            for (int j = 0; j < GRID; j++) {
                if (i + 1 < GRID && board[i][j].isMergeableWith(board[i + 1][j])) {
                    return false;
                }
                if (j + 1 < GRID && board[i][j].isMergeableWith(board[i][j + 1])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /* Description: returns true when the user has merged 2 tiles of 1024 so that 
     *              the tile of 2048 enters the board.
     * Input: none
     * Output: returns true if user has made 2048 so that they have won and false 
     *         otherwise
     */
    public boolean hasWonGame() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && board[i][j].getNum() >= 2048) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /* Description: draws out the dashboard including the number of tiles at the top
     * Input: none
     * Ouput: none
     */
    public void drawDashboard() {
        PennDraw.setFontSize(12);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(225, 560, "Number Of Tiles:" + numberOfTiles);
        PennDraw.text(225, 600, "Moves:" + numberOfMoves);
    }
    
    /* Description: when 2048 tile enters the game and the user has won, this screen
     *              will appear and presents the number of moves it took with a 
     *              victory message
     * Input: none
     * Output: none
     */
    public void winDashboard() {
        PennDraw.setPenColor(255, 251, 239, 230);
        PennDraw.filledRectangle(250, 300, 275, 325);
        PennDraw.setFontSize(32);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(250, 350, "Congrations! You won!");
        PennDraw.setFontSize(16);
        PennDraw.text(250, 275, "Number of Moves:" + numberOfMoves);
    }
    
    /* Description: when board is full and no more moves can be made then the game 
     *              is over and this will appear on the screen with the number of 
     *              moves the user took
     * Input: none
     * Output: none
     */
    public void lostDashboard() {
        PennDraw.setPenColor(160, 14, 1, 230);
        PennDraw.filledRectangle(250, 300, 275, 325);
        PennDraw.setFontSize(32);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(250, 350, "Game Over!");
        PennDraw.setFontSize(16);
        PennDraw.text(250, 275, "Number of Moves:" + numberOfMoves);
    }
}