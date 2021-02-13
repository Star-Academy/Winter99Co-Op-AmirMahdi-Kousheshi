import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HashInvertedIndexTest {
    @Test
    public void searchFirstTest() {
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
        String searchInput = "get help +disease -ok";
        HashInvertedIndex hashInvertedIndex = new HashInvertedIndex(new StringSeparation() {
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
        List<String> expectedValues = Arrays.asList("2.txt");
        Assert.assertEquals(expectedValues, hashInvertedIndex.search(searchInput));
        searchInput = "+disease";
        Assert.assertEquals(expectedValues, hashInvertedIndex.search(searchInput));
        searchInput = "-ok";
        List<String> expectedValues1 = Arrays.asList("1.txt", "2.txt");
        Assert.assertEquals(expectedValues1, hashInvertedIndex.search(searchInput));
    }
}
