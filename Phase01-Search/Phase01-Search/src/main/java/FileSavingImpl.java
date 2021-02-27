import java.io.File;
import java.util.Collections;
import java.util.List;

public class FileSavingImpl implements FileSaving {
    String path;

    public FileSavingImpl(String path) {
        this.path = path;
    }

    @Override
    public void addAllFiles() {
        File folder = new File(this.path);
        Collections.addAll(allFiles, folder.listFiles());
    }

    @Override
    public List<File> getAllFiles() {
        return allFiles;
    }

}
