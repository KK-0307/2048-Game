/*
 * 
 * Description: Where the 2048 game is simulated. The user presses one of the 4 keys
 *              to describe the direction in which the tile should move.
 */

public class TwentyFourtyEight {
    private static Board board = new Board();
    // set frame value at 100
    private static final int FRAMES = 100;
    // determines if the game is over or not
    private static boolean isOver = false;
    
    /* Description: erases and draws the background
     * Input: none
     * Output: none
     */
    public static void background() {
        PennDraw.clear();
        PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        PennDraw.filledSquare(250, 250, 250);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(0.005);
        PennDraw.square(250, 250, 250);
        PennDraw.setPenColor(PennDraw.WHITE);
    }
    
   public static void main(String[] args) {
       PennDraw.setCanvasSize(550, 650);
       PennDraw.setXscale(-25, 525);
       PennDraw.setYscale(-25, 625);
       PennDraw.enableAnimation(FRAMES);
       
       while (true) {
            background();
            board.draw();
            board.drawDashboard();
           
           if (board.hasWonGame()) {
                board.winDashboard();
                isOver = true;
           }
           
           if (board.isGameOver()) {
                board.lostDashboard();
                isOver = true;
           }
           
           if (PennDraw.hasNextKeyTyped() && !isOver) {
                char c = PennDraw.nextKeyTyped();
                board.shift(c);
           }
           PennDraw.advance();
       }
   }
}