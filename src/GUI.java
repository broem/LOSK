import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.ScrollPane;

public class GUI extends JFrame {

    /**
     * Create the application.
     */
    public GUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("LOSK");
        setForeground(Color.BLACK);
        setBackground(Color.BLACK);
        getContentPane().setBackground(Color.BLACK);
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);
        getContentPane().setLayout(null);
        
        JTextArea txtpnOk = new JTextArea();
//        txtpnOk.setEditable(true);
        txtpnOk.setLineWrap(true);
        txtpnOk.setEditable(false);
        txtpnOk.setForeground(Color.GREEN);
        txtpnOk.setBackground(Color.BLACK);
        txtpnOk.setBounds(20, 11, 382, 239);
        txtpnOk.append("LOSK:");
        
        txtpnOk.setEditable(true);
        
        getContentPane().add(txtpnOk);
        
    }
    
    public void onEnter(){
        
    }
}
