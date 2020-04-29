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
import java.lang.Math;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontMetrics;


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
    protected int GRID_N = 20;
    protected int GRID_WIDTH = B_WIDTH / GRID_N;
    protected int GRID_HEIGHT = B_HEIGHT / GRID_N;

    protected boolean inGame;
    protected int Score;
    protected int DELAY = 200;
    protected static boolean VERBOSE = false;
    
    public Board () {
	initBoard ();
    }

    public void initBoard (){

	addKeyListener(new TAdapter());

	setFocusable(true);
        setBackground(Color.lightGray);

	setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

	grid = new Grid (GRID_WIDTH, GRID_HEIGHT, B_WIDTH, B_HEIGHT);
	if (VERBOSE)
	    System.out.println("Position apple "+grid.getGridAtEdgeX (IAPPLE_X)+" "+grid.getGridAtEdgeY (IAPPLE_Y));
	apple = new Apple (grid.getGridAtEdgeX (IAPPLE_X),
			   grid.getGridAtEdgeY (IAPPLE_Y), grid);
	snake = new Snake (grid.getGridAtEdgeX (ISNAKE_X),
			   grid.getGridAtEdgeY (ISNAKE_Y), grid);
	
	inGame = true;
	Score = 0;
	timer = new Timer(DELAY, this);
        timer.start();
    }
    
    

    
    @Override
    public void actionPerformed(ActionEvent e) {

	
	if (!inGame) timer.stop();
	
    	if (VERBOSE)
	    System.out.println ("printato in action");

	updateSnake ();

	checkEating ();
	inGame = checkCollisions ();

	repaint();

	    
	snake.ALREADY_PRESSED=false;
    }

    public void updateSnake (){
	snake.move();
    }

    public void checkEating (){

	Rectangle snake_head = new Rectangle();
	snake_head.setRect (snake.x, snake.y, grid.getGridSizeX(), grid.getGridSizeY() );
	Rectangle ap_rect = new Rectangle();
	ap_rect.setRect (apple.x, apple.y, grid.getGridSizeX(), grid.getGridSizeY() );

       
	
	if (snake_head.intersects (ap_rect)){
	    
	    snake.addApple (new Apple (apple.x,apple.y,grid));
	    
	    if (VERBOSE) System.out.println ("==> APPLE EATEN!");
	    while (true){
		if (VERBOSE) System.out.println ("    > extract new apple");
		int posx = grid.getGridBinEdgeX ((int)(Math.random()*GRID_N));
		int posy = grid.getGridBinEdgeY ((int)(Math.random()*GRID_N));
		apple.setX ( posx );
		apple.setY ( posy );
		ap_rect.setRect (posx, posy, grid.getGridSizeX(), grid.getGridSizeY() );
		if (VERBOSE){
		    System.out.println ("    > posx "+posx+" y "+posy);
		    System.out.println ("    > intersect? "+snake.isIn( ap_rect ));
		}
		if ( !snake.isIn( ap_rect ) ) break;
	    }

	    Score += 1;
	}
    }

    public boolean checkCollisions (){

	
	// Collision against the snake
	Rectangle snake_head = new Rectangle();
	snake_head.setRect (snake.x, snake.y, grid.getGridSizeX(), grid.getGridSizeY() );
	if ( snake.headIsIn (snake_head) ) return false;

	// Collision against the border
	Rectangle border = grid.getBorders();
	if (!border.contains(snake_head)) return false;

	return true;	
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

	if (inGame){
	    drawObjects (g);

	    drawScore (g);
	}
	else {
	    drawObjects (g);

	    drawScore (g);

	    drawGameOver (g);
	}
	
	Toolkit.getDefaultToolkit().sync();
    }


    public void drawObjects (Graphics g){

	grid.drawBorders ((Graphics2D)g);

	apple.drawApple ((Graphics2D)g);

	snake.drawSnake ((Graphics2D)g);
		
	
    }

    public void drawScore (Graphics g){
	g.setColor(Color.BLACK);
        g.drawString("Score: " + Score, 5, 15);
    }

    private void drawGameOver(Graphics g) {
	
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
	
        g.setColor(Color.RED);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
		     B_HEIGHT / 2);
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

    public static boolean isVerbose() {return VERBOSE;}
}
