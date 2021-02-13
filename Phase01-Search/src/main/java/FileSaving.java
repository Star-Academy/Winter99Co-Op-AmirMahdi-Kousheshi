import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface FileSaving {
    String path = "C:/Users/Amkam/Desktop/Folder";
    File folder = new File(path);
    List<File> allFiles = new ArrayList<>();

    void addAllFiles();
    List<File> getAllFiles();
}
