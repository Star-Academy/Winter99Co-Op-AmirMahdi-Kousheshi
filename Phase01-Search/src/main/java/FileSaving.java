import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface FileSaving {
    List<File> allFiles = new ArrayList<>();

    void addAllFiles();

    List<File> getAllFiles();
}
