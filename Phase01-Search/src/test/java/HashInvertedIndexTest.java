import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashInvertedIndexTest {
    static HashInvertedIndex hashInvertedIndex;

    @BeforeClass
    public static void setup() {
        FileSavingImpl fileSaving = new FileSavingImpl("C://Users/Amkam/Desktop/Folder3");
        StringSeparatorImpl stringSeparator = new StringSeparatorImpl();
        hashInvertedIndex = new HashInvertedIndex(stringSeparator, fileSaving);
    }

    @Test
    public void searchFirstTest() {
        String searchInput = "get help +disease -ok";
        List<String> expectedValues = Arrays.asList("2.txt");
        Assert.assertEquals(expectedValues, hashInvertedIndex.search(searchInput));
    }

    @Test
    public void searchSecondTest() {
        String searchInput = "+disease";
        List<String> expectedValues = Arrays.asList("2.txt");
        Assert.assertEquals(expectedValues, hashInvertedIndex.search(searchInput));
    }

    @Test
    public void searchThirdTest() {
        String searchInput = "-ok";
        List<String> expectedValues = Arrays.asList("1.txt", "2.txt");
        Assert.assertEquals(expectedValues, hashInvertedIndex.search(searchInput));
    }
}
