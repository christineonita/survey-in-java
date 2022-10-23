package survey;

import java.io.Serializable;

public class MultipleChoice extends Question implements Serializable {
    private static final long serialVersionUID = 493583457744295169L;
    protected String[] questionChoices;
    protected int[] answersForMultChoiceQuestionsWithMultipleAnswers;
    protected boolean hasMultipleAnswers;
    protected String userAnswer;

    MultipleChoice() {

    }

    @Override
    protected void setQuestionChoices() {
        int choiceNumber, numberOfChoices;
        //boolean repeat = true;
        String yesOrNo;
        Display.displayString("Enter the number of choices for your multiple-choice question: ");

        numberOfChoices = UserInput.getInt();

        questionChoices = new String[numberOfChoices];

        Display.displayString("Does this question allow multiple answers? (Type yes or no)");

        //check if question can have more than one answer
        while (true) {
            yesOrNo = UserInput.getString();
            if (yesOrNo.equalsIgnoreCase("yes")) {
                hasMultipleAnswers = true;
                answersForMultChoiceQuestionsWithMultipleAnswers = new int[numberOfChoices];
                break;
            } else if (yesOrNo.equalsIgnoreCase("no")) {
                hasMultipleAnswers = false;
                break;
            } else {
                Display.displayString("Please enter yes or no");
            }
        }

        for (int i = 0; i < numberOfChoices; i++) {
            choiceNumber = i + 1;
            Display.displayString("Please enter choice #" + choiceNumber);
            questionChoices[i] = UserInput.getString();
        }
    }

    @Override
    public String[] getQuestionChoices() {
        return questionChoices;
    }

    @Override
    public void modifyQuestionChoice(int choiceIndex, String newChoice) {
        questionChoices[choiceIndex] = newChoice;
    }

    @Override
    public void take() {
        Display.displayString(getPrompt());
        Display.displayStringArray(questionChoices);
        askUserForChoices();
    }

    @Override
    public String getQuestionType() {
        return "multiple-choice";
    }

    protected void askUserForChoices() {
        if (hasMultipleAnswers == false) {
            Display.displayString("Please enter your choice #: ");

            setSingleUserAnswer(UserInput.getOption(0, questionChoices.length + 1));
        } else {
            Display.displayString("This question has multiple answers so press enter after each choice you type and enter a 0 when you're finished ");
            for (int y = 0; y < answersForMultChoiceQuestionsWithMultipleAnswers.length; y++) {
                Display.displayString("Please enter your choice #: ");
                answersForMultChoiceQuestionsWithMultipleAnswers[y] = UserInput.getOption(0, answersForMultChoiceQuestionsWithMultipleAnswers.length);
                if (answersForMultChoiceQuestionsWithMultipleAnswers[y] == 0) {
                    break;
                }
            }
            setMultipleUserAnswer(answersForMultChoiceQuestionsWithMultipleAnswers);
        }
    }

    protected void setMultipleUserAnswer(int[] multipleAnswersArray) {
        userAnswer = "";
        //change this to a while loop
        for (int g = 0; g < multipleAnswersArray.length; g++) {
            if (multipleAnswersArray[g] == 0) {
                break;
            }
            userAnswer = userAnswer + Integer.toString(multipleAnswersArray[g]) + ") " + questionChoices[multipleAnswersArray[g] - 1] + "\n";
        }
    }

    protected void setSingleUserAnswer(int anInt) {
        userAnswer = Integer.toString(anInt) + ") " + this.questionChoices[anInt - 1];
    }
}
