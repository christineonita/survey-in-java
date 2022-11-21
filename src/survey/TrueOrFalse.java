package survey;

import java.io.Serializable;
import java.util.HashMap;

public class TrueOrFalse extends MultipleChoice implements Serializable {

    private static final long serialVersionUID = 2106840000528017311L;
    protected String[] trueOrFalseQuestionChoices;
    protected String trueOrFalseChoice;

    TrueOrFalse() {
    }

    @Override
    protected void setQuestionChoices() {
        hasMultipleAnswers = false;
        trueOrFalseQuestionChoices = new String[2];
        trueOrFalseQuestionChoices[0] = "True";
        trueOrFalseQuestionChoices[1] = "False";
    }

    @Override
    public void take() {
        Display.displayString(getPrompt());
        Display.displayStringArray(trueOrFalseQuestionChoices);
        askUserForChoices();
    }

    @Override
    protected void askUserForChoices() {
        Display.displayString("Please enter your choice #: ");

        setSingleUserAnswer(UserInput.getOption(0, 2 + 1));
    }

    @Override
    protected void setSingleUserAnswer(int anInt) { // stored as either "True" or "False" not the choice number
        trueOrFalseChoice = this.trueOrFalseQuestionChoices[anInt - 1];
        userResponse = trueOrFalseChoice;
    }

    @Override
    public String getQuestionType() {
        return "true or false";
    }

    @Override
    public String[] getQuestionChoices() {
        return trueOrFalseQuestionChoices;
    }

    /*@Override
    public void populate(HashMap<String, Integer> responsesCounter) {
        for (String choice : getQuestionChoices()) {
            responsesCounter.put(choice, 0);
        }
    }*/

    @Override
    public void tabulate(HashMap<String, Integer> questionResponsesCounter) {
        Display.displayString(this.getPrompt());

        questionResponsesCounter.entrySet().forEach(entry -> {
            Display.displayString(entry.getKey() + ": " + entry.getValue());
        });

        Display.displayString("");
    }

    @Override
    public void setCorrectAnswer() {
        Display.displayString(getPrompt());
        Display.displayStringArray(trueOrFalseQuestionChoices);
        Display.displayString("Enter the correct answer(s) for this question.");
        askCreatorForChoices();
    }

    @Override
    public void askCreatorForChoices() {
        Display.displayString("Please enter your choice #: ");

        setSingleCreatorAnswer(UserInput.getOption(0, 2 + 1));
    }

    @Override
    public void setSingleCreatorAnswer(int option) {
        trueOrFalseChoice = this.trueOrFalseQuestionChoices[option - 1];
        this.correctAnswer = trueOrFalseChoice;
        responseCorrectAnswer.setCorrectAnswer(this.correctAnswer);
    }
}
