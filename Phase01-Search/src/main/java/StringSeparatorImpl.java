import java.util.ArrayList;

public class StringSeparatorImpl implements StringSeparator {

    @Override
    public String normalize(String word) {
        return word.replaceAll("\\W+", "");
    }

    @Override
    public ArrayList<String> separateWordsBySign(String[] splitInput, String regex) {
        ArrayList<String> strings = new ArrayList<>();
        for (String word : splitInput) {
            if (regex.equals("")) {
                strings.add(word);
            } else if (word.startsWith(regex)) {
                strings.add(word.replace(regex, ""));
            }

        }
        return strings;
    }
}

