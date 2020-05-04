package ps;
    
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.Timer;
import java.lang.Math;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.FileReader; 
import java.io.FileNotFoundException;

   
/*! \brief Class containing the code running the application.
 *
 * The board class manages the execution of the whole application. Therefore every object shown and running are members of this class.
 * It extends the JPanel class and implements the ActionLister to listen key pressing.
 */

public class Board extends JPanel implements ActionListener {
    
    private Snake snake; /*!< Instance of the snake */
    private Apple apple; /*!< Instance of the apple */
    private Grid grid;  /*!< Instance of the grid */
    private Timer timer; /*!< Instance of the timer */
    private JsonHandler Json; /*!< Instance of the Json handler.*/
    
    protected int IAPPLE_X = 200; /*!< Initial horizontal position of the apple */
    protected int IAPPLE_Y = 200; /*!< Initial vertical position of the apple */
    protected int ISNAKE_X = 300; /*!< Initial horizontal position of the snake */
    protected int ISNAKE_Y = 200; /*!< Initial vertical position of the snake */
        

    protected int B_WIDTH = 400; /*!< Width of the playing field */
    protected int B_HEIGHT = 400; /*!< Height of the playing field and of the board */
    protected int TOTAL_WIDTH = (int)(400*1.25); /*!< Total width of the board */
    protected int GRID_N = 20; /*!< Number of segmentations */
    protected int GRID_WIDTH = B_WIDTH / GRID_N; /*!< Horizontal segmentation size of the playground */
    protected int GRID_HEIGHT = B_HEIGHT / GRID_N; /*!< Vertical segmentation size of the playground */

    protected boolean inGame; /*!<  Flag for "still in game"*/
    protected int Score; /*!<  Variable containing the total score*/
    protected int DELAY = 150; /*!<  Timer period (ms). The higher the slower the snake moves.*/
    protected static boolean VERBOSE = false; /*!<  Flag to enable the verbose output */

    /*! \brief Constructor.
     *
     * Constructor of the class.
     *
     * @param json_filename Name of the json file to read
     */
    
    public Board (String json_filename) {
	initJson (json_filename);
	initBoard ();
    }

    /*! \brief Initilise the Json reader
     *
     * Initialise the json reader to handle the configuration of the code.
     *
     * @param json_filename Name of the json file to read
     */
    
    public void initJson (String json_filename) {
	Json = new JsonHandler (json_filename);
    }
    
    /*! \brief Initialisation of the board.
     *
     * It sets up the initial conditions of the game:
     * - Color and size of the board.
     * - Initialisation of the grid instance (to deal with the segmentation).
     * - Initialise the apple and the snake.
     * - Initialise the inGame flag and the score.
     * - It starts the timer, that makes the actions go.
     */

    public void initBoard (){

	addKeyListener(new TAdapter());

	setFocusable(true);
        setBackground(Color.lightGray);

	setPreferredSize(new Dimension(TOTAL_WIDTH, B_HEIGHT));

	grid = new Grid (GRID_WIDTH, GRID_HEIGHT, B_WIDTH, B_HEIGHT);
	if (VERBOSE)
	    System.out.println("Position apple "+grid.getGridAtEdgeX (IAPPLE_X)+" "+grid.getGridAtEdgeY (IAPPLE_Y));
	apple = new Apple (grid.getGridAtEdgeX (IAPPLE_X),
			   grid.getGridAtEdgeY (IAPPLE_Y), grid);
	snake = new Snake (grid.getGridAtEdgeX (ISNAKE_X),
			   grid.getGridAtEdgeY (ISNAKE_Y), grid);
	
	inGame = true;
	Score = 0;
	timer = new Timer(DELAY, this);
        timer.start();
    }
    
     /*! \brief Action that is performed at each clock occurrence.
     *
     * It makes the snake move according to the key pressed, checks whether the apple is eaten (in case reproduce the apple).
     * It checks collisions of the snake with borders or itself, update the score and the inGame flag.
     * In case the game is over, the timer is stopped.
     * The already pressed flag tells whether the action is already busy.
     * 
     * @param e Action event that is called at each timer clock.
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
	
	if (VERBOSE)
	    System.out.println ("printed in action");

	updateSnake ();

	checkEating ();
	inGame = checkCollisions ();

	repaint();

	if (!inGame) timer.stop();
	    
	snake.ALREADY_PRESSED=false;
    }

    /*! \brief Makes the snake move.
     *
     * The snake is moved according to the key pressed. 
     */

    public void updateSnake (){
	snake.move( Json.getMode(),
		    apple);
    }

    /*! \brief Checks whether the apple is eaten.
     *
     * In case the head of the snake intersects the apple, another apple is created.
     * The new apple must not be in the snake body. If it is randomly extracted in there, it is extracted again.
     */
    
    public void checkEating (){

	Rectangle snake_head = new Rectangle();
	snake_head.setRect (snake.x, snake.y, grid.getGridSizeX(), grid.getGridSizeY() );
	Rectangle ap_rect = new Rectangle();
	ap_rect.setRect (apple.x, apple.y, grid.getGridSizeX(), grid.getGridSizeY() );
	
	if (snake_head.intersects (ap_rect)){
	    
	    snake.addApple (new Apple (apple.x,apple.y,grid));
	    
	    if (VERBOSE) System.out.println ("==> APPLE EATEN!");
	    while (true){
		if (VERBOSE) System.out.println ("    > extract new apple");
		int posx = grid.getGridBinEdgeX ((int)(Math.random()*GRID_N));
		int posy = grid.getGridBinEdgeY ((int)(Math.random()*GRID_N));
		apple.setX ( posx );
		apple.setY ( posy );
		ap_rect.setRect (posx, posy, grid.getGridSizeX(), grid.getGridSizeY() );
		if (VERBOSE){
		    System.out.println ("    > posx "+posx+" y "+posy);
		    System.out.println ("    > intersect? "+snake.isIn( ap_rect ));
		}
		if ( !snake.isIn( ap_rect ) ) break;
	    }
	    Score += 1;
	}
    }

    /*! \brief Checks the snake collides with borders or itself.
     *
     * It checks whether the snake head intersects the other snake blocks.
     * It checks wehter the snake is fully contained by the borders.
     */

    public boolean checkCollisions (){
	
	// Collision against the snake
	Rectangle snake_head = new Rectangle();
	snake_head.setRect (snake.x, snake.y, grid.getGridSizeX(), grid.getGridSizeY() );
	if ( snake.headIsIn (snake_head) ) return false;

	// Collision against the border
	Rectangle border = grid.getBorders();
	if (!border.contains(snake_head)) return false;

	return true;	
    }
    
    /*! \brief It reimplements the paintComponent method of JPanel.
     *        This is extended to draw also the other objects
     *        
     * If the game is going all the objects are drawn with the score as well.
     * If not, "Game Over" is displayed.
     *
     * @param g Graphics to paint the different objects. 
     */
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

	if (inGame){
	    drawObjects (g);

	    drawScore ((Graphics2D)g);
	}

	else {
	    drawObjects (g);

	    drawScore ((Graphics2D)g);

	    drawGameOver (g);
	}
	
	Toolkit.getDefaultToolkit().sync();
    }

    /*! \brief It draws all the objects needed to the code to run.
     *        
     * It draws borders, the apple and the snake.
     *
     * @param g Graphics to paint the different objects. 
     */

    public void drawObjects (Graphics g){

	grid.drawBorders ((Graphics2D)g);

	apple.drawApple ((Graphics2D)g);

	snake.drawSnake ((Graphics2D)g);
    }

    /*! \brief It draws the score pad on top-right.
     *        
     * It creates the top-right box and draw the score info. 
     *
     * @param g2d Graphics2D to paint the different objects. 
     */

    public void drawScore (Graphics2D g2d){
	
	double pad_offset = 0.02;
	double pad_height_fraction = 0.4;

	// It creates the box
	g2d.setColor (Color.darkGray);
	g2d.setStroke(new BasicStroke(2));
	g2d.drawRect ((int) (B_WIDTH*(1+pad_offset) ) ,
		      (int) (B_HEIGHT*(0.5*pad_offset)) ,
		      (int) ((TOTAL_WIDTH-B_WIDTH)-1.5*pad_offset*B_WIDTH),
		      (int) (B_HEIGHT*(pad_height_fraction)) );
	
	// It creates the "score"
	g2d.setColor(Color.BLACK);
        Font normal = new Font("Helvetica", Font.BOLD, 14);
	g2d.setFont (normal);
        g2d.drawString("Score",
		       (int) (B_WIDTH+0.25*(TOTAL_WIDTH-B_WIDTH)),
		       (int) (2.5*B_HEIGHT*pad_offset) );

	// It creates the score number	
	g2d.setColor(Color.RED);
        Font large = new Font("Helvetica", Font.BOLD, 25);
	g2d.setFont (large);
        g2d.drawString(""+(Score),
		       (int) (B_WIDTH+0.4*(TOTAL_WIDTH-B_WIDTH)),
		       (int) (0.5*B_HEIGHT*(pad_height_fraction)+ B_HEIGHT*(0.5*pad_offset) ) );
    }

    /*! \brief It draws "Game Over" at the end of the game.
     *        
     *  "Game Over" is displayed big at the center of the playground. 
     *
     * @param g Graphics to paint the different objects. 
     */

    private void drawGameOver(Graphics g) {
	
        String msg = "GAME OVER";
        Font small = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(small);
	
        g.setColor(Color.RED);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
		     B_HEIGHT / 2);
    }

    /*! \brief Class needed to reimplement the KeyAdapter to the game needs.
     *        
     * It wants to extend the KeyAdapter class to adapt the snake movement.
     */

    private class TAdapter extends KeyAdapter {

	/*! \brief Indicates what happens when a key is released.
	 *        
	 * It redirects to the snake action when the key is released.
	 */
	
        @Override
        public void keyReleased(KeyEvent e) {
	    if (Json.getMode ().equals( "MANUAL" )) snake.keyReleased(e);
	    
	}

	/*!\brief Indicates what happens when a key is pressed.
	 *        
	 * It redirects to the snake action when the key is pressed.
	 */

        @Override
        public void keyPressed(KeyEvent e) {
	    if (Json.getMode ().equals( "MANUAL" )) snake.keyPressed(e);
	    else snake.autoPressed (e);
	}
    }

    
    /*! \brief Useful to propagate to the other classes whether the verbose is on/off.
     *        
     * Returns the VERBOSE member.
     */

    public static boolean isVerbose() {return VERBOSE;}
}
