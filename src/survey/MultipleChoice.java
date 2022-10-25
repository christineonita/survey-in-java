package survey;

import java.io.Serializable;
import java.util.InputMismatchException;

public class MultipleChoice extends Question implements Serializable {
    private static final long serialVersionUID = 493583457744295169L;
    protected String[] multipleChoiceQuestionChoices;
    protected int[] answersForMultChoiceQuestionsWithMultipleAnswers;
    protected boolean hasMultipleAnswers;
    protected String userAnswer;
    private int mult, numberOfChoices, choiceNumber;

    MultipleChoice() {

    }

    @Override
    protected void setQuestionChoices() {
        String yesOrNo;
        Display.displayString("Enter the number of choices for your multiple-choice question: ");

        numberOfChoices = UserInput.getInt();
        if (numberOfChoices > 26) {
            Display.displayString("The number of choices should be less than or equal to 26.");
            setQuestionChoices();
        } else {
            multipleChoiceQuestionChoices = new String[numberOfChoices];

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
                multipleChoiceQuestionChoices[i] = UserInput.getString();
            }
        }
    }

    @Override
    public String[] getQuestionChoices() {
        return multipleChoiceQuestionChoices;
    }

    @Override
    public void modifyQuestionChoice(int choiceIndex, String newChoice) {
        multipleChoiceQuestionChoices[choiceIndex] = newChoice;
    }

    @Override
    public void setRequiredNumberOfResponses() {
        // TODO - not needed
    }

    @Override
    public void setShortAnswerLimit() {
        // TODO - not needed
    }

    @Override
    public void take() {
        Display.displayString(getPrompt());
        Display.displayStringArray(multipleChoiceQuestionChoices);
        askUserForChoices();
    }

    @Override
    public String getQuestionType() {
        return "multiple-choice";
    }

    protected void askUserForChoices() {
        if (hasMultipleAnswers == false) {
            Display.displayString("Please enter your choice #: ");

            setSingleUserAnswer(UserInput.getOption(0, multipleChoiceQuestionChoices.length + 1));
        } else {
            Display.displayString("This question has multiple answers so press enter after each choice you type and enter again when you're finished "); // i might edit this prompt to be more clear
            for (int y = 0; y < answersForMultChoiceQuestionsWithMultipleAnswers.length; y++) {
                Display.displayString("Please enter your choice #: ");
                try {
                    mult = UserInput.getMultipleOptions(0, answersForMultChoiceQuestionsWithMultipleAnswers.length + 1);
                    if (mult == 0) { // when the user presses just the enter key after a prompt the recorded integer value is 0 so this saves the responses previous to that
                        break;
                    } else {
                        answersForMultChoiceQuestionsWithMultipleAnswers[y] = mult;
                    }
                } catch (InputMismatchException e) {
                    break;
                }
            }
            setMultipleUserAnswer(answersForMultChoiceQuestionsWithMultipleAnswers);
        }
    }

    protected void setMultipleUserAnswer(int[] multipleAnswersArray) {
        userAnswer = "";
        for (int i : multipleAnswersArray) {
            if (i == 0) {
                break;
            }
            //userAnswer = userAnswer + Integer.toString(multipleAnswersArray[g]) + ") " + multipleChoiceQuestionChoices[multipleAnswersArray[g] - 1] + "\n";
            userAnswer = multipleChoiceQuestionChoices[i - 1] + "\n"; // so that the question choice numbers are not saved in the responses
            userResponse = userResponse + userAnswer;
        }
    }

    protected void setSingleUserAnswer(int anInt) {
        //userAnswer = Integer.toString(anInt) + ") " + this.multipleChoiceQuestionChoices[anInt - 1];
        userAnswer = this.multipleChoiceQuestionChoices[anInt - 1]; // so that the question choice numbers are not saved in the responses
        userResponse = userAnswer;
    }
}
