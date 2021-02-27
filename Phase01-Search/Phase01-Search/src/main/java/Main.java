import java.io.File;
import java.util.Scanner;

public class Main {
    private static HashInvertedIndex invertedIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = checkAddress(scanner);

        invertedIndex = new HashInvertedIndex(new StringSeparatorImpl(), new FileSavingImpl(path));
        while (true) {
            System.out.println("Enter your input:");
            String searchInput = scanner.nextLine();
            System.out.println(invertedIndex.search(searchInput));
        }
    }

    public static String checkAddress(Scanner scanner) {
        String path;
        File file;
        while (true) {
            System.out.println("address of files:");
            path = scanner.nextLine();
            file = new File(path);
            if (!file.exists()) {
                System.out.println("Incorrect address");
            } else {
                return path;
            }
        }
    }
}
