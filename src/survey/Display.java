package survey;

import java.io.Serializable;
import java.util.ArrayList;

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

    public static void displayResponses(ArrayList<Question> questions, String[] userAnswers) {
        int questionCnt = 0;
        for (String answer : userAnswers) {
            int responseCnt = 0;
            //System.out.print((questionCnt + 1) + ". "); - prints just question number
            System.out.println((questionCnt + 1) + ". " + questions.get(questionCnt).getPrompt()); // prints question number and prompt
            if (questions.get(questionCnt) instanceof Matching) {
                for (int k = 0; k < ((Matching) questions.get(questionCnt)).takerFirstChoice.length; k++) {
                    //System.out.printf("%-30s", ((Matching) questions.get(questionCnt)).takerFirstChoice[k]);
                    //System.out.printf(((Matching) questions.get(questionCnt)).takerSecondchoice[k] + "\n");
                    displayTwoColumns("  " + ((Matching) questions.get(questionCnt)).takerFirstChoice[k], ((Matching) questions.get(questionCnt)).takerSecondchoice[k]);
                }
            } else {
                String lines[] = answer.split("\\r?\\n");
                for (String line : lines) {
                    //if (responseCnt == 0) {
                    //System.out.println(line);
                    //} else {
                    System.out.println("  " + line);
                    //}
                    responseCnt++;
                }
            }
            System.out.println();
            questionCnt++;
        }
    }

    public static void displayTwoColumns(String left, String right) {
        System.out.printf("%-30s", left); // using printf to format the display of both columns
        System.out.printf(right + "\n");
    }
}
