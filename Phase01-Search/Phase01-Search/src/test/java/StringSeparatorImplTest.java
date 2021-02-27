import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class StringSeparatorImplTest {
    StringSeparatorImpl stringSeparator;

    @Before
    public void setup() {
        stringSeparator = new StringSeparatorImpl();
    }

    @Test
    public void normalizeTes() {
        Assert.assertEquals("heloo", stringSeparator.normalize("hel--oo"));
    }

    @Test
    public void separateWordsBySignTest() {
        ArrayList<String> expectedValues = new ArrayList<>();
        expectedValues.add("disease");
        expectedValues.add("ok");
        String[] values = {"+disease", "+ok", "-noWay"};
        Assert.assertEquals(expectedValues, stringSeparator.separateWordsBySign(values, "+"));
    }
}
