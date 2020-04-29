package ps;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected int GRID_SEG_SIZE_X = 20;
    protected int GRID_SEG_SIZE_Y = 20;
    
    public Sprite(int x, int y) {
	this.x = x;
	this.y = y;
	visible = true;
    }      
    
    public void setX (int x) {this.x = x;}
    public void setY (int y) {this.y = y;}
    
    public int getX () {return this.x;}
    public int getY () {return this.y;}

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }


}
