import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileReaderTest {

    @Test
    public void getAllFilesTest() {
        FileReader.addAllFiles();
        String path = "C:/Users/Amkam/Desktop/Folder";
        File file = new File(path);
        ArrayList<File> allFiles = new ArrayList<>();
        Collections.addAll(allFiles, file.listFiles());
        Assert.assertEquals(allFiles, FileReader.getAllFiles());
    }
}
