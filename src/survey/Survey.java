package survey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Survey implements Serializable {

    private static final long serialVersionUID = 4925296878791173007L;
    public String nameOfSurvey;
    String surveyResponseFolder;// = "SurveyResponses";
    //public static ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<Question> questions = null;
    String[] userAnswers;
    String newPrompt;
    String modifyPrompt;
    String modifyChoicesYesOrNo;
    int questionToModify;

    Survey() {
        questions = new ArrayList<Question>();
    }

    protected void setQuestionPrompt(Question question) {
        Display.displayString("Enter the prompt for your " + question.getQuestionType() + " question: ");
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
        //System.out.println("code needed to add t/f question to survey");

        Question question = new TrueOrFalse();
        setQuestionPrompt(question);
        question.setQuestionChoices();
        questions.add(question);
    }

    protected void addEssay() {
        //System.out.println("code needed to add essay question to survey");
        Question question = new Essay();
        setQuestionPrompt(question);
        //question.setQuestionChoices();
        questions.add(question);

    }

    protected void addShortAnswer() {
        System.out.println("code needed to add short answer question to survey");
    }


    protected void addValidDate() {
        System.out.println("code needed to add valid date question to survey");
    }

    protected void addMatching() {
        System.out.println("code needed to add matching question to survey");
        // for matching questions mark the options like option A, B, C --> mak it so that the creator cannot choose above 26 for num of items in a column
    }

    /*protected ArrayList<Question> getQuestions() {
        return this.questions;
    }*/

    protected void clearAllQuestions() {
        questions.clear();
    }

    public void displaySurvey() {
        int choicesLoop = 1;
        for (int x = 0; x < this.questions.size(); x++) {
            Display.displayString(" Question " + (x + 1) + ". " + this.questions.get(x).getPrompt());
            if (this.questions.get(x) instanceof MultipleChoice || this.questions.get(x) instanceof TrueOrFalse) {
                for (String s : this.questions.get(x).getQuestionChoices()) {
                    Display.displayString("   " + choicesLoop + ".) " + s);
                    choicesLoop++;
                }
            } /*else if (this.questions.get(x) instanceof TrueOrFalse) {
                for (String s : this.questions.get(x).getQuestionChoices()) {
                    Display.displayString("   " + choicesLoop + ".) " + s);
                    choicesLoop++;
                }
            }*/
            choicesLoop = 1;
        }
    }

    public void take() {
        int x = 0;
        Serialize serialize = new Serialize();
        userAnswers = new String[this.questions.size()];

        for (Question question : questions) {
            System.out.print("Question " + (x + 1) + ". "); //not using println here so a new line doesnt print
            question.take();
            this.userAnswers[x] = question.userResponse;
            x++;
        }

        /// -------- remove this, just a debugger to check that my answers are actually saved - they are =D
        /*for (String b : this.userAnswers) {
            System.out.println("These are the saved answers\n" + b);
        }*/

        serialize.saveUserAnswers(userAnswers, surveyResponseFolder, nameOfSurvey);
    }

    public void modify() {
        Serialize serialize = new Serialize();


        Display.displayString("What question do you wish to modify?");
        questionToModify = UserInput.getOption(0, this.questions.size() + 1);
        modifyQuestionPrompt(questionToModify);

        //if (this.questions.get(questionToModify - 1) instanceof MultipleChoice) {
        if (this.questions.get(questionToModify - 1).getClass().equals(MultipleChoice.class)) {
            Display.displayString("Do you wish to modify the choices?");
            modifyChoicesYesOrNo = UserInput.getString();
            if (modifyChoicesYesOrNo.equalsIgnoreCase("yes")) {
                modifyQuestionChoices(questionToModify);
            }
        }


        serialize.modifySurvey(this, this.nameOfSurvey);
    }

    public void modifyQuestionPrompt(int toModify) {

        Display.displayString("Question " + toModify + ". " + this.questions.get(toModify - 1).getPrompt() + "\n");
        Display.displayString("Do you wish to modify the prompt?");
        modifyPrompt = UserInput.getString();
        if (modifyPrompt.equalsIgnoreCase("yes")) {
            Display.displayString("Enter a new prompt:");
            newPrompt = UserInput.getString();
            this.questions.get(toModify - 1).setPrompt(newPrompt);
        } else if (modifyPrompt.equalsIgnoreCase("no")) {
        } else {
            Display.displayString("Please enter yes or no.");
            modifyQuestionPrompt(toModify);
        }


    }

    public void modifyQuestionChoices(int questionBeingModified) {
        int choiceToModify;
        String newChoice, modifyAnotherQuestionYesOrNo;
        Display.displayString("Which choice do you want to modify?");
        Display.displayStringArray(this.questions.get(questionBeingModified - 1).getQuestionChoices());
        choiceToModify = UserInput.getInt();
        Display.displayString("Enter new choice #" + choiceToModify);
        newChoice = UserInput.getString();
        this.questions.get(questionBeingModified - 1).modifyQuestionChoice(choiceToModify - 1, newChoice);
        Display.displayString("Do you wish to modify another question?");
        modifyAnotherQuestionYesOrNo = UserInput.getString();
        if (modifyAnotherQuestionYesOrNo.equalsIgnoreCase("yes")) {
            modify();
        }

        //ask if we want to modify another question - done in above methods
    }
}
