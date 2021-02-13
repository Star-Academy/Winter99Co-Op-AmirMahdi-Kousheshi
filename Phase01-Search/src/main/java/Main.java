import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static HashInvertedIndex invertedIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader(new FileSaving() {
            @Override
            public void addAllFiles() {
                Collections.addAll(allFiles, folder.listFiles());
            }

            @Override
            public List<File> getAllFiles() {
                return allFiles;
            }
        });
        invertedIndex = new HashInvertedIndex(new StringSeparation() {
            @Override
            public String normalize(String word) {
                return word.replaceAll("\\W+", "");
            }

            @Override
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
        });

        while (true) {
            System.out.println("Enter your input:");
            String searchInput = scanner.nextLine();
            System.out.println(invertedIndex.search(searchInput));
        }
    }


}
