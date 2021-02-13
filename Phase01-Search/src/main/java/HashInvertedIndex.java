import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashInvertedIndex implements InvertedIndex {
    StringSeparation stringSeparation;
    private static Map<String, ArrayList<String>> wordLocations = new HashMap<>();

    public HashInvertedIndex(StringSeparation stringSeparation) {
        this.stringSeparation = stringSeparation;
        this.initialiseSearch();
    }

    public ArrayList<String> search(String searchInput) {
        String[] splitInput = searchInput.split(" ");
        ArrayList<String> noSignWordsFinalDocIDs = new ArrayList<>
                (checkSubscriptionForNoSignWords(stringSeparation.findWordsFromInput(splitInput, "")));

        ArrayList<String> plusSignWordsFinalDocIDs = new ArrayList<>
                (checkSubscriptionForSignWords(stringSeparation.findWordsFromInput(splitInput, "+")));

        ArrayList<String> minusSignWordsFinalDocIDs = new ArrayList<>
                (checkSubscriptionForSignWords(stringSeparation.findWordsFromInput(splitInput, "-")));

        return finalCheck(noSignWordsFinalDocIDs, plusSignWordsFinalDocIDs, minusSignWordsFinalDocIDs, stringSeparation.findWordsFromInput(splitInput, "-"));
    }

    private void initialiseSearch() {
        for (File allFile : FileReader.getAllFiles()) {
            try {
                this.readFromFile(allFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split("\\W+");
            for (String s : input) {
                this.addWord(s, file.getName());
            }
        }
    }

    private void addWord(String word, String docID) {
        String normalizeWord = stringSeparation.normalize(word);
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
        for (String word : SignWords) {
            checker.addAll(findDoc(word));
        }
        return checker;
    }

    private ArrayList<String> finalCheck(ArrayList<String> noSignWordsDocs, ArrayList<String> plusSignWordsDocs, ArrayList<String> minusSignWordsDocs, ArrayList<String> minusSignWords) {
        if (noSignWordsDocs.size() != 0) {
            return thirdFinalCheckCondition(plusSignWordsDocs, noSignWordsDocs, minusSignWordsDocs);
        } else {
            return firstFinalCheckCondition(plusSignWordsDocs, minusSignWordsDocs, minusSignWords);
        }
    }

    private ArrayList<String> firstFinalCheckCondition(ArrayList<String> plusSignWordsDocs, ArrayList<String> minusSignWordsDocs, ArrayList<String> minusSignWords) {
        ArrayList<String> finalDocs = new ArrayList<>();
        if (plusSignWordsDocs.size() == 0 && minusSignWordsDocs.size() == 0 && minusSignWords.size() == 0) {
            return finalDocs;
        } else {
            return checkNoneDocsFind(plusSignWordsDocs, minusSignWordsDocs, minusSignWords);
        }
    }

    private ArrayList<String> checkNoneDocsFind(ArrayList<String> plusSignWordsDocs, ArrayList<String> minusSignWordsDocs, ArrayList<String> minusSignWords) {
        ArrayList<String> finalDocs = new ArrayList<>();
        if (minusSignWordsDocs.size() != 0 && plusSignWordsDocs.size() != 0) {
            plusSignWordsDocs.removeAll(minusSignWordsDocs);
            return plusSignWordsDocs;
        } else if (plusSignWordsDocs.size() != 0) {
            plusSignWordsDocs.removeAll(minusSignWordsDocs);
            return plusSignWordsDocs;
        } else {
            if (minusSignWords.size() != 0) {
                FileReader.getAllFiles().forEach((file) -> finalDocs.add(file.getName()));
                finalDocs.removeAll(minusSignWordsDocs);
            }
            return finalDocs;
        }
    }

    private ArrayList<String> secondFinalCheckCondition
            (ArrayList<String> plusSignWordsDocs, HashMap<String, Boolean> hashMap) {
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
                ArrayList<String> hashMapKeySet = new ArrayList<>(hashMap.keySet());
                return removeMinusWordsDocsFromFinalDocs(minusSignWordsDocs, hashMapKeySet);
            }
        }
    }

    private ArrayList<String> removeMinusWordsDocsFromFinalDocs
            (ArrayList<String> minusSignWordsDocs, ArrayList<String> finalDocs) {
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
