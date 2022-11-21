package survey;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Question implements Serializable {
    private static final long serialVersionUID = 1399189310822722123L;

    protected String prompt, userResponse = "", correctAnswer = "";
    protected String[] choices;
    ResponseCorrectAnswer responseCorrectAnswer = new ResponseCorrectAnswer();

    Question() {
    }

    public abstract void take();

    public abstract String getQuestionType();


    protected String getPrompt() {
        return prompt;
    }

    protected void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String[] getQuestionChoices() {
        return choices;
    }

    protected void setQuestionChoices() {

    }

    protected void modifyQuestionChoice(int choiceNum, String choice) {

    }

    public void setRequiredNumberOfResponses() {

    }

    public void setShortAnswerLimit() {

    }

    public void setFirstColumn() {

    }

    public void setSecondColumn() {

    }

    public String[] getFirstColumn() {
        return new String[0];
    }

    public String[] getSecondColumn() {
        return new String[0];
    }


    public void setNumberOfFirstColumnItems() {

    }

    public void setNumberOfSecondColumnItems() {

    }

    public int getNumOfFirstColumnItems() {
        return 0;
    }

    public int getNumOfSecondColumnItems() {
        return 0;
    }

    public void printTwoColumns(String s, String s1) {

    }

    public void modifyFirstColumnItem(int i, String newItem) {

    }

    public void modifySecondColumnItem(int i, String newItem) {

    }

    public void tabulate(HashMap<String, Integer> questionResponsesCounter) {
    }

    public String[] getResponses() {
        return responseCorrectAnswer.getResponses();
    }

    public void setResponses(String userResponse) {
        responseCorrectAnswer.setResponses(userResponse);
    }

    public void setCorrectAnswer() {
    }


    public String[] getCorrectResponses() {
        return responseCorrectAnswer.getCorrectResponses();
    }

    public void modifyCorrectAnswer() {
        this.correctAnswer = "";
        setCorrectAnswer();
    }
}
