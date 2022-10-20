package survey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput implements Serializable {

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
                if (a < end || a > start) {
                    break;
                } else {
                    Display.displayString("Enter a number between " + (start + 1) + "and" + (end - 1));
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

        /*
        public static int getInt(int lower, int upper) {
		int rtn = 0;
		boolean num = false;
		Scanner scan = new Scanner(System.in);

		while (!num) {
			try {
				rtn = scan.nextInt();
				if (rtn > lower || rtn < upper) {
					num = true;
				} else {
					Display.display("Please enter number above " + lower);
					scan.nextLine();
				}
			} catch (InputMismatchException e) {
				Display.display("Please enter an integer");
				scan.nextLine();
			}
		}
		scan.nextLine();
		return rtn;
	}
         */
        return a;
    }
}
