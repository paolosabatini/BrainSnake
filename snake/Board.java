package ps;
    
// import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
// import java.awt.RenderingHints;
// import java.awt.geom.AffineTransform;
// import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import java.awt.Toolkit;

public class Board extends JPanel {

    private Apple apple;
    private Grid grid;
    protected int IAPPLE_X = 200;
    protected int IAPPLE_Y = 200;
        
    protected int B_WIDTH = 500;
    protected int B_HEIGHT = 500;
    protected int GRID_WIDTH = B_WIDTH / 20;
    protected int GRID_HEIGHT = B_HEIGHT / 20;

    public Board () {
	initBoard ();
    }

    public void initBoard (){
	setFocusable(true);
        setBackground(Color.lightGray);

	setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

	grid = new Grid (GRID_WIDTH, GRID_HEIGHT, B_WIDTH, B_HEIGHT);
	apple = new Apple (IAPPLE_X, IAPPLE_Y, grid);
	

    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

	drawObjects (g);

	Toolkit.getDefaultToolkit().sync();
    }


    public void drawObjects (Graphics g){

	grid.drawBorders ((Graphics2D)g);

	apple.drawApple ((Graphics2D)g);
	
	
    }
}
