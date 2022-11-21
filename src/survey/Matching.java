package survey;

import java.io.Serializable;
import java.util.HashMap;

public class Matching extends Question implements Serializable {
    private static final long serialVersionUID = -8176926467152362392L;
    public int numOfFirstColumnItems, numOfSecondColumnItems;
    protected String[] firstColumnOptions, secondColumnOptions, takerFirstChoice, takerSecondChoice;
    protected String matchAns;

    Matching() {

    }

    @Override
    public void take() {
        int h = 0;
        this.takerFirstChoice = new String[firstColumnOptions.length];
        this.takerSecondChoice = new String[secondColumnOptions.length];
        Display.displayString(getPrompt() + " (each item on the left has only one answer on the right - and vice versa)");
        for (h = 0; h < Math.max(numOfFirstColumnItems, numOfSecondColumnItems); h++) {
            if (h + 1 > numOfFirstColumnItems) {
                for (int f = h; f < numOfSecondColumnItems; f++) {
                    Display.displayString("                              " + (f + 1) + ". " + getSecondColumn()[f]);
                }
                break;
            } else if (h + 1 > numOfSecondColumnItems) {
                for (int g = h; g < numOfFirstColumnItems; g++) {
                    Display.displayString("   " + (g + 1) + ". " + getFirstColumn()[g]);
                }
                break;
            } else {
                printTwoColumns("   " + (h + 1) + ". " + getFirstColumn()[h], (h + 1) + ". " + getSecondColumn()[h]);
            }
        }
        Display.displayString("\nPlease enter your response(s) in a valid format e.g. '1 3' will match 1 to 3\n");
        askUserForMatchingResponse();
    }

    public void askUserForMatchingResponse() {
        for (int l = 0; l < numOfFirstColumnItems; l++) {
            String d;
            int one;
            int two;

            while (true) {
                try {
                    d = UserInput.getString();
                    String[] arr = d.split(" ", 2);
                    one = Integer.parseInt(arr[0]);
                    two = Integer.parseInt(arr[1]);
                    if (one > numOfFirstColumnItems || one < 0) {
                        Display.displayString("Please re-enter your response.");
                    } else if (two > numOfSecondColumnItems || two < 0) {
                        Display.displayString("Please re-enter your response.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    Display.displayString("Please enter your response(s) in a valid format e.g. '1 3' will match 1 to 3.");
                    continue;
                }
            }

            this.takerFirstChoice[l] = firstColumnOptions[one - 1];
            this.takerSecondChoice[l] = secondColumnOptions[two - 1];

            matchAns = String.format("%-30s", firstColumnOptions[one - 1]) + secondColumnOptions[two - 1] + "\n";
            userResponse = userResponse + matchAns;
        }

    }

    @Override
    public String getQuestionType() {
        return "matching";
    }

    @Override
    public void printTwoColumns(String left, String right) {
        Display.displayTwoColumns(left, right);
    }

    @Override
    public void modifyFirstColumnItem(int i, String newItem) {
        firstColumnOptions[i] = newItem;
    }

    @Override
    public void modifySecondColumnItem(int i, String newItem) {
        secondColumnOptions[i] = newItem;
    }

    @Override
    public void setNumberOfFirstColumnItems() {
        Display.displayString("How many items do you want in the first column?");
        int first = UserInput.getInt();
        if (first > 26) {
            Display.displayString("The number of items should be less than or equal to 26.");
            setNumberOfFirstColumnItems();
        } else {
            this.numOfFirstColumnItems = first;
        }
    }

    @Override
    public void setNumberOfSecondColumnItems() {
        Display.displayString("How many items do you want in the second column?");
        int second = UserInput.getInt();
        if (second > 26) {
            Display.displayString("The number of items should be less than or equal to 26.");
            setNumberOfSecondColumnItems();
        } else if (second < this.numOfFirstColumnItems) {
            Display.displayString("The number of items on the second column cannot be less than the first.");
            setNumberOfSecondColumnItems();
        } else {
            this.numOfSecondColumnItems = second;
        }
    }

    @Override
    public void setFirstColumn() {
        firstColumnOptions = new String[this.numOfFirstColumnItems];
        for (int s = 0; s < this.numOfFirstColumnItems; s++) {
            Display.displayString("Please enter column one's choice " + (s + 1));
            firstColumnOptions[s] = UserInput.getString();
        }
    }

    @Override
    public void setSecondColumn() {
        secondColumnOptions = new String[this.numOfSecondColumnItems];
        for (int s = 0; s < this.numOfSecondColumnItems; s++) {
            Display.displayString("Please enter column two's choice " + (s + 1));
            secondColumnOptions[s] = UserInput.getString();
        }
    }

    @Override
    public String[] getFirstColumn() {
        return this.firstColumnOptions;
    }

    @Override
    public String[] getSecondColumn() {
        return this.secondColumnOptions;
    }

    @Override
    public int getNumOfFirstColumnItems() {
        return this.numOfFirstColumnItems;
    }

    @Override
    public int getNumOfSecondColumnItems() {
        return this.numOfSecondColumnItems;
    }

    @Override
    public void tabulate(HashMap<String, Integer> questionResponsesCounter) {
        System.out.println(this.getPrompt());

        questionResponsesCounter.entrySet().forEach(entry -> {
            System.out.println(String.format("%-100s", entry.getKey() + ":") + entry.getValue());
        });

        System.out.println();
    }

    @Override
    public void setCorrectAnswer() {
        Display.displayString(getPrompt());
        Display.displayString("Enter the correct answer(s) for this question.");
        int h = 0;
        this.takerFirstChoice = new String[firstColumnOptions.length];
        this.takerSecondChoice = new String[secondColumnOptions.length];
        Display.displayString(getPrompt() + " (each item on the left has only one answer on the right - and vice versa)");
        for (h = 0; h < Math.max(numOfFirstColumnItems, numOfSecondColumnItems); h++) {
            if (h + 1 > numOfFirstColumnItems) {
                for (int f = h; f < numOfSecondColumnItems; f++) {
                    Display.displayString("                              " + (f + 1) + ". " + getSecondColumn()[f]);
                }
                break;
            } else if (h + 1 > numOfSecondColumnItems) {
                for (int g = h; g < numOfFirstColumnItems; g++) {
                    Display.displayString("   " + (g + 1) + ". " + getFirstColumn()[g]);
                }
                break;
            } else {
                printTwoColumns("   " + (h + 1) + ". " + getFirstColumn()[h], (h + 1) + ". " + getSecondColumn()[h]);
            }
        }
        Display.displayString("\nPlease enter your response(s) in a valid format e.g. '1 3' will match 1 to 3\n");
        askCreatorForMatchingResponse();
    }

    protected void askCreatorForMatchingResponse() {
        for (int l = 0; l < numOfFirstColumnItems; l++) {
            String d;
            int one;
            int two;

            while (true) {
                try {
                    d = UserInput.getString();
                    String[] arr = d.split(" ", 2);
                    one = Integer.parseInt(arr[0]);
                    two = Integer.parseInt(arr[1]);
                    if (one > numOfFirstColumnItems || one < 0) {
                        Display.displayString("Please re-enter your response.");
                    } else if (two > numOfSecondColumnItems || two < 0) {
                        Display.displayString("Please re-enter your response.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    Display.displayString("Please enter your response(s) in a valid format e.g. '1 3' will match 1 to 3.");
                    continue;
                }
            }

            this.takerFirstChoice[l] = firstColumnOptions[one - 1];
            this.takerSecondChoice[l] = secondColumnOptions[two - 1];

            this.matchAns = String.format("%-30s", firstColumnOptions[one - 1]) + secondColumnOptions[two - 1] + "\n";
            this.correctAnswer = this.correctAnswer + this.matchAns;
            responseCorrectAnswer.setCorrectAnswer(this.correctAnswer);
        }
    }
}
