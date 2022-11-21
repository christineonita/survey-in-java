package survey;

import java.io.Serializable;

public class Menus implements Serializable {
    private static final long serialVersionUID = 5292926198099734510L;

    private final String[] surveyOrTestMenu = {"Survey", "Test", "Quit"};
    private final String[] surveyMenu = {"Create a new survey", "Display an existing Survey", "Load an existing Survey", "Save the current Survey",
            "Take an existing Survey", "Modify an existing Survey", "Tabulate a survey", "Return to previous menu"/*, "Quit"*/};
    private final String[] questionTypes = {"Add a new T/F question", "Add a new multiple-choice question", "Add a new short answer question", "Add a new essay question",
            "Add a new date question", "Add a new matching question", "Return to previous menu"/*, "Quit"*/};

    private final String[] testMenu = {"Create a new Test", "Display an existing Test without correct answers", "Display an existing Test with correct answers", "Load an existing Test",
            "Save the current Test", "Take an existing Test", "Modify an existing Test", "Tabulate a Test", "Grade a Test", "Return to the previous menu"/*, "Quit"*/};

    public void mainMenu() {
        int surveyOrTest;
        Display.displayStringArray(surveyOrTestMenu);

        surveyOrTest = UserInput.getOption(0, surveyOrTestMenu.length + 1);

        switch (surveyOrTest) {
            case 1:
                mainSurveyMenu();
            case 2:
                mainTestMenu();
            case 3:
                Display.displayString("Quitting program.... Bye!");
                System.exit(0);
                break;
            default:
                Display.displayString("Please enter an integer value between 1 and " + surveyOrTestMenu.length);
                break;
        }
    }

    public void mainSurveyMenu() {
        Survey survey = new Survey();
        int selectedSurveyMenuItem;

        while (true) {

            // prints survey menu
            Display.displayStringArray(surveyMenu);

            // accepts user input to choose option
            selectedSurveyMenuItem = UserInput.getOption(0, surveyMenu.length + 1);

            switch (selectedSurveyMenuItem) {
                case 1:
                    survey = questionTypesMenu(survey);
                    break;
                case 2:
                    try {
                        survey = loadSurvey();
                        survey.display();
                    } catch (NullPointerException e) {
                        Display.displayString("No survey has been made yet.\n");
                    }
                    break;
                case 3:
                    try {
                        survey = loadSurvey();
                    } catch (NullPointerException e) {
                        Display.displayString("No survey has been made yet.\n");
                    }
                    break;
                case 4:
                    saveSurvey(survey);
                    break;
                case 5:
                    try {
                        survey = loadSurvey();
                        survey.take();
                    } catch (NullPointerException e) {
                        Display.displayString("No survey has been made yet.\n");
                    }
                    break;
                case 6:
                    try {
                        survey = loadSurvey();
                        survey.modify();
                    } catch (NullPointerException e) {
                        Display.displayString("No survey has been made yet.\n");
                    }
                    break;
                case 7:
                    System.out.println("need to finish code code for tabulating survey");
                    survey = loadSurvey();
                    tabulateSurvey(survey);
                    break;
                case 8:
                    mainMenu();
                default:
                    Display.displayString("Please enter an integer value between 1 and " + surveyMenu.length);
                    break;

            }
        }
    }

    public Survey questionTypesMenu(Survey survey) {
        int selectedQuestionType;
        survey.clearAllQuestions();
        boolean n = true;
        String saveBeforeLeaving;

        while (n) {
            // prints survey menu
            Display.displayStringArray(questionTypes);

            // accepts user input to choose option
            selectedQuestionType = UserInput.getOption(0, questionTypes.length + 1);

            switch (selectedQuestionType) {
                case 1:
                    survey.addTrueOrFalse();
                    break;
                case 2:
                    survey.addMultipleChoice();
                    break;
                case 3:
                    survey.addShortAnswer();
                    break;
                case 4:
                    survey.addEssay();
                    break;
                case 5:
                    survey.addValidDate();
                    break;
                case 6:
                    survey.addMatching();
                    break;
                case 7:
                    Display.displayString("Save before leaving?");

                    while (true) {
                        saveBeforeLeaving = UserInput.getString();
                        if (saveBeforeLeaving.equalsIgnoreCase("yes")) {
                            saveSurvey(survey);
                            return survey;
                        } else if (saveBeforeLeaving.equalsIgnoreCase("no")) {
                            return survey;
                        } else {
                            Display.displayString("Please enter yes or no.");
                        }
                    }
                default:
                    return survey;
            }
        }
        return survey;
    }

    public void mainTestMenu() {
        Test test = new Test();
        int selectedTestMenuItem;

        while (true) {
            Display.displayStringArray(testMenu);

            selectedTestMenuItem = UserInput.getOption(0, testMenu.length + 1);

            switch (selectedTestMenuItem) {
                case 1:
                    test = questionTypesMenu(test);
                    break;
                case 2:
                    try {
                        test = loadTest();
                        test.display();
                    } catch (NullPointerException e) {
                        Display.displayString("No test has been made yet.\n");
                    }
                    break;
                case 3:
                    try {
                        test = loadTest();
                        test.displayTestWithCorrectAnswers();
                    } catch (NullPointerException e) {
                        Display.displayString("No test has been made yet.\n");
                    }
                    break;
                case 4:
                    try {
                        test = loadTest();
                    } catch (NullPointerException e) {
                        Display.displayString("No test has been made yet.\n");
                    }
                    break;
                case 5:
                    saveTest(test);
                    break;
                case 6:
                    try {
                        test = loadTest();
                        test.take();
                    } catch (NullPointerException e) {
                        Display.displayString("No test has been made yet.\n");
                    }
                    break;
                case 7:
                    try {
                        test = loadTest();
                        test.modify();
                    } catch (NullPointerException e) {
                        Display.displayString("No test has been made yet.\n");
                    }

                    break;
                case 8:
                    test = loadTest();
                    tabulateTest(test);
                    break;
                case 9:
                    test = loadTest();
                    test.grade();
                    break;
                case 10:
                    mainMenu();
                default:
                    Display.displayString("Please enter an integer value between 1 and " + testMenu.length);
                    break;
            }
        }
    }

    public Test questionTypesMenu(Test test) {
        int selectedQuestionType;
        test.clearAllQuestions();
        boolean n = true;
        String saveBeforeLeaving;

        while (n) {
            // prints survey menu
            Display.displayStringArray(questionTypes);

            // accepts user input to choose option
            selectedQuestionType = UserInput.getOption(0, questionTypes.length + 1);

            switch (selectedQuestionType) {
                case 1:
                    test.addTrueOrFalse();
                    break;
                case 2:
                    test.addMultipleChoice();
                    break;
                case 3:
                    test.addShortAnswer();
                    break;
                case 4:
                    test.addEssay(); // note that essay has no correct answer so no need for a new addEssay method override in test class
                    break;
                case 5:
                    test.addValidDate();
                    break;
                case 6:
                    test.addMatching();
                    break;
                case 7:
                    Display.displayString("Save before leaving?");

                    while (true) {
                        saveBeforeLeaving = UserInput.getString();
                        if (saveBeforeLeaving.equalsIgnoreCase("yes")) {
                            saveTest(test);
                            return test;
                        } else if (saveBeforeLeaving.equalsIgnoreCase("no")) {
                            return test;
                        } else {
                            Display.displayString("Please enter yes or no.");
                        }
                    }
                default:
                    return test;
            }
        }
        return test;
    }


    public void tabulateSurvey(Survey survey) {
        Serialize serialize = new Serialize();
        serialize.tabulateSurvey(survey, survey.questions);
    }

    public Survey loadSurvey() {
        Serialize serialize = new Serialize();
        return serialize.loadSurvey();
    }

    public void saveSurvey(Survey survey) {
        Serialize serialize = new Serialize();
        serialize.saveSurvey(survey);
    }

    public void saveTest(Test test) {
        Serialize serialize = new Serialize();
        serialize.saveTest(test);
    }

    public Test loadTest() {
        Serialize serialize = new Serialize();
        return serialize.loadTest();
    }

    public void tabulateTest(Test test) {
        Serialize serialize = new Serialize();
        serialize.tabulateTest(test, test.questions);
    }
}
