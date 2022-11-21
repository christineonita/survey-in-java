package survey;

import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable {

    private static final long serialVersionUID = 4925296878791173007L;
    public String nameOfSurvey;
    String surveyResponseFolder, newPrompt, modifyPrompt, modifyChoicesYesOrNo, modifyColumnChoicesYesOrNo;// = "SurveyResponses";
    ArrayList<Question> questions = null;
    String[][] userAnswers;

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
        Question question = new Matching();
        setQuestionPrompt(question);

        question.setNumberOfFirstColumnItems();
        question.setNumberOfSecondColumnItems();

        question.setFirstColumn();
        question.setSecondColumn();


        questions.add(question);
    }

    protected void clearAllQuestions() {
        questions.clear();
    }

    public void display() {
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
        Display.displayString("");
    }

    public void take() {
        int x = 0;
        Serialize serialize = new Serialize();
        userAnswers = new String[this.questions.size()][];


        for (Question question : questions) {
            Display.displayString("\n");
            System.out.print("Question " + (x + 1) + ". "); //not using println here so a new line doesn't print
            question.take();

            question.setResponses(question.userResponse);

            this.userAnswers[x] = question.getResponses();

            x++;
        }

        serialize.displayUserResponses(this.questions, this.userAnswers);

        serialize.saveUserAnswers(userAnswers, surveyResponseFolder, nameOfSurvey);
    }

    public void modify() {
        Serialize serialize = new Serialize();


        Display.displayString("What question do you wish to modify?");
        questionToModify = UserInput.getOption(0, this.questions.size() + 1);
        modifyQuestionPrompt(questionToModify);

        if (this.questions.get(questionToModify - 1).getClass().equals(MultipleChoice.class)) {
            Display.displayString("Do you wish to modify the choices?");
            modifyChoicesYesOrNo = UserInput.getString();
            if (modifyChoicesYesOrNo.equalsIgnoreCase("yes")) {
                modifyQuestionChoices(questionToModify);
            }
        }

        if (this.questions.get(questionToModify - 1).getClass().equals(Matching.class)) {
            Display.displayString("Do you wish to modify the columns?");
            modifyColumnChoicesYesOrNo = UserInput.getString();
            if (modifyColumnChoicesYesOrNo.equalsIgnoreCase("yes")) {
                modifyColumnChoices(questionToModify);
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
        choiceToModify = UserInput.getOption(0, this.questions.get(questionBeingModified - 1).getQuestionChoices().length + 1);
        Display.displayString("Enter new choice #" + choiceToModify);
        newChoice = UserInput.getString();
        this.questions.get(questionBeingModified - 1).modifyQuestionChoice(choiceToModify - 1, newChoice);

    }

    public void modifyColumnChoices(int questionToModify) {
        int columnToModify;
        Display.displayString("Do you wish to modify column 1 or two? (enter either 1 for the left or 2 for the right)");
        Display.displayMatchingColumns(this.questions.get(questionToModify - 1));
        columnToModify = UserInput.getOption(0, 3);
        if (columnToModify == 1) {
            modifyFirstColumn(questionToModify);
        } else {
            modifySecondColumn(questionToModify);
        }
    }

    public void modifySecondColumn(int questionToModify) {
        int itemToModify;
        String newItem;
        Display.displayString("Which item do you want to modify?");
        Display.displayStringArray(this.questions.get(questionToModify - 1).getSecondColumn());
        itemToModify = UserInput.getOption(0, this.questions.get(questionToModify - 1).getSecondColumn().length + 1);
        Display.displayString("Enter a new item #" + itemToModify);
        newItem = UserInput.getString();

        this.questions.get(questionToModify - 1).modifySecondColumnItem(itemToModify - 1, newItem);
    }

    public void modifyFirstColumn(int questionToModify) {
        int itemToModify;
        String newItem;
        Display.displayString("Which item do you want to modify?");
        Display.displayStringArray(this.questions.get(questionToModify - 1).getFirstColumn());
        itemToModify = UserInput.getOption(0, this.questions.get(questionToModify - 1).getFirstColumn().length + 1);
        Display.displayString("Enter a new item #" + itemToModify);
        newItem = UserInput.getString();

        this.questions.get(questionToModify - 1).modifyFirstColumnItem(itemToModify - 1, newItem);
    }
}
