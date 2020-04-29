package ps;
    
import java.awt.BasicStroke;
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
    protected int TOTAL_WIDTH = (int)(400*1.25);
    protected int GRID_N = 20;
    protected int GRID_WIDTH = B_WIDTH / GRID_N;
    protected int GRID_HEIGHT = B_HEIGHT / GRID_N;

    protected boolean inGame;
    protected int Score;
    protected int DELAY = 150;
    protected static boolean VERBOSE = false;
    
    public Board () {
	initBoard ();
    }

    public void initBoard (){

	addKeyListener(new TAdapter());

	setFocusable(true);
        setBackground(Color.lightGray);

	setPreferredSize(new Dimension(TOTAL_WIDTH, B_HEIGHT));

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

	
	if (VERBOSE)
	    System.out.println ("printed in action");

	updateSnake ();

	checkEating ();
	inGame = checkCollisions ();

	repaint();

	if (!inGame) timer.stop();
	    
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

	    drawScore ((Graphics2D)g);
	}
	else {
	    drawObjects (g);

	    drawScore ((Graphics2D)g);

	    drawGameOver (g);
	}
	
	Toolkit.getDefaultToolkit().sync();
    }


    public void drawObjects (Graphics g){

	grid.drawBorders ((Graphics2D)g);

	apple.drawApple ((Graphics2D)g);

	snake.drawSnake ((Graphics2D)g);
		
	
    }

    public void drawScore (Graphics2D g2d){
	// g.setColor(Color.BLACK);
        // g.drawString("Score: " + Score, 5, 15);
	
	double pad_offset = 0.02;
	double pad_height_fraction = 0.4;
	g2d.setColor (Color.darkGray);
	g2d.setStroke(new BasicStroke(2));
	g2d.drawRect ((int) (B_WIDTH*(1+pad_offset) ) ,
		      (int) (B_HEIGHT*(0.5*pad_offset)) ,
		      (int) ((TOTAL_WIDTH-B_WIDTH)-1.5*pad_offset*B_WIDTH),
		      (int) (B_HEIGHT*(pad_height_fraction)) );
	
	g2d.setColor(Color.BLACK);
        Font normal = new Font("Helvetica", Font.BOLD, 14);
	g2d.setFont (normal);
        g2d.drawString("Score",
		       (int) (B_WIDTH+0.25*(TOTAL_WIDTH-B_WIDTH)),
		       (int) (2.5*B_HEIGHT*pad_offset) );

	
	g2d.setColor(Color.RED);
        Font large = new Font("Helvetica", Font.BOLD, 25);
	g2d.setFont (large);
        g2d.drawString(""+(Score),
		       (int) (B_WIDTH+0.4*(TOTAL_WIDTH-B_WIDTH)),
		       (int) (0.5*B_HEIGHT*(pad_height_fraction)+ B_HEIGHT*(0.5*pad_offset) ) );


    }

    private void drawGameOver(Graphics g) {
	
        String msg = "GAME OVER";
        Font small = new Font("Helvetica", Font.BOLD, 50);
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
