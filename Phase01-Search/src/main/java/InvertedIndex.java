import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {
    public static HashMap<String, ArrayList<String>> wordLocations = new HashMap<>();

    public InvertedIndex() {

    }

    public void addWord(String word, String docID) {
        if (wordLocations.containsKey(word.toLowerCase())) {
            if (!wordLocations.get(word.toLowerCase()).contains(docID)) {
                wordLocations.get(word.toLowerCase()).add(docID);
            }
        } else {
            ArrayList<String> locations = new ArrayList<>();
            locations.add(docID);
            wordLocations.put(word.toLowerCase(), locations);
        }
    }

    public ArrayList<String> search(String word) {
        if (wordLocations.containsKey(word.toLowerCase())) {
            return wordLocations.get(word.toLowerCase());
        }
        return null;
    }
}
