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
}
