package survey;

public class TrueOrFalse extends MultipleChoice {

    private static final long serialVersionUID = 2106840000528017311L;
    protected String[] trueOrFalseQuestionChoices;
    protected String trueOrFalseChoice;

    //private final String[] surveyMenu
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
    protected void setSingleUserAnswer(int anInt) { // stored as either "True" or "False"
        //trueOrFalseChoice = Integer.toString(anInt) + ") " + this.trueOrFalseQuestionChoices[anInt - 1];
        trueOrFalseChoice = this.trueOrFalseQuestionChoices[anInt - 1];
        //System.out.println(trueOrFalseChoice); // - debugger
    }

    @Override
    public String getQuestionType() {
        return "True/False";
    }

    @Override
    public String[] getQuestionChoices() {
        return trueOrFalseQuestionChoices;
    }

    @Override
    public void modifyQuestionChoice(int choiceNum, String choice) {
        // TODO
    }
}
