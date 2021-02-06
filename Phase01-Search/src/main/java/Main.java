import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static InvertedIndex invertedIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader("C:/Users/Amkam/Desktop/Folder");
        invertedIndex = new InvertedIndex();
        for (File allFile : fileReader.getAllFiles()) {
            try {
                separate(allFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.println("What is your search word?");
            String searchWord = scanner.nextLine();
            if (invertedIndex.search(searchWord) != null) {
                System.out.println(invertedIndex.search(searchWord).toString());
            } else {
                System.out.println("Not Found");
            }
        }


    }

    public static void separate(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] word = reader.nextLine().split(" ");
            for (String s : word) {
                invertedIndex.addWord(s, file.getName());
            }
        }

    }
}
