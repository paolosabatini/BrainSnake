package ps;

import java.awt.EventQueue;
import javax.swing.JFrame;

/*! \brief Main class of the application. 
 *         It contains the main() function.
 *
 *  The main function instanciates the class. The constructor initialises a new Board, containing most of the code and set the execution exitation, title etc..
 */

public class SnakeApp2D extends JFrame {

    private static String CONFIG_FILE = "share/CONFIG_MANUAL.json"; /*!< Configuration (json) to read to configure the running. */
    
    /*! \brief Constructor.
     *
     *  Constructor of the class. Initialise the configuratio file to read.
     */

    public SnakeApp2D(String conf_filename) {

	CONFIG_FILE = conf_filename;
        initUI();
    }
    
    /*! \brief Initialisation of the UI.
     *
     *  It creates a new Board, set its size, title and makes the code exit at closing the board.
     */

    private void initUI() {

        add(new Board(CONFIG_FILE));

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
		SnakeApp2D ex = (args.length != 0) ? new SnakeApp2D((String)args[0]) : new SnakeApp2D(CONFIG_FILE);
		ex.setVisible(true);
	    });
    }
}
