import java.util.Scanner;

public class Main {
    private static InvertedIndex invertedIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        invertedIndex = new InvertedIndex();
        FileReader.addAllFiles(invertedIndex);

        while (true) {
            System.out.println("Enter your input:");
            String searchInput = scanner.nextLine();
            System.out.println(invertedIndex.search(searchInput));
        }
    }


}
