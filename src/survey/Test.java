package survey;

import java.io.Serializable;
import java.text.DecimalFormat;

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

    @Override
    protected void addShortAnswer() {
        Question question = new ShortAnswer();
        setQuestionPrompt(question);
        question.setRequiredNumberOfResponses();
        question.setShortAnswerLimit();

        question.setCorrectAnswer();

        questions.add(question);
    }

    // no need for addEssay method in test class since it's the same as survey

    @Override
    protected void addValidDate() {
        Question question = new ValidDate();
        setQuestionPrompt(question);

        question.setCorrectAnswer();

        questions.add(question);
    }

    @Override
    protected void addMatching() {
        Question question = new Matching();
        setQuestionPrompt(question);

        question.setNumberOfFirstColumnItems();
        question.setNumberOfSecondColumnItems();

        question.setFirstColumn();
        question.setSecondColumn();

        question.setCorrectAnswer();

        questions.add(question);
    }

    public void displayTestWithCorrectAnswers() {
        int choicesLoop = 1;
        for (int x = 0; x < this.questions.size(); x++) {
            Display.displayString("\n Question " + (x + 1) + ". " + this.questions.get(x).getPrompt());
            if ((this.questions.get(x) instanceof Essay) && !(this.questions.get(x) instanceof ShortAnswer)) {
                continue;
            } else if (this.questions.get(x) instanceof MultipleChoice || this.questions.get(x) instanceof TrueOrFalse) {
                for (String s : this.questions.get(x).getQuestionChoices()) {
                    Display.displayString("   " + choicesLoop + ".) " + s);
                    choicesLoop++;
                }
            } else if (this.questions.get(x) instanceof Matching) {
                Display.displayMatchingColumns(this.questions.get(x));
            }
            choicesLoop = 1;
            Display.displayString(" The correct answers are: ");
            displayCorrectAnswers(this.questions.get(x).getCorrectResponses());
            Display.displayString("");
        }
    }

    protected void displayCorrectAnswers(String[] correctResponses) {
        String[] lines = correctResponses[0].split("\\r?\\n");
        for (int i = 0; i < lines.length; i++) {
            Display.displayString(("    " + lines[i]));
        }
    }

    @Override
    public void take() {
        int x = 0;
        Serialize serialize = new Serialize();
        userAnswers = new String[this.questions.size()][];


        for (Question question : questions) {
            System.out.print("Question " + (x + 1) + ". "); //not using println here so a new line doesn't print
            question.take();

            question.setResponses(question.userResponse);

            this.userAnswers[x] = question.getResponses();

            x++;
        }

        serialize.displayUserResponses(this.questions, this.userAnswers);

        serialize.saveUserAnswers(userAnswers, testResponseFolder, nameOfTest);
    }

    @Override
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

        String modifyCorrectAnswer;
        Display.displayString("Do you wish to modify the correct answer(s) for this question?");
        modifyCorrectAnswer = UserInput.getString();
        if (modifyCorrectAnswer.equalsIgnoreCase("yes")) {
            modifyCorrectAnswer(this.questions.get(questionToModify - 1));
        }

        String modifyAnotherQuestionYesOrNo;
        Display.displayString("Do you wish to modify another question?");
        modifyAnotherQuestionYesOrNo = UserInput.getString();
        if (modifyAnotherQuestionYesOrNo.equalsIgnoreCase("yes")) {
            modify();
        }

        serialize.modifyTest(this, this.nameOfTest);
    }

    protected void modifyCorrectAnswer(Question question) {
        question.modifyCorrectAnswer();
    }

    public void grade() {
        ResponseCorrectAnswer responseCorrectAnswer = new ResponseCorrectAnswer();
        int essayQuestionCounter = 0;
        double finalScore, score = 100;
        String testResultMessage;
        DecimalFormat df = new DecimalFormat("0.00");
        String[][] testResponses;
        Serialize serialize = new Serialize();
        testResponses = serialize.loadTestResponses(this.nameOfTest);
        for (int i = 0; i < this.questions.size(); i++) {
            if (this.questions.get(i) instanceof Essay && !(this.questions.get(i) instanceof ShortAnswer)) {
                score -= ((double) 100 / this.questions.size());
                essayQuestionCounter++;
                continue;
            }
            if (!responseCorrectAnswer.compare(this.questions.get(i).getCorrectResponses(), (testResponses[i]))) {
                score -= ((double) 100 / this.questions.size());
            }
        }

        finalScore = (score < 0) ? 0.00 : Double.parseDouble(df.format(score));

        testResultMessage = (essayQuestionCounter < 1) ? "Your score on this test is " + finalScore + "%." : "Your score on this test is " + finalScore + "%. The test was worth 100 points, but only " + Double.parseDouble(df.format(((double) 100 - ((double) 100 * essayQuestionCounter / this.questions.size())))) + " of those points could be auto graded because there was/were " + essayQuestionCounter + " essay question(s).";

        Display.displayString(testResultMessage);
    }
}
