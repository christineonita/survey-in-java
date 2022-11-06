package survey;

import java.io.Serializable;

public class Matching extends Question implements Serializable {
    private static final long serialVersionUID = -8176926467152362392L;
    public int numOfRows;
    protected String[] firstColumnOptions, secondColumnOptions;
    protected String[] matchingAnswers;
    protected String matchAns;

    @Override
    protected void setQuestionChoices() {
        // TODO - maybe needed
    }

    @Override
    public void take() {
        // TODO

        matchingAnswers = new String[this.numOfRows];
        Display.displayString(getPrompt() + " (each item on the left has only one answer on the right - and vice versa)");
        for (int h = 0; h < this.numOfRows; h++) {
            printTwoColumns("   " + (h + 1) + ". " + this.firstColumnOptions[h], (h + 1) + ". " + this.secondColumnOptions[h]);
        }
        Display.displayString("\nPlease enter your response(s) in a valid format e.g. '1 3' will match 1 to 3\n");
        askUserForMatchingResponse();
    }

    public void askUserForMatchingResponse() {
        for (int l = 0; l < numOfRows; l++) {
            String d = UserInput.getString();
            String arr[] = d.split(" ", 2);
            int one = Integer.parseInt(arr[0]);
            int two = Integer.parseInt(arr[1]);

            matchingAnswers[l] = firstColumnOptions[one - 1] + secondColumnOptions[two - 1];
            matchAns = firstColumnOptions[one - 1] + " " + secondColumnOptions[two - 1] + "\n";
            userResponse = userResponse + matchAns;
        }

    }

    @Override
    public String getQuestionType() {
        return "matching";
    }

    @Override
    protected void modifyQuestionChoice(int choiceNum, String choice) {
        // TODO - maybe needed
    }

    @Override
    public void setRequiredNumberOfResponses() {
        // TODO - not needed
    }

    @Override
    public void setShortAnswerLimit() {
        // TODO - not needed
    }

    protected void printTwoColumns(String left, String right) {
        System.out.printf("%-30s", left); // using printf to format the display of both columns
        //System.out.printf("%-25s\n", right);
        System.out.printf(right + "\n");
    }

    @Override
    public void setNumberOfRows() {
        Display.displayString("How many pairs do you want in this question?");
        int enteredNumOfRows = UserInput.getInt();
        if (enteredNumOfRows > 26) {
            Display.displayString("The number of pairs should be less than or equal to 26.");
            setNumberOfRows();
        } else {
            this.numOfRows = enteredNumOfRows;
        }
    }

    @Override
    public void setFirstColumn() {
        firstColumnOptions = new String[this.numOfRows];
        for (int s = 0; s < this.numOfRows; s++) {
            //Display.displayString("Please enter column one's choice " + alphabets[s]);
            Display.displayString("Please enter column one's choice " + (s + 1));
            firstColumnOptions[s] = UserInput.getString();
        }
    }

    @Override
    public void setSecondColumn() {
        secondColumnOptions = new String[this.numOfRows];
        for (int s = 0; s < this.numOfRows; s++) {
            //Display.displayString("Please enter column two's choice " + alphabets[s]);
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
    public int getNumOfRows() {
        return this.numOfRows;
    }
}
