import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashInvertedIndex implements InvertedIndex {
    StringSeparator stringSeparator;
    FileSaving fileSaving;
    private static Map<String, ArrayList<String>> wordLocations = new HashMap<>();

    public HashInvertedIndex(StringSeparator stringSeparator, FileSaving fileSaving) {
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
        return initialiseSearch(noSignWords, plusSignWords, minusSignWords);
    }

    private ArrayList<String> initialiseSearch(ArrayList<String> noSignWords, ArrayList<String> plusSignWords, ArrayList<String> minusSignWords) {
        ArrayList<String> noSignWordDocs = new ArrayList<>(findDocsOfNoSignWords(noSignWords));
        ArrayList<String> plusSignWordDocs = new ArrayList<>(findDocsOfSignWords(plusSignWords));
        ArrayList<String> minusSignWordDocs = new ArrayList<>(findDocsOfSignWords(minusSignWords));
        return findFinalDocs(noSignWordDocs, plusSignWordDocs, minusSignWordDocs, minusSignWords);
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

    public ArrayList<String> findDocsOfAWord(String word) {
        ArrayList<String> docIDs = new ArrayList<>();
        if (wordLocations.containsKey(word.toLowerCase())) {
            docIDs.addAll(wordLocations.get(word.toLowerCase()));
        }
        return docIDs;
    }

    private ArrayList<String> findDocsOfNoSignWords(ArrayList<String> noSignWords) {
        ArrayList<String> checker = new ArrayList<>();
        for (String nothingWord : noSignWords) {
            if (findDocsOfAWord(nothingWord).size() != 0) {
                checker.addAll(findDocsOfAWord(nothingWord));
                break;
            }
        }
        HashMap<String, Boolean> hashMap = markSameDocsOfNoSignWords(checker, noSignWords);
        ArrayList<String> docs = new ArrayList<>();
        hashMap.keySet().forEach((doc) -> {
            if (hashMap.get(doc)) {
                docs.add(doc);
            }
        });
        return docs;
    }

    private HashMap<String, Boolean> markSameDocsOfNoSignWords(ArrayList<String> checker, ArrayList<String> noSignWords) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        checker.forEach((doc) -> hashMap.put(doc, false));
        for (String nothingWord : noSignWords) {
            for (String docID : findDocsOfAWord(nothingWord)) {
                if (hashMap.containsKey(docID)) {
                    hashMap.put(docID, true);
                }
            }
        }
        return hashMap;
    }

    private ArrayList<String> findDocsOfSignWords(ArrayList<String> SignWords) {
        ArrayList<String> checker = new ArrayList<>();
        for (String word : SignWords) {
            checker.addAll(findDocsOfAWord(word));
        }
        return checker;
    }

    private ArrayList<String> findFinalDocs(ArrayList<String> noSignWordsDocs, ArrayList<String> plusSignWordsDocs, ArrayList<String> minusSignWordsDocs, ArrayList<String> minusSignWords) {
        ArrayList<String> finalDocs = new ArrayList<>();
        ArrayList<String> signWordsDocs = new ArrayList<>(findFinalDocsOfSignWords(plusSignWordsDocs,minusSignWordsDocs,minusSignWords));
        if (noSignWordsDocs.size() != 0) {
            if (signWordsDocs.size() != 0) {
                noSignWordsDocs.forEach((doc) -> {
                    if (signWordsDocs.contains(doc)) {
                        finalDocs.add(doc);
                    }
                });
            } else {
                finalDocs.addAll(noSignWordsDocs);
            }
            return finalDocs;
        } else {
            return signWordsDocs;
        }
    }

    private ArrayList<String> findFinalDocsOfSignWords(ArrayList<String> plusSignWordsDocs,
                                                       ArrayList<String> minusSignWordsDocs, ArrayList<String> minusSignWords) {
        ArrayList<String> docs = new ArrayList<>();
        if (plusSignWordsDocs.size() != 0) {
            docs.addAll(plusSignWordsDocs);
            if (minusSignWords.size() != 0) {
                fileSaving.getAllFiles().forEach((file) -> docs.add(file.getName()));
                docs.removeAll(minusSignWordsDocs);
            }
        } else {
            if (minusSignWords.size() != 0) {
                fileSaving.getAllFiles().forEach((file) -> docs.add(file.getName()));
                docs.removeAll(minusSignWordsDocs);
            }
        }
        return docs;
    }

}
