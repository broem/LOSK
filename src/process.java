import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class process {

    private ArrayList<String> processArray = new ArrayList();
    private String ID;
    private String calcTime;
    private int time;

    public process(File fileName) throws FileNotFoundException
    {

        Scanner in = new Scanner(fileName);
        ID = in.nextLine();
        setID(ID);
        if(in.next().equals("CALCULATE")){
            time = Integer.parseInt(in.nextLine().trim());
        }
    }

    public void setID(String name){
        processArray.add(name);
    }

    public void setCalc(String calc){
        processArray.add(calc);
    }

    public void setYeild(String yeild){
        processArray.add(yeild);
    }

    public void setIO(String IO){
        processArray.add(IO);
    }

    public void setOut(String out){
        processArray.add(out);
    }



}
