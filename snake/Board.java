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
    protected int APPLE_X = 200;
    protected int APPLE_Y = 200;
    protected int GRID_SEG_SIZE_X = 2;
    protected int GRID_SEG_SIZE_Y = 2;
    
    protected int B_WIDTH = 500;
    protected int B_HEIGHT = 500;
    
    public Board () {
	initBoard ();
    }

    public void initBoard (){
	setFocusable(true);
        setBackground(Color.lightGray);

	setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

	apple = new Apple (APPLE_X, APPLE_Y);
	

    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

	drawObjects (g);

	Toolkit.getDefaultToolkit().sync();
    }


    public void drawObjects (Graphics g){

	apple.drawApple ((Graphics2D)g);
	
	
    }
}
