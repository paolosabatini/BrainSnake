package ps;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.stream.Collectors;

/*! \brief Class containing all the snake information, extending the Sprite class.
 *
 * The snake is not a real sprite, it consist is managing a vector of SnakeBlocks in the movement in the playground.
 * It implements the rules for being driven by key inputs, movement, collision and eating.
 */

public class Snake extends Sprite {

    private Color snakeColor = new Color (252, 252, 252); /*!< Color of the snake. */
    private List<SnakeBlock> blocks; /*!< Vector of the SnakeBlocks that makes the snake. */
    private List<Apple> apples; /*!< List of apples eaten (used to enlarge the snake size when an apple is eaten). */ 
    private ArrayList<Integer> velocity; /*!< Direction of the snake movemnt: [up, left, down, right] */
    Grid grid; /*!< Refrence to the grid where the snake lives.*/
    public boolean ALREADY_PRESSED = false; /*!< Flag that tells whether in the time unit a key command is already stored.*/
    
    /*! \brief Constructor of the class.
     *
     * It sets up the position, grid, initial direction (downwards), initial snake shape (two blocks vertically disposed) and no eaten apples.
     */

    public Snake (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid;
	velocity =  grid.getDown ();
	blocks = new ArrayList<>();
	blocks.add ( new SnakeBlock (x,y,grid) );
	blocks.add (new SnakeBlock (x,y-grid.getGridSizeY(),grid) );
	apples = new ArrayList<> ();
    }

    
    /*! \brief Moves the snake.
     *
     * Compute the movement of the Snake head based on the pressed keys and update the positions of all the snake blocks.
     * In case the last block has moved away from the eaten apple position, the snake is enlarged by a new block at the eaten apple position.
     */

    public void move (String mode, Apple apple){

	if (mode != "MANUAL") autoMove(mode, apple);

	
	int dx = ( - velocity.get(1) + velocity.get(3) ) * grid.getGridSizeX();
	int dy = ( - velocity.get(0) + velocity.get(2) ) * grid.getGridSizeY();
	
	List <SnakeBlock> blocks_backup = new ArrayList<>();
	for (SnakeBlock b: blocks){
	    blocks_backup . add (new SnakeBlock (b.getX(),b.getY(),grid));
	}
	int x_last = blocks_backup.get (blocks_backup.size()-1).getX();
	int y_last = blocks_backup.get (blocks_backup.size()-1).getY();
	if (Board.isVerbose()) System.out.println ("> Moving the snake blocks:");
	for (int i = 1; i < blocks_backup.size(); i++){
	    if (Board.isVerbose())
		System.out.println ("  > from "+blocks.get (i).getX()+" -> "+blocks_backup.get (i-1).getX()+" , "+blocks.get (i).getY()+" -> "+blocks_backup.get (i-1).getY());
	    blocks.get (i).setX ( blocks_backup.get (i-1).getX() );
	    blocks.get (i).setY ( blocks_backup.get (i-1).getY() );
	}

	if (Board.isVerbose()) System.out.println ("> Check if snake has to be extended");
	int index_remove = -1;
	int apple_counter=0;
	for (Apple a : apples){
	    if (Board.isVerbose())
		System.out.println ("  apple x "+a.getX()+" y "+a.getY()+" last-block x "+x_last+" y "+y_last);
	    if ((a.getX()==x_last) &&
		(a.getY()==y_last)){
		if (Board.isVerbose()) System.out.println ("  > adding block x "+a.getX()+" y "+a.getY());
		index_remove = apple_counter;
		this.blocks.add (new SnakeBlock (a.getX(), a.getY(),grid));
		break;
	    }
	    apple_counter+=1;
	}
	if (index_remove>=0) apples.remove (index_remove);
	
	if (Board.isVerbose()) System.out.println ("> Head pre-move x "+this.x+" y "+this.y);
	this.x += dx;
	this.y += dy;
	blocks.get (0).setX (this.x);
	blocks.get (0).setY (this.y);
	if (Board.isVerbose()) System.out.println ("> Head post-move x "+this.x+" y "+this.y);


	if (Board.isVerbose()) {
	    System.out.println ("> dx "+dx+" dy "+dy);
	    for (SnakeBlock b : blocks){
		System.out.println ("\t > x "+b.getX()+" y "+b.getY());
	    }
	}
    }

    /*! \brief Manages the command given by pressing the keys.
     *
     * Arrows are used to move up, down, left and right. In case in the time unit a movement is already given, another pressed key is ignored.
     */
    
    public void keyPressed(KeyEvent e) {

	if (this.ALREADY_PRESSED)
	    return;
	
        int key = e.getKeyCode();

	if (Board.isVerbose()){
	    System.out.println ("KEY PRESSED "+key);
	    System.out.println ("Velocity "+velocity);
	}

	if (key == KeyEvent.VK_LEFT) {
	    velocity = ( !velocity.equals ( grid.getRight() ) ) ? grid.getLeft() : velocity ;
        }
	
        if (key == KeyEvent.VK_RIGHT) {
	    velocity = ( !velocity.equals ( grid.getLeft() ) ) ? grid.getRight() : velocity ;
        }
	
        if (key == KeyEvent.VK_UP) {
	    velocity = ( !velocity.equals ( grid.getDown() ) ) ? grid.getUp() : velocity ;
        }
	
        if (key == KeyEvent.VK_DOWN) {
	    velocity = ( !velocity.equals ( grid.getUp() ) ) ? grid.getDown() : velocity ;
        }

	if (Board.isVerbose()) System.out.println (" ===> "+velocity);
	this.ALREADY_PRESSED = true;
    }

    /*! \brief Empty method since no action is required done when the key is released.
     *
     * Nothing has to be done if auto is selected
     */
    
    public void autoPressed(KeyEvent e) {

	// If auto selected, the velocity is as before.

    }

    /*! \brief Empty method since no action is required done when the key is released.
     *
     * If nothing is pressed the snake still moves in its direction.
     */
    
    public void keyReleased(KeyEvent e) {

	// If nothing is pressed the velocity is as before.

    }

    /*! \brief Empty method since no action is required done when the key is released.
     *
     * Nothing has to be done if auto is selected
     */
    
    public void autoReleased(KeyEvent e) {

	// If nothing is pressed the velocity is as before.

    }

    
    /*! \brief Auto moving: it means that keys are neglected, the movements are computed.
     *
     * One can choose which policy to use to move the snake.
     */
    
    public void autoMove(String mode, Apple apple) {
	System.out.println (mode+" "+(mode.equals( "BENCHMARK" )));
	if (mode.equals( "BENCHMARK" )) {
	    this.velocity = Model.moveBenchmark (x,y, blocks, velocity, apple, grid);
	}
	
     }
    
    /*! \brief Draws the snake.
     *
     * It loops over the vector of blocks and draw them with the dedicatd SnakeBlock command.
     */

    public void drawSnake (Graphics2D g2d){

	for (SnakeBlock b : blocks ){
	    
	    b.drawSnakeBlock(g2d);

	}
    }

    /*! \brief Checks whether the input rectangle intersects with the snake.
     *
     * Returns true in case the input rectangle intesects with any of the snake blocs.
     *
     * @param r Input rectangle under test.
     */

    public boolean isIn (Rectangle r){

	boolean isin = false;
	for (SnakeBlock b : blocks){
	    Rectangle b_r = new Rectangle();
	    b_r.setRect(b.getX (), b.getY(), grid.getGridSizeX(), grid.getGridSizeY());
	    if (b_r.intersects (r)){
		isin = true;
		break;
	    }
	}
	return isin;
    }
    
    /*! \brief Checks whether the input rectangle intersects with the snake (head excluded).
     *
     * Returns true in case the input rectangle intesects with any of the snake blocs (head excluded).
     *
     * @param r Input rectangle under test.
     */
    
    public boolean headIsIn (Rectangle r){

	boolean isin = false;
	for (int i = 1; i<blocks.size();i++){
	    Rectangle b_r = new Rectangle();
	    b_r.setRect(blocks.get(i).getX (), blocks.get(i).getY(),
			grid.getGridSizeX(), grid.getGridSizeY());
	    if (b_r.intersects (r)){
		isin = true;
		break;
	    }
	}
	return isin;
    }

    /*! \brief Add a new apple to the eaten apple list of the snake.
     *
     * @param a Apple in input to be added.
     */

    public void addApple (Apple a){
	this.apples.add (a);
    }


}
