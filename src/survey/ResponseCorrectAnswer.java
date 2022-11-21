package survey;

import java.io.Serializable;
import java.util.Arrays;

public class ResponseCorrectAnswer implements Serializable {
    private static final long serialVersionUID = 1891686652405264430L;
    String[] responses;// = new String[1];
    String[] correctResponses;

    ResponseCorrectAnswer() {
    }

    public String[] getResponses() {
        return responses;
    }

    public void setResponses(String userResponse) {
        responses = new String[1];
        responses[0] = userResponse;
    }

    public void setCorrectAnswer(String creatorResponse) {
        correctResponses = new String[1];
        correctResponses[0] = creatorResponse;
        //System.out.println("--->" + correctResponses[0] + "<---"); // debugger
    }

    public String[] getCorrectResponses() {
        return correctResponses;
    }

    public boolean compare(String[] correctResponses, String[] testResponse) {
        return Arrays.equals(correctResponses, testResponse);
    }
}
