import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    static FileSaving fileSaving;

    public FileReader(FileSaving fileSaving) {
        FileReader.fileSaving = fileSaving;
        this.addAllFiles();
    }

    private void addAllFiles() {
        fileSaving.addAllFiles();
    }

    public static List<File> getAllFiles() {
        return fileSaving.getAllFiles();
    }

}
