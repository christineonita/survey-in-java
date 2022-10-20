package survey;
import java.io.Serializable;

public abstract class Question implements Serializable {
    private static final long serialVersionUID = 1399189310822722123L;

    protected String prompt;

    protected abstract void setQuestionChoices();

    protected void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
