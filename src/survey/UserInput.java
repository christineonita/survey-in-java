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
}
