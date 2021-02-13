import java.util.ArrayList;

public interface StringSeparation {

    String normalize(String word);

    ArrayList<String> findWordsFromInput(String[] splitInput, String regex);
}
