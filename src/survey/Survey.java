package survey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Survey implements Serializable {

    private static final long serialVersionUID = 4925296878791173007L;
    String path;
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

    protected void setQuestionPrompt(Question question) {
        Display.displayString("Enter the question prompt --> ");
        Scanner scan = new Scanner(System.in);

        String prompt = scan.nextLine();
        question.setPrompt(prompt);
    }

    protected void addMultipleChoice() {
        //System.out.println("code needed to add multiple-choice question to survey");
        Question question = new MultipleChoice();
        setQuestionPrompt(question);
        question.setQuestionChoices();
        questions.add(question);
    }


    protected void addTrueOrFalse() {
        System.out.println("code needed to add t/f question to survey");
    }

    protected void addEssay() {
        System.out.println("code needed to add essay question to survey");
    }

    protected void addShortAnswer() {
        System.out.println("code needed to add short answer question to survey");
    }


    protected void addValidDate() {
        System.out.println("code needed to add valid date question to survey");
    }

    protected void modify() {
        System.out.println("code needed to add matching question to survey");
    }

    /*protected ArrayList<Question> getQuestions() {
        return this.questions;
    }*/

    protected void clearAllQuestions() {
        questions.clear();
    }

    public void displaySurvey() {
        for (int x = 0; x < this.questions.size(); x++) {
            Display.displayString(this.questions.get(x).getPrompt());
            if (this.questions.get(x) instanceof MultipleChoice) {
                for (String s : this.questions.get(x).getQuestionChoices()) {
                    Display.displayString(s);
                }
            }
        }

        /*
        public void dtest() {
        //Serialize ser = new Serialize();
        //answers = new String[this.questions.size()];
        for (int i = 0; i < this.questions.size(); i++) {
            System.out.println(this.questions.get(i).getPrompt());
            if (this.questions.get(i) instanceof MultChoice) {
                for (String s : this.questions.get(i).getChoices()) {
                    System.out.println(s);
                }
            }
        }
    }
         */
    }
}
