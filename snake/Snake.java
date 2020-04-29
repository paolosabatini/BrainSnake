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
    private ArrayList<Integer> velocity; // [up, left, down, right]
    Grid grid;
    public boolean ALREADY_PRESSED = false;
    
    public Snake (int x, int y, Grid grid){
	super(x,y);
	this.grid = grid;
	velocity =  grid.getDown ();
	blocks = new ArrayList<>();
	blocks.add ( new SnakeBlock (x,y,grid) );
	blocks.add (new SnakeBlock (x,y-grid.getGridSizeY(),grid) );

    }

    public void move (){


	int dx = ( - velocity.get(1) + velocity.get(3) ) * grid.getGridSizeX();
	int dy = ( - velocity.get(0) + velocity.get(2) ) * grid.getGridSizeY();
	
	List <SnakeBlock> blocks_backup = blocks; 
	for (int i = 1; i < blocks_backup.size(); i++){
	    blocks.get (i).setX ( blocks_backup.get (i-1).getX() );
	    blocks.get (i).setY ( blocks_backup.get (i-1).getY() );
	}
	
	System.out.println ("> Head pre-move x "+this.x+" y "+this.y);
	this.x += dx;
	this.y += dy;
	blocks.get (0).setX (this.x);
	blocks.get (0).setY (this.y);
	System.out.println ("> Head post-move x "+this.x+" y "+this.y);


	System.out.println ("> dx "+dx+" dy "+dy);
	for (SnakeBlock b : blocks){
	    System.out.println ("\t > x "+b.getX()+" y "+b.getY());
	}

    }

    public void keyPressed(KeyEvent e) {

	if (this.ALREADY_PRESSED)
	    return;
	
        int key = e.getKeyCode();

	System.out.println ("KEY PRESSED "+key);
	System.out.println ("Velocity "+velocity);
	
        if (key == KeyEvent.VK_LEFT) {
	    velocity = ( !velocity.equals ( grid.getRight() ) ) ? grid.getLeft() : velocity ;
        }
	
        if (key == KeyEvent.VK_RIGHT) {
	    velocity = ( !velocity.equals ( grid.getLeft() ) ) ? grid.getRight() : velocity ;
        }
	
        if (key == KeyEvent.VK_UP) {
	    velocity = ( !velocity.equals ( grid.getDown() ) ) ? grid.getUp() : velocity ;
        }
	
        if (key == KeyEvent.VK_DOWN) {
	    velocity = ( !velocity.equals ( grid.getUp() ) ) ? grid.getDown() : velocity ;
        }

	System.out.println (" ===> "+velocity);
	this.ALREADY_PRESSED = true;
    }

    public void keyReleased(KeyEvent e) {

	// If nothing is pressed the velocity is as before
	
        // int key = e.getKeyCode();

        // if (key == KeyEvent.VK_LEFT) {
        //     dx = 0;
        // }

        // if (key == KeyEvent.VK_RIGHT) {
        //     dx = 0;
        // }

        // if (key == KeyEvent.VK_UP) {
        //     dy = 0;
        // }

        // if (key == KeyEvent.VK_DOWN) {
        //     dy = 0;
        // }
    }
    
    public void drawSnake (Graphics2D g2d){

	for (SnakeBlock b : blocks ){
	    
	    b.drawSnakeBlock(g2d);

	}
	
    }
}
