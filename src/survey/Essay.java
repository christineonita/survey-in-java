package survey;

import java.io.Serializable;

public class Essay extends Question implements Serializable { // - short answer will hava character limit set by the creator
    private static final long serialVersionUID = -1175125181899751223L;
    protected String[] essayResponses;
    private String userAnswer;
    private int numberOfResponsesRequired, enteredNumOfEssayRequiredResponses;

    Essay() {

    }

    @Override
    protected void setQuestionChoices() {
        // TODO - not needed
    }

    @Override
    public void take() {
        Display.displayString(getPrompt());
        Display.displayString("\nPlease enter your response(s)\n");
        getEssayResponse(this.numberOfResponsesRequired);
    }

    public void getEssayResponse(int numOfResponses) {
        for (int p = 0; p < numOfResponses; p++) {
            Display.displayString("Response #" + (p + 1));
            String ans = UserInput.getString();
            userAnswer = ans + "\n";
            userResponse = userResponse + userAnswer;
            //essayResponses[p] = ans;
        }
    }

    @Override
    public String getQuestionType() {
        return "essay";
    }

    @Override
    protected void modifyQuestionChoice(int choiceNum, String choice) {
        // TODO - not needed
    }

    @Override
    public void setRequiredNumberOfResponses() {
        Display.displayString("How many responses are required for this essay question?");
        enteredNumOfEssayRequiredResponses = UserInput.getInt();
        if (enteredNumOfEssayRequiredResponses > 100) {
            Display.displayString("The number of responses should be less than or equal to 100.");
            setRequiredNumberOfResponses();
        } else {
            this.numberOfResponsesRequired = enteredNumOfEssayRequiredResponses;
        }

    }

    @Override
    public void setShortAnswerLimit() {
        // TODO - not needed
    }

    /*@Override
    public void setNumberOfRows() {
        // TODO - not needed
    }*/

    @Override
    public void setFirstColumn() {
        // TODO - not needed
    }

    @Override
    public void setSecondColumn() {
        // TODO - not needed
    }

    @Override
    public String[] getFirstColumn() {
        // TODO - not needed
        return new String[0];
    }

    @Override
    public String[] getSecondColumn() {
        // TODO - not needed
        return new String[0];
    }


    @Override
    public void setNumberOfFirstColumnItems() {
        // TODO - not needed
    }

    @Override
    public void setNumberOfSecondColumnItems() {
        // TODO - not needed
    }

    @Override
    public int getNumOfFirstColumnItems() {
        // TODO - not needed
        return 0;
    }

    @Override
    public int getNumOfSecondColumnItems() {
        // TODO - not needed
        return 0;
    }

    @Override
    public void printTwoColumns(String s, String s1) {
        // TODO - not needed
    }

    @Override
    public void modifyFirstColumnItem(int i, String newItem) {
        // TODO - not needed
    }

    @Override
    public void modifySecondColumnItem(int i, String newItem) {
        // TODO - not needed
    }
}
