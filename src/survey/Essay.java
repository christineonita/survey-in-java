package survey;

import java.io.Serializable;
import java.util.HashMap;

public class Essay extends Question implements Serializable { // - short answer will hava character limit set by the creator
    private static final long serialVersionUID = -1175125181899751223L;
    private String userAnswer;
    private int numberOfResponsesRequired, enteredNumOfEssayRequiredResponses;

    Essay() {

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
        }
    }

    @Override
    public String getQuestionType() {
        return "essay";
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
    public void tabulate(HashMap<String, Integer> questionResponsesCounter) {
        Display.displayString(this.getPrompt());

        questionResponsesCounter.entrySet().forEach(entry -> {
            Display.displayString(entry.getKey() + "\n");
        });

        Display.displayString("");
    }
}
