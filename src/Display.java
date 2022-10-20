import java.io.Serializable;

public class Display implements Serializable {
    private static final long serialVersionUID = -2210274800574981255L;

    public static void displayStringArray(String[] stringArray) {

        int option;

        for (int i = 0; i < stringArray.length; i++) {
            option = i + 1;
            System.out.println((" " + option + "." + stringArray[i]));
        }

    }
}
