package ps;

import java.awt.EventQueue;
import javax.swing.JFrame;

/*! \brief Main class of the application. 
 *         It contains the main() function.
 *
 *  The main function instanciates the class. The constructor initialises a new Board, containing most of the code and set the execution exitation, title etc..
 */

public class SnakeApp2D extends JFrame {


    /*! \brief Constructor.
     *
     *  Constructor of the class.
     */

    public SnakeApp2D() {

        initUI();
    }
    
    /*! \brief Initialisation of the UI.
     *
     *  It creates a new Board, set its size, title and makes the code exit at closing the board.
     */

    private void initUI() {

        add(new Board());

	setResizable(false);
	pack();

	setTitle("SnakeApp2D - Paolo Sabatini Prod.");
	setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    
        
    /*! \brief Main function of the application.
     *
     *  It invokes the main function and the queue of events ot be exectued. It creates the SnakeApp2D.
     */

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            SnakeApp2D ex = new SnakeApp2D();
            ex.setVisible(true);
        });
    }
}
