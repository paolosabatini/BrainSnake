package ps;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/*! \brief Class that extends the Sprite class to implement the features of the apple.
 *
 *  It implements only the constructor and the drawing options.
 */

public class Apple extends Sprite {

    
    private Color appleColor = new Color (252, 40, 0); /*!< Color of the apple */
    Grid grid; /*!< Reference to the grid where the apple lives.*/
    
    /*! \brief Constructor of the class.
     *
     *  Setting up the position and the grid.
     */
    
    public Apple (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid; 
    }

    /*! \brief Draws th apple.
     *
     *  It is basically a red rectangle with the same dimension as the grid segmentation.
     */

    public void drawApple (Graphics2D g2d){
	g2d.setColor (appleColor);
	int round_h = (int) grid.getGridSizeY();
	int round_r = (int) 0.2*grid.getGridSizeY();

	g2d.fillRoundRect(x,y,
			  grid.getGridSizeX(),
			  grid.getGridSizeY(),
			  round_h,
			  round_r);
    }
}
