package ps;
    
// import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Snake snake;
    private Apple apple;
    private Grid grid;
    private Timer timer;
    
    protected int IAPPLE_X = 200;
    protected int IAPPLE_Y = 200;
    protected int ISNAKE_X = 300;
    protected int ISNAKE_Y = 200;
        
    protected int B_WIDTH = 400;
    protected int B_HEIGHT = 400;
    protected int GRID_WIDTH = B_WIDTH / 20;
    protected int GRID_HEIGHT = B_HEIGHT / 20;

    protected int DELAY = 300;
    
    public Board () {
	initBoard ();
    }

    public void initBoard (){

	addKeyListener(new TAdapter());

	setFocusable(true);
        setBackground(Color.lightGray);

	setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

	grid = new Grid (GRID_WIDTH, GRID_HEIGHT, B_WIDTH, B_HEIGHT);
	System.out.println("Position apple "+grid.getGridAtEdgeX (IAPPLE_X)+" "+grid.getGridAtEdgeY (IAPPLE_Y));
	apple = new Apple (grid.getGridAtEdgeX (IAPPLE_X),
			   grid.getGridAtEdgeY (IAPPLE_Y), grid);
	snake = new Snake (grid.getGridAtEdgeX (ISNAKE_X),
			   grid.getGridAtEdgeY (ISNAKE_Y), grid);
	

	timer = new Timer(DELAY, this);
        timer.start();
    }
    
    

    
    @Override
    public void actionPerformed(ActionEvent e) {

    	System.out.println ("printato in action");
	updateSnake ();


	repaint();
	snake.ALREADY_PRESSED=false;
    }

    public void updateSnake (){
	snake.move();
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

	snake.drawSnake ((Graphics2D)g);
		
	
    }




    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
	    snake.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            snake.keyPressed(e);
        }
    }


}
