package survey;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Serialize implements Serializable {
    private static final long serialVersionUID = 6435622019401604877L;
    protected String surveyFolderName = "MySurveys", responsesFolderName = "SurveyResponses";
    String survName;

    Serialize() {
    }

    protected void saveSurvey(Survey survey) {

        String surveyPath, surveyName;
        Display.displayString("Enter the name you want to save this file as --> ");
        surveyName = UserInput.getString();

        new File("." + File.separator + surveyFolderName).mkdirs();
        surveyPath = surveyFolderName + File.separator + surveyName + ".ser";

        try {
            FileOutputStream fileOut = new FileOutputStream(surveyPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(survey);
            out.close();
            fileOut.close();
            Display.displayString("Saved in " + surveyPath);
            System.out.println(); // just for neatness
        } catch (IOException i) {
            i.printStackTrace();
        }
        survey.displaySurvey();
    }

    protected void modifySurvey(Survey survey, String nameOfSurvey) {
        String surveyPath;

        new File("." + File.separator + surveyFolderName).mkdirs();
        surveyPath = surveyFolderName + File.separator + nameOfSurvey + ".ser";

        try {
            FileOutputStream fileOut = new FileOutputStream(surveyPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(survey);
            out.close();
            fileOut.close();
            Display.displayString("Survey " + nameOfSurvey + ".ser updated successfully and saved in " + surveyPath);
        } catch (IOException i) {
            i.printStackTrace();
        }

        survey.displaySurvey();
    }

    protected Survey loadSurvey() {
        Survey survey = null;
        String surveyPath;
        int surveyNumber;

        File f = new File(surveyFolderName);

        File[] files = f.listFiles();

        if (files.length == 0) {
            Display.displayString("\nThere are no surveys to load.\n");
        } else {
            Display.displayString("Select a survey to load: ");
            for (int j = 0; j < files.length; j++) {
                Display.displayString((j + 1) + ") " + files[j].getName());
            }

            surveyNumber = UserInput.getOption(0, files.length + 1);
            surveyPath = surveyFolderName + File.separator + files[surveyNumber - 1].getName();

            try {
                FileInputStream fileIn = new FileInputStream(surveyPath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                survey = (Survey) in.readObject();
                in.close();
                fileIn.close();
                Display.displayString("Survey file " + surveyPath + " has been loaded.\n");
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                Display.displayString("Survey class not found");
                c.printStackTrace();
            }

            survey.nameOfSurvey = files[surveyNumber - 1].getName().replace(".ser", "");
            this.survName = files[surveyNumber - 1].getName().replace(".ser", "");
            survey.surveyResponseFolder = responsesFolderName;
        }

        return survey;
    }

    protected void saveUserAnswers(String[][] userAnswers, String surveyResponseFolder, String name) {
        String pth;
        String responsesFolderPerSurvey = surveyResponseFolder + File.separator + name + "_responses";

        new File(responsesFolderPerSurvey).mkdirs();
        int f = new File(responsesFolderPerSurvey).list().length;

        pth = "." + File.separator + responsesFolderPerSurvey + File.separator + name + "_response" + (f + 1) + ".ser";

        try {
            FileOutputStream fileOut = new FileOutputStream(pth);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(userAnswers);
            out.close();
            fileOut.close();
            Display.displayString("Survey responses saved in " + pth);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    protected void displayUserResponses(ArrayList<Question> questions, String[][] userAnswers) {
        Display.displayResponses(questions, userAnswers);
    }

    public /*String[][]*/ void loadSurveyResponses(Survey survey, ArrayList<Question> questions) { // todo - finish making this
        String[][] surveyResponse = new String[0][];
        int numOfQuestions = questions.size();
        HashMap<String, Integer> questionResponsesCounter = null;
        for (int i = 0; i < questions.size(); i++) {
            questionResponsesCounter = new HashMap<String, Integer>();
            //System.out.println(questions.get(i).getPrompt());
            if (questions.get(i) instanceof TrueOrFalse) {
                //System.out.println(Arrays.toString(questions.get(i).getQuestionChoices()));
                questionResponsesCounter.put("True", 0);
                questionResponsesCounter.put("False", 0);
            } else if (questions.get(i) instanceof MultipleChoice) {
                for (String choice : questions.get(i).getQuestionChoices()) {
                    questionResponsesCounter.put(choice, 0);
                }
            }


            //new File("." + File.separator + this.responsesFolderName + File.separator + survey.nameOfSurvey + "_responses").mkdirs(); - i just used 'if (!f.exists() || files.length == 0)' instead of this

            File f = new File("." + File.separator + this.responsesFolderName + File.separator + survey.nameOfSurvey + "_responses");

            File[] files = f.listFiles();

            if (!f.exists() || files.length == 0) {
                Display.displayString("There are no responses to the chosen survey yet.\n");
            } else {
                countResponses(survey.nameOfSurvey, files, surveyResponse, i, questionResponsesCounter);

            }
            //System.out.println(questions.get(i).getPrompt() + "\n" + questionResponsesCounter);
            printPromptAndResponseCount(questions.get(i).getPrompt(), questionResponsesCounter);
            questionResponsesCounter.clear();// this clears the hashmap after each question
        }

        //return surveyResponse;
    }

    private void countResponses(String nameOfSurvey, File[] files, String[][] surveyResponse, int loop, HashMap<String, Integer> questionResponsesCounter) {
        int responseFileCount = 0;
        for (File file : files) {
            //System.out.println(file.getName()); // debugger
            try {
                FileInputStream fileIn = new FileInputStream("." + File.separator + this.responsesFolderName + File.separator + nameOfSurvey + "_responses" + File.separator + nameOfSurvey + "_response" + (responseFileCount + 1) + ".ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                surveyResponse = (String[][]) in.readObject();
                //System.out.println("seeing array of arrays" + Arrays.deepToString(surveyResponse)); // debugger
                String lines[] = surveyResponse[loop][0].split("\\r?\\n");
                for (int x = 0; x < lines.length; x++) {
                    questionResponsesCounter.put(lines[x], questionResponsesCounter.get(lines[x]) + 1);
                }
                in.close();
                fileIn.close();
                //Display.displayString("Response file " + "." + File.separator + this.responsesFolderName + File.separator + survey.nameOfSurvey + "_responses" + File.separator + survey.nameOfSurvey + "_response" + (responseFileCount + 1) + ".ser has been loaded.\n");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException c) {
                Display.displayString("Survey class not found");
                c.printStackTrace();
            }
            responseFileCount++;
        }
    }

    private void printPromptAndResponseCount(String prompt, HashMap<String, Integer> questionResponsesCounter) {
        System.out.println(prompt);

        questionResponsesCounter.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });

        System.out.println();
    }
}
