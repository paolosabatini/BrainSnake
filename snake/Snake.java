package ps;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Snake extends Sprite {

    
    private Color snakeColor = new Color (252, 252, 252);
    Grid grid;

    
    public Snake (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid; 
    }



    public void drawSnake (Graphics2D g2d){
	g2d.setColor (snakeColor);
	int round_h = (int) grid.getGridSizeY();
	int round_r = (int) 0.2*grid.getGridSizeY();

	g2d.fillRoundRect(x,y,
			  grid.getGridSizeX(),
			  grid.getGridSizeY(),
			  round_h,
			  round_r);
	
    }
}
