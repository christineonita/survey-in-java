package survey;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidDate extends Question implements Serializable {
    private static final long serialVersionUID = 1471791369487669173L;
    private String userInputDate, userSavedDateResponse;

    @Override
    protected void setQuestionChoices() {
        // TODO - not needed
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
    protected void modifyQuestionChoice(int choiceNum, String choice) {
        // TODO - not needed
    }

    @Override
    public void setRequiredNumberOfResponses() {
        // TODO - not needed
    }

    @Override
    public void setShortAnswerLimit() {
        // TODO - not needed
    }

    /*@Override
    public void setNumberOfRows() {
        // TODO - not needed
    }*/

    @Override
    public void setFirstColumn() {
        // TODO - not needed
    }

    @Override
    public void setSecondColumn() {
        // TODO - not needed
    }

    @Override
    public String[] getFirstColumn() {
        // TODO - not needed
        return new String[0];
    }

    @Override
    public Object[] getSecondColumn() {
        // TODO - not needed
        return new Object[0];
    }


    @Override
    public void setNumberOfFirstColumnItems() {
        // TODO - not needed
    }

    @Override
    public void setNumberOfSecondColumnItems() {
        // TODO - not needed
    }

    @Override
    public int getNumOfFirstColumnItems() {
        // TODO - not needed
        return 0;
    }

    @Override
    public int getNumOfSecondColumnItems() {
        // TODO - not needed
        return 0;
    }

    @Override
    public void printTwoColumns(String s, String s1) {
        // TODO - not needed
    }
}
