package survey;

import java.io.Serializable;

public class Test extends Survey implements Serializable {

    private static final long serialVersionUID = 8102060438561824214L;
    public String nameOfTest;
    String testResponseFolder;

    @Override
    protected void addTrueOrFalse() {
        Question question = new TrueOrFalse();
        setQuestionPrompt(question);
        question.setQuestionChoices();

        question.setCorrectAnswer();

        questions.add(question);
    }

    @Override
    protected void addMultipleChoice() {
        Question question = new MultipleChoice();
        setQuestionPrompt(question);
        question.setQuestionChoices();

        question.setCorrectAnswer();

        questions.add(question);
    }

    public void displayTestWithoutCorrectAnswers() {
        int h = 0;
        int choicesLoop = 1;
        for (int x = 0; x < this.questions.size(); x++) {
            Display.displayString("\n Question " + (x + 1) + ". " + this.questions.get(x).getPrompt());
            if (this.questions.get(x) instanceof MultipleChoice || this.questions.get(x) instanceof TrueOrFalse) {
                for (String s : this.questions.get(x).getQuestionChoices()) {
                    Display.displayString("   " + choicesLoop + ".) " + s);
                    choicesLoop++;
                }
            } else if (this.questions.get(x) instanceof Matching) {
                Display.displayMatchingColumns(this.questions.get(x));
            }
            choicesLoop = 1;
        }
        System.out.println();
    }

    public void displayTestWithCorrectAnswers() {
        int h = 0;
        int choicesLoop = 1;
        for (int x = 0; x < this.questions.size(); x++) {
            Display.displayString("\n Question " + (x + 1) + ". " + this.questions.get(x).getPrompt());
            if (this.questions.get(x) instanceof MultipleChoice || this.questions.get(x) instanceof TrueOrFalse) {
                for (String s : this.questions.get(x).getQuestionChoices()) {
                    Display.displayString("   " + choicesLoop + ".) " + s);
                    choicesLoop++;
                }
            } else if (this.questions.get(x) instanceof Matching) {
                Display.displayMatchingColumns(this.questions.get(x));
            }
            choicesLoop = 1;
            System.out.println("The correct answers are: ");
            //Display.displayStringArray(this.questions.get(x).getCorrectResponses());
            displayCorrectAnswers(this.questions.get(x).getCorrectResponses());
            System.out.println();
        }
    }

    protected void displayCorrectAnswers(String[] correctResponses) {
        String lines[] = correctResponses[0].split("\\r?\\n");
        for (int i = 0; i < lines.length; i++) {
            System.out.println(("    " + lines[i]));
        }
    }
}
