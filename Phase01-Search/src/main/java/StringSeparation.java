import java.util.ArrayList;

public interface StringSeparation {

    public String normalize(String word);

    public ArrayList<String> findNothingWords(String[] splitInput);

    public ArrayList<String> findPlusWords(String[] splitInput);

    public ArrayList<String> findMinusWords(String[] splitInput);
}
