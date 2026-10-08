package se.lexicon;

public class StringValidation {
    
    
    public static boolean IsEmptyString(String inputString) {
        boolean isEmpty = inputString == null || inputString.trim().isEmpty();
        if (isEmpty){
            IO.println("Empty input!");
        }
        return isEmpty;
    }
}
