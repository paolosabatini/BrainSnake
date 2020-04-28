package ps;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Snake extends Sprite {

    
    private Color snakeColor = new Color (252, 252, 252);
    private List<SnakeBlock> blocks;
    Grid grid;

    
    public Snake (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid;
	blocks = new ArrayList<>();
	blocks.add ( new SnakeBlock (x,y,grid) );
	blocks.add (new SnakeBlock (x,y-grid.getGridSizeY(),grid) );

    }



    public void drawSnake (Graphics2D g2d){

	for (SnakeBlock b : blocks ){
	    
	    b.drawSnakeBlock(g2d);

	}
	
    }
}
