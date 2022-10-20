import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable {

    private static final long serialVersionUID = 4925296878791173007L;
    //public static ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<Question> questions = null;
    Survey() {
        questions = new ArrayList<Question>();
    }

    /*public void setPrompt(Question question) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String prompt;
        try {
            prompt = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        question.setQuestionPrompt(prompt);
    }*/

    public void addMultipleChoice() {
        System.out.println("code needed to add multiple-choice question to survey");
    }

    public void addTrueOrFalse() {
        System.out.println("code needed to add t/f question to survey");
    }

    public void addEssay() {
        System.out.println("code needed to add essay question to survey");
    }

    public void addShortAnswer() {
        System.out.println("code needed to add short answer question to survey");
    }


    public void addValidDate() {
        System.out.println("code needed to add valid date question to survey");
    }

    public void modify() {
        System.out.println("code needed to add matching question to survey");
    }

    /*public ArrayList<Question> getQuestions() {
        return this.questions;
    }*/

    public void clearAllQuestions() {
        questions.clear();
    }
}
