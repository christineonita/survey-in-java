package survey;

import java.io.Serializable;

public class MultipleChoice extends Question implements Serializable {
    protected String[] questionChoices;

    MultipleChoice() {

    }

    @Override
    protected void setQuestionChoices() {
        int choiceNumber, numberOfChoices;
        //boolean repeat = true;
        String yesOrNo;
        Display.displayString("Enter the number of choices for your question: ");

        numberOfChoices = UserInput.getInt();

        questionChoices = new String[numberOfChoices];

        Display.displayString("Does this question allow multiple answers? (Type yes or no)");

        //check if question can have more than one answer
        while (true) {
            yesOrNo = UserInput.getString();
            if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no")) {
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
}
