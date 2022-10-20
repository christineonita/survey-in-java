import java.io.Serializable;
import java.util.Scanner;

public class Menus implements Serializable {
    private static final long serialVersionUID = 5292926198099734510L;
    private final String[] surveyMenu = {"Create a new survey", "Display an existing Survey", "Load an existing Survey", "Save the current Survey",
            "Take the current Survey", "Modify the current Survey", "Quit"};
    private final String[] questionTypes = {"Add a new T/F question", "Add a new multiple-choice question", "Add a new short answer question", "Add a new essay question",
            "Add a new date question", "Add a new matching question", "Return to previous menu"};

    public void mainMenu() {
        Survey survey = new Survey();
        int selectedSurveyMenuItem;

        while (true) {

            // prints survey menu
            Display.displayStringArray(surveyMenu);

            // accepts user input to choose option
            Scanner scanner = new Scanner(System.in);

            try {
                selectedSurveyMenuItem = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter an integer value");
                continue;
            }

            switch (selectedSurveyMenuItem) {
                case 1:
                    //System.out.println("Code needed for: " + surveyMenu[selectedSurveyMenuItem - 1]);
                    //questionTypesMenu(survey);
                    survey = questionTypesMenu(survey);
                    break;
                case 2:
                    //Display.displaySurvey(survey);
                    //displaySurvey(survey);
                    System.out.println("Code needed for: " + surveyMenu[selectedSurveyMenuItem - 1]);
                    break;
                case 3:
                    //survey = loadSurvey();
                    System.out.println("Code needed for: " + surveyMenu[selectedSurveyMenuItem - 1]);
                    break;
                case 4:
                    //saveSurvey(survey);
                    System.out.println("Code needed for: " + surveyMenu[selectedSurveyMenuItem - 1]);
                    break;
                case 5:
                    //survey = loadSurvey();
                    //survey.take();
                    System.out.println("Code needed for: " + surveyMenu[selectedSurveyMenuItem - 1]);
                    break;
                case 6:
                    //survey.modify();
                    System.out.println("Code needed for: " + surveyMenu[selectedSurveyMenuItem - 1]);
                    break;
                case 7:
                    System.exit(0);
                    System.out.println("Quitting program.... Bye!");
                    break;
                default:
                    //mainMenu();
                    System.out.println("Please enter an integer value between 1 and " + surveyMenu.length);
                    break;

            }
        }
    }

    public Survey questionTypesMenu(Survey survey) {
        int selectedQuestionType;
        survey.clearAllQuestions();
        boolean n = true;

        while (n) {
            // prints survey menu
            Display.displayStringArray(questionTypes);

            // accepts user input to choose option
            Scanner scanner = new Scanner(System.in);

            try {
                selectedQuestionType = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter an integer value between 1 and " + questionTypes.length);
                continue;
            }

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
                    survey.modify();
                    break;
                case 7:
                    //mainMenu();
                    //break;
                    return survey;
                default:
                    //System.out.println("Please enter an integer value between 1 and " + questionTypes.length);
                    //break;
                    return survey;
            }
        }
        return survey;
    }
}
