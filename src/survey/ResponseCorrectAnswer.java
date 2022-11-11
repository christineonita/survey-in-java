package survey;

public class ResponseCorrectAnswer {
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
}
