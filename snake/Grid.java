package ps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

/*! \brief This class help managing the coordinates.
 *
 * It manages the playground coordinates, converting from board coordinates to the segmentations, needed in the game.
 */

public class Grid {
    
    private Color borderColor = new Color (100, 177, 0); /*!< Color of the borders. */
    private int GRID_SIZE_X = 20;  /*!< default horizontal size of the grid segmentation, reinstanciated in the constructor.*/
    private int GRID_SIZE_Y = 20;   /*!< default vertical size of the grid segmentation, reinstanciated in the constructor.*/
    private int WINDOW_SIZE_X = 200; /*!< default horizontal size of the window, reinstanciated in the constructor.*/
    private int WINDOW_SIZE_Y = 200; /*!< default vertical size of the window, reinstanciated in the constructor.*/
    
    /*! \brief Constructor.
     *
     * Constructor of the class. It initialise the sizes of windows and segmentation.
     *
     * @param GRID_SIZE_X Horizontal segmentation size.
     * @param GRID_SIZE_Y Vertical segmentation size.
     * @param WINDOW_X Horizontal window size.
     * @param WINDOW_Y Vertical window size.
     */

    public Grid (int seg_x, int seg_y, int window_x, int window_y){

	GRID_SIZE_X = seg_x;
	GRID_SIZE_Y = seg_y;
	WINDOW_SIZE_X = window_x;
	WINDOW_SIZE_Y = window_y;
    }

    /*! \brief Get the grid segmentation horizontal size.
     */

    public int getGridSizeX () {return GRID_SIZE_X;}

    /*! \brief Get the grid segmentation vertical size.
     */

    public int getGridSizeY () {return GRID_SIZE_Y;}

    /*! \brief Get the window horizontal size.
     */

    public int getWindowSizeX () {return WINDOW_SIZE_X;}

    /*! \brief Get the window vertical size.
     */

    public int getWindowSizeY () {return WINDOW_SIZE_Y;}

    /*! \brief Set the grid segmentation vertical size.
     */
    public void setGridSizeX (int val) {GRID_SIZE_X = val;}

    /*! \brief Set the grid segmentation horizontal size.
     */

    public void setGridSizeY (int val) {GRID_SIZE_Y = val;}

    /*! \brief Set the window horizontal size.
     */

    public void setWindowSizeX (int val) {WINDOW_SIZE_X = val;}

    /*! \brief Set the window vertical size.
     */

    public void setWindowSizeY (int val) {WINDOW_SIZE_Y = val;}

    
    /*! \brief Draw the borders of the playground.
     *
     * @ param g2d Grapics2D instance to draw the 2D rectangle.
     */

    public void drawBorders (Graphics2D g2d){

	g2d.setColor (borderColor);
	g2d.setStroke(new BasicStroke(5));
	g2d.drawRect(0,0,
		     WINDOW_SIZE_X,
		     WINDOW_SIZE_Y);
    }

    /*! \brief Returns the rectangle corresponding to the playground borders.
     *
     * It returns a rectangle object correspondings to the playgroud borders. 
     * Used to study interceptions etc..
     */

    public Rectangle getBorders (){

	Rectangle border = new Rectangle ();
	border.setRect(0,0,
		       WINDOW_SIZE_X,
		       WINDOW_SIZE_Y);

	return border;
    }

    /*! \brief Returns the horizontal index in grid segmentations units corresponding to a board coordinate in input.
     *
     * @param x Input value of boards coordinates
     */

    public int getGridBinX (int x){
	return x / GRID_SIZE_X;
    }

    /*! \brief Returns the vertical index in grid segmentations units corresponding to a board coordinate in input.
     *
     * @param x Input value of boards coordinates
     */

    public int getGridBinY (int y){
	return y / GRID_SIZE_Y;
    }
    
    /*! \brief Returns the horizontal center of grid segmentations units in board coordinates.
     *
     * @param x Input value of the grid bin
     */

    public int getGridBinCenterX (int x){
	return x + (int)(GRID_SIZE_X/2.);
    }
    
    /*! \brief Returns the vertical center of grid segmentations units in board coordinates.
     *
     * @param x Input value of the grid bin
     */

    public int getGridBinCenterY (int y){
	return y + (int)(GRID_SIZE_Y/2.);
    }
    
    /*! \brief Moves the board horizontal coordinate in input to the center of the grid segmentation .
     *
     * @param x Input value of board coordinate.
     */

    public int getGridCenteredX (int x){
	return getGridBinX(x)*GRID_SIZE_X + (int)(GRID_SIZE_X/2.);
    }

    /*! \brief Moves the board vertical coordinate in input to the center of the grid segmentation .
     *
     * @param x Input value of board coordinate.
     */

    public int getGridCenteredY (int y){
	return getGridBinY (y)*GRID_SIZE_X+ (int)(GRID_SIZE_Y/2.);
    }

    /*! \brief Returns the horizontal coordinate of the input grid segmentation .
     *
     * @param x Input value of grid bin index.
     */
    
    public int getGridBinEdgeX (int x){
	return x*GRID_SIZE_X;
    }

    /*! \brief Returns the vertical coordinate of the input grid segmentation .
     *
     * @param x Input value of grid bin index.
     */

    public int getGridBinEdgeY (int y){
	return y*GRID_SIZE_Y;
    }

    /*! \brief Moves the board horizontal coordinate in input to the edge of the grid segmentation .
     *
     * @param x Input value of board coordinate.
     */

    public int getGridAtEdgeX (int x){
	return getGridBinX(x)*GRID_SIZE_X;
    }

    /*! \brief Moves the board vertical coordinate in input to the edge of the grid segmentation .
     *
     * @param x Input value of board coordinate.
     */

    public int getGridAtEdgeY (int y){
	return getGridBinY(y)*GRID_SIZE_Y;
    }

    /*! \brief Returns the direction vector corresponding to upwards movement.
     */

    public ArrayList<Integer> getUp (){
	ArrayList<Integer> vec_up =  new ArrayList<Integer>();
	vec_up.add (1);
	vec_up.add (0);
	vec_up.add (0);
	vec_up.add (0);
	return (vec_up);
    }

    /*! \brief Returns the direction vector corresponding to left movement.
     */

    public ArrayList<Integer> getLeft (){
	ArrayList<Integer> vec_left =  new ArrayList<Integer>();
	vec_left.add (0);
	vec_left.add (1);
	vec_left.add (0);
	vec_left.add (0);
	return (vec_left);
    }

    /*! \brief Returns the direction vector corresponding to downwards movement.
     */

    public ArrayList<Integer> getDown (){
	ArrayList<Integer> vec_down =  new ArrayList<Integer>();
	vec_down.add (0);
	vec_down.add (0);
	vec_down.add (1);
	vec_down.add (0);
	return (vec_down);
    }

    /*! \brief Returns the direction vector corresponding to right movement.
     */

    public ArrayList<Integer> getRight (){
	ArrayList<Integer> vec_right =  new ArrayList<Integer>();
	vec_right.add (0);
	vec_right.add (0);
	vec_right.add (0);
	vec_right.add (1);
	return (vec_right);
    }

}
