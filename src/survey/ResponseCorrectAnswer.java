package survey;

import java.io.Serializable;

public class ResponseCorrectAnswer implements Serializable {
    private static final long serialVersionUID = 1891686652405264430L;
    String[] responses;// = new String[1];

    ResponseCorrectAnswer() {
    }

    public String[] getResponses() {
        return responses;
    }

    public void setResponses(String userResponse) {
        responses = new String[1];
        responses[0] = userResponse;
    }
    //todo - to grade tests
    // + compare (RCA a, RCA b): Bool
}
