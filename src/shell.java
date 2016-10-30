import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.ScrollPane;

public class shell {

    private JFrame frmLosk;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                shell window = new shell();
                window.frmLosk.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        
        
    }

    /**
     * Create the application.
     */
    public shell() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmLosk = new JFrame();
        frmLosk.setTitle("LOSK");
        frmLosk.setForeground(Color.BLACK);
        frmLosk.setBackground(Color.BLACK);
        frmLosk.getContentPane().setBackground(Color.BLACK);
        frmLosk.setBounds(100, 100, 450, 300);
        frmLosk.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);
        frmLosk.getContentPane().setLayout(null);
        
        JTextArea txtpnOk = new JTextArea();
//        txtpnOk.setEditable(true);
        txtpnOk.setLineWrap(true);
        txtpnOk.setEditable(false);
        txtpnOk.setForeground(Color.GREEN);
        txtpnOk.setBackground(Color.BLACK);
        txtpnOk.setBounds(20, 11, 382, 239);
        txtpnOk.append("LOSK:");
        
        txtpnOk.setEditable(true);
        
        frmLosk.getContentPane().add(txtpnOk);
        
    }
    
    public void onEnter(){
        
    }
}
