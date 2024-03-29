package survey;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput implements Serializable {

    private static final long serialVersionUID = 4590960300204883310L;

    UserInput() {
    }

    protected static int getInt() {
        int input;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                Display.displayString("Please enter an integer value");
            }
        }
        return input;
    }

    protected static String getString() {
        String input;

        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();

        return input;
    }

    public static int getOption(int start, int end) {
        int a;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                a = scanner.nextInt();
                if (a < end && a > start) {
                    break;
                } else {
                    Display.displayString("Enter a number from " + (start + 1) + " to " + (end - 1));
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                Display.displayString("Please enter an integer ");
                scanner.nextLine();
            }
        }
        return a;
    }

    public static int getMultipleOptions(int start, int end) {
        int a = 0;
        String x = "";

        Scanner scanner = new Scanner(System.in);
        x = scanner.nextLine();
        while (!x.equalsIgnoreCase("")) {

            try {
                a = Integer.valueOf(x);
                if (a < end && a > start) {
                    break;
                } else {
                    Display.displayString("Enter a number from " + (start + 1) + " to " + (end - 1));
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                Display.displayString("Please enter an integer");
                scanner.nextLine();
            }

        }
        return a;
    }
}
