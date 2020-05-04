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
import java.util.Random;
import java.lang.Math;

/*! \brief Class containing all the model information.
 *
 * It contains all the instruction how to move the snake. At the moment it contains only the benchmark model.
 */

public class Model {

    /*! \brief Benchmark policy for snake move
     *
     * Snake tries to point to the apple position every time.
     * 
     * @param x Snake head horizontal position.
     * @param y Snake head vertical position.
     * @param blocks List of all the SnakeBlocks. 
     * @param v Direction of the snake. 
     * @param a Apple position.
     */
    
    public static ArrayList<Integer> moveBenchmark (int x,
						    int y,
						    List<SnakeBlock> blocks,
						    ArrayList<Integer> v,
						    Apple a,
						    Grid grid) {

	ArrayList<Integer> output = new ArrayList<Integer>();
	output.add (v.get(0));
	output.add (v.get(1));
	output.add (v.get(2));
	output.add (v.get(3));

	int dx_apple_head = a.getX() - x;
	int dy_apple_head = a.getY() - y; 

	boolean isGoodVelocity = false;

	while (!isGoodVelocity) {
	
	    if ( dx_apple_head > 0 && dy_apple_head > 0 ){
		if (Math.random()>0.5) output = grid.getRight();
		else output = grid.getDown();
	    } else if ( dx_apple_head < 0 && dy_apple_head > 0 ) {
		if (Math.random()>0.5) output = grid.getLeft();
	    else output = grid.getDown();
	    } else if ( dx_apple_head > 0 && dy_apple_head < 0 ) {
		if (Math.random()>0.5) output = grid.getRight();
		else output = grid.getUp();
	    } else if ( dx_apple_head < 0 && dy_apple_head < 0 ) {
		if (Math.random()>0.5) output = grid.getLeft();
		else output = grid.getUp();
	    } else if ( dx_apple_head == 0 ) {
		if (dy_apple_head > 0 ) output = grid.getDown();
		else output = grid.getUp();
	    } else if ( dy_apple_head == 0 ) {
	    if (dx_apple_head > 0 ) output = grid.getRight();
	    else output = grid.getLeft();
	    } 
	    
	    if ( ( output.equals(grid.getRight()) && !v.equals(grid.getLeft()) ) ||
	    	 ( output.equals(grid.getLeft()) && !v.equals(grid.getRight()) ) ||
	    	 ( output.equals(grid.getUp()) && !v.equals(grid.getDown()) ) ||
	    	 ( output.equals(grid.getDown()) && !v.equals(grid.getUp()) ) 
	    	 ) isGoodVelocity = true;
	}
	
	return output;
    }

}
