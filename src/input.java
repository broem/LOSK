/*
 * Gets the user input from consol to work
 */
public class input {

    public void inputRead(String input) throws IllegalArgumentException{

        switch(input){
            case "LOAD": System.out.println("load");
                break;
            case "EXE": System.out.println("exe");
                break;
            case "PROC":
                break;
            case "MEM":
                break;
            case "STOP":
                break;
            case "EXIT":
                break;
            case "RESET":
                break;
            default: System.out.println("please enter valid command...");
                break;

        }



    }


}
