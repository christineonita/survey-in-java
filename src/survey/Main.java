package survey;

public class Main {
    public static void main(String[] args) {
        Menus menus = new Menus();
        menus.mainMenu();
    }

    // todo - replace all souts with display class
    // todo - (not sure about this) - valid date response should not be after current date - but what if question asks for a future date? - hw2b feedback said valid date took invalid responses?
}