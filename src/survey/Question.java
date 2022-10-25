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

    public abstract void setNumberOfRows();

    public abstract void setFirstColumn();

    public abstract void setSecondColumn();

    public abstract String[] getFirstColumn();

    public abstract Object[] getSecondColumn();

    public abstract int getNumOfRows();
}
