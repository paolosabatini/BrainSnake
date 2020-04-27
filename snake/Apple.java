package ps;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Apple extends Sprite {

    Color appleColor = new Color (252, 40, 0);
    protected int size_x;
    protected int size_y;
    
    public Apple (int x, int y){
	super(x,y);
	initApple();

    }


    public void initApple (){

	size_x = GRID_SEG_SIZE_X;
	size_y = GRID_SEG_SIZE_Y;
	
	
    }


    public void drawApple (Graphics2D g2d){
	g2d.setColor (appleColor);
	int round_h = (int) 1*size_x;
	int round_r = (int) 0.2*size_x;
	g2d.fillRoundRect(x,y,size_x,size_y,round_h,round_r);
	
    }
}
