import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {
    Scanner scan = new Scanner(System.in);

    public String promptText(String msg) {
        System.out.println(msg);
        String input = scan.nextLine();
        return input;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }


    public int promptNumeric(String msg) {
        System.out.println(msg);
        int input;
        try {
            input = scan.nextInt();
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Skriv et tal");
            input = promptNumeric(msg);
        }

        scan.nextLine();//scannerbug flush

        return input;
    }
}