package survey;

import java.io.Serializable;

public class Essay extends Question implements Serializable { // - short answer will hava character limit set by the creator
    private static final long serialVersionUID = -1175125181899751223L;
    protected String userAnswer;
    protected String[] essayResponses;
    private int numberOfResponsesRequired;

    @Override
    protected void setQuestionChoices() {
        // TODO - not needed
    }

    @Override
    public void take() {
        // TODO
        Display.displayString(getPrompt());
        Display.displayString("\nPlease enter your responses\n");
        getEssayResponse(this.numberOfResponsesRequired);
    }

    public void getEssayResponse(int numOfResponses) {
        //String ans = UserInput.getString();
        //userResponse = ans;
        //System.out.println(ans);
        //essayResponses = new int[numOfResponses];
        for (int p = 0; p < numOfResponses; p++) {
            Display.displayString("Response #" + (p + 1));
            String ans = UserInput.getString();
            userAnswer = userAnswer + ans + "\n";
            userResponse = userAnswer;
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
        this.numberOfResponsesRequired = UserInput.getInt();
    }
}
