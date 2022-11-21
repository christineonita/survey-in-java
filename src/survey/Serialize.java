package survey;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Serialize implements Serializable {
    private static final long serialVersionUID = 6435622019401604877L;
    protected String surveyFolderName = "MySurveys", responsesFolderName = "SurveyResponses", testFolderName = "MyTests", testResponsesFolderName = "TestResponses";
    String survName, tName;

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
        survey.display();
    }

    protected void saveTest(Test test) {

        String testPath, testName;
        Display.displayString("Enter the name you want to save this file as --> ");
        testName = UserInput.getString();

        new File("." + File.separator + testFolderName).mkdirs();
        testPath = testFolderName + File.separator + testName + ".ser";

        try {
            FileOutputStream fileOut = new FileOutputStream(testPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(test);
            out.close();
            fileOut.close();
            Display.displayString("Saved in " + testPath);
            System.out.println(); // just for neatness
        } catch (IOException i) {
            i.printStackTrace();
        }
        test.display();
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

        // this deletes the past responses of the survey being modified since one or more of the responses would be invalid
        File f = new File(responsesFolderName + File.separator + nameOfSurvey + "_responses");
        if (f.exists()) {
            for (File g : f.listFiles()) {
                g.delete();
            }
            f.delete();
        }


        survey.display();
    }

    protected void modifyTest(Test test, String nameOfTest) {
        String testPath;

        new File("." + File.separator + testFolderName).mkdirs();
        testPath = testFolderName + File.separator + nameOfTest + ".ser";

        try {
            FileOutputStream fileOut = new FileOutputStream(testPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(test);
            out.close();
            fileOut.close();
            Display.displayString("Survey " + nameOfTest + ".ser updated successfully and saved in " + testPath);
        } catch (IOException i) {
            i.printStackTrace();
        }

        //System.out.println(test.questions.get(0).getPrompt()); // - debugger
        // this deletes the past responses of the survey being modified since one or more of the responses would be invalid

        File f = new File(testResponsesFolderName + File.separator + nameOfTest + "_responses");
        if (f.exists()) {
            for (File g : f.listFiles()) {
                g.delete();
            }
            f.delete();
        }

        test.display();
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

    public /*String[][]*/ void tabulateSurvey(Survey survey, ArrayList<Question> questions) {
        File f = new File("." + File.separator + this.responsesFolderName + File.separator + survey.nameOfSurvey + "_responses");

        File[] files = f.listFiles();
        String[][] questionResponse = new String[0][];

        HashMap<String, Integer> questionResponsesCounter = null;

        if (!f.exists() || files.length == 0) {
            Display.displayString("There are no responses to the chosen survey yet.\n");
        } else {
            for (int i = 0; i < questions.size(); i++) {
                questionResponsesCounter = new HashMap<String, Integer>();

                countResponses(survey.nameOfSurvey, this.responsesFolderName, files, questionResponse, i, questionResponsesCounter);
                questions.get(i).tabulate(questionResponsesCounter);
                questionResponsesCounter.clear(); // this clears the hashmap after each question
            }
        }
    }

    public /*String[][]*/ void tabulateTest(Test test, ArrayList<Question> questions) {
        File f = new File("." + File.separator + this.testResponsesFolderName + File.separator + test.nameOfTest + "_responses");

        File[] files = f.listFiles();
        String[][] questionResponse = new String[0][];

        HashMap<String, Integer> questionResponsesCounter = null;

        if (!f.exists() || files.length == 0) {
            Display.displayString("There are no responses to the chosen survey yet.\n");
        } else {
            for (int i = 0; i < questions.size(); i++) {
                questionResponsesCounter = new HashMap<String, Integer>();

                countResponses(test.nameOfTest, this.testResponsesFolderName, files, questionResponse, i, questionResponsesCounter);
                questions.get(i).tabulate(questionResponsesCounter);
                questionResponsesCounter.clear(); // this clears the hashmap after each question
            }
        }
    }

    protected void countResponses(String nameOfSurveyOrTest, String testOrSurveyResponseFolder, File[] files, String[][] questionResponse, int loop, HashMap<String, Integer> questionResponsesCounter) {
        int responseFileCount = 0;
        for (File file : files) {
            //System.out.println(file.getName()); // debugger
            try {
                FileInputStream fileIn = new FileInputStream("." + File.separator + testOrSurveyResponseFolder + File.separator + nameOfSurveyOrTest + "_responses" + File.separator + nameOfSurveyOrTest + "_response" + (responseFileCount + 1) + ".ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                questionResponse = (String[][]) in.readObject();
                //System.out.println("seeing array of arrays" + Arrays.deepToString(surveyResponse)); // debugger
                String lines[] = questionResponse[loop][0].split("\\r?\\n");
                for (int x = 0; x < lines.length; x++) {

                    if (!questionResponsesCounter.containsKey(lines[x])) {
                        //System.out.println(">" + lines[x] + "<"); // debugger
                        questionResponsesCounter.put(lines[x], 1);
                    } else {
                        questionResponsesCounter.put(lines[x], questionResponsesCounter.get(lines[x]) + 1);
                    }
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

    protected Test loadTest() {
        Test test = null;
        String testPath;
        int testNumber;

        File f = new File(testFolderName);

        File[] files = f.listFiles();

        if (files.length == 0) {
            Display.displayString("\nThere are no tests to load.\n");
        } else {
            Display.displayString("Select a test to load: ");
            for (int j = 0; j < files.length; j++) {
                Display.displayString((j + 1) + ") " + files[j].getName());
            }

            testNumber = UserInput.getOption(0, files.length + 1);
            testPath = testFolderName + File.separator + files[testNumber - 1].getName();

            try {
                FileInputStream fileIn = new FileInputStream(testPath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                test = (Test) in.readObject();
                in.close();
                fileIn.close();
                Display.displayString("Test file " + testPath + " has been loaded.\n");
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                Display.displayString("Test class not found");
                c.printStackTrace();
            }

            test.nameOfTest = files[testNumber - 1].getName().replace(".ser", "");
            this.tName = files[testNumber - 1].getName().replace(".ser", "");
            test.testResponseFolder = testResponsesFolderName;
        }

        return test;
    }

    public String[][] loadTestResponses(String nameOfTest) {
        String[][] singleTestResponse = new String[0][];
        String testResponsePath;
        int testResponseNumber;

        File f = new File(testResponsesFolderName + File.separator + nameOfTest + "_responses");
        //System.out.println(">" + testResponsesFolderName + File.separator + nameOfTest + "<");

        File[] files = f.listFiles();

        if (files == null || files.length == 0) {
            Display.displayString("\nThere are no test responses to load.\n");
        } else {
            Display.displayString("Select a test response to load: ");
            for (int j = 0; j < files.length; j++) {
                Display.displayString((j + 1) + ") " + files[j].getName());
            }

            testResponseNumber = UserInput.getOption(0, files.length + 1);
            testResponsePath = testResponsesFolderName + File.separator + nameOfTest + "_responses" + File.separator + files[testResponseNumber - 1].getName();


            try {
                FileInputStream fileIn = new FileInputStream(testResponsePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                //test = (Test) in.readObject();
                singleTestResponse = (String[][]) in.readObject();
                //System.out.println("seeing test arrays" + Arrays.deepToString(singleTestResponse));
                in.close();
                fileIn.close();
                Display.displayString("Test response file " + testResponsePath + " has been loaded.\n");
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return singleTestResponse;
    }
}
