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
            System.out.println("Question " + (questionCnt + 1) + ". " + questions.get(questionCnt).getPrompt()); // prints question number and prompt
            if (questions.get(questionCnt) instanceof Matching) {
                for (int k = 0; k < ((Matching) questions.get(questionCnt)).takerFirstChoice.length; k++) {
                    //System.out.printf("%-30s", ((Matching) questions.get(questionCnt)).takerFirstChoice[k]);
                    //System.out.printf(((Matching) questions.get(questionCnt)).takerSecondchoice[k] + "\n");
                    displayTwoColumns("  " + ((Matching) questions.get(questionCnt)).takerFirstChoice[k], ((Matching) questions.get(questionCnt)).takerSecondChoice[k]);
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

    public static void displayMatchingColumns(int h, Question question) {
        for (h = 0; h < Math.max(question.getNumOfFirstColumnItems(), question.getNumOfSecondColumnItems()); h++) {

            if (h + 1 > question.getNumOfFirstColumnItems()) {
                for (int f = h; f < question.getNumOfSecondColumnItems(); f++) {
                    //System.out.println("extra 2nd --- " + this.questions.get(x).getSecondColumn()[f]);
                    Display.displayString("                              " + (f + 1) + ".) " + question.getSecondColumn()[f]);
                }
                break;
            } else if (h + 1 > question.getNumOfSecondColumnItems()) {
                for (int g = h; g < question.getNumOfFirstColumnItems(); g++) {
                    //System.out.println("extra 1st --- " + this.questions.get(x).getFirstColumn()[g]);
                    Display.displayString("   " + (g + 1) + ".) " + question.getFirstColumn()[g]);
                }
                break;
            } else {
                question.printTwoColumns("   " + (h + 1) + ".) " + question.getFirstColumn()[h], (h + 1) + ".) " + question.getSecondColumn()[h]);
            }
            //System.out.printf("   " + (h + 1) + ". " + this.questions.get(x).getFirstColumn()[h] + "\t" + (h + 1) + ". " + this.questions.get(x).getSecondColumn()[h]);
        }
    }
}
