package survey;

import java.io.Serializable;

public class ShortAnswer extends Essay implements Serializable {
    private static final long serialVersionUID = -3786764219358424837L;
    protected String[] shortAnswerResponses;
    protected String userShortAnswer;
    private int numberOfShortAnswerResponsesRequired;
    private int shortAnswerLimit;
    private int enteredLimit;
    private int enteredRequiredNumberOfResponses;

    @Override
    protected void setQuestionChoices() {
        // todo - not needed
    }

    @Override
    public void take() {
        Display.displayString(getPrompt());
        Display.displayString("\nPlease enter your responses\n");
        getShortAnswerResponse(this.numberOfShortAnswerResponsesRequired, this.shortAnswerLimit);
    }

    public void getShortAnswerResponse(int numOfResponses, int limit) {
        System.out.println("The limit for your response(s) is " + limit + " characters");
        int p = 0;
        while (p < numOfResponses) {
            //for (int p = 0; p < numOfResponses; p++) {

            Display.displayString("Response #" + (p + 1));
            String ans = UserInput.getString();

            if (ans.length() > limit) {
                System.out.println("Your response is over the " + limit + " character limit. Try again");
                continue;
            } //code here to ask for reentry
            userShortAnswer = ans + "\n";
            userResponse = userResponse + userShortAnswer;
            //shortAnswerResponses[p] = ans;
            //}
            p++;
        }

    }

    @Override
    public String getQuestionType() {
        return "short answer";
    }

    @Override
    protected void modifyQuestionChoice(int choiceNum, String choice) {
        // todo
    }

    @Override
    public void setRequiredNumberOfResponses() {
        Display.displayString("How many responses are required for this short answer question?");
        enteredRequiredNumberOfResponses = UserInput.getInt();
        if (enteredRequiredNumberOfResponses > 100) {
            Display.displayString("The number of responses should be less than or equal to 100.");
            setRequiredNumberOfResponses();
        }
        this.numberOfShortAnswerResponsesRequired = enteredRequiredNumberOfResponses;
    }

    @Override
    public void setShortAnswerLimit() {
        Display.displayString("Enter the short answer character limit you want");
        enteredLimit = UserInput.getInt();
        if (enteredLimit > 80) {
            Display.displayString("The character limit should be less than or equal to " + 80);
            setShortAnswerLimit();
        }
        this.shortAnswerLimit = enteredLimit;
    }
}
