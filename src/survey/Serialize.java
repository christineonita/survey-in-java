package survey;

import java.io.*;

public class Serialize implements Serializable {
    protected String surveyFolderName = "MySurveys";

    protected void saveSurvey(Survey survey) {

        String surveyPath, surveyName;
        Display.displayString("Enter the name you want to save this file as --> ");
        surveyName = UserInput.getString();

        new File("./" + surveyFolderName).mkdirs();
        surveyPath = surveyFolderName + "/" + surveyName + ".ser";

        try {
            FileOutputStream fileOut = new FileOutputStream(surveyPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(survey);
            out.close();
            fileOut.close();
            Display.displayString("Saved in " + surveyFolderName + "/" + surveyName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    protected Survey loadSurvey() {
        Survey survey = null;
        String surveyPath;
        int surveyNumber;

        File f = new File(surveyFolderName);
        //File[] files = f.listFiles(File::isDirectory);
        File[] files = f.listFiles();

        //System.out.println("starting printing file names"); // - debugger
        //System.out.println(files.length); // - debugger


        for (int j = 0; j < files.length; j++) {
            //surveyNumber = j + 1;
            Display.displayString((j + 1) + ") " + files[j].getName());
        }

        surveyNumber = UserInput.getOption(0, files.length + 1);
        surveyPath = surveyFolderName + "/" + files[surveyNumber - 1].getName();

        try {
            FileInputStream fileIn = new FileInputStream(surveyPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            survey = (Survey) in.readObject();
            in.close();
            fileIn.close();
            Display.displayString("Survey file " + surveyPath + " has been loaded.");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Survey class not found");
            c.printStackTrace();
        }

        survey.path = surveyFolderName;

        return survey;
    }
}
