import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InvertedIndex {
    public static HashMap<String, ArrayList<String>> wordLocations = new HashMap<>();

    public InvertedIndex() {

    }

    public void addWord(String word, String docID) {
        String normalizeWord = normalize(word);
        if (wordLocations.containsKey(normalizeWord.toLowerCase())) {
            if (!wordLocations.get(normalizeWord.toLowerCase()).contains(docID)) {
                wordLocations.get(normalizeWord.toLowerCase()).add(docID);
            }
        } else {
            ArrayList<String> locations = new ArrayList<>();
            locations.add(docID);
            wordLocations.put(normalizeWord.toLowerCase(), locations);
        }
    }

    public ArrayList<String> search(String word) {
        ArrayList<String> docIDs = new ArrayList<>();
        if (wordLocations.containsKey(word.toLowerCase())) {
            docIDs.addAll(wordLocations.get(word.toLowerCase()));
        }
        return docIDs;
    }

    private String normalize(String word) {
        return word.replaceAll("\\W+", "");
    }
}
