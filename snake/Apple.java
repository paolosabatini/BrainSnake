package ps;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Apple extends Sprite {

    
    private Color appleColor = new Color (252, 40, 0);
    Grid grid;

    
    public Apple (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid; 
    }



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
