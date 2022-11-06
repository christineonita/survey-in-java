package survey;

import java.io.Serializable;

public abstract class Question implements Serializable {
    private static final long serialVersionUID = 1399189310822722123L;

    protected String prompt, userResponse = "";
    protected String[] choices;

    protected abstract void setQuestionChoices();

    protected String getPrompt() {
        return prompt;
    }

    protected void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String[] getQuestionChoices() {
        return choices;
    }

    public abstract void take();

    public abstract String getQuestionType();

    protected abstract void modifyQuestionChoice(int choiceNum, String choice);

    public abstract void setRequiredNumberOfResponses();

    public abstract void setShortAnswerLimit();

    //public abstract void setNumberOfRows();

    public abstract void setFirstColumn();

    public abstract void setSecondColumn();

    public abstract String[] getFirstColumn();

    public abstract String[] getSecondColumn();


    public abstract void setNumberOfFirstColumnItems();

    public abstract void setNumberOfSecondColumnItems();

    public abstract int getNumOfFirstColumnItems();

    public abstract int getNumOfSecondColumnItems();

    public abstract void printTwoColumns(String s, String s1);

    public abstract void modifyFirstColumnItem(int i, String newItem);

    public abstract void modifySecondColumnItem(int i, String newItem);
}
