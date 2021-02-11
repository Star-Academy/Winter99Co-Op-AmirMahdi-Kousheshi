import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashInvertedIndexTest {
    @Test
    public void initialiseTest() {

    }

    @Test
    public void normalizeTest() {
        String string = "hel--lo";
        HashInvertedIndex hashInvertedIndex = new HashInvertedIndex();
        String expectedValue = "hello";
        Assert.assertEquals(expectedValue, hashInvertedIndex.normalize(string));
    }

    @Test
    public void searchTest() {
        FileReader.addAllFiles();
        String searchInput = "get help -disease";
        HashInvertedIndex hashInvertedIndex = new HashInvertedIndex();
        List<String> expectedValues = Arrays.asList("1.txt");
        Assert.assertEquals(expectedValues, hashInvertedIndex.search(searchInput));
    }


}
