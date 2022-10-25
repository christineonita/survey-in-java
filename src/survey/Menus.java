package survey;

import java.io.Serializable;

public class Menus implements Serializable {
    private static final long serialVersionUID = 5292926198099734510L;
    private final String[] surveyMenu = {"Create a new survey", "Display an existing Survey", "Load an existing Survey", "Save the current Survey",
            "Take an existing Survey", "Modify an existing Survey", "Quit"};
    private final String[] questionTypes = {"Add a new T/F question", "Add a new multiple-choice question", "Add a new short answer question", "Add a new essay question",
            "Add a new date question", "Add a new matching question", "Return to previous menu"};

    public void mainMenu() {
        Survey survey = new Survey();
        int selectedSurveyMenuItem;

        while (true) {

            // prints survey menu
            Display.displayStringArray(surveyMenu);

            // accepts user input to choose option
            selectedSurveyMenuItem = UserInput.getInt();

            switch (selectedSurveyMenuItem) {
                case 1:
                    survey = questionTypesMenu(survey);
                    break;
                case 2:
                    try {
                        survey = loadSurvey();
                        survey.displaySurvey();
                    } catch (NullPointerException e) {
                        System.out.println("No survey has been made yet.\n");
                    }
                    break;
                case 3:
                    try {
                        survey = loadSurvey();
                    } catch (NullPointerException e) {
                        System.out.println("No survey has been made yet.\n");
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
                        System.out.println("No survey has been made yet.\n");
                    }
                    break;
                case 6:
                    try {
                        survey = loadSurvey();
                        survey.modify();
                    } catch (NullPointerException e) {
                        System.out.println("No survey has been made yet.\n");
                    }
                    break;
                case 7:
                    System.exit(0);
                    System.out.println("Quitting program.... Bye!");
                    break;
                default:
                    System.out.println("Please enter an integer value between 1 and " + surveyMenu.length);
                    break;

            }
        }
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
                    System.out.println("Save before leaving?");

                    while (true) {
                        saveBeforeLeaving = UserInput.getString();
                        if (saveBeforeLeaving.equalsIgnoreCase("yes")) {
                            saveSurvey(survey);
                            return survey;
                        } else if (saveBeforeLeaving.equalsIgnoreCase("no")) {
                            return survey;
                        } else {
                            System.out.println("Please enter yes or no.");
                        }
                    }
                default:
                    return survey;
            }
        }
        return survey;
    }
}
