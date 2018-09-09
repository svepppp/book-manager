import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ManagerBooks {
    private List<Book> catalog = new ArrayList<>();
    private List<String> menu;

    Output output = new ConsoleOutput();
  //  String mockInput = "Н.С.Лесков Левша";

    public Output getOutput() {
        return output;
    }

  /*  InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
    Input consoleInput = new ConsoleInput(mockInputStream);*/
  Input consoleInput = new ConsoleInput();

    public ManagerBooks() throws IOException {
        menu = new ArrayList<>();
        menu.add("Выберите действие:");
        menu.add("Добавить  книгу -- 1");
        menu.add("Посмотреть информацию о книге -- 2");
        menu.add("Удалить книгу--3");
        menu.add("Просмотреть весь каталог--4");
        menu.add("Завершить работу--5");
    }

    public ManagerBooks(List<Book> catalog) throws UnsupportedEncodingException {
        super();
        this.catalog = catalog;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void showMenu() {  // показать меню
        for (String string : menu) {
            output.setMessage(string);
        }
    }

    public void addNewBook() {
        Book newBook = consoleInput.getBook();
        catalog.add(newBook);
        output.showBookInfo(newBook, 0, 0);
    }

    public List<Book> initCatalog() {
        catalog.add(new Book("А.С.Пушкин", "Евгений Онегин"));
        catalog.add(new Book("Л.Н.Толстой", "Война и мир"));
        catalog.add(new Book("М.Горький", "Мать"));
        catalog.add(new Book("Е.И.Замятин", "Мы"));
        return catalog;
    }

    public void getInfoBook(int number) {   // получить информацию о книге
        String string;
        if (number - 1 <= catalog.size()) {
            Book book = catalog.get(number - 1);
            output.showBookInfo(book, number, 1);
        } else {
            output.showBookInfo(new Book(), number, 2);
        }
    }

    public void delBook(int number) {          //  удалить книгу
        if (number - 1 < catalog.size()) {
            Book book = catalog.get(number - 1);
            catalog.remove(number - 1);
            output.showBookInfo(book, number, 3);
        } else {
            output.showBookInfo(new Book(), number, 2);
        }
    }

    public boolean checkCatalog() {                       //  проверить  каталог
        if (catalog.size() == 0) {
            output.showBookInfo(new Book(), 0, 4);
            return false;
        } else return true;
    }

    public boolean chooseMenu(int itemMenu) throws IOException {   //  выбор пункта меню
        boolean exit = false;

        switch (itemMenu) {
            case 1:                                    //добавить книгу
                output.showBookInfo(new Book(), 0, 5);
                addNewBook();
                break;
            case 2:                                    // получить информацию о книге
                if (checkCatalog()) {
                    output.showBookInfo(new Book(), 0, 6);
                /*    mockInput = "1\n6";
                    InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
                    ConsoleInput consoleInput = new ConsoleInput(mockInputStream);*/
                    int numberBook = consoleInput.getNumber();
                    getInfoBook(numberBook);
                }
                break;
            case 3:                                      //  удалить книгу
                if (checkCatalog()) {
                    output.showBookInfo(new Book(), 0, 6);
                   /* mockInput = "2";
                    InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
                    ConsoleInput consoleInput = new ConsoleInput(mockInputStream);*/
                    int numberBook = consoleInput.getNumber();
                    delBook(numberBook);
                }
                break;
            case 4:                                       //  показать каталог
                if (checkCatalog()) {
                    showCatalog();
                }
                break;
            case 5:                                        //  завершить работу
                exit = true;
                break;
        }
        return exit;
    }

    private void showCatalog() {           //  показать каталог
        output.showBookInfo(new Book(), 0, 7);
        for (Book book : catalog) {
            output.showBookInfo(book, 0, 8);
        }
        try (PrintWriter out = new PrintWriter(new FileWriter("catalog.txt"))) {  // для тесстирования
            out.println("Список книг");
            for (Book book : catalog) {
                out.println(book.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
