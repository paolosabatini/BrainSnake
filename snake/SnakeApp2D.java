package ps;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SnakeApp2D extends JFrame {
    
    public SnakeApp2D() {

        initUI();
    }

    private void initUI() {

        add(new Board());

	setResizable(false);
	pack();

	setTitle("SnakeApp2D - Paolo Sabatini Prod.");
	setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            SnakeApp2D ex = new SnakeApp2D();
            ex.setVisible(true);
        });
    }
}
