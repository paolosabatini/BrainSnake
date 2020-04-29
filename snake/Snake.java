package ps;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.stream.Collectors;

public class Snake extends Sprite {

    private Color snakeColor = new Color (252, 252, 252);
    private List<SnakeBlock> blocks;
    private List<Apple> apples;
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
	apples = new ArrayList<> ();
    }

    public void move (){


	int dx = ( - velocity.get(1) + velocity.get(3) ) * grid.getGridSizeX();
	int dy = ( - velocity.get(0) + velocity.get(2) ) * grid.getGridSizeY();
	
	List <SnakeBlock> blocks_backup = new ArrayList<>();
	for (SnakeBlock b: blocks){
	    blocks_backup . add (new SnakeBlock (b.getX(),b.getY(),grid));
	}
	int x_last = blocks_backup.get (blocks_backup.size()-1).getX();
	int y_last = blocks_backup.get (blocks_backup.size()-1).getY();
	System.out.println ("> Moving the snake blocks:");
	for (int i = 1; i < blocks_backup.size(); i++){
	    System.out.println ("  > from "+blocks.get (i).getX()+" -> "+blocks_backup.get (i-1).getX()+" , "+blocks.get (i).getY()+" -> "+blocks_backup.get (i-1).getY());
	    blocks.get (i).setX ( blocks_backup.get (i-1).getX() );
	    blocks.get (i).setY ( blocks_backup.get (i-1).getY() );
	}

	System.out.println ("> Check if snake has to be extended");
	int index_remove = -1;
	int apple_counter=0;
	for (Apple a : apples){
	    System.out.println ("  apple x "+a.getX()+" y "+a.getY()+" last-block x "+x_last+" y "+y_last);
	    if ((a.getX()==x_last) &&
		(a.getY()==y_last)){
		System.out.println ("  > adding block x "+a.getX()+" y "+a.getY());
		index_remove = apple_counter;
		this.blocks.add (new SnakeBlock (a.getX(), a.getY(),grid));
		break;
	    }
	    apple_counter+=1;
	}
	if (index_remove>=0) apples.remove (index_remove);
	
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

    }
    
    public void drawSnake (Graphics2D g2d){

	for (SnakeBlock b : blocks ){
	    
	    b.drawSnakeBlock(g2d);

	}
	
    }

    public boolean isIn (Rectangle r){

	boolean isin = false;
	for (SnakeBlock b : blocks){
	    Rectangle b_r = new Rectangle();
	    b_r.setRect(b.getX (), b.getY(), grid.getGridSizeX(), grid.getGridSizeY());
	    if (b_r.intersects (r)){
		isin = true;
		break;
	    }
	}
	return isin;
    }

    
    public boolean headIsIn (Rectangle r){

	boolean isin = false;
	for (int i = 1; i<blocks.size();i++){
	    Rectangle b_r = new Rectangle();
	    b_r.setRect(blocks.get(i).getX (), blocks.get(i).getY(),
			grid.getGridSizeX(), grid.getGridSizeY());
	    if (b_r.intersects (r)){
		isin = true;
		break;
	    }
	}
	return isin;
    }

    public void addApple (Apple a){
	this.apples.add (a);
    }


}
