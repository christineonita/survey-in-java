package survey;

import java.io.Serializable;
import java.util.ArrayList;

public class Display implements Serializable {
    private static final long serialVersionUID = -2210274800574981255L;

    Display() {

    }

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

    public static void displayResponses(ArrayList<Question> questions, String[][] userAnswers) {
        int questionCnt = 0;
        for (String[] p : userAnswers) {
            for (String answer : p) {
                int responseCnt = 0;
                System.out.println("Question " + (questionCnt + 1) + ". " + questions.get(questionCnt).getPrompt()); // prints question number and prompt
                if (questions.get(questionCnt) instanceof Matching) {
                    for (int k = 0; k < ((Matching) questions.get(questionCnt)).takerFirstChoice.length; k++) {
                        displayTwoColumns("  " + ((Matching) questions.get(questionCnt)).takerFirstChoice[k], ((Matching) questions.get(questionCnt)).takerSecondChoice[k]);
                    }
                } else {
                    String lines[] = answer.split("\\r?\\n");
                    for (String line : lines) {
                        System.out.println("  " + line);
                        responseCnt++;
                    }
                }
                System.out.println();
                questionCnt++;
            }
        }
    }

    public static void displayTwoColumns(String left, String right) {
        System.out.printf("%-30s", left); // using printf to format the display of both columns
        System.out.printf(right + "\n");
    }

    public static void displayMatchingColumns(Question question) {
        for (int h = 0; h < Math.max(question.getNumOfFirstColumnItems(), question.getNumOfSecondColumnItems()); h++) {

            if (h + 1 > question.getNumOfFirstColumnItems()) {
                for (int f = h; f < question.getNumOfSecondColumnItems(); f++) {
                    Display.displayString("                              " + (f + 1) + ".) " + question.getSecondColumn()[f]);
                }
                break;
            } else if (h + 1 > question.getNumOfSecondColumnItems()) {
                for (int g = h; g < question.getNumOfFirstColumnItems(); g++) {
                    Display.displayString("   " + (g + 1) + ".) " + question.getFirstColumn()[g]);
                }
                break;
            } else {
                question.printTwoColumns("   " + (h + 1) + ".) " + question.getFirstColumn()[h], (h + 1) + ".) " + question.getSecondColumn()[h]);
            }
        }
    }
}
