import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FileReader {
    String path;
    File folder ;
    public ArrayList<File> allFiles = new ArrayList<>();

    public FileReader(String path) {
        folder=new File(path);
//        this.path = path;
        Collections.addAll(allFiles, folder.listFiles());
    }

    public ArrayList<File> getAllFiles() {
        return allFiles;
    }
}
