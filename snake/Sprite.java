package ps;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/*! \brief Base class for any sprite implementation.
 *
 */

public class Sprite {

    protected int x; /*!< Horizontal position.*/
    protected int y; /*!< Vertical position.*/
    protected int width; /*!< Width of the sprite.*/
    protected int height; /*!< Height of the sprite.*/
    protected boolean visible; /*!< Flag to set it visible or not on the board (NOT USED).*/
    protected int GRID_SEG_SIZE_X = 20; /*!< Grid segmentation horizontal size (NOT USED).*/
    protected int GRID_SEG_SIZE_Y = 20; /*!< Grid segmentation horizontal size (NOT USED).*/

    /*! \brief Constructor of the class.
     */
    
    public Sprite(int x, int y) {
	this.x = x;
	this.y = y;
	visible = true;
    }      

    /*! \brief Set horizontal position.
     */

    public void setX (int x) {this.x = x;}

    /*! \brief Set vertical position.
     */

    public void setY (int y) {this.y = y;}
    
    /*! \brief Returns the horizontal position.
     */

    public int getX () {return this.x;}

    /*! \brief Returns the vertical position.
     */

    public int getY () {return this.y;}

    /*! \brief Returns whether the sprite is visible.
     */

    public boolean isVisible() {
        return visible;
    }

    /*! \brief Set whether the sprite is visible.
     */
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }


}
