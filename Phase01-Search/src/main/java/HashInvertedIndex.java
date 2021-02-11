import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HashInvertedIndex implements StringSeparation, InvertedIndex {
    public static Map<String, ArrayList<String>> wordLocations = new HashMap<>();

    public HashInvertedIndex() {

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

    public String normalize(String word) {
        return word.replaceAll("\\W+", "");
    }

    public ArrayList<String> search(String searchInput) {
        String[] splitInput = searchInput.split(" ");
        ArrayList<String> noSignWordsFinalDocIDs = new ArrayList<>
                (checkSubscriptionForNoSignWords(findWordsFromInput(splitInput, "")));

        ArrayList<String> plusSignWordsFinalDocIDs = new ArrayList<>
                (checkSubscriptionForSignWords(findWordsFromInput(splitInput, "+")));

        ArrayList<String> minusSignWordsFinalDocIDs = new ArrayList<>
                (checkSubscriptionForSignWords(findWordsFromInput(splitInput, "-")));

        return finalCheck(noSignWordsFinalDocIDs, plusSignWordsFinalDocIDs, minusSignWordsFinalDocIDs);
    }

    public ArrayList<String> findWordsFromInput(String[] splitInput, String regex) {
        ArrayList<String> strings = new ArrayList<>();
        for (String word : splitInput) {
            if (regex.equals("")) {
                strings.add(word);
            } else {
                if (word.startsWith(regex)) {
                    strings.add(word.replace(regex, ""));
                }

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
        if (noSignWordsDocs.size() == 0 && plusSignWordsDocs.size() == 0) {
            return firstFinalCheckCondition(minusSignWordsDocs);
        } else {
            return thirdFinalCheckCondition(plusSignWordsDocs, noSignWordsDocs, minusSignWordsDocs);
        }
    }

    private ArrayList<String> firstFinalCheckCondition(ArrayList<String> minusSignWordsDocs) {
        ArrayList<String> finalDocs = new ArrayList<>();
        if (minusSignWordsDocs.size() != 0) {
            for (File allFile : FileReader.getAllFiles()) {
                finalDocs.add(allFile.getName());
            }
            finalDocs.removeAll(minusSignWordsDocs);
        }
        return finalDocs;
    }

    private ArrayList<String> secondFinalCheckCondition(ArrayList<String> plusSignWordsDocs, HashMap<String, Boolean> hashMap) {
        ArrayList<String> finalDocs = new ArrayList<>();
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
        return finalDocs;
    }

    private ArrayList<String> thirdFinalCheckCondition(ArrayList<String> plusSignWordsDocs,
                                                       ArrayList<String> noSignWordsDocs,
                                                       ArrayList<String> minusSignWordsDocs) {
        if (noSignWordsDocs.size() == 0) {
            return removeMinusWordsDocsFromFinalDocs(minusSignWordsDocs, plusSignWordsDocs);
        } else {
            HashMap<String, Boolean> hashMap = new HashMap<>();
            for (String noSignWordsDoc : noSignWordsDocs) {
                hashMap.put(noSignWordsDoc, false);
            }
            if (plusSignWordsDocs.size() != 0) {
                return removeMinusWordsDocsFromFinalDocs(minusSignWordsDocs, secondFinalCheckCondition(plusSignWordsDocs, hashMap));
            } else {
                return removeMinusWordsDocsFromFinalDocs(minusSignWordsDocs, (ArrayList<String>) hashMap.keySet());
            }
        }
    }

    private ArrayList<String> removeMinusWordsDocsFromFinalDocs(ArrayList<String> minusSignWordsDocs, ArrayList<String> finalDocs) {
        for (String minusSignWordsDoc : minusSignWordsDocs) {
            finalDocs.remove(minusSignWordsDoc);
        }
        return finalDocs;
    }

    public ArrayList<String> findDoc(String word) {
        ArrayList<String> docIDs = new ArrayList<>();
        if (wordLocations.containsKey(word.toLowerCase())) {
            docIDs.addAll(wordLocations.get(word.toLowerCase()));
        }
        return docIDs;
    }
}
