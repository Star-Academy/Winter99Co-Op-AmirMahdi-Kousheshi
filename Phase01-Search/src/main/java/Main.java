import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static InvertedIndex invertedIndex;
    private static FileReader fileReader;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        fileReader = new FileReader();
        invertedIndex = new InvertedIndex();
        for (File allFile : fileReader.getAllFiles()) {
            try {
                separate(allFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

//        while (true) {
//            System.out.println("What is your search word?");
//            String searchWord = scanner.nextLine();
//            if (invertedIndex.search(searchWord) != null) {
//                System.out.println(invertedIndex.search(searchWord).toString());
//            } else {
//                System.out.println("Not Found");
//            }
//        }
        ArrayList<String> noSignWords = new ArrayList<>();
        ArrayList<String> plusSignWords = new ArrayList<>();
        ArrayList<String> minusSignWords = new ArrayList<>();
        ArrayList<String> noSignWordsFinalDocIDs = new ArrayList<>();
        ArrayList<String> plusSignWordsFinalDocIDs = new ArrayList<>();
        ArrayList<String> minusSignWordsFinalDocIDs = new ArrayList<>();
        while (true) {
            System.out.println("Enter your input:");
            String searchWord = scanner.nextLine();
            String[] splitInput = searchWord.split(" ");
            noSignWords.addAll(findNothingWords(splitInput));
            plusSignWords.addAll(findPlusWords(splitInput));
            minusSignWords.addAll(findMinusWords(splitInput));
            noSignWordsFinalDocIDs.addAll(checkSubscriptionForNoSignWords(noSignWords));
            plusSignWordsFinalDocIDs.addAll(checkSubscriptionForPlusSignWords(plusSignWords));
            minusSignWordsFinalDocIDs.addAll(checkSubscriptionForMinusSignWords(minusSignWords));
            System.out.println(finalCheck(noSignWordsFinalDocIDs, plusSignWordsFinalDocIDs, minusSignWordsFinalDocIDs).toString());

        }
    }

    private static void separate(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] word = reader.nextLine().split("\\W+");
            for (String s : word) {
                invertedIndex.addWord(s, file.getName());
            }
        }
    }

    private static ArrayList<String> findNothingWords(String[] splitInput) {
        ArrayList<String> strings = new ArrayList<>();
        for (String word : splitInput) {
            if (!word.startsWith("+") || !word.startsWith("-")) {
                strings.add(word);
            }
        }
        return strings;
    }

    private static ArrayList<String> findPlusWords(String[] splitInput) {
        ArrayList<String> strings = new ArrayList<>();
        for (String word : splitInput) {
            if (word.startsWith("+")) {
                strings.add(word.replace("+", ""));
            }
        }
        return strings;
    }

    private static ArrayList<String> findMinusWords(String[] splitInput) {
        ArrayList<String> strings = new ArrayList<>();
        for (String word : splitInput) {
            if (word.startsWith("-")) {
                strings.add(word.replace("-", ""));
            }
        }
        return strings;
    }

    private static ArrayList<String> checkSubscriptionForNoSignWords(ArrayList<String> noSignWords) {
        ArrayList<String> checker = new ArrayList<>();
        for (String nothingWord : noSignWords) {
            if (invertedIndex.search(nothingWord) != null) {
                checker.addAll(invertedIndex.search(nothingWord));
                break;
            }
        }
        HashMap<String, Boolean> hashMap = new HashMap<>();
        for (String s : checker) {
            hashMap.put(s, false);
        }
        for (String nothingWord : noSignWords) {
            for (String docID : invertedIndex.search(nothingWord)) {
                if (hashMap.containsKey(docID)) {
                    hashMap.put(docID, true);
                }
            }
        }
        checker.clear();
        for (String s : hashMap.keySet()) {
            if (hashMap.get(s)) {
                checker.add(s);
            }
        }
        return checker;
    }

    private static ArrayList<String> checkSubscriptionForPlusSignWords(ArrayList<String> plusSignWords) {
        ArrayList<String> checker = new ArrayList<>();
        for (String plusSignWord : plusSignWords) {
            checker.addAll(invertedIndex.search(plusSignWord));
        }
        return checker;
    }

    private static ArrayList<String> checkSubscriptionForMinusSignWords(ArrayList<String> minusSignWords) {
        ArrayList<String> checker = new ArrayList<>();
        for (File allFile : fileReader.getAllFiles()) {
            checker.add(allFile.getName());
        }
        for (String minusSignWord : minusSignWords) {
            checker.remove(minusSignWord);
        }
        return checker;
    }

    private static ArrayList<String> finalCheck(ArrayList<String> noSignWordsDocs, ArrayList<String> plusSignWordsDocs, ArrayList<String> minusSignWordsDocs) {
        ArrayList<String> finalDocs = new ArrayList<>();
        if (noSignWordsDocs.size() == 0 && plusSignWordsDocs.size() == 0) {
            return minusSignWordsDocs;
        } else {
            if (noSignWordsDocs.size() == 0) {
                finalDocs.addAll(plusSignWordsDocs);
                for (String minusSignWordsDoc : minusSignWordsDocs) {
                    if (plusSignWordsDocs.contains(minusSignWordsDoc)) {
                        finalDocs.remove(minusSignWordsDoc);
                    }
                }
            } else {
                HashMap<String, Boolean> hashMap = new HashMap<>();
                for (String noSignWordsDoc : noSignWordsDocs) {
                    hashMap.put(noSignWordsDoc, false);
                }
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

                for (String minusSignWordsDoc : minusSignWordsDocs) {
                    finalDocs.remove(minusSignWordsDoc);
                }
            }
        }
        return finalDocs;
    }
}
