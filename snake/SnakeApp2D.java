package ps;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SnakeApp2D extends JFrame {
    
    public SnakeApp2D() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setSize(400,400);

        setTitle("SnakeApp2D - Paolo Sabatini Prod.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            SnakeApp2D ex = new SnakeApp2D();
            ex.setVisible(true);
        });
    }
}
