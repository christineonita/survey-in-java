package survey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput implements Serializable {

    private static final long serialVersionUID = 4590960300204883310L;

    protected static int getInt() {
        int input;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                input = scanner.nextInt();
                //repeat = false;
                break;
            } catch (InputMismatchException e) {
                Display.displayString("Please enter an integer value");
                //repeat = true;
                //continue;
            }
        }
        return input;
    }

    protected static String getString() {
        String input;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return input;
    }

    public static int getOption(int start, int end) {
        int a;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                a = scanner.nextInt();
                //if (a < end || a > start) {
                if (a < end && a > start) {
                    break;
                } else {
                    Display.displayString("Enter a number from " + (start + 1) + " to " + (end - 1));
                    //Display.displayString("Enter a number between " + (start + 1) + " and " + (end - 1));
                    scanner.nextLine();
                }
                /*if(!(a < end || a > start)){
                    Display.displayString("Enter a number between " + (start + 1) + "and"+ (end - 1));
                    scanner.nextLine();
                }*/
            } catch (InputMismatchException e) {
                //throw new RuntimeException(e);
                Display.displayString("Please enter an integer ");
                scanner.nextLine();
            }
        }
        return a;
    }

    public static int getMultipleOptions(int start, int end) {
        int a = 0;
        boolean i = true;

        Scanner scanner = new Scanner(System.in);
        while (i) {

            a = scanner.nextInt();
            if (a < end && a > start) {
                break;
            } else {
                Display.displayString("Enter a number from " + (start + 1) + " to " + (end - 1));
                scanner.nextLine();
            }

        }
        return a;
    }
}
