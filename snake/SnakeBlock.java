package ps;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class SnakeBlock extends Sprite {

    
    private Color snakeColor = new Color (252, 252, 252);
    Grid grid;
    
    
    public SnakeBlock (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid;
	
    }



    public void drawSnakeBlock (Graphics2D g2d){
	g2d.setColor (snakeColor);
	int round_h = (int) grid.getGridSizeY();
	int round_r = (int) 0.2*grid.getGridSizeY();

	g2d.fillRoundRect(x,y,
			  grid.getGridSizeX(),
			  grid.getGridSizeY(),
			  round_h,
			  round_r);
    }

    public void setX (int x) {this.x = x;}
    public void setY (int y) {this.y = y;}
    
    public int getX () {return this.x;}
    public int getY () {return this.y;}
}
