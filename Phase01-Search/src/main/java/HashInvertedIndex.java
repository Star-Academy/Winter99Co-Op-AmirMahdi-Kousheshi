import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashInvertedIndex {
    public StringSeparator stringSeparator;
    public FileSaving fileSaving;
    SearchController searchController;
    public Map<String, ArrayList<String>> wordLocations = new HashMap<>();

    public HashInvertedIndex(StringSeparator stringSeparator, FileSaving fileSaving) {
        this.searchController = new SearchController(this);
        this.fileSaving = fileSaving;
        this.stringSeparator = stringSeparator;
        initialiseFindingFiles();
        this.initialiseReadingFiles();
    }

    public ArrayList<String> search(String searchInput) {
        String[] splitInput = searchInput.split(" ");
        ArrayList<String> noSignWords = new ArrayList<>(stringSeparator.separateWordsBySign(splitInput, ""));
        ArrayList<String> plusSignWords = new ArrayList<>(stringSeparator.separateWordsBySign(splitInput, "+"));
        ArrayList<String> minusSignWords = new ArrayList<>(stringSeparator.separateWordsBySign(splitInput, "-"));
        return this.searchController.initialiseSearch(noSignWords, plusSignWords, minusSignWords);
    }

    private void initialiseFindingFiles() {
        fileSaving.addAllFiles();
    }

    private void initialiseReadingFiles() {
        for (File file : fileSaving.getAllFiles()) {
            try {
                this.readFromFile(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            initialiseAddWord(scanner, file);
        }
    }

    private void initialiseAddWord(Scanner scanner, File file) {
        String[] input = scanner.nextLine().split("\\W+");
        for (String s : input) {
            this.addWord(s, file.getName());
        }
    }

    private void addWord(String word, String docID) {
        String normalizeWord = stringSeparator.normalize(word).toLowerCase();
        if (!wordLocations.containsKey(normalizeWord)) {
            wordLocations.put(normalizeWord, new ArrayList<String>());
        }
        ArrayList<String> locations = wordLocations.get(normalizeWord);
        if (!locations.contains(docID)) {
            locations.add(docID);
        }
    }
}
