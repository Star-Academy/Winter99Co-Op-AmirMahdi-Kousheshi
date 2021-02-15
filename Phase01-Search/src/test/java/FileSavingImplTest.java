import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSavingImplTest {
    FileSavingImpl fileSaving;

    @Before
    public void setup() {
        fileSaving = new FileSavingImpl("C:\\Users\\Amkam\\Desktop\\Folder3");
        fileSaving.addAllFiles();
    }

    @Test
    public void getAllFilesTest() {
        List<File> files = new ArrayList<>();
        files.add(new File("C:\\Users\\Amkam\\Desktop\\Folder3\\1.txt"));
        files.add(new File("C:\\Users\\Amkam\\Desktop\\Folder3\\2.txt"));
        Assert.assertEquals(files, fileSaving.getAllFiles());
    }

}
