import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        doManage();
    }

    private static void doManage() throws IOException {
        ManagerBooks managerBooks = new ManagerBooks();
        ConsoleInput consoleInput = new ConsoleInput();
        boolean exit;

        do {
            managerBooks.initCatalog();
            managerBooks.showMenu();
            exit = managerBooks.chooseMenu(consoleInput.getNumber());
        } while (!exit);
    }
}

