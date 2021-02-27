import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchControllerTest {
    static HashInvertedIndex hashInvertedIndex;
    static SearchController searchController;

    @BeforeClass
    public static void setup() {
        FileSavingImpl fileSaving = new FileSavingImpl("C://Users/Amkam/Desktop/Folder3");
        StringSeparatorImpl stringSeparator = new StringSeparatorImpl();
        hashInvertedIndex = new HashInvertedIndex(stringSeparator, fileSaving);
        searchController = new SearchController(hashInvertedIndex);
    }

    @Test
    public void findDocsOfAWord() {
        ArrayList<String> docs = new ArrayList<>();
        docs.add("2.txt");
        Assert.assertEquals(docs, searchController.findDocsOfAWord("disease"));
    }

    @Test
    public void searchTest() {
        ArrayList<String> plusSignWords = new ArrayList<>();
        ArrayList<String> minusSignWords = new ArrayList<>();
        ArrayList<String> noSignWords = new ArrayList<>();
        minusSignWords.add("-ok");
        ArrayList<String> expectedValues = new ArrayList<>(Arrays.asList("1.txt", "2.txt"));
        Assert.assertEquals(expectedValues, searchController.initialiseSearch(noSignWords, plusSignWords, minusSignWords));
    }
}
