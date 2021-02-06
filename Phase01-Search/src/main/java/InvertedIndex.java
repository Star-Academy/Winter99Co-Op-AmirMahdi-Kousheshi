import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {
    public static HashMap<String, ArrayList<String>> wordLocations = new HashMap<>();

    public InvertedIndex() {

    }

    public void addWord(String word, String docID) {
        String normalizeWord=normalize(word);
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
        if (wordLocations.containsKey(word.toLowerCase())) {
            return wordLocations.get(word.toLowerCase());
        }
        return null;
    }

    private String normalize(String word){
        return word.replaceAll("[(_)'\"\\.]","");
    }
}
