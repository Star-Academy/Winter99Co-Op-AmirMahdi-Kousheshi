import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FileReader {
    String path = "C:/Users/Amkam/Desktop/Folder";
    File folder = new File(path);
    public ArrayList<File> allFiles = new ArrayList<>();

    public FileReader() {
        Collections.addAll(allFiles, folder.listFiles());
    }

    public ArrayList<File> getAllFiles() {
        return allFiles;
    }
}
