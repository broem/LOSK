import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

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

        DefaultCaret caret = (DefaultCaret) logArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

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


                switch(lastCommand[0]){
                    case "LOAD":
                        appendLogArea("===LOAD Results===");
                        try {
                            File jobFile = new File(lastCommand[1]);
                            if(jobFile.exists()) {
                                String name = lastCommand[1];
                                appendLogArea("File exists!");
                                JobReader.get().jobReaderIn(jobFile);
                                //add Process to a queue somewhere in here?
                                //Pass inputSeparated[1] which contains file name
                            } else {
                                appendLogArea("File doesn't exist!");
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException exception){
                            appendLogArea("File name must be included...");
                        }
                        appendLogArea("-----");

                        break;
                    case "EXE":
                        if(lastCommand.length == 2) {
                            int tempInt = Integer.valueOf(lastCommand[1]);
                            if(tempInt>0) {
                                LOSK.setCycle(tempInt);
                                CycleClock.get().setCycleStopTime(LOSK.getCycle());
                                CycleClock.get().setIsRunning(true);
                                appendLogArea("===Executing===");
                            }
                            else{
                                appendLogArea("Please enter non-negative numbers!");
                                appendLogArea("===Execution Cancelled===");
                            }
                        }

                        /*
                        if(lastCommand.length == 2) {
                            exeRunForLength(Integer.parseInt(lastCommand[1]));
                        }
                        appendLogArea("");
                        ProcessScheduler.get().scheduleExe();
                        // add runnable in here for the step portion, or if solo then just run!
                        */
                        break;
                    case "PROC":
                        appendLogArea("===PROC Results===");
                        appendLogArea(Clock.get().getClock() + " Clock time");
                        appendLogArea(ProcessScheduler.get().processesCurrentlyWaiting());
                        appendLogArea(ProcessScheduler.get().processesInNew());
                        appendLogArea(ProcessScheduler.get().processRunning());
                        appendLogArea("Cycle time: " + CycleClock.get().getCycleTime());
                        appendLogArea("-----");
                        break;
                    case "MEM":
                        //Shows current Memory usage
                        appendLogArea("===MEM Results===");
                        appendLogArea(Integer.toString(Memory.get().getMemoryLeft()));
                        appendLogArea("-----");
                        break;
                    case "EXIT":
                        endLOSK();
                        break;
                    case "RESET":
                        //All unfinished processes are terminated and Clock set to 0
                        clearLogArea();
                        Reset.get().resetAll();
                        appendLogArea("===\nSimulation Reset\n===");
                        break;
                    case "HELP":
                        appendLogArea("===HELP Results===");
                        appendLogArea("List of commands:");
                        appendLogArea("-LOAD + FileName.txt,\n-EXE + FileName.txt,\n-MEM,\n-EXIT,\n-RESET");
                        appendLogArea("-----");
                        break;
                    default: appendLogArea("Please enter valid command...");
                        break;

                }
            }
        });
    }

    public void endLOSK() {
        appendLogArea("Exiting LOSK...");
        System.exit(0);
    }

//    public static void exeRunForLength(int cycles){
//        java.util.Timer timer = new java.util.Timer();
//        timer.scheduleAtFixedRate(CycleClock.get(), 1, 1000);
//
//        if(CycleClock.get().getCycleTime() == cycles){
//            timer.cancel();
//        }
//    }

    public void appendLogArea(String string){
        logArea.append(string+"\n");
    }

    public void clearLogArea(){
        logArea.setText("");
    }

    public void appendDataArea(String string){
        processArea.append(string+"\n");
    }

    public void clearDataArea(){
        processArea.setText("");
    }

    public void appendSimulationInfoArea(String string) {
        simulationInfoArea.append(string+"\n");
    }

    public void clearSimulationInfoArea() {
        simulationInfoArea.setText("");
    }

    public String[] getLastCommand(){
        return lastCommand;
    }


    public void onEnter(){
        
    }
}
