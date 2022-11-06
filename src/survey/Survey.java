package survey;

import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable {

    private static final long serialVersionUID = 4925296878791173007L;
    public String nameOfSurvey;
    String surveyResponseFolder;// = "SurveyResponses";
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

        String prompt = UserInput.getString();
        question.setPrompt(prompt);
    }

    protected void addMultipleChoice() {
        Question question = new MultipleChoice();
        setQuestionPrompt(question);
        question.setQuestionChoices();
        questions.add(question);
    }

    protected void addTrueOrFalse() {
        Question question = new TrueOrFalse();
        setQuestionPrompt(question);
        question.setQuestionChoices();
        questions.add(question);
    }

    protected void addEssay() {
        Question question = new Essay();
        setQuestionPrompt(question);
        question.setRequiredNumberOfResponses();
        questions.add(question);

    }

    protected void addShortAnswer() {
        Question question = new ShortAnswer();
        setQuestionPrompt(question);
        question.setRequiredNumberOfResponses();
        question.setShortAnswerLimit();
        questions.add(question);
    }

    protected void addValidDate() {
        Question question = new ValidDate();
        setQuestionPrompt(question);
        questions.add(question);
    }

    protected void addMatching() {
        //System.out.println("code needed to add matching question to survey");
        // for matching questions mark the options like option A, B, C --> mak it so that the creator cannot choose above 26 for num of items in a column

        Question question = new Matching();
        setQuestionPrompt(question);

        //question.setNumberOfRows();

        question.setNumberOfFirstColumnItems();
        question.setNumberOfSecondColumnItems();

        question.setFirstColumn();
        question.setSecondColumn();


        questions.add(question);
    }

    protected void clearAllQuestions() {
        questions.clear();
    }

    public void displaySurvey() {
        int h;
        int choicesLoop = 1;
        for (int x = 0; x < this.questions.size(); x++) {
            Display.displayString("\n Question " + (x + 1) + ". " + this.questions.get(x).getPrompt());
            if (this.questions.get(x) instanceof MultipleChoice || this.questions.get(x) instanceof TrueOrFalse) {
                for (String s : this.questions.get(x).getQuestionChoices()) {
                    Display.displayString("   " + choicesLoop + ".) " + s);
                    choicesLoop++;
                }
            } else if (this.questions.get(x) instanceof Matching) {
                for (h = 0; h < Math.max(((Matching) this.questions.get(x)).numOfFirstColumnItems, ((Matching) this.questions.get(x)).numOfSecondColumnItems); h++) {

                    if (h + 1 > ((Matching) this.questions.get(x)).numOfFirstColumnItems) {
                        for (int f = h; f < ((Matching) this.questions.get(x)).numOfSecondColumnItems; f++) {
                            //System.out.println("extra 2nd --- " + this.questions.get(x).getSecondColumn()[f]);
                            Display.displayString("                              " + (f + 1) + ".) " + this.questions.get(x).getSecondColumn()[f]);
                        }
                        break;
                    } else if (h + 1 > ((Matching) this.questions.get(x)).numOfSecondColumnItems) {
                        for (int g = h; g < ((Matching) this.questions.get(x)).numOfFirstColumnItems; g++) {
                            //System.out.println("extra 1st --- " + this.questions.get(x).getFirstColumn()[g]);
                            Display.displayString("   " + (g + 1) + ".) " + this.questions.get(x).getFirstColumn()[g]);
                        }
                        break;
                    } else {
                        ((Matching) this.questions.get(x)).printTwoColumns("   " + (h + 1) + ".) " + this.questions.get(x).getFirstColumn()[h], (h + 1) + ".) " + this.questions.get(x).getSecondColumn()[h]);
                    }
                    //System.out.printf("   " + (h + 1) + ". " + this.questions.get(x).getFirstColumn()[h] + "\t" + (h + 1) + ". " + this.questions.get(x).getSecondColumn()[h]);
                }
            }
            choicesLoop = 1;
        }
        System.out.println();
    }

    public void take() {
        int x = 0;
        Serialize serialize = new Serialize();
        userAnswers = new String[this.questions.size()];

        for (Question question : questions) {
            System.out.print("Question " + (x + 1) + ". "); //not using println here so a new line doesn't print
            question.take();
            this.userAnswers[x] = question.userResponse;
            x++;
        }

        /// -------- remove this, just a debugger to check that my answers are actually saved - they are =D
        /*for (String b : this.userAnswers) {
            Display.displayString("These are the saved answers\n" + b + "\n");
        }*/

        //serialize.displayUserResponses(userAnswers);

        serialize.displayUserResponses(this.questions, userAnswers);

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
        String modifyAnotherQuestionYesOrNo;
        Display.displayString("Do you wish to modify another question?");
        modifyAnotherQuestionYesOrNo = UserInput.getString();
        if (modifyAnotherQuestionYesOrNo.equalsIgnoreCase("yes")) {
            modify();
        }

        serialize.modifySurvey(this, this.nameOfSurvey);
    }

    public void modifyQuestionPrompt(int toModify) {

        Display.displayString("Question " + toModify + ". " + this.questions.get(toModify - 1).getPrompt() + "\n");
        Display.displayString("Do you wish to modify the prompt?");
        modifyPrompt = UserInput.getString();
        if (modifyPrompt.equalsIgnoreCase("yes")) {
            if ((this.questions.get(toModify - 1) instanceof Essay) || (this.questions.get(toModify - 1) instanceof ShortAnswer)) {
                Display.displayString("Note that you cannot change the number of requested responses for this question");
            }
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
        String newChoice;
        Display.displayString("Which choice do you want to modify?");
        Display.displayStringArray(this.questions.get(questionBeingModified - 1).getQuestionChoices());
        choiceToModify = UserInput.getInt();
        Display.displayString("Enter new choice #" + choiceToModify);
        newChoice = UserInput.getString();
        this.questions.get(questionBeingModified - 1).modifyQuestionChoice(choiceToModify - 1, newChoice);

    }
}
