import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {
    public static HashMap<String, ArrayList<String>> wordLocations = new HashMap<>();

    public InvertedIndex() {

    }

    public void addWord(String word, String docID) {
        if (wordLocations.containsKey(word)) {
            wordLocations.get(word).add(docID);
        } else {
            ArrayList<String> locations = new ArrayList<>();
            locations.add(docID);
            wordLocations.put(word, locations);
        }
    }

    public ArrayList<String> search(String word) {
        if (wordLocations.containsKey(word)) {
            return wordLocations.get(word);
        }
        return null;
    }
}
