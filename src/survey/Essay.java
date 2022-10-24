package survey;

import java.io.Serializable;

public class Essay extends Question implements Serializable { // - short answer will hava character limit set by the creator
    private static final long serialVersionUID = -1175125181899751223L;

    @Override
    protected void setQuestionChoices() {
        // TODO - not needed
    }

    @Override
    public void take() {
        // TODO
        Display.displayString(getPrompt());
        Display.displayString("\nPlease enter your response\n");
        getEssayResponse();
    }

    public void getEssayResponse() {
        String ans = UserInput.getString();
        userResponse = ans;
        //System.out.println(ans);
    }

    @Override
    public String getQuestionType() {
        return "essay";
    }

    @Override
    protected void modifyQuestionChoice(int choiceNum, String choice) {
        // TODO - not needed
    }
}
