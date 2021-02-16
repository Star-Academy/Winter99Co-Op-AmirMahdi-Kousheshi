import java.util.ArrayList;
import java.util.HashMap;

public class SearchController implements InvertedIndex {
    HashInvertedIndex hashInvertedIndex;

    public SearchController(HashInvertedIndex hashInvertedIndex) {
        this.hashInvertedIndex = hashInvertedIndex;
    }

    private ArrayList<String> findFinalDocs(ArrayList<String> noSignWordsDocs, ArrayList<String> plusSignWordsDocs, ArrayList<String> minusSignWordsDocs, ArrayList<String> minusSignWords) {
        ArrayList<String> signWordsDocs = new ArrayList<>(findFinalDocsOfSignWords(plusSignWordsDocs, minusSignWordsDocs, minusSignWords));
        if (noSignWordsDocs.size() != 0) {
            ArrayList<String> finalDocs = new ArrayList<>();
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
                docs.removeAll(minusSignWordsDocs);
            }
        } else {
            if (minusSignWords.size() != 0) {
                this.hashInvertedIndex.fileSaving.getAllFiles().forEach((file) -> docs.add(file.getName()));
                docs.removeAll(minusSignWordsDocs);
            }
        }
        return docs;
    }

    public ArrayList<String> initialiseSearch(ArrayList<String> noSignWords, ArrayList<String> plusSignWords, ArrayList<String> minusSignWords) {
        ArrayList<String> noSignWordDocs = new ArrayList<>(findDocsOfNoSignWords(noSignWords));
        ArrayList<String> plusSignWordDocs = new ArrayList<>(findDocsOfSignWords(plusSignWords));
        ArrayList<String> minusSignWordDocs = new ArrayList<>(findDocsOfSignWords(minusSignWords));
        return findFinalDocs(noSignWordDocs, plusSignWordDocs, minusSignWordDocs, minusSignWords);
    }

    private ArrayList<String> findDocsOfSignWords(ArrayList<String> SignWords) {
        ArrayList<String> checker = new ArrayList<>();
        for (String word : SignWords) {
            checker.addAll(findDocsOfAWord(word));
        }
        return checker;
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

    public ArrayList<String> findDocsOfAWord(String word) {
        ArrayList<String> docIDs = new ArrayList<>();
        if (hashInvertedIndex.wordLocations.containsKey(word.toLowerCase())) {
            docIDs.addAll(hashInvertedIndex.wordLocations.get(word.toLowerCase()));
        }
        return docIDs;
    }

}
