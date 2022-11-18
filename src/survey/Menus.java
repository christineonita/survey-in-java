package survey;

import java.io.Serializable;

public class Menus implements Serializable {
    private static final long serialVersionUID = 5292926198099734510L;

    private final String[] surveyOrTestMenu = {"Survey", "Test", "Quit"};
    private final String[] surveyMenu = {"Create a new survey", "Display an existing Survey", "Load an existing Survey", "Save the current Survey",
            "Take an existing Survey", "Modify an existing Survey", "Tabulate a survey", "Return to previous menu", "Quit"};
    private final String[] questionTypes = {"Add a new T/F question", "Add a new multiple-choice question", "Add a new short answer question", "Add a new essay question",
            "Add a new date question", "Add a new matching question", "Return to previous menu", "Quit"};

    private final String[] testMenu = {"Create a new Test", "Display an existing Test without correct answers", "Display an existing Test with correct answers", "Load an existing Test",
            "Save the current Test", "Take the current Test", "Modify the current Test", "Tabulate a Test", "Grade a Test", "Return to the previous menu", "Quit"};

    public void mainMenu() {
        int surveyOrTest;
        Display.displayStringArray(surveyOrTestMenu);

        surveyOrTest = UserInput.getOption(0, surveyOrTestMenu.length + 1);

        switch (surveyOrTest) {
            case 1:
                mainSurveyMenu();
            case 2:
                //Display.displayString("no code for test at all yet");
                mainTestMenu();
                //break; // remove this after adding test menu
            case 3:
                Display.displayString("Quitting program.... Bye!");
                System.exit(0);
                break;
            default:
                Display.displayString("Please enter an integer value between 1 and " + surveyOrTestMenu.length);
                break;
        }
    }

    public void mainTestMenu() {
        Test test = new Test();
        int selectedTestMenuItem;

        while (true) {
            Display.displayStringArray(testMenu);

            selectedTestMenuItem = UserInput.getOption(0, testMenu.length + 1);

            switch (selectedTestMenuItem) {
                case 1:
                    Display.displayString("code needed for ------Create a new Test------");
                    break;
                case 2:
                    Display.displayString("code needed for ------Display an existing Test without correct answers------");
                    break;
                case 3:
                    Display.displayString("code needed for ------Display an existing Test with correct answers------");
                    break;
                case 4:
                    Display.displayString("code needed for ------Load an existing Test------");
                    break;
                case 5:
                    Display.displayString("code needed for ------Save the current Test------");
                    break;
                case 6:
                    Display.displayString("code needed for ------Take the current Test------");
                    break;
                case 7:
                    Display.displayString("code needed for ------Modify the current Test------");
                    break;
                case 8:
                    Display.displayString("code needed for ------Tabulate a Test------");
                    break;
                case 9:
                    Display.displayString("code needed for ------Grade a Test------");
                    break;
                case 10:
                    mainMenu();
                case 11:
                    Display.displayString("Quitting program.... Bye!");
                    System.exit(0);
                    break;
                default:
                    Display.displayString("Please enter an integer value between 1 and " + testMenu.length);
                    break;
            }
        }
    }

    public void mainSurveyMenu() {
        Survey survey = new Survey();
        String[][] survResponse;// = new Response();
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
                        survey.displaySurvey();
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
                    /*survResponse = */
                    tabulateSurvey(survey);
                    //System.out.println("seeing array of arrays" + Arrays.deepToString(survResponse)); // debugger
                    break;
                case 8:
                    mainMenu();
                case 9:
                    Display.displayString("Quitting program.... Bye!");
                    System.exit(0);
                    break;
                default:
                    Display.displayString("Please enter an integer value between 1 and " + surveyMenu.length);
                    break;

            }
        }
    }

    public /*String[][]*/ void tabulateSurvey(Survey survey) {
        Serialize serialize = new Serialize();
        //return serialize.tabulateSurvey(survey, survey.questions);
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
                case 8:
                    Display.displayString("Quitting program.... Bye!");
                    System.exit(0);
                    break;
                default:
                    return survey;
            }
        }
        return survey;
    }
}
