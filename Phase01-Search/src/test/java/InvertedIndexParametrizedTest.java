import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class InvertedIndexParametrizedTest {
    HashInvertedIndex hashInvertedIndex;
    String input;
    ArrayList<String> expected;

    public InvertedIndexParametrizedTest(String input, ArrayList<String> expected) {
        this.input = input;
        this.expected = expected;
    }

    @Before
    public void setup() {
        FileSavingImpl fileSaving = new FileSavingImpl("C://Users/Amkam/Desktop/Folder3");
        StringSeparatorImpl stringSeparator = new StringSeparatorImpl();
        hashInvertedIndex = new HashInvertedIndex(stringSeparator, fileSaving);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getValues() {
        ArrayList<String> ok = new ArrayList<>();
        ok.add("2.txt");
        Object[][] data = new Object[][]{{"get help +disease -ok", new ArrayList<String>(Arrays.asList("2.txt"))}, {"+disease", new ArrayList<String>(Arrays.asList("2.txt"))}, {"ok", new ArrayList<String>()}};
        return Arrays.asList(data);
    }

    @Test
    public void searchTest() {
        Assert.assertEquals(expected, hashInvertedIndex.search(input));
    }
}
