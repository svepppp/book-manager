import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput  implements Input{
    private Scanner scanner;

    public ConsoleInput() {
        this(System.in);
    }

    public ConsoleInput(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public Book getBook() {
        String s = scanner.nextLine();
        String[] split = s.split(" ", 2);
        return new Book(split[0], split[1]);
    }

    public int getNumber() {
        return Integer.parseInt(scanner.nextLine());
    }
}
