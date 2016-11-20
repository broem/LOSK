import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class GUI extends JFrame {

    private static JTextArea logArea;
    private static JTextArea processArea;
    private static JTextArea simulationInfoArea;
    private static String[] lastCommand;

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
        setBounds(100, 100, 820, 365);
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

        //Text area for general simulation info
        simulationInfoArea = new JTextArea();
        simulationInfoArea.setEditable(false);
        simulationInfoArea.setBounds(615, 5, 200, 335);
        simulationInfoArea.setBorder(border);

        simulationInfoArea.append("Memory Left: ?");

        //Text area for info on processes
        processArea = new JTextArea("Processes Data Here");
        processArea.setEditable(false);

        JScrollPane processAreaScrollPane = new JScrollPane(processArea);
        processAreaScrollPane.setBounds(310, 5, 300, 335);
        processAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        processAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        processAreaScrollPane.setBorder(border);

        getContentPane().add(inputField);
        getContentPane().add(logAreaScrollPane);
        getContentPane().add(processAreaScrollPane);
        getContentPane().add(simulationInfoArea);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastCommand = inputField.getText().split(" ");
                appendLogArea(inputField.getText());
                inputField.setText("");
            }
        });
    }

    public void appendLogArea(String string){
        logArea.append(string+"\n");
    }

    public void clearLogArea(){
        logArea.setText("");
    }

    public void appendDataArea(String string){
        processArea.append(string);
    }

    public void clearDataArea(){
        processArea.setText("");
    }

    public String[] getLastCommand(){
        return lastCommand;
    }


    public void onEnter(){
        
    }
}
