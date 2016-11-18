import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class GUI extends JFrame {

    private static JTextArea logArea;
    private static JTextArea dataArea;

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
        setBounds(100, 100, 625, 365);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //Text field for input
        JTextField inputField = new JTextField("Terminal Input Here");
        inputField.setEditable(true);
        inputField.setBounds(5, 5, 300, 25);
        inputField.setBorder(border);

        //Text area for log
        logArea = new JTextArea();
        logArea.setEditable(false);

        JScrollPane logAreaScrollPane = new JScrollPane(logArea);
        logAreaScrollPane.setBounds(5, 40, 300, 300);
        logAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        logAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        logAreaScrollPane.setBorder(border);

        //Text area for info on processes
        dataArea = new JTextArea("Processes Data Here");
        dataArea.setEditable(false);

        JScrollPane dataAreaScrollPane = new JScrollPane(dataArea);
        dataAreaScrollPane.setBounds(320, 5, 300, 335);
        dataAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        dataAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        dataAreaScrollPane.setBorder(border);

        getContentPane().add(inputField);
        getContentPane().add(logAreaScrollPane);
        getContentPane().add(dataAreaScrollPane);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.append(inputField.getText()+"\n");
                inputField.setText("");
            }
        });
    }

    public void appendLogArea(String string){
        logArea.append(string);
    }

    public void clearLogArea(){
        logArea.setText("");
    }

    public void appendDataArea(String string){
        dataArea.append(string);
    }

    public void clearDataArea(){
        dataArea.setText("");
    }

    public void onEnter(){
        
    }
}
