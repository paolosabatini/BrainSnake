package ps;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

/*! \brief Class that extend the Sprite class to adapt to SnakeBlock features.
 *
 * The class implements the info of the single blocks of the snake. 
 * Similar to Apple class, it needs only to be created and drawn.
 */

public class SnakeBlock extends Sprite {
    
    private Color snakeColor = new Color (252, 252, 252); /*!< Color of the snake blocks.*/
    Grid grid; /*!< Reference to the grid where the block lives.*/

    /*! \brief Constructor of the class.
     *
     * It creates a SnakeBlock at a given position and living in the input grid.
     * 
     * @param x Horizontal position.
     * @param y Vertical position.
     * @param grid Reference to the grid.
     */
    
    public SnakeBlock (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid;
	
    }
    
    /*! \brief It draws the SnakeBlock.
     *
     * It is basically a white rectangle with the same dimension of the grid segmentation.
     * 
     */

    public void drawSnakeBlock (Graphics2D g2d){

	int round_h = (int) grid.getGridSizeY();
	int round_r = (int) 0.2*grid.getGridSizeY();

	// Lines to add also border lines to the blocks 
	// g2d.setColor (Color.BLACK);
	// g2d.setStroke(new BasicStroke(1));
	// g2d.drawRect(x,y,
	// 	     grid.getGridSizeX(),
	// 	     grid.getGridSizeY()
	// 	     );

	g2d.setColor (snakeColor);
	g2d.fillRect(x,y,
		     grid.getGridSizeX(),
		     grid.getGridSizeY()
		     );
    }
}
