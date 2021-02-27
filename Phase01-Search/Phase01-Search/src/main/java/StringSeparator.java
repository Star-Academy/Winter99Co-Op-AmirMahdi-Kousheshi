import java.util.ArrayList;

public interface StringSeparator {

    String normalize(String word);

    ArrayList<String> separateWordsBySign(String[] splitInput, String regex);
}
