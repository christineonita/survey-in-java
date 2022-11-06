package survey;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


}
