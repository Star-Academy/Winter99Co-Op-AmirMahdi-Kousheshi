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

    public static void addAllFiles(HashInvertedIndex invertedIndex) {
        Collections.addAll(allFiles, folder.listFiles());
        for (File allFile : getAllFiles()) {
            try {
                separate(allFile, invertedIndex);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void separate(File file, HashInvertedIndex invertedIndex) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] word = reader.nextLine().split("\\W+");
            for (String s : word) {
                invertedIndex.addWord(s, file.getName());
            }
        }
    }

    public static List<File> getAllFiles() {
        return allFiles;
    }
}
