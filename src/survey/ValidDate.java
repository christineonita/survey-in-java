package survey;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ValidDate extends Question implements Serializable {
    private static final long serialVersionUID = 1471791369487669173L;
    private String userInputDate, userSavedDateResponse;

    ValidDate() {
    }


    @Override
    public void take() {
        Display.displayString(getPrompt());
        Display.displayString("\nPlease enter your response(s) in the format MM/DD/YYYY\n");
        getValidDateResponse();

    }

    public void getValidDateResponse() {
        userInputDate = UserInput.getString();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setLenient(false);

        while (true) {
            try {
                Date date = formatter.parse(userInputDate);
                userSavedDateResponse = userInputDate;
                userResponse = userSavedDateResponse;
                break;
            } catch (ParseException e) {
                Display.displayString("Please enter a valid date in the format MM/DD/YYYY");
                userInputDate = UserInput.getString();
            }
        }
    }

    @Override
    public String getQuestionType() {
        return "valid date";
    }


    @Override
    public void tabulate(HashMap<String, Integer> questionResponsesCounter) {
        System.out.println(this.getPrompt());

        questionResponsesCounter.entrySet().forEach(entry -> {
            Display.displayString(entry.getKey()/* + ": " + entry.getValue()*/);
            Display.displayString(String.valueOf(entry.getValue()));
        });

        Display.displayString("");
    }

    @Override
    public void setCorrectAnswer() {
        Display.displayString(getPrompt());
        //Display.displayStringArray(multipleChoiceQuestionChoices);
        Display.displayString("Enter the correct answer(s) for this question.");
        Display.displayString("\nPlease enter your response(s) in the format MM/DD/YYYY\n");
        getCreatorValidDateResponse();
    }

    protected void getCreatorValidDateResponse() {

        userInputDate = UserInput.getString();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setLenient(false);

        while (true) {
            try {
                Date date = formatter.parse(userInputDate);
                userSavedDateResponse = userInputDate;
                correctAnswer = userSavedDateResponse;
                responseCorrectAnswer.setCorrectAnswer(this.correctAnswer);
                break;
            } catch (ParseException e) {
                Display.displayString("Please enter a valid date in the format MM/DD/YYYY");
                userInputDate = UserInput.getString();
            }
        }
    }
}
