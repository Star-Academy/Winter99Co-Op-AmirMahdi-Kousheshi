import java.io.File;
import java.util.ArrayList;
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

    private String normalize(String word) {
        return word.replaceAll("\\W+", "");
    }

    public ArrayList<String> search(String searchInput) {
        String[] splitInput = searchInput.split(" ");
        ArrayList<String> noSignWords = new ArrayList<>(findNothingWords(splitInput));
        ArrayList<String> plusSignWords = new ArrayList<>(findPlusWords(splitInput));
        ArrayList<String> minusSignWords = new ArrayList<>(findMinusWords(splitInput));
        ArrayList<String> noSignWordsFinalDocIDs = new ArrayList<>(checkSubscriptionForNoSignWords(noSignWords));
        ArrayList<String> plusSignWordsFinalDocIDs = new ArrayList<>(checkSubscriptionForSignWords(plusSignWords));
        ArrayList<String> minusSignWordsFinalDocIDs = new ArrayList<>(checkSubscriptionForSignWords(minusSignWords));
        return finalCheck(noSignWordsFinalDocIDs, plusSignWordsFinalDocIDs, minusSignWordsFinalDocIDs);
    }

    private ArrayList<String> findNothingWords(String[] splitInput) {
        ArrayList<String> strings = new ArrayList<>();
        for (String word : splitInput) {
            if (!word.startsWith("+") || !word.startsWith("-")) {
                strings.add(word);
            }
        }
        return strings;
    }

    private ArrayList<String> findPlusWords(String[] splitInput) {
        ArrayList<String> strings = new ArrayList<>();
        for (String word : splitInput) {
            if (word.startsWith("+")) {
                strings.add(word.replace("+", ""));
            }
        }
        return strings;
    }

    private ArrayList<String> findMinusWords(String[] splitInput) {
        ArrayList<String> strings = new ArrayList<>();
        for (String word : splitInput) {
            if (word.startsWith("-")) {
                strings.add(word.replace("-", ""));
            }
        }
        return strings;
    }

    private ArrayList<String> checkSubscriptionForNoSignWords(ArrayList<String> noSignWords) {
        ArrayList<String> checker = new ArrayList<>();
        for (String nothingWord : noSignWords) {
            if (findDoc(nothingWord).size() != 0) {
                checker.addAll(findDoc(nothingWord));
                break;
            }
        }
        HashMap<String, Boolean> hashMap = subscriptionOfNoSignWords(checker, noSignWords);
        checker.clear();
        for (String s : hashMap.keySet()) {
            if (hashMap.get(s)) {
                checker.add(s);
            }
        }
        return checker;
    }

    private HashMap<String, Boolean> subscriptionOfNoSignWords(ArrayList<String> checker, ArrayList<String> noSignWords) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        for (String s : checker) {
            hashMap.put(s, false);
        }
        for (String nothingWord : noSignWords) {
            for (String docID : findDoc(nothingWord)) {
                if (hashMap.containsKey(docID)) {
                    hashMap.put(docID, true);
                }
            }
        }
        return hashMap;
    }

    private ArrayList<String> checkSubscriptionForSignWords(ArrayList<String> SignWords) {
        ArrayList<String> checker = new ArrayList<>();
        for (String minusSignWord : SignWords) {
            checker.addAll(findDoc(minusSignWord));
        }
        return checker;
    }

    private ArrayList<String> finalCheck(ArrayList<String> noSignWordsDocs, ArrayList<String> plusSignWordsDocs, ArrayList<String> minusSignWordsDocs) {
        ArrayList<String> finalDocs = new ArrayList<>();
        if (noSignWordsDocs.size() == 0 && plusSignWordsDocs.size() == 0) {
            if (minusSignWordsDocs.size() != 0) {
                for (File allFile : FileReader.getAllFiles()) {
                    finalDocs.add(allFile.getName());
                }
                finalDocs.removeAll(minusSignWordsDocs);
            }
            return finalDocs;
        } else {
            if (noSignWordsDocs.size() == 0) {
                finalDocs.addAll(plusSignWordsDocs);
                for (String minusSignWordsDoc : minusSignWordsDocs) {
                    finalDocs.remove(minusSignWordsDoc);
                }
            } else {
                HashMap<String, Boolean> hashMap = new HashMap<>();
                for (String noSignWordsDoc : noSignWordsDocs) {
                    hashMap.put(noSignWordsDoc, false);
                }
                if (plusSignWordsDocs.size() != 0) {
                    for (String plusSignWordsDoc : plusSignWordsDocs) {
                        if (hashMap.containsKey(plusSignWordsDoc)) {
                            hashMap.put(plusSignWordsDoc, true);
                        }
                    }
                    for (String s : hashMap.keySet()) {
                        if (hashMap.get(s)) {
                            finalDocs.add(s);
                        }
                    }
                } else {
                    finalDocs.addAll(hashMap.keySet());
                }
                for (String minusSignWordsDoc : minusSignWordsDocs) {
                    finalDocs.remove(minusSignWordsDoc);
                }
            }
        }
        return finalDocs;
    }

    private ArrayList<String> findDoc(String word) {
        ArrayList<String> docIDs = new ArrayList<>();
        if (wordLocations.containsKey(word.toLowerCase())) {
            docIDs.addAll(wordLocations.get(word.toLowerCase()));
        }
        return docIDs;
    }
}
