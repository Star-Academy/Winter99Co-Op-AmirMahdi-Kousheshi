import java.util.ArrayList;

public interface StringSeparation {

    public String normalize(String word);

    public ArrayList<String> findWordsFromInput(String[] splitInput, String regex);
}
