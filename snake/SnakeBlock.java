package ps;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class SnakeBlock extends Sprite {

    
    private Color snakeColor = new Color (252, 252, 252);
    Grid grid;
    
    
    public SnakeBlock (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid;
	
    }



    public void drawSnakeBlock (Graphics2D g2d){

	int round_h = (int) grid.getGridSizeY();
	int round_r = (int) 0.2*grid.getGridSizeY();

	g2d.setColor (Color.BLACK);
	g2d.setStroke(new BasicStroke(1));
	g2d.drawRect(x,y,
		     grid.getGridSizeX(),
		     grid.getGridSizeY()
		     );
	g2d.setColor (snakeColor);
	g2d.fillRect(x,y,
		     grid.getGridSizeX(),
		     grid.getGridSizeY()
		     );
    }
}
