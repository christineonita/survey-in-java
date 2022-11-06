package survey;

import java.io.Serializable;

public class Display implements Serializable {
    private static final long serialVersionUID = -2210274800574981255L;

    public static void displayStringArray(String[] stringArray) {

        int option;
        for (int i = 0; i < stringArray.length; i++) {
            option = i + 1;
            System.out.println(("    " + option + ". " + stringArray[i]));
        }

    }

    public static void displayString(String string) {
        System.out.println(string);
    }

    public static void displayResponses(String[] userAnswers) {
        int questionCnt = 0;
        for (String answer : userAnswers) {
            int responseCnt = 0;
            System.out.print((questionCnt + 1) + ". ");
            String lines[] = answer.split("\\r?\\n");
            for (String line : lines) {
                if (responseCnt == 0) {
                    System.out.println(line);
                } else {
                    System.out.println("   " + line);
                }
                responseCnt++;
            }
            System.out.println();
            questionCnt++;
        }
    }
}
