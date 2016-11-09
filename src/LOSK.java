import java.util.Scanner;

public class LOSK {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        cpu ourCpu = new cpu();
        System.out.print(">");
        //ourCpu.advanceClock(2);
        input userType = new input();

        Scanner in = new Scanner(System.in);
        String input = in.next();
        input.trim();
        if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))
        {
            System.out.println("exiting LOSK...");
            System.exit(0);
        }

        userType.inputRead(input);


    }

    void initialize(){
        cpu ourCpu = new cpu();
    }


}
