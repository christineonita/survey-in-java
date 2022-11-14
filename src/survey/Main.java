package survey;

public class Main {
    public static void main(String[] args) {
        Menus menus = new Menus();
        menus.mainMenu();
    }
    // todo - remember to implement serializable
    // todo - when modifying a survey or test delete the old responses (so just delete the corresponding response folder) since they’ll be invalid
    // todo - for mult choice test, sort responses - alphabetically? - (both taker and creator) before comparing for grading
    // todo - (not sure about this) - valid date response should not be after current date - but what if question asks for a future date? - hw2b feedback said valid date took invalid responses?
    // TODO - (-15) Still missing response correct answer class, vital to grading. - i think ive done this right
    // TODO - (-7.5) You need some more functions in Test, like modify, take as that functionality will differ from Survey due to the added requirements about correct answers.
}