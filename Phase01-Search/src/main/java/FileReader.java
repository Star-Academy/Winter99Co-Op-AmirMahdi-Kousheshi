import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileReader implements FileSaving {
    static String path = "C:/Users/Amkam/Desktop/Folder";
    static File folder = new File(path);
    public static List<File> allFiles = new ArrayList<>();

    public static void addAllFiles() {
        Collections.addAll(allFiles, folder.listFiles());
    }

    public static List<File> getAllFiles() {
        return allFiles;
    }
}
