package survey;

import java.io.Serializable;
import java.util.Arrays;

public class ResponseCorrectAnswer implements Serializable {
    private static final long serialVersionUID = 1891686652405264430L;
    String[] correctResponses, responses;

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
    }

    public String[] getCorrectResponses() {
        return correctResponses;
    }

    public boolean compare(String[] correctResponses, String[] testResponse) {
        String[] sortedCorrectResponses = correctResponses[0].split("\\r?\\n");
        String[] sortedTestResponses = testResponse[0].split("\\r?\\n");

        Arrays.sort(sortedCorrectResponses);
        Arrays.sort(sortedTestResponses);

        return Arrays.equals(sortedCorrectResponses, sortedTestResponses);
    }
}
