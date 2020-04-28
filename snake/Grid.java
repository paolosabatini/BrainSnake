package ps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Grid {

    
    private Color borderColor = new Color (100, 177, 0);
    private int GRID_SIZE_X = 20;
    private int GRID_SIZE_Y = 20;
    private int WINDOW_SIZE_X = 200;
    private int WINDOW_SIZE_Y = 200;

    public Grid (int seg_x, int seg_y, int window_x, int window_y){

	GRID_SIZE_X = seg_x;
	GRID_SIZE_Y = seg_y;
	WINDOW_SIZE_X = window_x;
	WINDOW_SIZE_Y = window_y;
    }

    public int getGridSizeX () {return GRID_SIZE_X;}
    public int getGridSizeY () {return GRID_SIZE_Y;}
    public int getWindowSizeX () {return WINDOW_SIZE_X;}
    public int getWindowSizeY () {return WINDOW_SIZE_Y;}

    public void setGridSizeX (int val) {GRID_SIZE_X = val;}
    public void setGridSizeY (int val) {GRID_SIZE_Y = val;}
    public void setWindowSizeX (int val) {WINDOW_SIZE_X = val;}
    public void setWindowSizeY (int val) {WINDOW_SIZE_Y = val;}

    public void drawBorders (Graphics2D g2d){

	g2d.setColor (borderColor);
	g2d.setStroke(new BasicStroke(5));
	g2d.drawRect(0,0,
		     WINDOW_SIZE_X,
		     WINDOW_SIZE_Y);

    }

    public int getGridBinX (int x){
	return x / GRID_SIZE_X;
    }

    public int getGridBinY (int y){
	return y / GRID_SIZE_Y;
    }
    
    public int getGridBinCenterX (int x){
	return x + (int)(GRID_SIZE_X/2.);
    }

    public int getGridBinCenterY (int y){
	return y + (int)(GRID_SIZE_Y/2.);
    }

    public int getGridCenteredX (int x){
	return getGridBinX(x)*GRID_SIZE_X + (int)(GRID_SIZE_X/2.);
    }

    public int getGridCenteredY (int y){
	return getGridBinY (y)*GRID_SIZE_X+ (int)(GRID_SIZE_Y/2.);
    }

    
    public int getGridBinEdgeX (int x){
	return x*GRID_SIZE_X;
    }

    public int getGridBinEdgeY (int y){
	return y*GRID_SIZE_Y;
    }

    public int getGridAtEdgeX (int x){
	return getGridBinX(x)*GRID_SIZE_X;
    }

    public int getGridAtEdgeY (int y){
	return getGridBinY(y)*GRID_SIZE_Y;
    }


}
